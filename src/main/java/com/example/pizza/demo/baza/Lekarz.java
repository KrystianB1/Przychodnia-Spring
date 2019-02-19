package com.example.pizza.demo.baza;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lekarz {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Imie")
    private String imie;
    @Column(name = "Nazwisko")
    private String nazwisko;
    @Column
    private double stawka;
    @OneToMany(mappedBy = "lekarz")
    private List<Pacjent> pacjenci;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public double getStawka() {
        return stawka;
    }

    public void setStawka(double stawka) {
        this.stawka = stawka;
    }

    public List<Pacjent> getPacjenci() {
        return pacjenci;
    }

    public void setPacjenci(List<Pacjent> pacjenci) {
        this.pacjenci = pacjenci;
    }
}
