package com.example.lr14.controllers;

import org.springframework.ui.Model;
import com.example.lr14.entities.SpisokTicket;
import com.example.lr14.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping
    public String showTicketsList(Model model) {
        SpisokTicket ticket = new SpisokTicket();
        model.addAttribute("tickets", ticketService.getAllTickets());
        model.addAttribute("ticket", ticket);
        return "tickets";
    }

    @PostMapping("/add")
    public String addTicket(@ModelAttribute("ticket") SpisokTicket spisokTicket) {
        ticketService.add(spisokTicket);
        return "redirect:/tickets";
    }

    @GetMapping("/show/{id}")
    public String showOneTicket(Model model, @PathVariable(value = "id") Integer id) {
        SpisokTicket spisokTicket = ticketService.getById(id);
        model.addAttribute("ticket", spisokTicket);
        return "ticket_check";
    }
    @GetMapping("/delete/{id}")
    public String deleteTickets(Model model, @PathVariable(value = "id") Integer id) {
        SpisokTicket spisokTicket = ticketService.getById(id);
        ticketService.delete(spisokTicket);
        return "redirect:/tickets";
    }

    @GetMapping("/add")
    public String showAddTicketForm(Model model) {
        SpisokTicket ticket = new SpisokTicket();
        model.addAttribute("tickets", ticketService.getAllTickets());
        model.addAttribute("ticket", ticket);
        return "add-edit";
    }
    @GetMapping("/filter")
    public String filterTickets(Model model,
                                @RequestParam(value = "passenger", required = false)String passenger,
                                @RequestParam(value = "route", required = false) String route,
                                @RequestParam(value = "price", required = false) Integer price) {
        SpisokTicket spisokTicket = new SpisokTicket();
        model.addAttribute("tickets", ticketService.getAllTickets(passenger, route, price));
        model.addAttribute("ticket", spisokTicket);
        model.addAttribute("passenger", passenger);
        model.addAttribute("route", route);
        model.addAttribute("price", price);
        return "tickets";
    }

    @GetMapping("/edit/{id}")
    public String showEditTicketForm(Model model, @PathVariable(value = "id") Integer id) {
        SpisokTicket spisokTicket = ticketService.getById(id);
        model.addAttribute("ticket", spisokTicket);
        return "add-edit";
    }

    @PostMapping("/edit/update")
    public String updateTicket(@ModelAttribute("ticket") SpisokTicket updatedOrganization) {
        SpisokTicket ticket = ticketService.getById(updatedOrganization.getId());
        ticketService.update(ticket, updatedOrganization);
        return "redirect:/tickets";
    }
}