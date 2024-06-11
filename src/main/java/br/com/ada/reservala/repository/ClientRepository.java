package br.com.ada.reservala.repository;

import br.com.ada.reservala.domain.Client;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String createSQL = "insert into client(id,name, age) values (?,?, ?)";
    private final String readSQL = "select * from client";
    private final String updateSQL = "update client set name = ?, age = ? where id = ? ";
    private final String deleteSQL = "delete from client where id = ?";

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Client createClient(Client client){
        int id;
        if (getLastInsertedId().isEmpty()){
            id = 1;
        } else {
            id = getLastInsertedId().get() + 1;
        }
        jdbcTemplate.update(
                createSQL,
                id,
                client.getName(),
                client.getAge()
        );
        client.setIdClient(id);
        return client;
    }

    public List<Client> readClient(){
        RowMapper<Client> rowMapper = ((rs, rowNum) -> new Client(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age")
        ));
        return jdbcTemplate.query(readSQL,rowMapper);
    }

    public Client udpateClient(Client client, Integer idClient){
        jdbcTemplate.update(
                updateSQL,
                client.getName(),
                client.getAge(),
                idClient
        );
        return client;
    }

    public void deleteClient(Integer idClient){
        jdbcTemplate.update(deleteSQL,idClient);
    }

    public Optional<Integer> getLastInsertedId(){
        try{
            Integer lastId = jdbcTemplate.queryForObject("select max(id) from reservation", Integer.class);
            return Optional.ofNullable(lastId);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public Boolean existsClient(Integer idClient){
        Integer count =  jdbcTemplate.queryForObject("select count(*) from client where id = ?", Integer.class,idClient);
        return count != null && count > 0;
    }
}
