/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import Model.User;

/**
 *
 * @author darre
 */
public class UsersCollection {
    private static List<User> arr;

    public UsersCollection(List<User> arr) {
        this.arr = arr;
    }
    
    public static User getObjById(String id){
        for(int i = 0;i<arr.size();i++){
            if(arr.get(i).getId().equals(id)){
                return arr.get(i);
            }
        }
        return null;
    }
    
    
}
