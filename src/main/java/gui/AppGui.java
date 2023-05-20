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
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author wille
 */
public class AppGui extends Application {
    
    spravce.Spravce instance = new spravce.Spravce();
    Stage primaryStage;
    ListView<String> listView;
    ObservableList<String> oList;
    
    enum TypFiltrace {
        BY_ID, BY_NAME
    };
    boolean isFiltraceOn = false;
    ComboBox<TypFiltrace> cbTypFiltrace;
    ComboBox<TypyKomponent> cbTypFiltraceKomponenta;
    TextField tfFiltracni;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        primaryStage = stage;
        
        VBox root = new VBox(); // Anchor for the program

        oList = FXCollections.observableArrayList();
        listView = new ListView(oList);
        
        listView.setOnMouseClicked((t) -> {
            if (instance.dej() != null) {
                String vybranaKomponenta = listView.getSelectionModel().getSelectedItem();
                instance.prvni();
                while (!vybranaKomponenta.equals(instance.dej().toString())) {
                    instance.dalsi();
                }
            }
        });
        
        HBox hlavniBox = new HBox(); // Box for the list
        VBox.setVgrow(hlavniBox, Priority.ALWAYS); // Making sure the list covers most of the application's window
        HBox.setHgrow(listView, Priority.ALWAYS);
        
        hlavniBox.getChildren().add(listView); // adding ListView to the box as a child

        HBox boxTlacitka = generujTlacitkovyBox(); // Generation of both boxes (adding buttons/labels/comboBoxes and shoving them inside boxes)
        VBox boxOperaci = generujOperacniBox();
        
        hlavniBox.getChildren().add(boxOperaci); // adding the operation vertical box as child to the list view box

        root.getChildren().addAll(hlavniBox, boxTlacitka); // adding both boxes as children to our anchor/root

        Scene scene = new Scene(root, 800, 600); // making a scene

