/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import data.KomponentaPocitaceBase;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author wille
 */
abstract class GenericEditDialog extends Dialog<Consumer<KomponentaPocitaceBase>> {

    private boolean isOK = false;
    public KomponentaPocitaceBase komponentaPocitaceBase;
    public TextField tfNazev;
    protected boolean jsouKomponentyVypnuty;

    public GenericEditDialog(KomponentaPocitaceBase komponentaPocitaceBase, String titulek, boolean jsouKomponentyVypnuty) {
        this.jsouKomponentyVypnuty = jsouKomponentyVypnuty;
        setKomponentaPocitaceBase(komponentaPocitaceBase);
        callStage(titulek);
    }

    private void setKomponentaPocitaceBase(KomponentaPocitaceBase komponentaPocitaceBase) {
        this.komponentaPocitaceBase = komponentaPocitaceBase;
    }

    protected KomponentaPocitaceBase getKomponentaPocitaceBase() {
        return komponentaPocitaceBase;
    }
    
    

    private void callStage(String titulek) {
//        stage = new Stage();
        setTitle(titulek);
        setHeaderText(jsouKomponentyVypnuty ? "Zobrazena komponenta" : "Editujte vybranou komponentu");
        GridPane root = populateRoot();
        getDialogPane().setContent(root);
        if (!jsouKomponentyVypnuty) {
            ButtonType btnOK = ButtonType.OK;
            ButtonType btnCancel = ButtonType.CANCEL;
            getDialogPane().getButtonTypes().addAll(btnOK, btnCancel);
            Button btn = (Button) getDialogPane().lookupButton(btnOK);
            btn.addEventFilter(ActionEvent.ACTION, (t) -> {
                if (!JeValidni()) {
                    t.consume();
                }
            });
            setResultConverter((param) -> {
                if (param == btnOK) {
                    return generateConsumer();
                }
                return null;
            });
        } else {
            ButtonType btnClose = ButtonType.CLOSE;
            getDialogPane().getButtonTypes().add(btnClose);
        }

//        stage.setTitle(titulek);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
    }

    private GridPane populateRoot() {
        GridPane gridPaneDialog = new GridPane();

        Label labelID = new Label();
        labelID.setText("ID");
        Label labelIDValue = new Label();
        labelIDValue.setText(String.valueOf(komponentaPocitaceBase.getIdKomponenty()));

        Label labelType = new Label();
        labelType.setText("Typ komponenty");
        Label labelTypeValue = new Label();
        labelTypeValue.setText(String.valueOf(komponentaPocitaceBase.getTyp()));

        Label labelNazev = new Label();
        labelNazev.setText("Nazev");
        tfNazev = new TextField(komponentaPocitaceBase.getNazevKomponenty());

        tfNazev.setDisable(jsouKomponentyVypnuty);

//        Button buttonOk = new Button("Ok");
//        buttonOk.setOnAction((t) -> {
//            if (JeValidni()) {
//                isOK = true;
//                stage.close();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setHeaderText("Upozorneni");
//                alert.setContentText("Polozka nesmi byt prazdnou!");
//                alert.showAndWait();
//            }
//        });
//
//        Button buttonCancel = new Button("Zrusit");
//        buttonCancel.setOnAction((t) -> {
//            isOK = false;
//            stage.close();
//        });
        gridPaneDialog.setPadding(new Insets(10, 10, 10, 10));
        gridPaneDialog.setVgap(5);
        gridPaneDialog.setHgap(5);

        gridPaneDialog.setAlignment(Pos.CENTER);

        gridPaneDialog.addColumn(0, labelID, labelType, labelNazev);
        gridPaneDialog.addColumn(0, addLabelsToGridPane());
//        gridPaneDialog.addColumn(0, buttonOk);

        gridPaneDialog.addColumn(1, labelIDValue, labelTypeValue, tfNazev);
        gridPaneDialog.addColumn(1, addTextFieldsToGridPane());
//        gridPaneDialog.addColumn(1, buttonCancel);

        return gridPaneDialog;
    }

    protected boolean JeValidni() {
        return !tfNazev.getText().isBlank();
    }

    protected abstract Label[] addLabelsToGridPane();

    protected abstract Node[] addTextFieldsToGridPane();

    public abstract Consumer<KomponentaPocitaceBase> generateConsumer();

    public boolean isIsOK() {
        return isOK;
    }

}
