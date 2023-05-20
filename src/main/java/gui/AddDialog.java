/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceBase;
import data.KomponentaPocitaceCPU;
import data.KomponentaPocitaceGPU;
import data.KomponentaPocitaceRAM;
import data.TypyKomponent;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author wille
 */
public class AddDialog extends Dialog<KomponentaPocitaceBase> {
    
    TextField tfNazev;
    ComboBox<TypyKomponent> cbTypKomponenty;
    
    AttributyCPU cpuAttributy = new AttributyCPU();
    AttributyGPU gpuAttributy = new AttributyGPU();
    AttributyRAM ramAttributy = new AttributyRAM();
    int newItemID = 0;
    
    public AddDialog(int posledniID) {
        newItemID = posledniID;
        callStage("Adding new item dialog");
    }
    
    private void callStage(String titulek) {
        setTitle(titulek);
        setHeaderText("Vlozte novou komponenty");
        GridPane root = populateRoot();
        getDialogPane().setContent(root);
        ButtonType btnOK = ButtonType.OK;
        
        ButtonType btnCancel = ButtonType.CANCEL;
        getDialogPane().getButtonTypes().addAll(btnOK, btnCancel);
        Button btn = (Button) getDialogPane().lookupButton(btnOK);
        btn.addEventFilter(ActionEvent.ACTION, (t) -> {
            if (!jeValidni()) {
                t.consume();
            }
        });
        setResultConverter((param) -> {
            if (param == btnOK) {
                return generateKomponent();
            }
            return null;
        });
    }
    
    private GridPane populateRoot() {
        GridPane gridPaneDialog = new GridPane();
        
        Label labelID = new Label("ID");
        Label labelIDValue = new Label(String.valueOf(newItemID));
        
        Label labelType = new Label("Typ komponenty");
        cbTypKomponenty = new ComboBox();
        cbTypKomponenty.getItems().addAll(TypyKomponent.values());
        cbTypKomponenty.getSelectionModel().selectLast();
        
        Label labelNazev = new Label();
        labelNazev.setText("Nazev");
        tfNazev = new TextField();
        
        gridPaneDialog.setPadding(new Insets(10, 10, 10, 10));
        gridPaneDialog.setVgap(5);
        gridPaneDialog.setHgap(5);
        
        gridPaneDialog.setAlignment(Pos.CENTER);
        
        gridPaneDialog.addColumn(0, labelID, labelNazev, labelType);
        gridPaneDialog.addColumn(0, cpuAttributy.addLabels());
        gridPaneDialog.addColumn(0, gpuAttributy.addLabels());
        gridPaneDialog.addColumn(0, ramAttributy.addLabels());
        
        gridPaneDialog.addColumn(1, labelIDValue, tfNazev, cbTypKomponenty);
        gridPaneDialog.addColumn(1, cpuAttributy.addTextFields());
        gridPaneDialog.addColumn(1, gpuAttributy.addTextFields());
        gridPaneDialog.addColumn(1, ramAttributy.addTextFields());
        cbTypKomponenty.setOnAction((t) -> {
            updateVisible();
        });
        updateVisible();
        return gridPaneDialog;
    }
    
    private KomponentaPocitaceBase generateKomponent() {
        if (cbTypKomponenty.getValue() == TypyKomponent.CPU) {
            return new KomponentaPocitaceCPU(newItemID, tfNazev.getText(), cpuAttributy.getTfRychlost(), cpuAttributy.getTfPocetJader(), cpuAttributy.getTfPocetVlaken());
        } else if (cbTypKomponenty.getValue() == TypyKomponent.GPU) {
            return new KomponentaPocitaceGPU(newItemID, tfNazev.getText(), gpuAttributy.getTfRychlost(), gpuAttributy.getTfKapacitaPameti(), gpuAttributy.getTfPocetFanu(), gpuAttributy.getTfHlasitost());
        } else if (cbTypKomponenty.getValue() == TypyKomponent.RAM) {
            return new KomponentaPocitaceRAM(newItemID, tfNazev.getText(), ramAttributy.getTfRychlost(), ramAttributy.getTfKapacitaPameti(), ramAttributy.getCBTypPameti(), ramAttributy.getCBPodporaDualChannel());
        } else {
            return null;
        }
    }
    
    private boolean jeValidni() {
        if (!tfNazev.getText().isBlank()) {
            return switch (cbTypKomponenty.getValue()) {
                case CPU ->
                    cpuAttributy.jeValidni();
                case GPU ->
                    gpuAttributy.JeValidni();
                case RAM ->
                    ramAttributy.JeValidni();
                default ->
                    false;
            };
        } else {
            return false;
        }
    }
    
    private void updateVisible() {
        cpuAttributy.setVisible(cbTypKomponenty.getValue() == TypyKomponent.CPU);
        gpuAttributy.setVisible(cbTypKomponenty.getValue() == TypyKomponent.GPU);
        ramAttributy.setVisible(cbTypKomponenty.getValue() == TypyKomponent.RAM);
        getDialogPane().getScene().getWindow().sizeToScene();
    }
}
