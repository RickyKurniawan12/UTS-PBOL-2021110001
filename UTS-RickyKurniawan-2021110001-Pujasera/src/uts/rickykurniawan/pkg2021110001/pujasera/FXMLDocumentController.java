/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Acer
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnawal;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnhapus;
    @FXML
    private TableView<MenuModel> tbvmenu;
    @FXML
    private Button button;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           showdata();
    }    
     public void showdata(){    
       ObservableList<MenuModel> data=FXML_mainmenuController.dtmenu.Load();
        if(data!=null){            
            tbvmenu.getColumns().clear();
            tbvmenu.getItems().clear();
            TableColumn col=new TableColumn("idmenu");
            col.setCellValueFactory(new PropertyValueFactory<MenuModel, String>("idmenu"));
            tbvmenu.getColumns().addAll(col);
            col=new TableColumn("makanan");
            col.setCellValueFactory(new PropertyValueFactory<MenuModel, String>("makanan"));
            tbvmenu.getColumns().addAll(col);
            col=new TableColumn("minuman");
            col.setCellValueFactory(new PropertyValueFactory<MenuModel, String>("minuman"));
            tbvmenu.getColumns().addAll(col);
            tbvmenu.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmenu.getScene().getWindow().hide();
        }   
     }

    @FXML
    private void awalklik(ActionEvent event) {
          tbvmenu.getSelectionModel().selectFirst();        tbvmenu.requestFocus();  
    }

    @FXML
    private void tambahklik(ActionEvent event) {
           try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_inputmenu.fxml"));    
        Parent root = (Parent)loader.load();        Scene scene = new Scene(root);        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        stg.setIconified(false);        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();        awalklik(event);
        
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        MenuModel s= new MenuModel();
        s=tbvmenu.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_inputmenu.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_inputmenuController isidt=(FXML_inputmenuController)loader.getController();
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
    private void hapusklik(ActionEvent event) {
          MenuModel s= new MenuModel();       s=tbvmenu.getSelectionModel().getSelectedItem();
       Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
       a.showAndWait();
       if(a.getResult()==ButtonType.YES){
           if(FXML_mainmenuController.dtmenu.delete(s.getIdmenu())){
               Alert b=new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           awalklik(event);       }           
    }
}
