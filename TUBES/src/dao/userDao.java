/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.DBConnection;
import Model.User;
import java.util.List;
import Model.*;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darre
 */
public class userDao implements DaoInterface<User> {
    public static List <User> listUser;
    
    @Override
    public void insert(User obj){
        String sql; 
        sql = "INSERT INTO user (user_id, nama, password, email, no_hp, status, jumlah_pesanan) VALUES (?, ?, ?, ?, ?, ?, ?)";
         try (PreparedStatement statement = DBConnection.connect().prepareStatement(sql)) {
            statement.setString(1, obj.getId());
            statement.setString(2, obj.getNama());
            statement.setString(3, obj.getPassword());
            statement.setString(4, obj.getEmail());
            statement.setString(5, obj.getNoHp());
            statement.setString(6, obj.getStatus());
            statement.setInt(7, 0);
            
            statement.executeUpdate();
            System.out.print("masok");
            statement.close();
            
            } catch (SQLException e) {
                Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, e);
                System.out.print("maswok");
        }

    }
    
    @Override
    public void update(User obj){
        
    }
    
    @Override
    public void delete(int id){
        
    }
    
    @Override
    public User getObj(String primaryKey){
        User objModel = null;
        Statement statement;
        String sql = "SELECT * FROM user WHERE user_id= '"+ primaryKey+"'";
        try {
            statement = DBConnection.connect().createStatement();
            
            try (ResultSet result = statement.executeQuery(sql)) {
                
                while (result.next()) { // kalau ada 
                    String id = result.getString("user_id");
                    String nama =  result.getString("nama");
                    String password =  result.getString("password");
                    String email =  result.getString("email");
                    String no_hp =  result.getString("no_hp");
                    String status = result.getString("status");
                    
                   
                    objModel = switch (status) {
                        case "user" -> new UserCommon(id, nama, no_hp, password, email);
                        case "operator" -> new Operator(id, nama, no_hp, password, email);
                        case "admin"->new Admin(id, nama, no_hp, password, email);
                        default -> null;
                    };
                    
                }
                statement.close();
                
            }
            return objModel;
        } catch (SQLException e) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        
    }
    
    @Override
    public List<User> getAllObj(){
        List<User> arr = new ArrayList<User>();
        Statement statement;
        try {
            statement = DBConnection.connect().createStatement();
            
            ResultSet result = statement.executeQuery("SELECT * FROM user");
            
            while (result.next()) {  // selama masih ada datanya 
                
               String id = result.getString("user_id");
               String nama =  result.getString("nama");
               String password =  result.getString("password");
               String email =  result.getString("email");
               String no_hp =  result.getString("no_hp");
               String status = result.getString("status");
               
               User temp = switch (status) {
                        case "user" -> new UserCommon(id, nama, no_hp, password, email);
                        case "operator" -> new Operator(id, nama, no_hp, password, email);
                        default -> null;
                    };
               
               arr.add(temp);
            }
            statement.close();
            result.close();

            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(userDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
      
    }
    
    public static User getObjbyId(String idUser){
        Statement statement;
        User objModel = null;
        try{
            statement = DBConnection.connect().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user WHERE user_id = '"+idUser+"'");
            while(result.next()){
               String id = result.getString("user_id");
               String nama =  result.getString("nama");
               String password =  result.getString("password");
               String email =  result.getString("email");
               String no_hp =  result.getString("no_hp");
               String status = result.getString("status");
                    
                   
               objModel = switch (status) {
                        case "user" -> new UserCommon(id, nama, no_hp, password, email);
                        case "operator" -> new Operator(id, nama, no_hp, password, email);
                        default -> null;
                    };
            }
            statement.close();
            return objModel;
        }catch(Exception E){
            System.out.println("Error in getObjByNama/userDao");
        }
        return objModel;
        
    }
}
