/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceRAM;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author wille
 */
public class AttributyRAM {

    Label labelRamRychlost = new Label("Rychlost MHZ");
    Label labelRamKapacita = new Label("Kapacita pameti");
    Label labelRamTyp = new Label("Typ Pameti");
    Label labelDualChannel = new Label("Podpora dual channel");
    TextField tfRychlost;
    TextField tfKapacitaPameti;
    ComboBox<KomponentaPocitaceRAM.TypPameti> comboBoxTypPameti;
    CheckBox checkBoxDualChannel;
    Label typPameti;

    KomponentaPocitaceRAM ram;

    public AttributyRAM() {
        tfRychlost = new TextField();
        tfKapacitaPameti = new TextField();
        typPameti = new Label();
        comboBoxTypPameti = new ComboBox();
        comboBoxTypPameti.getItems().addAll(KomponentaPocitaceRAM.TypPameti.values());
        comboBoxTypPameti.getSelectionModel().selectFirst();

        checkBoxDualChannel = new CheckBox("Podporuje dual-channel?");
        setTextFormatters();
    }

    public AttributyRAM(KomponentaPocitaceRAM ram, boolean setDisable) {
        this(ram);
        disableTextFields(setDisable);
    }

    public AttributyRAM(KomponentaPocitaceRAM ram) {
        tfRychlost = new TextField(String.valueOf(ram.getRychlostMHZ()));
        tfKapacitaPameti = new TextField(String.valueOf(ram.getKapacitaPametiMB()));
        comboBoxTypPameti = new ComboBox();
        comboBoxTypPameti.getItems().addAll(KomponentaPocitaceRAM.TypPameti.values());
        comboBoxTypPameti.getSelectionModel().select(ram.getTypPameti());
        typPameti = new Label(ram.getTypPameti().toString());

        checkBoxDualChannel = new CheckBox("Podporuje dual-channel?");
        checkBoxDualChannel.setSelected(ram.isDualChannel());
        setTextFormatters();

    }

    private void setTextFormatters() {
        tfRychlost.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        tfKapacitaPameti.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));

        labelRamRychlost.managedProperty().bind(labelRamRychlost.visibleProperty());
        labelRamKapacita.managedProperty().bind(labelRamKapacita.visibleProperty());
        labelRamTyp.managedProperty().bind(labelRamTyp.visibleProperty());
        labelDualChannel.managedProperty().bind(labelDualChannel.visibleProperty());
        typPameti.managedProperty().bind(typPameti.visibleProperty());
        tfRychlost.managedProperty().bind(tfRychlost.visibleProperty());
        tfKapacitaPameti.managedProperty().bind(tfKapacitaPameti.visibleProperty());
        comboBoxTypPameti.managedProperty().bind(comboBoxTypPameti.visibleProperty());
        checkBoxDualChannel.managedProperty().bind(checkBoxDualChannel.visibleProperty());

    }

    public int getTfRychlost() {
        return Integer.parseInt(tfRychlost.getText());
    }

    public int getTfKapacitaPameti() {
        return Integer.parseInt(tfKapacitaPameti.getText());
    }

    public KomponentaPocitaceRAM.TypPameti getCBTypPameti() {
        return comboBoxTypPameti.getValue();
    }

    public boolean getCBPodporaDualChannel() {
        return checkBoxDualChannel.isSelected();
    }

    public Label[] addLabels() {
        return new Label[]{labelRamRychlost, labelRamKapacita, labelRamTyp, labelDualChannel};
    }

    public Node[] addTextFields() {
        return new Node[]{tfRychlost, tfKapacitaPameti, comboBoxTypPameti, checkBoxDualChannel};
    }

    public boolean JeValidni() {
        return !(tfRychlost.getText().isBlank() || tfKapacitaPameti.getText().isBlank() || tfRychlost.getText().isBlank() || checkBoxDualChannel == null || comboBoxTypPameti.getValue() == null);
    }

    public void setVisible(boolean isVisible) {
        labelRamRychlost.setVisible(isVisible);
        labelRamKapacita.setVisible(isVisible);
        labelRamTyp.setVisible(isVisible);
        labelDualChannel.setVisible(isVisible);
        tfRychlost.setVisible(isVisible);
        tfKapacitaPameti.setVisible(isVisible);
        comboBoxTypPameti.setVisible(isVisible);
        checkBoxDualChannel.setVisible(isVisible);
        typPameti.setVisible(isVisible);
    }

    public void disableTextFields(boolean setDisable) {
        tfRychlost.setDisable(setDisable);
        tfKapacitaPameti.setDisable(setDisable);
        comboBoxTypPameti.setDisable(setDisable);
        checkBoxDualChannel.setDisable(setDisable);
    }

//    public void setValuesFromCurrent(KomponentaPocitaceBase ram) {
//        tfRychlost.setText(String.valueOf(((KomponentaPocitaceRAM) ram).getRychlostMHZ()));
//        tfKapacitaPameti.setText(String.valueOf(((KomponentaPocitaceRAM) ram).getKapacitaPametiMB()));
//        if (((KomponentaPocitaceRAM) ram).isDualChannel() == true) {
//            checkBoxDualChannel.setSelected(true);
//        } else {
//            checkBoxDualChannel.setSelected(false);
//        }
//        typPameti.setText(String.valueOf(((KomponentaPocitaceRAM) ram).getTypPameti()));
//    }
}
