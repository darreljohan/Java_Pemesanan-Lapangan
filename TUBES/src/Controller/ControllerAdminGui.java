/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.AdminGui;
import View.Register;

/**
 *
 * @author darre
 */
public class ControllerAdminGui {
    private AdminGui form;
    
    public ControllerAdminGui(AdminGui form) {
        this.form = form;
    }
    
    public void RegistrasiOperatorButton(){
        form.dispose();
        new Register().setVisible(true);
    }
    
   
    
}
