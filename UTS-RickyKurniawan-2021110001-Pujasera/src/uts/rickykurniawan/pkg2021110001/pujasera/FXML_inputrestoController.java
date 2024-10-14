/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts.rickykurniawan.pkg2021110001.pujasera;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXML_inputrestoController implements Initializable {

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private TextField txtharga;
    @FXML
    private TextField txtmenu;
    @FXML
    private TextField txtid_restoran;
    private boolean editdata = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        RestoModel s = new RestoModel();
        s.setId_restoran(Integer.parseInt(txtid_restoran.getText()));
        s.setMenu(txtmenu.getText());
        s.setHarga(Integer.parseInt(txtharga.getText()));
        FXML_mainmenuController.dtresto.setRestoModel(s);
        if (editdata) {
            if (FXML_mainmenuController.dtresto.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtid_restoran.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        }  else if (FXML_mainmenuController.dtresto.validasi(String.valueOf(s.getId_restoran())) <= 0) {
            if (FXML_mainmenuController.dtresto.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtid_restoran.requestFocus();
        }

    }

    @FXML
    private void keluarklik(ActionEvent event) {
       btnkeluar.getScene().getWindow().hide();
    }


    @FXML
    private void batalklik(ActionEvent event) {
        txtid_restoran.setText("");
        txtmenu.setText("");
        txtharga.setText("");
        txtid_restoran.requestFocus();
    }
    public void execute(RestoModel d) {
    if (d != null && d.getId_restoran() > 0) {
        editdata = true;
        txtid_restoran.setText(String.valueOf(d.getId_restoran())); 
        txtmenu.setText(d.getMenu());
        txtharga.setText(String.valueOf(d.getHarga())); 
        txtid_restoran.setEditable(false); 
        txtmenu.requestFocus(); 
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Data restoran tidak valid.", ButtonType.OK);
        alert.showAndWait();
    }
}

}

