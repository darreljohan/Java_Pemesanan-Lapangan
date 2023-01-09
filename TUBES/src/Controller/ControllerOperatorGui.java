/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AppConfig;
import Model.LapanganCollection;
import Model.Transaksi;
import Model.TransaksiCollection;
import Model.UsersCollection;
import View.OperatorGui;
import View.UserBook;
import dao.lapanganDao;
import dao.transaksiDao;
import dao.userDao;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author darre
 */
public class ControllerOperatorGui {
    private OperatorGui form;
    private transaksiDao transaksiDB;
    private TransaksiCollection transaksiList;
    private lapanganDao lapanganDB;
    private LapanganCollection lapanganList;
    private UsersCollection userList;
    private userDao userDB;

    public ControllerOperatorGui(OperatorGui form) {
        this.form = form;
        this.transaksiDB = new transaksiDao();
        this.lapanganDB = new lapanganDao();
        this.userDB = new userDao();
        this.lapanganList  =  new LapanganCollection(lapanganDB.getAllObj());
        this.userList = new UsersCollection(userDB.getAllObj());

    }
    
    public void getMenuLapangan(){ //belum selesai
        form.getDateChooserView().setDateFormatString("yyyy-MM-dd k:m:s");
        Date ddte = form.getDateChooserView().getDate();
        String lapangan = (String)form.getLapanganListView().getSelectedItem();
        lapangan = lapanganList.getObjByNama(lapangan).getIdLapangan();

        if (ddte != null ){
            List<Transaksi> transaksi = transaksiDB.getObjDate(ddte);
            Collections.sort(transaksi);
            TransaksiCollection trCollection = new TransaksiCollection(transaksi);
            trCollection.filterLapangan( lapangan);
            transaksiList = trCollection;
            DefaultTableModel modelTabel = trCollection.createTabelModelOperator();
            form.getTabelUserGui().setModel(modelTabel);
            
        }else{
            System.out.println("Gagal Masuk");
        } 
    }
    
    public void getMenuCalendar(){
        String lapangan = (String)form.getLapanganListView().getSelectedItem();
        lapangan = lapanganList.getObjByNama(lapangan).getIdLapangan();
        form.getDateChooserView().setDateFormatString("yyyy-MM-dd k:m:s");
        Date ddte = form.getDateChooserView().getDate();


        if (lapangan != null ){
            List<Transaksi> transaksi = transaksiDB.getObjDate(ddte);
            Collections.sort(transaksi);
            TransaksiCollection trCollection = new TransaksiCollection(transaksi);
            trCollection.filterLapangan(lapangan);
            transaksiList=trCollection;
            DefaultTableModel modelTabel = trCollection.createTabelModelOperator();
            form.getTabelUserGui().setModel(modelTabel);
            
        }else{
            System.out.println("Gagal Masuk - Menu Calendar");
        } 
    }
    
    public DefaultComboBoxModel menuLapangan(){
        return lapanganList.tablemodel();
    }
    
    public void setTable(){
        form.getTabelUserGui().setModel(transaksiList.createTabelModelOperator());
    }
    
    public void pesanButton(){
        new UserBook().setVisible(true);
        form.dispose();
    }
    
    public void lunasButton(){
        Transaksi temp = getTransaksiByRow(form.getTabelUserGui().getSelectedRow());
        temp.setStatusPembayaran(Boolean.TRUE);
        transaksiDB.update(temp);
        form.dispose();
        new OperatorGui().setVisible(true);
    }
    
    public Transaksi getTransaksiByRow(int rowIndex){
        String[][] row = transaksiList.createRowForLog();
        Transaksi transaksi = transaksiDao.getTransaksiById(row[rowIndex][0]);
        
        if(transaksi.getStatusPembayaran()){
            form.getBayarButton().setEnabled(false);
        }else{
            TransaksiCollection.jumlahBayar = transaksi.getTotalPrice()*(100-AppConfig.persenDP)/100;
            form.getBayarButton().setEnabled(true);
        }
        return transaksi;
    }

}
