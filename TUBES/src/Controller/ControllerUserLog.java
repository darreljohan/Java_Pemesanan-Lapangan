/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.UserLog;
import View.UserPembayaran;
import dao.userDao;
import dao.transaksiDao;
import Model.ActiveUser;
import Model.User;
import Model.Lapangan;
import Model.AppConfig;
import Model.TransaksiCollection;
import Model.Transaksi;
import java.util.List;

/**
 *
 * @author darre
 */
public class ControllerUserLog {
    UserLog form;
    TransaksiCollection TransaksiList;
    List<Transaksi> arr;

    public ControllerUserLog(UserLog form) {
        this.form = form;
        setTableLog();
        form.getJudulLabel().setText("RIWAYAT TRANSAKSI - "+userDao.getObjbyId(ActiveUser.User).getNama());
    }
    
    public void setTableLog(){
        User user = userDao.getObjbyId(ActiveUser.User);
       
        arr = transaksiDao.getTransaksibyUser(user);
        TransaksiList = new TransaksiCollection(arr);
        form.getTableLog().setModel(TransaksiList.createTableModelLog());
        
    }
    
    public Transaksi getTransaksiByRow(int rowIndex){
        String[][] row = TransaksiList.createRowForLog();
        Transaksi transaksi = transaksiDao.getTransaksiById(row[rowIndex][0]);
        
        if(transaksi.getStatusPembayaran()){
            form.getBayarLunasButton().setEnabled(false);
        }else{
            TransaksiCollection.jumlahBayar = transaksi.getTotalPrice()*(100-AppConfig.persenDP)/100;
            form.getBayarLunasButton().setEnabled(true);
        }
        
        return transaksi;
    }
    
    public void LunasButton(){
        
        new UserPembayaran(getTransaksiByRow(form.getTableLog().getSelectedRow())).setVisible(true);
        form.dispose();
    }
    
}
