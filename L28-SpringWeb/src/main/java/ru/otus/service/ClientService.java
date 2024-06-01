package ru.otus.service;

import ru.otus.db.Client;

import java.util.List;

public interface ClientService {

    void saveClient(Client client);

    List<Client> getAll();
}