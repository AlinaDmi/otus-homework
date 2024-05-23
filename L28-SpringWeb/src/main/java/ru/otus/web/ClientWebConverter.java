package ru.otus.web;

import ru.otus.db.Address;
import ru.otus.db.Client;

import java.util.List;

public class ClientWebConverter {

    public ClientDto toDto(Client client){
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setAddress(client.getAddress() != null ? client.getAddress().getStreet() : null);
        return dto;
    }

    public Client fromDto(ClientDto dto){
        Address address = new Address(dto.getId(), dto.getAddress());

        return new Client(dto.getName(), address);
    }

    public List<ClientDto> toDtoList(List<Client> models){
        return models.stream().map(this::toDto).toList();
    }
}
