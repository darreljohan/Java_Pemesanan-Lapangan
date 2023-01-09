/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.DBConnection;
import Model.Lapangan;
import Model.Transaksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darre
 */
public class lapanganDao implements DaoInterface<Lapangan>{
    public void insert(Lapangan obj){
        
    }
    
    public void update(Lapangan obj){
        
    }
    
    public void delete(int id){
        
    }
    
    public Lapangan getObj(String primaryKey){
        return null;
    }
    
    public List<Lapangan> getAllObj(){
        List<Lapangan> arr = new ArrayList<Lapangan>();
        Statement statement;
        try {
            statement = DBConnection.connect().createStatement();
            
            ResultSet result = statement.executeQuery("SELECT * FROM lapangan");
            
            while (result.next()) {  // selama masih ada datanya 
                
                Lapangan lapangan  = new Lapangan();
                // salin ke class kotak
                lapangan.setIdLapangan(result.getString("id_lapangan"));
                lapangan.setNamaLapangan(result.getString("nama_lapangan"));
                lapangan.setPanjangLapangan(result.getInt("panjang_lapangan"));

                lapangan.setLebarLapangan(result.getInt("lebar_lapangan"));
                lapangan.setJenisLapangan(result.getString("jenis_lapangan"));
                lapangan.setHargaJam(result.getInt("harga_jam"));
                // masukkan kotak ke dalam list
                arr.add(lapangan);
            }
            statement.close();
            result.close();
            // connection tidak perlu di close 
            
            // menghasilkan list kotak yang berisikan tabel kotak
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }    
}
