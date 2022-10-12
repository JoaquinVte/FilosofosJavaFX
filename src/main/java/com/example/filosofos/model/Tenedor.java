package com.example.filosofos.model;

import com.example.filosofos.model.Filosofo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Tenedor {
    private Label label;
    private Filosofo filosofo;

    public Tenedor(Label label) {
        this.label = label;
    }

    public synchronized boolean coger(Filosofo filosofo){
        if(this.filosofo!=null)
            return false;

        this.filosofo = filosofo;
        return true;
    }
    public synchronized boolean soltar(Filosofo filosofo){

        if(this.filosofo!=filosofo) return false;

        this.filosofo = null;
        return true;
    }

    public Filosofo getFilosofo() {
        return filosofo;
    }

    public Label getLabel(){
        return label;
    }
}
