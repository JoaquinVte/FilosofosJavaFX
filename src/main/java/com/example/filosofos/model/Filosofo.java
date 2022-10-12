package com.example.filosofos.model;

import com.example.filosofos.view.RegionColor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Filosofo {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final int tiempoDescanso = 3000;
    private final int tiempoComiendo = 1000;
    private final int tiempoEsperaAntesComer=100;
    private Label labelFilosofo;
    private Tenedor tenedor1;
    private Tenedor tenedor2;
    private String id;

    public Filosofo(String id, Label labelFilosofo, Tenedor tenedor1, Tenedor tenedor2) {
        this.labelFilosofo = labelFilosofo;
        this.tenedor1 = tenedor1;
        this.tenedor2 = tenedor2;
        this.id = id;
    }

    public void comer() {

        Thread thread = new Thread(new ComerTask());
        thread.setDaemon(true);
        thread.start();

    }

    @Override
    public String toString() {
        return id;
    }

    private class ComerTask extends Task<List<RegionColor>> {

        public ComerTask() {
            valueProperty().addListener((observableValue, regionColors, newValues) -> {
                for(RegionColor newValue : newValues)
                    newValue.getRegion().setBackground(new Background(new BackgroundFill(newValue.getColor(), new CornerRadii(5.0), new Insets(-5.0))));
            });
        }

        @Override
        protected List<RegionColor> call() throws Exception {

            while (true) {
                do {
                    if(tenedor1.soltar(Filosofo.this))
                        updateValue(Arrays.asList(new RegionColor(tenedor1.getLabel(), Color.WHITE),new RegionColor(labelFilosofo, Color.WHITE)));
                    if(tenedor2.soltar(Filosofo.this))
                        updateValue(Arrays.asList(new RegionColor(tenedor2.getLabel(), Color.WHITE),new RegionColor(labelFilosofo, Color.WHITE)));

                    if (tenedor1.coger(Filosofo.this))
                        updateValue(Arrays.asList(new RegionColor(tenedor1.getLabel(), Color.RED),new RegionColor(labelFilosofo, Color.RED)));


                    if (tenedor2.coger(Filosofo.this)) {
                        updateValue(Arrays.asList(new RegionColor(tenedor2.getLabel(), Color.RED),new RegionColor(labelFilosofo, Color.RED)));
                    }

                } while (tenedor1.getFilosofo() != Filosofo.this || tenedor2.getFilosofo() != Filosofo.this);

                Thread.sleep(tiempoEsperaAntesComer);

                LOGGER.log(Level.INFO, "Come el filosofo " + Filosofo.this);
                updateValue(Arrays.asList(new RegionColor(labelFilosofo, Color.GREEN),
                        new RegionColor(tenedor1.getLabel(), Color.GREEN),
                        new RegionColor(tenedor2.getLabel(), Color.GREEN)));

                Thread.sleep(tiempoComiendo);

                tenedor1.soltar(Filosofo.this);
                tenedor2.soltar(Filosofo.this);

                updateValue(Arrays.asList(new RegionColor(labelFilosofo, Color.WHITE),
                        new RegionColor(tenedor1.getLabel(), Color.WHITE),
                        new RegionColor(tenedor2.getLabel(), Color.WHITE)));

                Thread.sleep(tiempoDescanso);
            }
        }
    }
}
