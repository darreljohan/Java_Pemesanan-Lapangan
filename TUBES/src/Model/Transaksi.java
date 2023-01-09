/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author darre
 */
public class Transaksi implements Comparable<Transaksi>{
    protected String idTransaksi;
    protected String userId;
    protected String idLapangan;
    protected Date[] bookedJadwal;
    protected int totalPrice;
    protected String pembayaran;
    protected boolean statusPembayaran = false;

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(String idLapangan) {
        this.idLapangan = idLapangan;
    }

    public Date[] getBookedJadwal() {
        return bookedJadwal;
    }

    public void setBookedJadwal(Date[] bookedJadwal) {
        this.bookedJadwal = bookedJadwal;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

     public int compareTo(Transaksi o){
         return getBookedJadwal()[0].compareTo(o.getBookedJadwal()[0]);
     }   

    public Boolean getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(Boolean statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
    
       
}
