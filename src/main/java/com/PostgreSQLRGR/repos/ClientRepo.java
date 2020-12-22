package com.PostgreSQLRGR.repos;

import com.PostgreSQLRGR.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);
    List<Client> findByCity(String city);
    List<Client> findByAddress(String address);

}
