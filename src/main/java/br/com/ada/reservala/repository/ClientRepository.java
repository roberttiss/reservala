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

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Client createClient(Client client){
        String createSQL = "insert into client(id,name, age) values (?,?, ?)";
        jdbcTemplate.update(
                createSQL,
                client.getIdClient(),
                client.getName(),
                client.getAge()
        );
        return client;
    }

    public List<Client> readClient(){
        RowMapper<Client> rowMapper = ((rs, rowNum) -> new Client(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age")
        ));
        String readSQL = "select * from client";
        return jdbcTemplate.query(readSQL,rowMapper);
    }

    public Client readClientById(int idClient){
    RowMapper<Client> rowMapper = ((rs, rowNum) -> new Client(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("age")
    ));

    String sql = "select * from client where id = ?";

    return jdbcTemplate.queryForObject(sql, rowMapper, idClient);
}
    public Client udpateClient(Client client, Integer idClient){
        String updateSQL = "update client set name = ?, age = ? where id = ? ";
        jdbcTemplate.update(
                updateSQL,
                client.getName(),
                client.getAge(),
                idClient
        );
        return readClientById(idClient);
    }

    public void deleteClient(Integer idClient){
        String deleteSQL = "delete from client where id = ?";
        jdbcTemplate.update(deleteSQL,idClient);
    }

    public Optional<Integer> getLastInsertedId(){
        try{
            Integer lastId = jdbcTemplate.queryForObject("select max(id) from client", Integer.class);
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
