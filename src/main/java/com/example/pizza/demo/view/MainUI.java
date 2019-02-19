package com.example.pizza.demo.view;



import com.example.pizza.demo.baza.Lekarz;
import com.example.pizza.demo.baza.Pacjent;
import com.example.pizza.demo.baza.RepoLekarz;
import com.example.pizza.demo.baza.RepoPacjent;
import com.vaadin.server.VaadinRequest;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;


@SpringUI
public class MainUI extends UI {

    @Autowired
    RepoLekarz repoLekarz;
    @Autowired
    RepoPacjent repoPacjent;


    static Lekarz lekarz_umawianie_imie =new Lekarz();
    static LocalDate date;
    static String godzina;
    static Grid<Pacjent> zamowieniaGrid = new Grid<>("Zapisani Pacjenci");
    static Grid<Pacjent> RaportGrid = new Grid<>("Tabela raportu");
    static List<Pacjent> pacjents ;
    List<Lekarz> lekarz;

    Raporty Raporty =new Raporty();


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalLayout horizontal = new HorizontalLayout();
        HorizontalLayout horizontal2 = new HorizontalLayout();
        TabSheet sample = new TabSheet();
        sample.setHeight(100.0f, Unit.PERCENTAGE);
        sample.addStyleName(ValoTheme.TABSHEET_FRAMED);
        sample.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        sample.addTab(Wizyta(horizontal), "Wizyty");  //zakładki
        sample.addTab(Raport(horizontal2), "Raport ");
        setContent(sample);

    }

    private HorizontalLayout Wizyta(HorizontalLayout horizontalLayout){ // zakładka Wizyty
        final Label label = new Label("Przychodnia online – umawianie wizyty ");
        label.setStyleName("h1");

        label.setWidth(100.0f, Unit.PERCENTAGE);
        VerticalLayout verticalLayout = new VerticalLayout();
        FormLayout layout = new FormLayout();
        layout.setSpacing(false);
        layout.setMargin(false);
        layout.setCaption("Rejestracja");
        TextField imie = new TextField("Imie");
        TextField nazwisko = new TextField("Nazwisko");
        TextField pesel = new TextField("Pesel");
        TextField numertelefonu = new TextField("Numer Telefonu");
        lekarz =repoLekarz.findAll();
        DateField dateField =new DateField("Wybierz Date");
        dateField.addValueChangeListener((e)->{
             date =e.getValue();
        });
        List<Lekarz> lekarz =repoLekarz.findAll();
        NativeSelect nativeSelect =new NativeSelect("Wybierz lekarza");
        nativeSelect.setItems(lekarz.stream().map(Lekarz::getImie).collect(Collectors.toList()));

        nativeSelect.addValueChangeListener((e)->
                {
                    Object o =e.getValue();
                    lekarz_umawianie_imie= lekarz.stream().filter(line->line.getImie().equals(o.toString())).findAny().get();
                    pacjents =repoPacjent.findAllByid(lekarz_umawianie_imie.getId());
                    zamowieniaGrid.setItems();
                    zamowieniaGrid.setItems(pacjents);
                }
        );
        Button button =new Button("Zapisz !");

        button.addClickListener((e)->
        {
            Pacjent pacjent =new Pacjent();
            pacjent.setImie(imie.getValue());
            pacjent.setNazwisko(nazwisko.getValue());
            pacjent.setNumertel(numertelefonu.getValue());
            pacjent.setPesel(pesel.getValue());
            pacjent.setData(date);
            pacjent.setGodzina(godzina);
            pacjent.setCena_wizyty(lekarz_umawianie_imie.getStawka());
            pacjent.setLekarz(lekarz_umawianie_imie);
            repoPacjent.save(pacjent);
            pacjents.clear();
            pacjents =repoPacjent.findAllByid(lekarz_umawianie_imie.getId());
            zamowieniaGrid.setItems();
            zamowieniaGrid.setItems(pacjents);

        });
        List<Integer> godziny = Arrays.asList(8,9,10,11,12,13,14,15,16);
        NativeSelect nativeSelect_godzina =new NativeSelect("Wybierz godzine wizyty");
        nativeSelect_godzina.setItems(godziny);
        nativeSelect_godzina.addValueChangeListener((e)->{
            Object o=e.getValue();
            godzina=o.toString();
        });

        layout.addComponents(imie,nazwisko,pesel,numertelefonu,nativeSelect);
        verticalLayout.addComponent(label);
        verticalLayout.addComponent(layout);
        verticalLayout.addComponent(dateField);
        verticalLayout.addComponent(nativeSelect_godzina);
        verticalLayout.addComponent(button);

        zamowieniaGrid.addColumn(Pacjent::getImie).setCaption("Imie");
        zamowieniaGrid.addColumn(Pacjent::getNazwisko).setCaption("Nazwisko");
        zamowieniaGrid.addColumn(Pacjent::getData).setCaption("Data");
        zamowieniaGrid.addColumn(Pacjent::getGodzina).setCaption("Godzina");
        horizontalLayout.addComponents(verticalLayout,zamowieniaGrid);
        return horizontalLayout;
    }

    private HorizontalLayout Raport(HorizontalLayout horizontalLayout){

        final Label label = new Label("Raporty ");
        label.setStyleName("h1");
        label.setWidth(100.0f, Unit.PERCENTAGE);
        horizontalLayout.addComponents(label);
       Button button =new Button();
       button.addClickListener((e)->{
           System.out.println(Raporty.dochody_wszystkie());
       });

       horizontalLayout.addComponent(button);
        setContent(horizontalLayout);
        return  horizontalLayout;
    }



}
