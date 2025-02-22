package com.example.seat_management_api.repository;

import com.example.seat_management_api.model.Poltrona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PoltronaRepository extends JpaRepository<Poltrona, Long> {
    List<Poltrona> findByDisponivelTrue();  // Método para pegar as disponíveis
    List<Poltrona> findByDisponivelFalse(); // Método para pegar as ocupadas
}