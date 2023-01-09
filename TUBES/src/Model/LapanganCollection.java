/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Lapangan;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author darre
 */
public class LapanganCollection {
    public static List<Lapangan> arr;
   
    public LapanganCollection(List<Lapangan> arr) {
        this.arr = arr;
    }
    
    public DefaultComboBoxModel tablemodel(){
        String[] arrNama = new String[arr.size()];
        for(int i = 0; i<arr.size();i++){
            arrNama[i]=arr.get(i).namaLapangan;
        }
        return new DefaultComboBoxModel<>(arrNama);
    }
    
    public static Lapangan getObjByNama(String nama){
        for(int i = 0; i<arr.size(); i++){
            if(nama.equals(arr.get(i).getNamaLapangan())){
                return arr.get(i);
            }
        }
        return null;
    }
    
    public static Lapangan getObjById(String id){
        for(int i = 0; i<arr.size(); i++){
            if(id.equals(arr.get(i).getIdLapangan())){
                return arr.get(i);
            }
        }
        return null;
    }

    public List<Lapangan> getArr() {
        return arr;
    }

    public void setArr(List<Lapangan> arr) {
        this.arr = arr;
    }



    
    
    
    
    
}
