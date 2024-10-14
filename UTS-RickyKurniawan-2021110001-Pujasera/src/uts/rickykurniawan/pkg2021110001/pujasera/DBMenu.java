/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.rickykurniawan.pkg2021110001.pujasera;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Acer
 */
public class DBMenu {

    private MenuModel dt = new MenuModel();

    public MenuModel getMenuModel() {
        return (dt);
    }

    public void setMenuModel(MenuModel m) {
        dt = m;
    }

    public ObservableList<MenuModel> Load() {
        try {
            ObservableList<MenuModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select idmenu, makanan, minuman from menu");
            int i = 1;
            while (rs.next()) {
                MenuModel d = new MenuModel();
                d.setIdmenu(rs.getString("idmenu"));
                d.setMenu(rs.getString("menu"));
                d.setHarga(rs.getInt("harga"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select count(*) as jml from siswa where idmenu = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "insert into menu (idmenu, menu, harga) values (?,?,?)");
            con.preparedStatement.setString(1, getMenuModel().getIdmenu());
            con.preparedStatement.setString(2, getMenuModel().getMenu());
            con.preparedStatement.setInt(3, getMenuModel().getHarga());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "update menu set menu = ?, harga = ?  where  idmenu = ? ; ");
            con.preparedStatement.setString(1, getMenuModel().getIdmenu());
            con.preparedStatement.setString(2, getMenuModel().getMenu());
            con.preparedStatement.setInt(3, getMenuModel().getHarga());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from menu where idmenu  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
     public ObservableList<MenuModel> LookUp(String fld,String dt){
   try{
       ObservableList<MenuModel> tableData=FXCollections.observableArrayList();
       Koneksi con=new Koneksi();
       con.bukaKoneksi();
       con.statement = con.dbKoneksi.createStatement();
       ResultSet rs = con.statement.executeQuery("Select NPM, Nama, Alamat from siswa where "+fld+" like '%"+dt+"%'");
       int i=1;
       while (rs.next()){
                MenuModel d=new MenuModel();
                d.setIdmenu(rs.getString("idmenu"));
                d.setMenu(rs.getString("menu"));
                d.setHarga(rs.getInt("harga"));
                tableData.add(d);
                i++;
       }
       con.tutupKoneksi();
       return tableData;
   } catch (SQLException ex){       ex.printStackTrace();       return null;      }
}
}
