package ru.otus.converter;

import crm.model.Address;
import crm.model.Client;
import crm.model.Phone;
import ru.otus.model.ClientDto;

import java.util.List;

public class ClientWebConverter {

    public ClientDto toDto(Client client){
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setPhone(!client.getPhones().isEmpty() ? client.getPhones().get(0).getNumber() : null);
        dto.setAddress(client.getAddress() != null ? client.getAddress().getStreet() : null);
        return dto;
    }

    public Client fromDto(ClientDto dto){
        Address address = new Address();
        address.setStreet(dto.getAddress());
        Phone phone = new Phone();
        phone.setNumber(dto.getPhone());

        Client newClient = new Client();
        newClient.setName(dto.getName());
        newClient.setId(dto.getId());
        newClient.setAddress(address);
        newClient.setPhones(List.of(phone));

        phone.setClient(newClient);
        address.setClient(newClient);

        return newClient;
    }

    public List<ClientDto> toDtoList(List<Client> models){
        return models.stream().map(this::toDto).toList();
    }
}
