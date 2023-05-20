/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceCPU;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author wille
 */
public class AttributyCPU {

    Label labelCpuRychlost = new Label("Rychlost MHZ");
    Label labelCpuPocetJader = new Label("Pocet jader");
    Label labelCpuPocetVlaken = new Label("Pocet vlaken");
    TextField tfRychlost;
    TextField tfPocetJader;
    TextField tfPocetVlaken;

    KomponentaPocitaceCPU cpu;

    public AttributyCPU() {
        tfRychlost = new TextField();
        tfPocetJader = new TextField();
        tfPocetVlaken = new TextField();
        setTextFormatters();
    }

    public AttributyCPU(KomponentaPocitaceCPU cpu, boolean setDisable) {
        tfRychlost = new TextField(String.valueOf(cpu.getRychlostMHZ()));
        tfPocetJader = new TextField(String.valueOf(cpu.getPocetJader()));
        tfPocetVlaken = new TextField(String.valueOf(cpu.getPocetVlaken()));
        setTextFormatters();
        disableTextFields(setDisable);
    }

    public AttributyCPU(KomponentaPocitaceCPU cpu) {
        tfRychlost = new TextField(String.valueOf(cpu.getRychlostMHZ()));
        tfPocetJader = new TextField(String.valueOf(cpu.getPocetJader()));
        tfPocetVlaken = new TextField(String.valueOf(cpu.getPocetVlaken()));
        setTextFormatters();

    }

    private void setTextFormatters() {
        tfRychlost.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfPocetJader.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfPocetVlaken.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfRychlost.managedProperty().bind(tfRychlost.visibleProperty());
        tfPocetJader.managedProperty().bind(tfPocetJader.visibleProperty());
        tfPocetVlaken.managedProperty().bind(tfPocetVlaken.visibleProperty());
        labelCpuRychlost.managedProperty().bind(labelCpuRychlost.visibleProperty());
        labelCpuPocetJader.managedProperty().bind(labelCpuPocetJader.visibleProperty());
        labelCpuPocetVlaken.managedProperty().bind(labelCpuPocetVlaken.visibleProperty());
    }

    public Label[] addLabels() {
        return new Label[]{labelCpuRychlost, labelCpuPocetJader, labelCpuPocetVlaken};
    }

    public Node[] addTextFields() {
        return new Node[]{tfRychlost, tfPocetJader, tfPocetVlaken};
    }

    public int getTfRychlost() {
        return Integer.parseInt(tfRychlost.getText());
    }

    public int getTfPocetJader() {
        return Integer.parseInt(tfPocetJader.getText());
    }

    public int getTfPocetVlaken() {
        return Integer.parseInt(tfPocetVlaken.getText());
    }

    public boolean jeValidni() {
        return !(tfPocetJader.getText().isBlank() || tfPocetVlaken.getText().isBlank() || tfRychlost.getText().isBlank());
    }

    public void setVisible(boolean isVisible) {

        labelCpuRychlost.setVisible(isVisible);
        labelCpuPocetJader.setVisible(isVisible);
        labelCpuPocetVlaken.setVisible(isVisible);
        tfRychlost.setVisible(isVisible);
        tfPocetJader.setVisible(isVisible);
        tfPocetVlaken.setVisible(isVisible);

    }

    public void disableTextFields(boolean setDisable) {
        tfRychlost.setDisable(setDisable);
        tfPocetJader.setDisable(setDisable);
        tfPocetVlaken.setDisable(setDisable);
    }

//    public void setValuesFromCurrent(KomponentaPocitaceCPU cpu) {
//        tfRychlost.setText(String.valueOf(((KomponentaPocitaceCPU) cpu).getRychlostMHZ()));
//        tfPocetJader.setText(String.valueOf(((KomponentaPocitaceCPU) cpu).getPocetJader()));
//        tfPocetVlaken.setText(String.valueOf(((KomponentaPocitaceCPU) cpu).getPocetVlaken()));
//    }
}
