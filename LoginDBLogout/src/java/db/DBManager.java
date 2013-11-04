/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 *
 * @author ANDre1
 */
public class DBManager implements Serializable {

 // transient == non viene serializzato
 private transient Connection con;
 

 public DBManager(String dburl) throws SQLException {
 //String driver = "org.apache.derby.jdbc.EmbeddedDriver";
 try 
 {
   

    //Class.forName(driver).newInstance();
     
    Class.forName("org.apache.derby.jdbc.ClientDriver", true, getClass().getClassLoader());
    //Connection conn = DriverManager.getConnection("jdbc:derby:sample");
 } 
 catch(Exception e) 
 {
    throw new RuntimeException(e.toString(), e);
 }

 Connection con = DriverManager.getConnection(dburl);

 this.con = con;

 }

 

 public static void shutdown() {

 try 
 {
 DriverManager.getConnection("jdbc:derby://localhost:1527/sample;shutdown=true");
 } 
 catch (SQLException ex) 
 {
     Logger.getLogger(DBManager.class.getName()).info(ex.getMessage());
 }

 }

 /**
 * Autentica un utente in base a un nome utente e a una password
 * 
 * @param username il nome utente
 * @param password la password
 * @return null se l'utente non è autenticato, un oggetto User se l'utente esiste ed è autenticato
 */

 public User authenticate(String username, String password) throws SQLException {

 // usare SEMPRE i PreparedStatement, anche per query banali. 
 // *** MAI E POI MAI COSTRUIRE LE QUERY CONCATENANDO STRINGHE !!!! 

    PreparedStatement stm = con.prepareStatement("SELECT * FROM andrea.users WHERE username = ? AND password = ?");

    try {

        stm.setString(1, username);

        stm.setString(2, password);
 
        ResultSet rs = stm.executeQuery();

            try {

                if (rs.next()) {
                    User user = new User();
                    user.setUserName(username);
                    user.setFullName(rs.getString("fullname"));
                    return user;
                } 
                else {
                    return null;
                }
            
            } 
            finally 
            {
                // ricordarsi SEMPRE di chiudere i ResultSet in un blocco finally 
                rs.close();
            }
    
    } 
    finally { 
        // ricordarsi SEMPRE di chiudere i PreparedStatement in un blocco finally 
        stm.close();
    }

 }
 
 /**
 * Ottiene la lista dei prodotti dal DB
 * 
 * @return
 * @throws SQLException 
 */

 public List<Product> getProducts() throws SQLException {

    List<Product> products = new ArrayList<Product>();

    PreparedStatement stm = con.prepareStatement("SELECT * FROM andrea.products");

    try {
        ResultSet rs = stm.executeQuery();
        try {
            while(rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                products.add(p);
            }
        } 
        finally {
            rs.close();
        }

    } 
    finally {
        stm.close();
    }

    return products;

 }

}