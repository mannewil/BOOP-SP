/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceBase;
import data.KomponentaPocitaceGPU;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author wille
 */
public class GPUEditDialog extends GenericEditDialog {

    TextField tfRychlost;
    TextField tfKapacitaPameti;
    TextField tfPocetFanu;
    TextField tfHlasitost;

    AttributyGPU attributy;

    public GPUEditDialog(KomponentaPocitaceGPU gpu, boolean jsouKomponentyVypnuty) {
        super(gpu, jsouKomponentyVypnuty ? "Zobraz GPU" : "Edituj GPU", jsouKomponentyVypnuty);
        tfRychlost = attributy.tfRychlost;
        tfKapacitaPameti = attributy.tfKapacitaPameti;
        tfPocetFanu = attributy.tfPocetFanu;
        tfHlasitost = attributy.tfHlasitost;
    }

    public GPUEditDialog(KomponentaPocitaceGPU gpu) {
        this(gpu, false);
    }

    @Override
    protected boolean JeValidni() {
        return !(tfRychlost.getText().isBlank() || tfKapacitaPameti.getText().isBlank() || tfPocetFanu.getText().isBlank() || tfHlasitost.getText().isBlank() && super.JeValidni());

    }

    public Consumer<KomponentaPocitaceBase> generateConsumer() {
        return (t) -> {
            t.setNazevKomponenty(tfNazev.getText());
            t.setRychlostMHZ(Integer.parseInt(tfRychlost.getText()));
            t.setKapacitaPametiMB(Integer.parseInt(tfKapacitaPameti.getText()));
            ((KomponentaPocitaceGPU) t).setPocetFanu(Integer.parseInt(tfPocetFanu.getText()));
            ((KomponentaPocitaceGPU) t).setHlasitost(Integer.parseInt(tfHlasitost.getText()));
        };
    }

    @Override
    protected Label[] addLabelsToGridPane() {
        Label labelGpuRychlost = new Label("Rychlost MHZ");
        Label labelGpuKapacita = new Label("Kapacita pameti");
        Label labelGpuPocetFanu = new Label("Pocet fanu");
        Label labelGpuHlasitost = new Label("Hlasitost");

        return new Label[]{labelGpuRychlost, labelGpuKapacita, labelGpuPocetFanu, labelGpuHlasitost};
    }

    @Override
    protected Node[] addTextFieldsToGridPane() {
//        tfRychlost = new TextField(String.valueOf(pomocnaKomponenta.getRychlostMHZ()));
//
//        tfKapacitaPameti = new TextField(String.valueOf(((KomponentaPocitaceGPU) pomocnaKomponenta).getKapacitaPametiMB()));
//
//        tfPocetFanu = new TextField(String.valueOf(((KomponentaPocitaceGPU) pomocnaKomponenta).getPocetFanu()));
//
//        tfHlasitost = new TextField(String.valueOf(((KomponentaPocitaceGPU) pomocnaKomponenta).getHlasitost()));
//
//        tfRychlost.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        tfKapacitaPameti.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        tfPocetFanu.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
//        tfHlasitost.setTextFormatter(new TextFormatter<>(change
//                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));

        attributy = new AttributyGPU((KomponentaPocitaceGPU) getKomponentaPocitaceBase());

        attributy.disableTextFields(jsouKomponentyVypnuty);

        return attributy.addTextFields();
    }

}
