/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbh;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rabinson
 */
public class SqliteConnect {
    private Connection Con;
    
    public Connection Connector(){
        
        try{
            Class.forName("org.sqlite.JDBC");
             Con = DriverManager.getConnection("jdbc:sqlite:kbhDB.sqlite");
            
        }
        catch(Exception e){
           
        }
        return Con;
    }
    
}
