/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts.rickykurniawan.pkg2021110001.pujasera;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXML_pilihmenuController implements Initializable {

    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btncari;
    @FXML
    private TextField txtisi;
    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private TableView<MenuModel> tbvmenu;
    
    private int hasil=0;
    private String idmenuhasil="";
     public int getHasil(){return(hasil);}
    public String getidmenu(){return(idmenuhasil);}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cmbfield.setItems(FXCollections.observableArrayList(
        "idmenu","menu","harga"));
        cmbfield.getSelectionModel().select(0);
        showdata("idmenu","");

    }    
    public void showdata(String f,String i){
         ObservableList<MenuModel> data= FXML_mainmenuController.dtmenu.LookUp(f, i);
        if(data.isEmpty()){    data=FXML_mainmenuController.dtmenu.Load();
                                          txtisi.setText("");       }
        if(data!=null){   tbvmenu.getColumns().clear();
 tbvmenu.getItems().clear();   TableColumn col=new TableColumn("idmenu");
 col.setCellValueFactory(new PropertyValueFactory<MenuModel, String>("idmenu"));
 tbvmenu.getColumns().addAll(col);  col=new TableColumn("menu");
col.setCellValueFactory(new PropertyValueFactory<MenuModel, String>("menu"));
tbvmenu.getColumns().addAll(col); col=new TableColumn("alamat");
col.setCellValueFactory(new PropertyValueFactory<MenuModel, String>("harga"));
tbvmenu.getColumns().addAll(col); tbvmenu.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();   tbvmenu.getScene().getWindow().hide();;
        }                
        awalklik(null);  txtisi.requestFocus();   }
 

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvmenu.getSelectionModel().selectLast();        tbvmenu.requestFocus();  
    }
    

    @FXML
    private void sesudahklik(ActionEvent event) {
         tbvmenu.getSelectionModel().selectBelowCell();        tbvmenu.requestFocus();    
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvmenu.getSelectionModel().selectAboveCell();        tbvmenu.requestFocus(); 
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvmenu.getSelectionModel().selectFirst();        tbvmenu.requestFocus();   
    }

    @FXML
    private void batalklik(ActionEvent event) {
         hasil=0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
         hasil=1;
        int pilihan=tbvmenu.getSelectionModel().getSelectedCells().get(0).getRow();
        idmenuhasil=tbvmenu.getItems().get(pilihan).getIdmenu();        
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }
    
}
