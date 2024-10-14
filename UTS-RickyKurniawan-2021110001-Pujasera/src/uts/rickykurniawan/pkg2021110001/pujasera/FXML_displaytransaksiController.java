package uts.rickykurniawan.pkg2021110001.pujasera;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXML_displaytransaksiController implements Initializable {

    @FXML
    private TableView<TransaksiModel> tbvtransaksi;
    @FXML
    private TextField txttotal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    public void showdata() {
        ObservableList<TransaksiModel> data = FXML_mainmenuController.dtnilai.Load();
        if (data != null && !data.isEmpty()) {
            tbvtransaksi.getColumns().clear();
            tbvtransaksi.getItems().clear();
            setupTableColumns();
            tbvtransaksi.setItems(data);
            calculateTotal(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvtransaksi.getScene().getWindow().hide();
        }
    }

    private void setupTableColumns() {
        String[] columnNames = {"id_menu", "makanan/minuman", "harga", "tanggal", "jumlah", "total"};
        for (String name : columnNames) {
            TableColumn<TransaksiModel, String> col = new TableColumn<>(name);
            col.setCellValueFactory(new PropertyValueFactory<>(name));
            tbvtransaksi.getColumns().add(col);
        }
    }

    private void calculateTotal(ObservableList<TransaksiModel> data) {
        int totalJumlah = 0;
        double totalHarga = 0.0;

        for (TransaksiModel m : data) {
            totalJumlah += m.getJumlah();
            totalHarga += m.getHarga() * m.getJumlah(); // Assuming harga is per item
        }

        txttotal.setText(String.valueOf(totalHarga)); // or whatever calculation you need
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    
    }

    @FXML
    private void ubahklik(ActionEvent event) {
     
    }

    @FXML
    private void tambahklik(ActionEvent event) {
   
    }

    @FXML
    private void akhirklik(ActionEvent event) {
       
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
  
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
 
    }

    @FXML
    private void awalklik(ActionEvent event) {
       
    }
}
