package com.example.lr14.repositories;

import com.example.lr14.entities.SpisokTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<SpisokTicket, Integer> {

}
