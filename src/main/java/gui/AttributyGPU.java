/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceGPU;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author wille
 */
public class AttributyGPU {
    
    Label labelGpuRychlost = new Label("Rychlost MHZ");
    Label labelGpuKapacita = new Label("Kapacita pameti");
    Label labelGpuPocetFanu = new Label("Pocet fanu");
    Label labelGpuHlasitost = new Label("Hlasitost");
    TextField tfRychlost;
    TextField tfKapacitaPameti;
    TextField tfPocetFanu;
    TextField tfHlasitost;
    
    KomponentaPocitaceGPU gpu;
    
    public AttributyGPU() {
        tfRychlost = new TextField();
        tfKapacitaPameti = new TextField();
        tfPocetFanu = new TextField();
        tfHlasitost = new TextField();
        setTextFormatters();
    }
    
    public AttributyGPU(KomponentaPocitaceGPU gpu, boolean setDisable) {
        this(gpu);
        disableTextFields(setDisable);
    }
    
    public AttributyGPU(KomponentaPocitaceGPU gpu) {
        tfRychlost = new TextField(String.valueOf(gpu.getRychlostMHZ()));
        tfKapacitaPameti = new TextField(String.valueOf(gpu.getKapacitaPametiMB()));
        tfPocetFanu = new TextField(String.valueOf(gpu.getPocetFanu()));
        tfHlasitost = new TextField(String.valueOf(gpu.getHlasitost()));
        setTextFormatters();
        
    }
    
    private void setTextFormatters() {
        tfRychlost.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfKapacitaPameti.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfPocetFanu.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfHlasitost.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        
        labelGpuRychlost.managedProperty().bind(labelGpuRychlost.visibleProperty());
        labelGpuKapacita.managedProperty().bind(labelGpuKapacita.visibleProperty());
        labelGpuPocetFanu.managedProperty().bind(labelGpuPocetFanu.visibleProperty());
        labelGpuHlasitost.managedProperty().bind(labelGpuHlasitost.visibleProperty());
        tfRychlost.managedProperty().bind(tfRychlost.visibleProperty());
        tfKapacitaPameti.managedProperty().bind(tfKapacitaPameti.visibleProperty());
        tfPocetFanu.managedProperty().bind(tfPocetFanu.visibleProperty());
        tfHlasitost.managedProperty().bind(tfHlasitost.visibleProperty());
        
    }
    
    public Label[] addLabels() {
        return new Label[]{labelGpuRychlost, labelGpuKapacita, labelGpuPocetFanu, labelGpuHlasitost};
    }
    
    public Node[] addTextFields() {
        return new Node[]{tfRychlost, tfKapacitaPameti, tfPocetFanu, tfHlasitost};
    }
    
    public int getTfRychlost() {
        return Integer.parseInt(tfRychlost.getText());
    }
    
    public int getTfKapacitaPameti() {
        return Integer.parseInt(tfKapacitaPameti.getText());
    }
    
    public int getTfPocetFanu() {
        return Integer.parseInt(tfPocetFanu.getText());
    }
    
    public int getTfHlasitost() {
        return Integer.parseInt(tfHlasitost.getText());
    }
    
    public boolean JeValidni() {
        return !(tfRychlost.getText().isBlank() || tfKapacitaPameti.getText().isBlank() || tfPocetFanu.getText().isBlank() || tfHlasitost.getText().isBlank());
    }
    
    public void setVisible(boolean isVisible) {
        labelGpuRychlost.setVisible(isVisible);
        labelGpuKapacita.setVisible(isVisible);
        labelGpuPocetFanu.setVisible(isVisible);
        labelGpuHlasitost.setVisible(isVisible);
        tfRychlost.setVisible(isVisible);
        tfKapacitaPameti.setVisible(isVisible);
        tfPocetFanu.setVisible(isVisible);
        tfHlasitost.setVisible(isVisible);
        
    }
    
    public void disableTextFields(boolean setDisable) {
        tfRychlost.setDisable(setDisable);
        tfKapacitaPameti.setDisable(setDisable);
        tfPocetFanu.setDisable(setDisable);
        tfHlasitost.setDisable(setDisable);
    }
    
//    public void setValuesFromCurrent(KomponentaPocitaceBase gpu) {
//        tfRychlost.setText(String.valueOf(((KomponentaPocitaceGPU) gpu).getRychlostMHZ()));
//        tfKapacitaPameti.setText(String.valueOf(((KomponentaPocitaceGPU) gpu).getKapacitaPametiMB()));
//        tfPocetFanu.setText(String.valueOf(((KomponentaPocitaceGPU) gpu).getPocetFanu()));
//        tfHlasitost.setText(String.valueOf(((KomponentaPocitaceGPU) gpu).getHlasitost()));
//    }
}
