package ru.otus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.db.Client;
import ru.otus.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientViewController {

    private final ClientService clientService;
    private final ClientWebConverter clientWebConverter;

    @Autowired
    public ClientViewController(ClientService clientService, ClientWebConverter clientWebConverter) {
        this.clientService = clientService;
        this.clientWebConverter = clientWebConverter;
    }

    @GetMapping
    public String showClientPage(Model model) {
        List<Client> all = clientService.getAll();
        List<ClientDto> clientDtos = clientWebConverter.toDtoList(all);
        model.addAttribute("clients", clientDtos);
        model.addAttribute("newClient", new ClientDto());
        return "client-management";
    }

    @PostMapping("/add")
    public String addClient(ClientDto clientDto) {
        Client client = clientWebConverter.fromDto(clientDto);
        clientService.saveClient(client);
        return "redirect:/clients";
    }
}

