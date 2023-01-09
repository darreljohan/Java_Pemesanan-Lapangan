/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.ActiveUser;
import Model.LapanganCollection;
import View.UserBook;
import View.UserPembayaran;
import dao.lapanganDao;
import dao.transaksiDao;
import javax.swing.DefaultComboBoxModel;
import Model.Transaksi;
import Model.Lapangan;
import Model.TransaksiCollection;
import View.OperatorGui;
import View.UserGui;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author darre
 */
public class ControllerUserBook {
    private UserBook form;
    private lapanganDao lapanganDB;
    private LapanganCollection lapanganList;
    private transaksiDao transaksiDB;
    private TransaksiCollection transaksiList;
    private int hargaTotal;

    public ControllerUserBook(UserBook form) {
        this.form = form;
        this.lapanganDB = new lapanganDao();
        this.lapanganList  =  new LapanganCollection(lapanganDB.getAllObj());
        this.transaksiDB = new transaksiDao();

    }
    
    public void getAllField(){
        
    }
    
    public DefaultComboBoxModel menuLapangan(){
        return lapanganList.tablemodel();
    }
    
    public void menuJamMulai(){
        transaksiList = new TransaksiCollection(transaksiDB.getObjDate(getCalendarField()));
        if(ActiveUser.obj.getStatus().equals("operator")){
            transaksiList.getPembayaran().add("Tunai");
        }
        Lapangan objLapangan = lapanganList.getObjByNama(getLapanganField());
        form.getJamMulaiField().setModel( transaksiList.ListJamBuka(objLapangan.getIdLapangan(), getCalendarField()));
    }
    
    public void menuJamSelesai(){
        Date jamMulai = (Date)form.getJamMulaiField().getSelectedItem();
        form.getJamSelesaiField().setEnabled(true);
        form.getJamSelesaiField().setModel(transaksiList.ListJamSelesai(jamMulai));
        form.getJamSelesaiField().setModel( transaksiList.ListJamSelesai(jamMulai));
    }
    
    public String getLapanganField(){
        return (String)form.getLapanganField().getSelectedItem();
    }
    
    public Date getCalendarField(){
        return form.getCalendarField().getDate();
    }
    
    public void updateHargaPesan(){
        Date date1 = (Date)form.getJamMulaiField().getSelectedItem();
        Date date2 = (Date)form.getJamSelesaiField().getSelectedItem();
        
        long range = (date2.getTime()-date1.getTime())/3600000;
        int hargaPesan = (int)range * lapanganList.getObjByNama((String)form.getLapanganField().getSelectedItem()).getHargaJam();
        float uangMuka = hargaPesan*20/100;
        TransaksiCollection.jumlahBayar = hargaPesan;
        hargaTotal = hargaPesan;
        
        form.getHargaPesanField().setText("Rp"+hargaPesan);
        form.getUangMukaField().setText("Rp"+uangMuka);
        form.getUangMukaButton().setEnabled(true);
    }
    
    public void setMetodePembayaran(){
        form.getPembayaranField().setModel(transaksiList.listPembayaran());
    }
    
    public String getMetodePembayaran(){
        System.out.println(form.getPembayaranField().getSelectedItem().toString());
        return form.getPembayaranField().getSelectedItem().toString();
    }
    
    public void kembaliGUI(){
        if(ActiveUser.obj.equals("operator")){
            form.dispose();
            new OperatorGui().setVisible(true);
        }else{
        form.dispose();
        new UserGui().setVisible(true);
        }
        
    }
    
    /*public boolean checkAllField(){
        System.out.println("CHECK MASUK");
        if(form.getLapanganField().getSelectedItem() == null){
            form.getAlert().setText("Lapangan Field wajib diisi");
            return false;
        }
        if(form.getCalendarField().getDate() == null){
            form.getAlert().setText("Calender Field wajib diisi");
            return false;
        }
        if(form.getJamMulaiField().getSelectedItem()== null){
            form.getAlert().setText("Jam Mulai wajib diisi");
            return false;
        }
        if(form.getJamSelesaiField().getSelectedItem().equals("Item 1")){
            form.getAlert().setText("Jam Selesai wajib diisi");
            return false;
        }
        if(form.getPembayaranField().getSelectedItem()==null){
            form.getAlert().setText("Pembayaran wajib diisi");
            return false;
        }
        return true;
    }*/
    
    public void uangMukaButton(){
        
        //if(checkAllField()){
            Transaksi temp = new Transaksi();
          
            temp.setIdLapangan(lapanganList.getObjByNama((String)form.getLapanganField().getSelectedItem()).getIdLapangan());
            
            Date[] date = new Date[2];
            String jamMulai = form.getJamMulaiField().getSelectedItem().toString();
            String jamSelesai = form.getJamSelesaiField().getSelectedItem().toString();

            try{
            date[0] = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.getDefault()).parse(jamMulai);
            date[1] = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.getDefault()).parse(jamSelesai);
            
            }catch(Exception e){
                //System.out.println("Parse gagal uangMukaButton/ControllerUserBook");
                System.out.println(e.getMessage());
            }
            temp.setBookedJadwal(date);

            
            temp.setIdTransaksi(transaksiDB.getLastId()+1+"");
            temp.setTotalPrice(hargaTotal);
            temp.setUserId(ActiveUser.User);
            temp.setPembayaran(form.getPembayaranField().getSelectedItem().toString());
            TransaksiCollection.jumlahBayar = transaksiList.jumlahBayar*Model.AppConfig.getPersenDP()/100;
            
            
            form.dispose();
            new UserPembayaran(temp).setVisible(true);
            
        //}
    }
    
    public void LunasButton(){
        Transaksi temp = new Transaksi();
          
        temp.setIdLapangan(lapanganList.getObjByNama((String)form.getLapanganField().getSelectedItem()).getIdLapangan());
            
            Date[] date = new Date[2];
            String jamMulai = form.getJamMulaiField().getSelectedItem().toString();
            String jamSelesai = form.getJamSelesaiField().getSelectedItem().toString();

            try{
            date[0] = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.getDefault()).parse(jamMulai);
            date[1] = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.getDefault()).parse(jamSelesai);
            
            }catch(Exception e){
                //System.out.println("Parse gagal uangMukaButton/ControllerUserBook");
                System.out.println(e.getMessage());
            }
            temp.setBookedJadwal(date);

            
            temp.setIdTransaksi(transaksiDB.getLastId()+1+"");
            temp.setTotalPrice(hargaTotal);
            temp.setUserId(ActiveUser.User);
            temp.setPembayaran(form.getPembayaranField().getSelectedItem().toString());
            
            if(temp.getPembayaran().equals("Tunai")){
                temp.setStatusPembayaran(true);
                transaksiDB.insert(temp);
                form.dispose();
                new OperatorGui().setVisible(true);
            }else{
            form.dispose();
            new UserPembayaran(temp).setVisible(true);
           }
    }
    
    public void checkPembayaran(){
        if(form.getPembayaranField().getSelectedItem().equals("Tunai")){
            form.getUangMukaButton().setEnabled(false);
        }else{
            form.getUangMukaButton().setEnabled(true);
        }
    }
}