
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
public class DBRestoran {

    private RestoModel dt = new RestoModel();

    public RestoModel getRestomodel() {
        return (dt);
    }

    public void setRestoModel(RestoModel s) {
        dt = s;
    }

    public ObservableList<RestoModel> Load() {
        try {
            ObservableList<RestoModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select id_restoran,menu, harga");
            int i = 1;
            while (rs.next()) {
                RestoModel d = new RestoModel();
                d.setId_restoran(rs.getInt("id_restoran"));
                d.setMenu(rs.getString("menu"));
                d.setHarga(rs.getInt("harga"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String menu) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select count(*) as jml from restoran where id_restoran = '" + menu + "'");
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
                    "insert into restoran (id_restoran, menu,harga) values (?,?,?)");
            con.preparedStatement.setInt(1, getRestomodel().getId_restoran());
            con.preparedStatement.setString(2, getRestomodel().getMenu());
            con.preparedStatement.setInt(3, getRestomodel().getHarga());
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

    public boolean delete(int id_restoran) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from restoran where id_restoran = ? ");
            con.preparedStatement.setInt(1, id_restoran);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
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
                    "update restoran set id_restoran= ?, menu = ?, harga=?  where  id_restoran= ? ; ");
            con.preparedStatement.setInt(1, getRestomodel().getId_restoran());
            con.preparedStatement.setString(2, getRestomodel().getMenu());
            con.preparedStatement.setInt(3, getRestomodel().getHarga());
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
}
