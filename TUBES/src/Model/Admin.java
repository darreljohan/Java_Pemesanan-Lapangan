/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author darre
 */
public class Admin extends User{

    public Admin(String id, String nama, String noHp, String password, String email) {
        super(id, nama, noHp, password, email);
        status = "admin";
    }
    
}
