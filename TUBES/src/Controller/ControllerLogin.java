/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.*;
import javax.swing.JFrame;
import dao.userDao;
import Model.*;
import javax.swing.JLabel;
import Model.ActiveUser;


/**
 *
 * @author darre
 */
public class ControllerLogin {
    private Login form;
    private userDao userDB;
    
    public ControllerLogin(Login form) {
        this.form = form;
        userDB = new userDao();
    }
    
    public void RegistButton(){
        //Button register login.java direct ke Register.java
        form.dispose();
        new Register().setVisible(true);
        
    }
    
    public void LoginButton(){
        User temp = userDB.getObj(form.getUserField().getText());
        if (temp != null && temp.getPassword().equals(form.getPasswordField().getText())){
            if (temp.getStatus().equals("user")){
                new UserGui().setVisible(true);
                form.dispose();
                ActiveUser.User = temp.getId();
                ActiveUser.obj = temp;
            }else if(temp.getStatus().equals("operator")){
                new OperatorGui().setVisible(true);
                form.dispose();
                ActiveUser.User = temp.getId();
                ActiveUser.obj = temp;
            }else if(temp.getStatus().equals("admin")){
                new AdminGui().setVisible(true);
                form.dispose();
            }
        }else if(temp!=null){
            form.getAlert().setText("Username dan Password tidak sesuai");
            
        }else{
            form.getAlert().setText("Username tidak ditemukan");
        }
    }

    
    
}
