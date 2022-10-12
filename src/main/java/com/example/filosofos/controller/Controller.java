package com.example.filosofos.controller;

import com.example.filosofos.model.Filosofo;
import com.example.filosofos.model.Tenedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private Label f1;
    @FXML
    private Label f2;
    @FXML
    private Label f3;
    @FXML
    private Label f4;
    @FXML
    private Label f5;
    @FXML
    private Label t1;
    @FXML
    private Label t2;
    @FXML
    private Label t3;
    @FXML
    private Label t4;
    @FXML
    private Label t5;
    @FXML
    private Button startButton;

    private List<Filosofo> filosofos;

    @FXML
    public void initialize() {

        Tenedor tenedor1 = new Tenedor(t1);
        Tenedor tenedor2 = new Tenedor(t2);
        Tenedor tenedor3 = new Tenedor(t3);
        Tenedor tenedor4 = new Tenedor(t4);
        Tenedor tenedor5 = new Tenedor(t5);

        filosofos.add(new Filosofo("1",f1, tenedor1, tenedor2));
        filosofos.add(new Filosofo("2",f2, tenedor2, tenedor3));
        filosofos.add(new Filosofo("3",f3, tenedor3, tenedor4));
        filosofos.add(new Filosofo("4",f4, tenedor4, tenedor5));
        filosofos.add(new Filosofo("5",f5, tenedor5, tenedor1));


    }

    public Controller() {
        filosofos = new ArrayList<>();
    }

    @FXML
    public void startButtonClic(){
        for (Filosofo filosofo : filosofos) {
            filosofo.comer();
        }
    }

}