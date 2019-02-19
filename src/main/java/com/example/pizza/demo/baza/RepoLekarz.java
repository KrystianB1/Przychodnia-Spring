package com.example.pizza.demo.baza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepoLekarz extends JpaRepository<Lekarz ,Integer> {



}
