package uts.rickykurniawan.pkg2021110001.pujasera;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Acer
 */
public class Koneksi {
        public Connection dbKoneksi;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public Koneksi() {        this.dbKoneksi = null;    }
    public void bukaKoneksi() {
        try {    Class.forName("com.mysql.jdbc.Driver");
            dbKoneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/pujasera?user=root");
        } catch (Exception e) {
            e.printStackTrace();}}
    public void tutupKoneksi() {
        try { if (statement != null) {statement.close();}
            if (preparedStatement != null) {preparedStatement.close();}
            if (dbKoneksi != null) {dbKoneksi.close();}
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());        }    }
}
