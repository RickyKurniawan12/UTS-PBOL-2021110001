/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts.rickykurniawan.pkg2021110001.pujasera;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXML_displayrestoranController implements Initializable {

    @FXML
    private Button btnhapus;
    @FXML
    private Button btnubah;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;
    @FXML
    private Button button;
    @FXML
    private TableView<RestoModel> tbvresto;
    
    public static DBRestoran dtrestoran = new DBRestoran();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    showdata();
    }
        public void showdata(){    
       ObservableList<RestoModel> data=FXML_mainmenuController.dtresto.Load();
        if(data!=null){            
            tbvresto.getColumns().clear();
            tbvresto.getItems().clear();
            TableColumn col=new TableColumn("id_restoran");
            col.setCellValueFactory(new PropertyValueFactory<RestoModel, String>("id_restoran"));
            tbvresto.getColumns().addAll(col);
            col=new TableColumn("menu");
            col.setCellValueFactory(new PropertyValueFactory<RestoModel, String>("menu"));
            tbvresto.getColumns().addAll(col);
            col=new TableColumn("harga");
            col.setCellValueFactory(new PropertyValueFactory<RestoModel, String>("harga"));
            tbvresto.getColumns().addAll(col);
            tbvresto.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvresto.getScene().getWindow().hide();
        }                
    }    

    @FXML
    private void hapusklik(ActionEvent event) {
          RestoModel s= new RestoModel();       s=tbvresto.getSelectionModel().getSelectedItem();
       Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",               ButtonType.YES,ButtonType.NO);
       a.showAndWait();
       if(a.getResult()==ButtonType.YES){
           if(FXML_mainmenuController.dtresto.delete(s.getId_restoran())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,                  "Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,                   "Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           awalklik(event);       }           
    
    }

    @FXML
    private void ubahklik(ActionEvent event) {
         RestoModel s= new RestoModel();
        s=(RestoModel) tbvresto.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_inputresto.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_inputtransaksiController isidt=(FXML_inputtransaksiController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  awalklik(event);
    }

    @FXML
    private void tambahklik(ActionEvent event) {
               try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_inputresto.fxml"));    
        Parent root = (Parent)loader.load();        Scene scene = new Scene(root);        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        stg.setIconified(false);        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();        awalklik(event);
    }

    @FXML
    private void akhirklik(ActionEvent event) {
         tbvresto.getSelectionModel().selectBelowCell();        tbvresto.requestFocus(); 
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

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
}
