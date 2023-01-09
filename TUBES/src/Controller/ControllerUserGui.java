/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.UserGui;
import View.*;
import Model.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import dao.transaksiDao;
import dao.lapanganDao;
import java.util.Date;
import javax.swing.JTable;
import java.util.List;
import java.util.ArrayList;
import Model.TransaksiCollection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author darre
 */
public class ControllerUserGui {
    private UserGui form;
    private transaksiDao transaksiDB;
    private lapanganDao lapanganDB;
    private LapanganCollection lapanganList;

    public ControllerUserGui(UserGui form) {
        this.form = form;
        this.transaksiDB = new transaksiDao();
        this.lapanganDB = new lapanganDao();
        this.lapanganList  =  new LapanganCollection(lapanganDB.getAllObj());
        
    }
    
    public void PesanLapanganButton(){
        new UserBook().setVisible(true);
        form.dispose();
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
            
            DefaultTableModel modelTabel = new DefaultTableModel(trCollection.createRowForJadwal(), trCollection.createColumnForJadwal());
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
            DefaultTableModel modelTabel = new DefaultTableModel(trCollection.createRowForJadwal(), trCollection.createColumnForJadwal());
            form.getTabelUserGui().setModel(modelTabel);
            
        }else{
            System.out.println("Gagal Masuk - Menu Calendar");
        } 
    }
    
    public DefaultComboBoxModel menuLapangan(){
        return lapanganList.tablemodel();
    }
    
    public void userLogButton(){
        new UserLog().setVisible(true);
        form.dispose();
    }
}
