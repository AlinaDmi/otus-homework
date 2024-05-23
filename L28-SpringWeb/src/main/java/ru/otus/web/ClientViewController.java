package ru.otus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.db.ClientService;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientViewController {

    private final ClientService clientService;

    @Autowired
    public ClientViewController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showClientPage(Model model) {
        List<ClientDto> clients = clientService.getAll();
        model.addAttribute("clients", clients);
        model.addAttribute("newClient", new ClientDto());
        return "client-management";
    }

    @PostMapping("/add")
    public String addClient(ClientDto clientDto) {
        clientService.saveClient(clientDto);
        return "redirect:/clients";
    }
}

