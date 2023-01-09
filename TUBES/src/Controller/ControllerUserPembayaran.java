/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.ActiveUser;
import Model.AppConfig;
import View.UserBook;
import View.UserPembayaran;
import dao.transaksiDao;
import View.UserGui;
import Model.TransaksiCollection;
import View.OperatorGui;

/**
 *
 * @author darre
 */
public class ControllerUserPembayaran {
    UserPembayaran form;
    transaksiDao transaksiDB;
    

    public ControllerUserPembayaran(UserPembayaran form) {
        this.form = form;
        this.transaksiDB = new transaksiDao();
        form.getTotalPembayaranField().setText("Total yang dibayar : Rp "+TransaksiCollection.jumlahBayar);
    }
    
    public void verifButton(){
        if(TransaksiCollection.jumlahBayar == form.getTransaksi().getTotalPrice()){
            form.getTransaksi().setStatusPembayaran(Boolean.TRUE);
        }
        if(TransaksiCollection.jumlahBayar == form.getTransaksi().getTotalPrice()*(100-AppConfig.persenDP)/100){
            form.getTransaksi().setStatusPembayaran(Boolean.TRUE);
            transaksiDB.update(form.getTransaksi());
        }else{
            transaksiDB.insert(form.getTransaksi());
        }
        if(ActiveUser.obj.equals("operator")){
            form.dispose();
            new OperatorGui().setVisible(true);
        }else{
        form.dispose();
        new UserGui().setVisible(true);
        }
    }
    
    public void kembaliButton(){
        if(ActiveUser.obj.equals("operator")){
            form.dispose();
            new OperatorGui().setVisible(true);
        }else{
        form.dispose();
        new UserGui().setVisible(true);
        }
    }
    
    
}
