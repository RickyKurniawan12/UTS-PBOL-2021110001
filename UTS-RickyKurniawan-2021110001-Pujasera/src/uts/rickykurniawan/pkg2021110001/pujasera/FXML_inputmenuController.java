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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXML_inputmenuController implements Initializable {

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtharga;
    @FXML
    private TextField txtmenu;
    @FXML
    private TextField txtidmenu;
      boolean editdata=false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void keluarklik(ActionEvent event) {
    }

    @FXML
    private void batalklik(ActionEvent event) {
    }

    @FXML
    private void simpanklik(ActionEvent event) {
    
    MenuModel d=new MenuModel();
        d.setIdmenu(txtidmenu.getText());
        d.setMenu(txtmenu.getText());
        d.setHarga(Integer.parseInt(txtharga.getText()));
        FXML_mainmenuController.dtmenu.setMenuModel(d);
        if(editdata){
            if(FXML_mainmenuController.dtmenu.update()){
              Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtidmenu.setEditable(true);        batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXML_mainmenuController.dtmenu.validasi(d.getIdmenu())<=0){
            if(FXML_mainmenuController.dtmenu.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidmenu.requestFocus();
        }
    }
     public void execute(MenuModel d) {
    if (d != null && !d.getIdmenu().isEmpty()) {
        editdata = true;
        txtidmenu.setText(d.getIdmenu());
        txtmenu.setText(d.getMenu()); // Corrected to set the menu name
        txtharga.setText(String.valueOf(d.getHarga())); // Convert Integer to String
        txtidmenu.setEditable(false);
        txtmenu.requestFocus();
    }
}
}
