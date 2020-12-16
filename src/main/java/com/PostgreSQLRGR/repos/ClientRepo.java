package com.PostgreSQLRGR.repos;

import com.PostgreSQLRGR.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findById(Integer id);
    List<Client> findByName(String name);
    List<Client> findByCity(String city);
}