        stage.setTitle("Pujcovna komponentu pocitacu - Autor: Demchenko Mikhail");
        stage.setScene(scene);
        stage.show();
    }
    
    private HBox generujTlacitkovyBox() {
        HBox vysledkovyBox = new HBox();
        Spinner<Integer> spinnerPocet = new Spinner<Integer>();
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10);
        spinnerPocet.setValueFactory(valueFactory);
        spinnerPocet.setPrefSize(65, 15);
        spinnerPocet.setEditable(true);
        spinnerPocet.getEditor().setTextFormatter(new TextFormatter<>((t) -> {
            if (t.getControlNewText().matches("([1-9][0-9]*)?")) {
                return t;
            } else {
                return null;
            }
        }));
        
        Button btnNacti = new Button("Nacti");
        btnNacti.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Vyberte soubor pro nacteni...");
            final ExtensionFilter extensionFilterCommon = new ExtensionFilter("Vsechne", "*.txt", "*.dat");
            final ExtensionFilter extensionFilterTXT = new ExtensionFilter("Textove soubory", "*.txt");
            final ExtensionFilter extensionFilterDAT = new ExtensionFilter("Datove soubory", "*.dat");
            fileChooser.getExtensionFilters().addAll(extensionFilterCommon, extensionFilterDAT, extensionFilterTXT);
            fileChooser.setInitialDirectory(new File("."));
            File vracenySoubor = fileChooser.showOpenDialog(primaryStage);
            if (vracenySoubor != null) {
                if (vracenySoubor.getName().endsWith(".dat")) {
                    instance.obnov(vracenySoubor.getPath());
                } else if (vracenySoubor.getName().endsWith(".txt")) {
                    instance.nactiText(vracenySoubor.getPath());
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upozorneni");
                    alert.setContentText("Prosim vas, vyberte soubor pro nacteni...");
                    alert.showAndWait();
                }
            }
            refreshListView();
            
        }
        );
        
        Button btnUloz = new Button("Uloz");
        btnUloz.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Vyberte soubor pro ulozeni...");
            final ExtensionFilter extensionFilterTXT = new ExtensionFilter("Textove soubory", "*.txt");
            final ExtensionFilter extensionFilterDAT = new ExtensionFilter("Datove soubory", "*.dat");
            fileChooser.getExtensionFilters().addAll(extensionFilterDAT, extensionFilterTXT);
            fileChooser.setInitialDirectory(new File("."));
            File vracenySoubor = fileChooser.showSaveDialog(primaryStage);
            if (vracenySoubor != null) {
                if (vracenySoubor.getName().endsWith(".dat")) {
                    instance.zalohuj(vracenySoubor.getPath());
                } else if (vracenySoubor.getName().endsWith(".txt")) {
                    instance.ulozText(vracenySoubor.getPath());
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upozorneni");
                    alert.setContentText("Prosim vas, vyberte soubor pro ulozeni...");
                    alert.showAndWait();
                }
            }
        }
        );
        
        Button btnGeneruj = new Button("Generuj");
        btnGeneruj.setOnAction((ActionEvent event) -> {
            instance.setID(getMaxID() + 1);
            instance.generuj(spinnerPocet.getValue());
            refreshListView();
        }
        );

        //filtrace
        tfFiltracni = new TextField();
        tfFiltracni.setTextFormatter(new TextFormatter<>(change
                -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
        
        cbTypFiltrace = new ComboBox();
        cbTypFiltrace.getItems().addAll(TypFiltrace.values());
        cbTypFiltrace.getSelectionModel().selectFirst();
        
        cbTypFiltrace.setOnAction((t) -> {
            
            if (cbTypFiltrace.getValue() == TypFiltrace.BY_ID) {
                tfFiltracni.setTextFormatter(new TextFormatter<>(change
                        -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
            } else {
                tfFiltracni.setTextFormatter(null);
            }
            tfFiltracni.clear();
        });
        
        cbTypFiltraceKomponenta = new ComboBox();
        cbTypFiltraceKomponenta.getItems().addAll(TypyKomponent.values());
        cbTypFiltraceKomponenta.getSelectionModel().selectLast();

        //filtrace
//        Button btnNajdi = new Button("Najdi");
//        btnNajdi.setOnAction((t) -> {
//            if ((cbTypFiltrace.getValue() == TypFiltrace.BY_ID) && !tfFiltracni.getText().isBlank() || tfFiltracni.getText().isBlank() && cbTypFiltraceKomponenta.getValue() != TypyKomponent.NeNastavena) {
//                instance.najdi(predicate);
//                refreshListView();
//            }
//        });
        Button btnFilterSet = new Button("Set filtrace");
        btnFilterSet.setOnAction((t) -> {
            if ((cbTypFiltrace.getValue() == TypFiltrace.BY_ID || cbTypFiltrace.getValue() == TypFiltrace.BY_NAME) && !tfFiltracni.getText().isBlank() || tfFiltracni.getText().isBlank() && cbTypFiltraceKomponenta.getValue() != TypyKomponent.NeNastavena) {
                isFiltraceOn = true;
                refreshListView();
            }
        });
        
        Button btnFilterClear = new Button("Clear filtrace");
        btnFilterClear.setOnAction((t) -> {
            isFiltraceOn = false;
            refreshListView();
        });
        
        vysledkovyBox.getChildren().addAll(btnNacti, btnUloz, btnGeneruj, spinnerPocet, tfFiltracni, cbTypFiltrace, cbTypFiltraceKomponenta, btnFilterSet, btnFilterClear); // adding buttons as children
        vysledkovyBox.getChildren().forEach((node) -> HBox.setMargin(node, new Insets(3))); // adding spaces between elements
        return vysledkovyBox;
        
    }
    
    private VBox generujOperacniBox() {
        VBox vysledkovyBox = new VBox();
        
        vysledkovyBox.setMinWidth(120); // minimal width for button spacing

        Label labelProchazeni = new Label("Prochazeni");
        
        Button btnPrvni = new Button("Prvni");
        btnPrvni.setOnAction((ActionEvent event) -> {
            if (!instance.prvni()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorneni!");
                alert.setContentText("Seznam je prazdny! Neni mozne nastavit prvni komponentu v seznamu!");
                alert.showAndWait();
            } else {
                listView.getSelectionModel().selectFirst();
            }
        });
        
        Button btnDalsi = new Button("Dalsi");
        btnDalsi.setOnAction((ActionEvent event) -> {
            if (!instance.dalsi()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorneni!");
                alert.setContentText("Neni dalsi prvek!");
                alert.showAndWait();
            } else {
                
                listView.getSelectionModel().selectNext();
            }
        });
        
        Button btnPredchozi = new Button("Predchozi");
        btnPredchozi.setOnAction((ActionEvent event) -> {
            if (!instance.predchozi()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorneni!");
                alert.setContentText("Neni predchozi prvek!");
                alert.showAndWait();
            } else {
                listView.getSelectionModel().selectPrevious();
                
            }
        });
        
        Button btnPosledni = new Button("Posledni");
        btnPosledni.setOnAction((ActionEvent event) -> {
            if (!instance.posledni()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorneni!");
                alert.setContentText("Seznam je prazdny! Neni mozne nastavit posledni komponentu v seznamu!");
                alert.showAndWait();
            } else {
                listView.getSelectionModel().selectLast();
                
            }
        });
        
        Label labelSelekce = new Label("Prikazy");
        
        Button btnNovy = new Button("Novy");
        btnNovy.setOnAction((ActionEvent event) -> {
            AddDialog addDialog = new AddDialog(getMaxID() + 1);
            addDialog.showAndWait().ifPresent((t) -> {
                if (instance.dej() == null) {
                    instance.vlozPosledni(t);
                } else {
                    instance.novy(t);
                }
                refreshListView();
            });
        });
        
        Button btnEdituj = new Button("Edituj");
        btnEdituj.setOnAction(
                (ActionEvent event) -> {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        
                        if (instance.dej().getTyp().equals(data.TypyKomponent.CPU) || instance.dej().getTyp().equals(data.TypyKomponent.GPU) || instance.dej().getTyp().equals(data.TypyKomponent.RAM) && instance.dej() != null) {
                            GenericEditDialog editDialog;
                            KomponentaPocitaceBase editableKomponent = instance.dej();
                            
                            switch (editableKomponent.getTyp()) {
                                case CPU ->
                                    editDialog = new CPUEditDialog((KomponentaPocitaceCPU) editableKomponent);
                                case GPU ->
                                    editDialog = new GPUEditDialog((KomponentaPocitaceGPU) editableKomponent);
                                case RAM ->
                                    editDialog = new RAMEditDialog((KomponentaPocitaceRAM) editableKomponent);
                                default -> {
                                    return;
                                }
                            }
                            editDialog.showAndWait().ifPresent((t) -> {
                                instance.edituj(t);
                                refreshListView();
                            });
                        }
                    }
                }
        );
        
        Button btnVyjmi = new Button("Vyjmi");
        btnVyjmi.setOnAction(
                (ActionEvent event) -> {
                    if (instance.vyjmi() != null) {
                        refreshListView();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Upozorneni!");
                        alert.setContentText("Nic nebylo vyjmeno. Mate nastavenou komponentu?");
                        alert.showAndWait();
                    }
                }
        );
        
        Button btnZrus = new Button("Zrus/Clear List");
        btnZrus.setOnAction(
                (ActionEvent event) -> {
                    instance.zrus();
                    instance.refreshID();
                    refreshListView();
                }
        );
        
        Button btnZobraz = new Button("Zobraz");
        btnZobraz.setOnAction(
                (ActionEvent event) -> {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        
                        GenericEditDialog editDialog;
                        KomponentaPocitaceBase editableKomponent = instance.dej();
                        
                        switch (editableKomponent.getTyp()) {
                            case CPU ->
                                editDialog = new CPUEditDialog((KomponentaPocitaceCPU) editableKomponent, true);
                            case GPU ->
                                editDialog = new GPUEditDialog((KomponentaPocitaceGPU) editableKomponent, true);
                            case RAM ->
                                editDialog = new RAMEditDialog((KomponentaPocitaceRAM) editableKomponent, true);
                            default -> {
                                return;
                            }
                        }
                        editDialog.showAndWait();
                        
                    }
                }
        );
        
        vysledkovyBox.getChildren()
                .addAll(labelProchazeni, btnPrvni, btnDalsi, btnPredchozi, btnPosledni, labelSelekce, btnNovy, btnEdituj, btnVyjmi, btnZrus, btnZobraz);
        vysledkovyBox.getChildren()
                .forEach((node) -> {
                    VBox.setMargin(node, new Insets(3));
                    if (node instanceof Button butNode) {
                        butNode.setMaxWidth(Double.MAX_VALUE);
                    }
                }
                );
        return vysledkovyBox;
    }
    
    private void refreshListView() {
        
        oList.clear();
        List<KomponentaPocitaceBase> list = instance.stream().filter(getFilter()).toList();
        ArrayList<String> listString = new ArrayList<>();
        list.forEach((t) -> {
            listString.add(t.toString());
        });
        
        oList.addAll(listString);
        
        listView.refresh();
        if (instance.pocet() > 0) {
            instance.prvni();
            listView.getSelectionModel().selectFirst();
        }
    }
    
    private int getMaxID() {
        int id = 0;
        Iterator<KomponentaPocitaceBase> it = instance.iterator();
        while (it.hasNext()) {
            int idPomocna = it.next().getIdKomponenty();
            if (idPomocna > id) {
                id = idPomocna;
            }
        }
        return id;
    }
    
    private Predicate<KomponentaPocitaceBase> getFilter() {
        if (isFiltraceOn) {
            if (tfFiltracni.getText().isBlank() && cbTypFiltraceKomponenta.getValue() != TypyKomponent.NeNastavena) {
                return (t) -> t.getTyp() == cbTypFiltraceKomponenta.getValue();
            }
            
            switch (cbTypFiltrace.getValue()) {
                case BY_ID:
                    if (cbTypFiltraceKomponenta.getValue() != TypyKomponent.NeNastavena) {
                        return (t) -> t.getTyp() == cbTypFiltraceKomponenta.getValue() && t.getIdKomponenty() == Integer.parseInt(tfFiltracni.getText());
                    } else {
                        return (t)
                                -> t.getIdKomponenty() == Integer.parseInt(tfFiltracni.getText());
                    }
                
                case BY_NAME:
                    if (cbTypFiltraceKomponenta.getValue() != TypyKomponent.NeNastavena) {
                        return (t) -> t.getTyp() == cbTypFiltraceKomponenta.getValue() && t.getNazevKomponenty().toLowerCase().contains(tfFiltracni.getText().toLowerCase());
                    } else {
                        return (t) -> t.getNazevKomponenty().toLowerCase().contains(tfFiltracni.getText().toLowerCase());
                    }
            }
            
        }
        return (t) -> true;
    }
}
