/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author darre
 */
public class TransaksiCollection {
    protected List<String> pembayaran;
    protected List<Transaksi> transaksi;
    protected List<Date> listJamBuka;  
    public static int jumlahBayar;

    public TransaksiCollection(List<Transaksi> transaksi) {
        this.transaksi = transaksi;
        pembayaran = new ArrayList<String>();
        pembayaran.add("BCA");
        pembayaran.add("QRIS");
        pembayaran.add("MANDIRI");
    }
   
    public String[][] createRowForJadwal(){
        String[][] row = new String[transaksi.size()][2];
        Calendar calender = Calendar.getInstance();
        
        for(int i=0; i<transaksi.size();i++){
            calender.setTime(transaksi.get(i).bookedJadwal[0]);
            row[i][0]=calender.get(Calendar.HOUR_OF_DAY)+":"+calender.get(Calendar.MINUTE)+"";
            calender.setTime(transaksi.get(i).bookedJadwal[1]);
            row[i][1]=calender.get(Calendar.HOUR_OF_DAY)+":"+calender.get(Calendar.MINUTE)+"";
            
        }
        return row;
    }
    
    public String[] createColumnForJadwal(){
        String[] column = new String[2];
        column[0] = "jam pesan";
        column[1] = "jam selesai";
        return column;
    }
    
    public void filterLapangan(String idLapangan){

        for(int i = 0; i<transaksi.size(); i++){
 
            if(!transaksi.get(i).getIdLapangan().equals(idLapangan)){

                transaksi.remove(i);
                i--;
            }
        }
    }
    
    public DefaultComboBoxModel ListJamBuka(String idLapangan, Date date){  

        ArrayList<Date> jadwal = new ArrayList<>();
        
        String pattern = "dd MMMM yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        
        int JamInput = 10;
        for (int i = 0; i<AppConfig.getJamTutup()+1-AppConfig.getJamBuka(); i++){
            
            String input = format.format(date)+" "+JamInput+":00:00";
            
            try{
                jadwal.add(new SimpleDateFormat(pattern+" HH:mm:ss").parse(input));
            }catch(Exception E){
                System.out.println("Parse Gagal: Lokasi transaksicollection/listJamBuka");
            }
            JamInput++;
            
        }
        

        listJamBuka = new ArrayList<>();
        listJamBuka.addAll(jadwal);
        
        for(int y = 0; y<jadwal.size(); y++){
        for(int i = 0; i<transaksi.size(); i++){
            if(transaksi.get(i).getIdLapangan().equals(idLapangan)){
                
                if(transaksi.get(i).getBookedJadwal()[0].before(jadwal.get(y)) && transaksi.get(i).getBookedJadwal()[1].after(jadwal.get(y)) ){
                   
                    jadwal.remove(y);
                    y--;
                    i--;
                    if(y<0){
                        y = 0;
                    }
                }else if(transaksi.get(i).getBookedJadwal()[0].getTime() == jadwal.get(y).getTime()){
                    
                    jadwal.remove(y);
                    y--;
                    i--;
                    if(y<0){
                        y = 0;
                    }
                }
            }
        }
        }
        jadwal.remove(jadwal.size()-1);
        
        return new DefaultComboBoxModel(jadwal.toArray());
    }
   
    public DefaultComboBoxModel ListJamSelesai(Date date){  
        List<Date> jadwal = new ArrayList<>();
        jadwal.addAll(listJamBuka);
        
        List<Transaksi> transaksiSorted = transaksi;
        Collections.sort(transaksiSorted);
        
        for (int y = 0; y<jadwal.size();y++){
            if(jadwal.get(y).before(date)){
                
                jadwal.remove(y);
                y--;
                if(y>0){
                    y=0;
                }
            }else if(jadwal.get(y).getTime()==date.getTime()){
                jadwal.remove(y);
                y--;
                if(y>0){
                    y=0;
                }
            }
        
        }
        
        int i =0;
        Date limiter = new Date();
        boolean condition = true;
        while(condition){
            if(i>transaksi.size()-1){
                String pattern = "dd MMMM yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                String input = format.format(date)+" "+AppConfig.getJamTutup()+":00:00";
            
                try{
                    limiter = new SimpleDateFormat(pattern+" HH:mm:ss").parse(input);
                }catch(Exception E){
                    System.out.println("Parse Gagal: Lokasi listJamSelesai/TransaksiCollection");
                }
                condition = false;
            }
            else if(transaksi.get(i).getBookedJadwal()[0].before(date)){
                i++;
            }else if(transaksi.get(i).getBookedJadwal()[0].after(date)){
                limiter = transaksi.get(i).getBookedJadwal()[0];
                condition = false;
            }
        }
        
        for(int y = 0; y<jadwal.size();y++){
            if(jadwal.get(y).after(limiter)){
                jadwal.remove(y);
                y--;
                if(y>0){
                    y=0;
                }
            }
        }
       
        return new DefaultComboBoxModel(jadwal.toArray());
    }
    
    public DefaultComboBoxModel listPembayaran(){
        return new DefaultComboBoxModel(pembayaran.toArray());
    }
    
    public String[][] createRowForLog(){
        String[][] row = new String[transaksi.size()][5];
        
        for(int i = 0; i<transaksi.size();i++){
            row[i][0] = transaksi.get(i).getIdTransaksi();
            row[i][1] = LapanganCollection.getObjById( transaksi.get(i).getIdLapangan()).getNamaLapangan();
            row[i][2] = transaksi.get(i).getBookedJadwal()[0].toString();
            row[i][3] = transaksi.get(i).getBookedJadwal()[1].toString();
            if (transaksi.get(i).getStatusPembayaran() == true){
                row[i][4]="Lunas";
            }else{
                row[i][4]="Uang Muka";
            }
        }
        return row;
    }
    
    public String[] createColumnForLog(){
        String[] column = new String[5];
        column[0] = "ID Transaksi";
        column[1] = "Lapangan";
        column[2] = "Jam Mulai";
        column[3] = "Jam Selesai";
        column[4] = "Status Bayar";
        return column;
    }
    
    public DefaultTableModel createTableModelLog(){
        DefaultTableModel table = new DefaultTableModel(createRowForLog(), createColumnForLog());
        return table;
    }

    public String[][] createRowForOperator(){
        String[][] row = new String[transaksi.size()][5];
        Calendar calender = Calendar.getInstance();
        
        for(int i = 0; i<transaksi.size();i++){
            row[i][0] = transaksi.get(i).idTransaksi;
            row[i][1] = transaksi.get(i).getUserId();
            calender.setTime(transaksi.get(i).bookedJadwal[0]);
            row[i][2]=calender.get(Calendar.HOUR_OF_DAY)+":"+calender.get(Calendar.MINUTE)+"";
            calender.setTime(transaksi.get(i).bookedJadwal[1]);
            row[i][3]=calender.get(Calendar.HOUR_OF_DAY)+":"+calender.get(Calendar.MINUTE)+"";
            row[i][4] = transaksi.get(i).getStatusPembayaran().toString();
        }
        
        return row;
    }
    
    public String[] createColumnforOperator(){
        String[] column =  new String[5];
        column[0] = "id_transaksi";
        column[1] = "User Id";
        column[2] = "Jam Mulai";
        column[3]= "Jam Selesai";
        column[4]= "Status Bayar";
        return column;
    }
    
    public DefaultTableModel createTabelModelOperator(){
        DefaultTableModel table = new DefaultTableModel(createRowForOperator(), createColumnforOperator());
        return table;
    }

    public List<String> getPembayaran() {
        return pembayaran;
    }
    
    
}
