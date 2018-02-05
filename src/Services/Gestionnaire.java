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

/**
 *
 * @author Ransom
 */
public interface Gestionnaire 
{
    // Call of Singleton 
    final Connection DB=Singleton.getInstence();
    /*#Start --- CRUD----- */
    public int create(Object o)throws SQLException;
    public int update(Object o)throws SQLException;
    public int remove(Object o)throws SQLException;// A.K.A delete
    public List<? extends Object>fetchAll()throws SQLException;// A.K.A Display
      /*#End --- CRUD----- */
    
      /*#Start --- SpecialFunctions----- */
    public List<? extends Object>fetchAll(String aux,int target_column,String OrderBy)throws SQLException;/*a simple order by fetch with column sensitivity 
    the aux variable is for the like alias in the query to compare individual columns ( AKA recherche dynamique ) 
    */
    
    public List<? extends Object>fetchAll(String aux,int target_column,String OrderBy,int startPoint,int breakPoint)throws SQLException;/*this function will fetch a sorted 
    list of your table with a limiter that starts from an index to the limit index ;
    example the first 20 rows of your table ordered by date the startPoint = 0 the breakPoint will be 20
    */
    
    
      /*#End --- SpecialFunctions----- */
    
    
}
