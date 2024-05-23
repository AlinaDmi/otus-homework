package ru.otus.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.db.Client;
import ru.otus.db.ClientRepository;
import ru.otus.web.ClientDto;
import ru.otus.web.ClientWebConverter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientWebConverter converter;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.converter = new ClientWebConverter();
    }

    public List<ClientDto> getAll() {
        Iterable<Client> all = clientRepository.findAll();
        List<Client> clients = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());

        return converter.toDtoList(clients);
    }

    public void saveClient(ClientDto clientDto) {
        Client newClient = converter.fromDto(clientDto);
        clientRepository.save(newClient);
    }
}

