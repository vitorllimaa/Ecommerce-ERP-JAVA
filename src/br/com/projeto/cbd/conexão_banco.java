
package br.com.projeto.cbd;
import java.sql.Connection;
import java.sql.DriverManager;
public class conexão_banco {
    
    
    
    public Connection getconexão(){
        
        try{
            
      return DriverManager.getConnection("jdbc:mysql://localhost/BDVENDAS", "root", "vitor1234");
       
         }catch(Exception erro){
             throw new RuntimeException("D"+erro);
          
        }
        
        
        
        
    }

    void getgetconexão() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
