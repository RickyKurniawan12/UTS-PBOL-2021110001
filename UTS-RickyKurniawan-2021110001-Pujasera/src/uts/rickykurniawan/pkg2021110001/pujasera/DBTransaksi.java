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
public class DBTransaksi {

    private TransaksiModel dt = new TransaksiModel();

    public TransaksiModel getTransaksiModel() {
        return (dt);
    }

    public void setTransaksiModel(TransaksiModel s) {
        dt = s;
    }

    public ObservableList<TransaksiModel> Load() {
        try {
            ObservableList<TransaksiModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select i.id_transaksi, tanggal, m.makanan, minuman, j.jumlah "
                    + "from Transaksi t, menu m, restoran r "
                    + "where i.id_transaksi = j.makanan and r.restoran = j.harga");
            int i = 1;
            while (rs.next()) {
                TransaksiModel d = new TransaksiModel();
                d.setId_transaksi(rs.getString("id_transaksi"));
                d.setTanggal(rs.getDate("tanggal"));
                d.setMakanan(rs.getString("makanan"));
                d.setMakanan(rs.getString("minuman"));
                d.setJumlah(rs.getInt("jumlah"));
                d.setHarga(rs.getInt("harga"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String kode, String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from transaksi where id_transaksi = '" + kode
                    + "' and harga='" + nomor + "'");
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
            "insert into transaksi (id_transaksi,tanggal,makanan,minuman,jumlah) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getTransaksiModel().getId_transaksi());
            con.preparedStatement.setDate(2, getTransaksiModel().getTanggal());
            con.preparedStatement.setString(3, getTransaksiModel().getMakanan());
            con.preparedStatement.setString(4, getTransaksiModel().getMinuman());
            con.preparedStatement.setInt(5, getTransaksiModel().getJumlah());
            con.preparedStatement.setInt(6, getTransaksiModel().getHarga());
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

    public boolean delete(String nomor, String kode) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from nilai where NPM  = ? and kodemk = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
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
                    "update transaksi set transaksi = ?, tanggal = ?, makanan = ?,  minuman = ?, harga = ? , harga = ? where  id_transaksi = ? and tanggal = ? ");
            con.preparedStatement.setString(1, getTransaksiModel().getId_transaksi());
            con.preparedStatement.setDate(2, getTransaksiModel().getTanggal());
            con.preparedStatement.setString(3, getTransaksiModel().getMakanan());
            con.preparedStatement.setString(4, getTransaksiModel().getMinuman());
            con.preparedStatement.setInt(5, getTransaksiModel().getHarga());
            con.preparedStatement.setInt(6, getTransaksiModel().getJumlah());
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
