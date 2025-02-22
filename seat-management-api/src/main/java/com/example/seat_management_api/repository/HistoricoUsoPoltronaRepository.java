package com.example.seat_management_api.repository;

import com.example.seat_management_api.model.HistoricoUsoPoltrona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoUsoPoltronaRepository extends JpaRepository<HistoricoUsoPoltrona, Long> {
}
