/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.util.List;

public interface DaoInterface<generic> {
    public void insert(generic obj);
    public void update(generic obj);
    public void delete(int id);
    public generic getObj(String primaryKey);
    public List<generic> getAllObj();
}
/**
 *
 * @author darre
 */

