
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ransom
 * @param <T>
 */
public  interface Gestionnaire <T>
{
    // Call of Singleton 
    final Connection DB=Singleton.getInstence();
    /*#Start --- CRUD----- */
    public int create(T o)throws SQLException;
    public int update(T o)throws SQLException;
    public int remove(T o)throws SQLException;// A.K.A delete
    public List<T>  fetchAll()throws SQLException;// A.K.A Display
      /*#End --- CRUD----- */
    
    public T fetchOneById(int id); // single fetch 
    public T fetchOnByCriteria(Map<String,String> Criteras);// where Key is the name of the column and value is the value of the column
    public List<T>fetchSomeBy(String aux, String target_column ,int StartPoint,int BreakPoint);// limited and one column is questioned
    public List<T>fetchSomeBy(String aux, int target_column);// no limit one column is questioned
    public List<T>fetchSomeBy(String aux,int StartPoint,int BreakPoint);// limited rows all Columns are questioned 
    
    
}
