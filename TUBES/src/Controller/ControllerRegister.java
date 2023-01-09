/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import javax.swing.JFrame;
import Database.DBConnection;
import Model.ActiveUser;
import Model.UserCommon;
import Model.Operator; 
import View.AdminGui;
import View.Login;
import dao.userDao;
import View.Register;


/**
 *
 * @author darre
 */
public class ControllerRegister {
    private Register form;
    private userDao userDB;
    
    public ControllerRegister(Register form) {
        this.form = form;
        userDB = new userDao();
    }
    
    public void RegisterUser(){
        if(ActiveUser.obj.getStatus().equals("admin")){
            Operator temp = new Operator(
                    form.getUserField().getText()
                , form.getNamaField().getText()
                , form.getHpField().getText()
                , form.getPasswordField().getText()
                , form.getEmailField().getText()
            );
            userDB.insert(temp);
            form.dispose();
            new AdminGui().setVisible(true);
        }else{
        UserCommon temp =  new UserCommon(
                form.getUserField().getText()
                , form.getNamaField().getText()
                , form.getHpField().getText()
                , form.getPasswordField().getText()
                , form.getEmailField().getText()
        );
        userDB.insert(temp);
        form.dispose();
        new Login().setVisible(true);
        }
    }
    
    public void backToLogin(){
        form.dispose();
        new Login().setVisible(true);
    }
}
