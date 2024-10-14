/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts.rickykurniawan.pkg2021110001.pujasera;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXML_inputtransaksiController implements Initializable {

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnsimpan;
    @FXML
    private Slider sldjumlah;
    @FXML
    private TextField txtharga;
    @FXML
    private DatePicker dtptanggal;
    @FXML
    private TextField txtmakanan;
    @FXML
    private TextField txtid_transaksi;
    @FXML
    private TextField txtminuman;
    @FXML
    private Label lbljumlah;
    @FXML
    private Label lblmakanan;
    @FXML
    private Label lblminuman;
    @FXML
    private Button btnpilihmenu;
    private boolean editdata = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sldjumlah.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed,
                    Number oldVal, Number newVal) {
                lbljumlah.setText(String.valueOf(newVal.intValue()));
            }
        });
    }    
    public void execute(TransaksiModel d) {
        if (!d.getId_transaksi().isEmpty() && !d.getMakanan().isEmpty() && !d.getMinuman().isEmpty()) {
            editdata = true;
            txtid_transaksi.setText(d.getId_transaksi());
            txtmakanan.setText(d.getMakanan());
            dtptanggal.setValue(d.getTanggal().toLocalDate());
            sldjumlah.setValue(d.getJumlah());
            txtharga.setText(String.valueOf(d.getHarga()));
            txtid_transaksi.setEditable(false);
            txtmakanan.setEditable(false);
            txtminuman.setEditable(false);
            dtptanggal.requestFocus();
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        txtid_transaksi.setText("");
        txtmakanan.setText("");
        txtminuman.setText("");
        dtptanggal.getEditor().clear();
        sldjumlah.setValue(0);
        txtharga.setText("");
        txtid_transaksi.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
          TransaksiModel n = new TransaksiModel();
        n.setId_transaksi(txtid_transaksi.getText());
        n.setMakanan(txtmakanan.getText());
        n.setMinuman(txtminuman.getText());
        n.setJumlah((int) sldjumlah.getValue());
        n.setHarga(Integer.parseInt(txtharga.getText()));
        n.setTanggal(Date.valueOf(dtptanggal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        FXML_mainmenuController.dtnilai.setTransaksiModel(n);
        if (editdata) {
            if (FXML_mainmenuController.dtnilai.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtid_transaksi.setEditable(true);
                hapusklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXML_mainmenuController.dtnilai.validasi(n.getId_transaksi(), n.getMakanan()) <= 0) {
            if (FXML_mainmenuController.dtnilai.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                hapusklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtid_transaksi.requestFocus();
        }
    }

    @FXML
    private void pilihmenuklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_pilihmenu.fxml"));
            Parent root = (Parent) loader.load();
            FXML_pilihmenuController isidt = (FXML_pilihmenuController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtid_transaksi.setText(isidt.getidmenu());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void execute(RestoModel s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }
    

