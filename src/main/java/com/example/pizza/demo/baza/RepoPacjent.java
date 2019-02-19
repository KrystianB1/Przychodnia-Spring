package com.example.pizza.demo.baza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoPacjent extends JpaRepository<Pacjent,Integer> {

    @Query(value = "SELECT e from Pacjent e where  lekarz_id=?1")
    List<Pacjent> findAllByid(int id);
}
