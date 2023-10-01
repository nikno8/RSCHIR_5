package com.mirea.rschirpractice5.repository;

import com.mirea.rschirpractice5.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
