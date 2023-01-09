/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tubes;

import Database.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.*;
import dao.transaksiDao;
import Model.*;
import java.util.Date;
/**
 *
 * @author darre
 */
public class index {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here;
       
        /* Percobaan query
        Statement statement;
        try {
            statement = DBConnection.connect().createStatement();
            
            ResultSet result = statement.executeQuery("SELECT * FROM user");
            while (result.next()) {  // selama masih ada datanya 
                System.out.print(result.getInt(1));
            }
            
            statement.close();
            result.close();
            // connection tidak perlu di close 
            
            // menghasilkan list kotak yang berisikan tabel kotak
            
        } catch (SQLException ex) {
            System.out.println("maswokk");
            
        }
        */
       new Login().setVisible(true);

        
       /* transaksiDao dao = new transaksiDao();
        Date date = new Date(2023, 1, 1);
        List<Transaksi> arr = dao.getObjDate(date);
        System.out.print(arr.get(0).getUserId());*/
    }
    
}
