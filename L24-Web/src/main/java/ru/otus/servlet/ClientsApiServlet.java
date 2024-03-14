package ru.otus.servlet;

import com.google.gson.Gson;
import crm.model.Client;
import crm.service.DBServiceClient;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.converter.ClientWebConverter;
import ru.otus.model.ClientDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@SuppressWarnings({"squid:S1948"})
public class ClientsApiServlet extends HttpServlet {
    private final DBServiceClient dbServiceClient;

    private final ClientWebConverter clientWebConverter;
    private final Gson gson;

    public ClientsApiServlet(DBServiceClient dbServiceClient, Gson gson) {
        this.dbServiceClient = dbServiceClient;
        this.clientWebConverter = new ClientWebConverter();
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Получить список всех клиентов
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        List<Client> clients = dbServiceClient.findAll();
        List<ClientDto> dtoList = clientWebConverter.toDtoList(clients);

        out.print(gson.toJson(dtoList));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Добавление нового клиента
        BufferedReader reader = request.getReader();
        ClientDto clientDto = gson.fromJson(reader, ClientDto.class);

        // Преобразование DTO в сущность Client
        Client newClient = clientWebConverter.fromDto(clientDto);

        // Сохранение клиента
        dbServiceClient.saveClient(newClient);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}

