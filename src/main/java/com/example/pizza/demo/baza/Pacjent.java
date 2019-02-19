package com.example.pizza.demo.baza;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Pacjent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String numertel;
    private LocalDate data;
    private String godzina;
    private double cena_wizyty;
    @ManyToOne
    @JoinColumn(name = "Lekarz_ID") // dodanie do tabeli kolumny z id pacjenta
    private Lekarz lekarz;

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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNumertel() {
        return numertel;
    }

    public void setNumertel(String numertel) {
        this.numertel = numertel;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }
    public double getCena_wizyty() {
        return cena_wizyty;
    }

    public void setCena_wizyty(double cena_wizyty) {
        this.cena_wizyty = cena_wizyty;
    }


    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }
}
