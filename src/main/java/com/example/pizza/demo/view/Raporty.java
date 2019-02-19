package com.example.pizza.demo.view;

import com.example.pizza.demo.baza.Pacjent;
import com.example.pizza.demo.baza.RepoPacjent;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
@SpringComponent
public class Raporty {


    RepoPacjent repoPacjent;

    static  List<Pacjent> pacjentList;
    public Double dochody_wszystkie(){
        pacjentList =repoPacjent.findAll();
        return pacjentList.stream().map(Pacjent::getCena_wizyty).mapToDouble(i->i).sum();
    }

}
