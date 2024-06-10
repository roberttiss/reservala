package br.com.ada.reservala.domain;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
public class Client {
    private int idClient;
    private String name;
    private int age;
}