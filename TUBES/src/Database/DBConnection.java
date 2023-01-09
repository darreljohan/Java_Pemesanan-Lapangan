/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.DriverManager;

/**
 *
 * @author darre
 */
public class DBConnection {
    static java.sql.Connection conn = null;
    public static java.sql.Connection connect(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pbotubes","root","");
            
        }
        catch (Exception E){
            System.out.print("GWAMASOK");
        }
        return conn;
    }
}
