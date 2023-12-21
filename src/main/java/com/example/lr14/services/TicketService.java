package com.example.lr14.services;

import com.example.lr14.entities.SpisokTicket;
import com.example.lr14.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository repository;

    @Autowired
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }
    public SpisokTicket getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public List<SpisokTicket> getAllTickets() {
        return repository.findAll();
    }
    public List<SpisokTicket> getAllTickets(String passenger, String route, Integer price) {
        return repository.findAll().stream()
                .filter(o -> passenger.isBlank()|| o.getPassenger().contains(passenger))
                .filter(o -> route.isBlank()|| o.getRoute().contains(route))
                .filter(o -> price==null || isTimeInRange(o.getPrice(), price))
                .collect(Collectors.toList());
    }
    private boolean isTimeInRange(Integer price, Integer maxPrice) {
        if (price == null) {
            return false;  // Если цена не указана, не фильтруем по цене
        }

        if (maxPrice == null) {
            return true;  // Если максимальная цена не указана, пропускаем фильтрацию по цене
        }

        return price >= maxPrice;
    }
    public void add(SpisokTicket spisokTicket) {
        repository.save(spisokTicket);
    }
    public void delete(SpisokTicket spisokTicket) {
        repository.delete(spisokTicket);
    }
    public void update(SpisokTicket exist, SpisokTicket updated) {
        if (!updated.getPassenger().isBlank()) exist.setPassenger(updated.getPassenger());
        if(!updated.getRoute().isBlank()) exist.setRoute(updated.getRoute());
//        if(!updated.getPrice().isBlank()) exist.setPrice(updated.getPrice());
        if (updated.getPrice() != null) {
            exist.setPrice(updated.getPrice());
        }
        repository.save(exist);
    }
}
