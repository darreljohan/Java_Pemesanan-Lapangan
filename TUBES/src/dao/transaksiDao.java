/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Model.Transaksi;
import Model.User;
import java.util.List;
import java.util.ArrayList;
import Model.*;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.DBConnection;
import java.util.Date;
import java.util.Calendar;
import java.time.ZoneId;
/**
 *
 * @author darre
 */
public class transaksiDao implements DaoInterface<Transaksi>{
    @Override
    public void insert(Transaksi obj){
        String sql; 
        sql = "INSERT INTO transaksi (id_transaksi, user_id, lapangan, jam_mulai, jam_selesai, total_harga, media_pembayaran, status_pembayaran) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
         try (PreparedStatement statement = DBConnection.connect().prepareStatement(sql)) {
            statement.setString(1, obj.getIdTransaksi());
            statement.setString(2, obj.getUserId());
            statement.setString(3, obj.getIdLapangan());
            
            long jadwal_buka = obj.getBookedJadwal()[0].getTime();
            java.sql.Timestamp sqlDateBuka = new java.sql.Timestamp(jadwal_buka);
            statement.setTimestamp(4, sqlDateBuka);

            
            long jadwal_tutup= obj.getBookedJadwal()[1].getTime();
            java.sql.Timestamp sqlDateTutup = new java.sql.Timestamp(jadwal_tutup);
            statement.setTimestamp(5, sqlDateTutup);
            
            statement.setInt(6, obj.getTotalPrice());
            statement.setString(7, obj.getPembayaran());
            statement.setBoolean(8, obj.getStatusPembayaran());
            statement.executeUpdate();
            statement.close();
            
            } catch (SQLException e) {
                Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void update(Transaksi obj){
        String sql = "UPDATE transaksi SET user_id=?, lapangan=?, jam_mulai=?, jam_selesai=?, total_harga=?, media_pembayaran=?, status_pembayaran=? WHERE user_id=?";
        try (PreparedStatement statement = DBConnection.connect().prepareStatement(sql)) {
            
            statement.setString(1, obj.getUserId());
            statement.setString(2, obj.getIdLapangan());
            
            long jadwal_buka = obj.getBookedJadwal()[0].getTime();
            java.sql.Timestamp sqlDateBuka = new java.sql.Timestamp(jadwal_buka);
            statement.setTimestamp(3, sqlDateBuka);

            
            long jadwal_tutup= obj.getBookedJadwal()[1].getTime();
            java.sql.Timestamp sqlDateTutup = new java.sql.Timestamp(jadwal_tutup);
            statement.setTimestamp(4, sqlDateTutup);
            
            statement.setInt(5, obj.getTotalPrice());
            statement.setString(6, obj.getPembayaran());
            statement.setString(7, "'"+obj.getStatusPembayaran().booleanValue()+"'");
            statement.setString(8, obj.getIdTransaksi());
            System.out.println("dari sql "+obj.getIdTransaksi());
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
                Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void delete(int id){
        
    }
    
    @Override
    public Transaksi getObj(String primaryKey){
        return null;
    }
    
    @Override
    public List<Transaksi> getAllObj(){
        List<Transaksi> arr = new ArrayList<Transaksi>();
        Statement statement;
        try {
            statement = DBConnection.connect().createStatement();
            
            ResultSet result = statement.executeQuery("SELECT * FROM transaksi");
            
            while (result.next()) {  // selama masih ada datanya 
                
                Transaksi transaksi  = new Transaksi();
                // salin ke class kotak
                transaksi.setIdTransaksi(result.getString("id_transaksi"));
                transaksi.setUserId(result.getString("user_id"));
                transaksi.setIdLapangan(result.getString("lapangan"));
                java.util.Date[] jadwal = new java.util.Date[2];
                jadwal[0] = result.getDate("jam_mulai");
                jadwal[1] = result.getDate("jam_selesai");
                transaksi.setBookedJadwal(jadwal);
                transaksi.setTotalPrice(result.getInt("total_harga"));
                transaksi.setPembayaran(result.getString("media_pembayaran"));
                // masukkan kotak ke dalam list
                arr.add(transaksi);
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
    
    public List<Transaksi> getObjDate(java.util.Date date){
        List<Transaksi> arr = new ArrayList<Transaksi>();
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        Statement statement;
        try{
            statement = DBConnection.connect().createStatement();
            
            int year = calender.get(Calendar.YEAR);
            int month = calender.get(Calendar.MONTH)+1;
            int day = calender.get(Calendar.DAY_OF_MONTH);
            String condition = "'"+year+"-"+month+"-"+day+" 00:00:00' AND '"+year+"-"+month+"-"+day+" 23:59:59'";
            ResultSet result = statement.executeQuery("SELECT * FROM transaksi WHERE jam_mulai BETWEEN "+condition);

            while (result.next()) {  // selama masih ada datanya 
                
                Transaksi transaksi  = new Transaksi();
                
                transaksi.setIdTransaksi(result.getString("id_transaksi"));
                transaksi.setUserId(result.getString("user_id"));
                transaksi.setIdLapangan(result.getString("lapangan"));
                
                java.util.Date[] jadwal = new java.util.Date[2];
                jadwal[0] = result.getTimestamp("jam_mulai");
                jadwal[1] = result.getTimestamp("jam_selesai");
               
                
                transaksi.setBookedJadwal(jadwal);
                transaksi.setTotalPrice(result.getInt("total_harga"));
                transaksi.setPembayaran(result.getString("media_pembayaran"));
               
                arr.add(transaksi);
            }
            statement.close();
            result.close();
            return arr;
        }catch(SQLException ex){
            Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
          
    }
    
    public int getLastId(){
        Statement statement;
        try{
             statement = DBConnection.connect().createStatement();
             ResultSet result = statement.executeQuery("SELECT id_transaksi FROM transaksi ORDER BY id_transaksi DESC LIMIT 1");
             while(result.next()){
                 return result.getInt("id_transaksi");
             }
        }catch(Exception E){
            Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, E);
            return 0;
        }
        return 0;
    }
    
    public static List<Transaksi> getTransaksibyUser(User obj){
        List<Transaksi> arr = new ArrayList<Transaksi>();
        Statement statement;
        try{
            statement = DBConnection.connect().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM transaksi WHERE user_id ='"+obj.getId()+"'");
            while(result.next()){
                Transaksi transaksi  = new Transaksi();
                
                transaksi.setIdTransaksi(result.getString("id_transaksi"));
                transaksi.setUserId(result.getString("user_id"));
                transaksi.setIdLapangan(result.getString("lapangan"));
                
                java.util.Date[] jadwal = new java.util.Date[2];
                jadwal[0] = result.getTimestamp("jam_mulai");
                jadwal[1] = result.getTimestamp("jam_selesai");
               
                
                transaksi.setBookedJadwal(jadwal);
                transaksi.setTotalPrice(result.getInt("total_harga"));
                transaksi.setPembayaran(result.getString("media_pembayaran"));
               
                arr.add(transaksi);
            }
            statement.close();
            result.close();
            return arr;
        }catch(Exception E){
            Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, E);
            return null;
        }
    }
    
    public static Transaksi getTransaksiById(String id){
        Transaksi transaksi = null;
        Statement statement;
        try{
            statement = DBConnection.connect().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM transaksi WHERE id_transaksi ="+id+"");
            if(result.next()){
                transaksi  = new Transaksi();
                
                transaksi.setIdTransaksi(result.getString("id_transaksi"));
                transaksi.setUserId(result.getString("user_id"));
                transaksi.setIdLapangan(result.getString("lapangan"));
                
                java.util.Date[] jadwal = new java.util.Date[2];
                jadwal[0] = result.getTimestamp("jam_mulai");
                jadwal[1] = result.getTimestamp("jam_selesai");
               
                
                transaksi.setBookedJadwal(jadwal);
                transaksi.setTotalPrice(result.getInt("total_harga"));
                transaksi.setPembayaran(result.getString("media_pembayaran"));
               
            }
            statement.close();
            result.close();
        }catch(Exception e){
            Logger.getLogger(transaksiDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        return transaksi;
    }
}
