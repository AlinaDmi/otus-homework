package ru.otus.model;

import lombok.Data;

@Data
public class ClientDto {
    String name;
    Long id;
    String address;
    String phone;
}
