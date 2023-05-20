/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceBase;
import data.KomponentaPocitaceRAM;
import data.KomponentaPocitaceRAM.TypPameti;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author wille
 */
public class RAMEditDialog extends GenericEditDialog {

    TextField tfRychlost;
    TextField tfKapacitaPameti;
    ComboBox<TypPameti> comboBoxTypPameti;
    CheckBox checkBoxDualChannel;

    AttributyRAM attributy;

    public RAMEditDialog(KomponentaPocitaceRAM ram, boolean jsouKomponentyVypnuty) {
        super(ram, jsouKomponentyVypnuty ? "Zobraz RAM" : "Edituj RAM", jsouKomponentyVypnuty);
        tfRychlost = attributy.tfRychlost;
        tfKapacitaPameti = attributy.tfKapacitaPameti;
        comboBoxTypPameti = attributy.comboBoxTypPameti;
        checkBoxDualChannel = attributy.checkBoxDualChannel;
    }

    public RAMEditDialog(KomponentaPocitaceRAM ram) {
        this(ram, false);
    }

    @Override
    protected boolean JeValidni() {
        return !(tfRychlost.getText().isBlank() || tfKapacitaPameti.getText().isBlank() || tfRychlost.getText().isBlank() || checkBoxDualChannel == null || comboBoxTypPameti.getValue() == null && super.JeValidni());

    }

    public Consumer<KomponentaPocitaceBase> generateConsumer() {
        return (t) -> {
            t.setNazevKomponenty(tfNazev.getText());
            t.setRychlostMHZ(Integer.parseInt(tfRychlost.getText()));
            t.setKapacitaPametiMB(Integer.parseInt(tfKapacitaPameti.getText()));
            ((KomponentaPocitaceRAM) t).setTypPameti(comboBoxTypPameti.getValue());
            ((KomponentaPocitaceRAM) t).setDualChannel(checkBoxDualChannel.isSelected());
        };
    }

    @Override
    protected Label[] addLabelsToGridPane() {
        Label labelRamRychlost = new Label("Rychlost MHZ");
        Label labelRamKapacita = new Label("Kapacita pameti");
        Label labelRamTyp = new Label("Typ Pameti");
        Label labelDualChannel = new Label("Podpora dual channel");

        return new Label[]{labelRamRychlost, labelRamKapacita, labelRamTyp, labelDualChannel};
    }

    @Override
    protected Node[] addTextFieldsToGridPane() {
//        tfRychlost = new TextField(String.valueOf(pomocnaKomponenta.getRychlostMHZ()));
//
//        tfKapacitaPameti = new TextField(String.valueOf(((KomponentaPocitaceRAM) pomocnaKomponenta).getKapacitaPametiMB()));
//
//        CBTypPameti = new ComboBox();
//        CBTypPameti.getItems().addAll(TypPameti.values());
//        CBTypPameti.getSelectionModel().select(((KomponentaPocitaceRAM) pomocnaKomponenta).getTypPameti());
//
//        CBPodporaDualChannel = new CheckBox("Podporuje dual-channel?");
//        CBPodporaDualChannel.setSelected(((KomponentaPocitaceRAM) pomocnaKomponenta).isDualChannel());
//        CBPodporaDualChannel.setOnAction((t) -> {
//            ((KomponentaPocitaceRAM) pomocnaKomponenta).setDualChannel(CBPodporaDualChannel.isSelected());
//        });
//
//        tfRychlost.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        tfKapacitaPameti.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        
        attributy = new AttributyRAM((KomponentaPocitaceRAM) getKomponentaPocitaceBase());

        attributy.disableTextFields(jsouKomponentyVypnuty);

        return attributy.addTextFields();
    }

}
