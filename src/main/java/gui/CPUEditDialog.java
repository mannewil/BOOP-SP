/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceBase;
import data.KomponentaPocitaceCPU;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author wille
 */
public class CPUEditDialog extends GenericEditDialog {

    TextField tfRychlost;
    TextField tfPocetJader;
    TextField tfPocetVlaken;

    AttributyCPU attributy;

    public CPUEditDialog(KomponentaPocitaceCPU cpu, boolean jsouKomponentyVypnuty) {
        super(cpu, jsouKomponentyVypnuty ? "Zobraz CPU" : "Edituj CPU", jsouKomponentyVypnuty);
        tfRychlost = attributy.tfRychlost;
        tfPocetJader = attributy.tfPocetJader;
        tfPocetVlaken = attributy.tfPocetVlaken;
    }

    public CPUEditDialog(KomponentaPocitaceCPU cpu) {
        this(cpu, false);
    }

    @Override
    protected boolean JeValidni() {
        return !(tfPocetJader.getText().isBlank() || tfPocetVlaken.getText().isBlank() || tfRychlost.getText().isBlank() && super.JeValidni());

    }

    public Consumer<KomponentaPocitaceBase> generateConsumer() {
        return (t) -> {
            t.setNazevKomponenty(tfNazev.getText());
            t.setRychlostMHZ(Integer.parseInt(tfRychlost.getText()));
            ((KomponentaPocitaceCPU) t).setPocetJader(Integer.parseInt(tfPocetJader.getText()));
            ((KomponentaPocitaceCPU) t).setPocetVlaken(Integer.parseInt(tfPocetVlaken.getText()));
        };
    }

    @Override
    protected Label[] addLabelsToGridPane() {
        Label labelCpuRychlost = new Label("Rychlost MHZ");
        Label labelCpuPocetJader = new Label("Pocet jader");
        Label labelCpuPocetVlaken = new Label("Pocet vlaken");

        return new Label[]{labelCpuRychlost, labelCpuPocetJader, labelCpuPocetVlaken};
    }

    @Override
    protected Node[] addTextFieldsToGridPane() {
//        tfRychlost = new TextField(String.valueOf(pomocnaKomponenta.getRychlostMHZ()));
//
//        tfPocetJader = new TextField(String.valueOf(((KomponentaPocitaceCPU) pomocnaKomponenta).getPocetJader()));
//
//        tfPocetVlaken = new TextField(String.valueOf(((KomponentaPocitaceCPU) pomocnaKomponenta).getPocetVlaken()));
//
//        tfRychlost.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        tfPocetJader.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        tfPocetVlaken.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));

        attributy = new AttributyCPU((KomponentaPocitaceCPU) getKomponentaPocitaceBase());

        attributy.disableTextFields(jsouKomponentyVypnuty);

        return attributy.addTextFields();
    }

}
