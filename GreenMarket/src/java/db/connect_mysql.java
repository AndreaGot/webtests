package db;

import java.io.IOException;
import sito.logs;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class connect_mysql
{
    public void connect_mysql()
    {
  
    }   
    
private Connection get_connect() throws IOException 
{
   logs mylog = null;
   mylog = new logs();
    Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/prog_web";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root"; 
    String password = "";
    try 
    {
        Class.forName(driver).newInstance();
        connection = DriverManager.getConnection(url,userName,password);
        mylog = null;
        return connection;
    } 
    catch (Exception e) 
    {
        mylog.write(e.getMessage());
        mylog = null;
        return null;
    }
}
public boolean user_exist(String name) throws IOException, Exception  {
ResultSet rs = null;
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  
      String query = "select count(*) from users WHERE username = '" + name + "'";
      rs =  stm.executeQuery(query);
      rs.next();
      // controllo che trovi l'utente e ne verifica la psw
      int rowCount = -1;
      rowCount = rs.getInt(1);
       if(rowCount != 0)
      {
          return true;
      }
      else
      {
          return false;
      }
    
      
    }catch (Exception e) 
    {
       mylog.write(e.getMessage()); 
       throw e;
    } 
    finally
    {
        rs.close();
        connect.close();
    }

}
public int authenticate(String name, String psw)throws Exception 
{
    ResultSet rs = null;
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  
      String query = "select id,role from users WHERE username = '" + name + "' AND password = '" + psw + "' Limit 1";
      rs =  stm.executeQuery(query);
      rs.next();
      // controllo che trovi l'utente e ne verifica la psw
      int rowCount = -1;
      rowCount = rs.getInt(1);
      int user_type = -1;
      user_type = rs.getInt("role");
      // Ritorno true se autenticato, false se non lo è  
      if(rowCount != -1)
      {
          return user_type;
      }
      else
      {
          return -1;
      }
    } 
    catch (Exception e) 
    {
       mylog.write(e.getMessage()); 
       throw e;
    } 
    finally
    {
        rs.close();
        connect.close();
    }
 }

 public static ArrayList<product> extract(ResultSet resultSet)  
    throws SQLException 
 {  
        ArrayList<product> table;  
        int columnCount = resultSet.getMetaData().getColumnCount();  
        if(resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) 
        {
            table = new ArrayList<product>();  
        }  
        else 
        {    
            resultSet.last();  
            table = new ArrayList<product>(resultSet.getRow());  
            resultSet.beforeFirst();  
        }
        for(product row_ = null; resultSet.next(); table.add(row_)) 
        {  
            String id = resultSet.getString(1).intern();
            String name = resultSet.getString(2).intern();
            String id_user = resultSet.getString(10).intern();
            String quantity = resultSet.getString(4).intern();
            String category = resultSet.getString(5).intern();
            String UM = resultSet.getString(6).intern();
            String price = resultSet.getString(7).intern();
            String photo = resultSet.getString(9).intern();
            row_ = new product(id,name,id_user,quantity,category,UM,price,photo);
        }  
        return table;  
 } 
 
 
public ArrayList get_products(String id_product) throws Exception 
{
    ResultSet rs = null;
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  
      String query = "SELECT p.*, u.username FROM products as p JOIN users as u ON p.id_user = u.id WHERE category = '"+ id_product +"'";
      mylog.write(query);
      rs =  stm.executeQuery(query);

       ArrayList productslist = null;
       productslist = new ArrayList();
       productslist = extract(rs);           
       return productslist;
    }
    catch (Exception e) 
    {
             mylog.write(e.getMessage());
             throw e;
    } 
    finally
    {
            //chiudere la connessione???
             rs.close();
    } 
}

public product get_product(String id_product) throws IOException, Exception 
{
        ResultSet rs = null;
        Connection connect = null;
        logs mylog = null;
        mylog = new logs();
        try 
        {
          // Creo connessione con il database
          connect = get_connect();     
          // Creo lo statment per eseguire query
          Statement stm = connect.createStatement();   
          // eseguo la query per l'autentificazione e ritorno se è presente nel DB  
          String query = "SELECT * FROM products WHERE id = '"+ id_product +"'  LIMIT 1";
          mylog.write(query);
          rs =  stm.executeQuery(query);
          product my_prod = null;
          rs.next();
          my_prod =  new product(rs.getString("id"),rs.getString("name"),rs.getString("id_user"),rs.getString("quantity"),rs.getString("category"),rs.getString("UM"),rs.getString("price"),rs.getString("image"));
          return my_prod;
        } 
        catch (Exception e) 
        {
             mylog.write(e.getMessage());
             throw e;
        } 
        finally
        {
            //chiudere la connessione???
             rs.close();
        } 
}
     
public ArrayList get_product_by_user(String id_user) throws Exception 
{
    ResultSet rs = null;
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  
      String query = "SELECT p.*, u.username FROM products as p JOIN users as u ON p.id_user = u.id WHERE u.username = '"+ id_user +"'";
      mylog.write(query);
      rs =  stm.executeQuery(query);

       ArrayList productslist = null;
       productslist = new ArrayList();
       productslist = extract(rs);           
       return productslist;
    }
    catch (Exception e) 
    {
             mylog.write(e.getMessage());
             throw e;
    } 
    finally
    {
            //chiudere la connessione???
             rs.close();
    } 
}
   
public ArrayList get_categories() throws Exception 
{
    ResultSet rs = null;
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  
      String query = "SELECT id, name FROM category";
      mylog.write(query);
      rs =  stm.executeQuery(query);
      ArrayList categories = null;
      categories = new ArrayList();
      categories = extract_pdf(rs);           
      return categories;
    }
    catch (Exception e) 
    {
             mylog.write(e.getMessage());
             throw e;
    } 
    finally
    {
            //chiudere la connessione???
             rs.close();
    } 
}

public void Insert(String query) throws IOException, Exception
{
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  

      mylog.write(query);
      stm.executeUpdate(query);        

    } 
    catch (Exception e) 
    {
         mylog.write(e.getMessage());
         throw e;
    } 
    finally
    {
    } 
}

   public static ArrayList<String[]> extract_pdf(ResultSet resultSet)  
throws SQLException {  
    ArrayList<String[]> table;  
    int columnCount = resultSet.getMetaData().getColumnCount();  

    if(resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) {
        table = new ArrayList<String[]>();  
    }  
    else {    
        resultSet.last();  
        table = new ArrayList<String[]>(resultSet.getRow());  
        resultSet.beforeFirst();  
    }  
    for(String[] row_ = null; resultSet.next(); table.add(row_)) 
    {  
        String date = resultSet.getString(1).intern();
        String path = resultSet.getString(2).intern();

       row_ = new String[2];
       row_[0] = date;
       row_[1] = path;
    }  
    return table;  
} 
    
    
    
    
 public ArrayList get_list_pdf(String id_utente) throws Exception 
 {
       ResultSet rs = null;
        Connection connect = null;
        logs mylog = null;
        mylog = new logs();
    try {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  

      String query = "SELECT date as ddate,receipt FROM sell  WHERE id_user = '"+ id_utente +"' GROUP BY ddate,receipt";
      mylog.write(query);
      rs =  stm.executeQuery(query);
  
       ArrayList pdflist = null;
       pdflist = new ArrayList();
       pdflist = extract_pdf(rs);           
       return pdflist;

     
    } catch (Exception e) {
         mylog.write(e.getMessage());
         throw e;
    } finally{
        //chiudere la connessione???
        
         rs.close();
    } 
}
 
public String get_id_from_user(String id_utente) throws Exception 
{
       ResultSet rs = null;
        Connection connect = null;
        logs mylog = null;
        mylog = new logs();
        try 
        {
            // Creo connessione con il database
            connect = get_connect();     
            // Creo lo statment per eseguire query
            Statement stm = connect.createStatement();   
            // eseguo la query per l'autentificazione e ritorno se è presente nel DB  

            String query = "SELECT id FROM users  WHERE username = '"+ id_utente +"'";
            mylog.write(query);
            rs =  stm.executeQuery(query);
            rs.next();
            return rs.getString(1).intern();
        } 
        catch (Exception e)
        {
            mylog.write(e.getMessage());
            throw e;
        } 
        finally
        {
         rs.close();
        } 
    }

    public void register(String name, String psw, String email, String address) throws IOException, Exception {
        
    Connection connect = null;
    logs mylog = null;
    mylog = new logs();
    String query = "INSERT INTO users (username,password,role,address,email,date_reg) VALUES ('"+name+"','"+psw+"',0,'"+address+"','"+email+"',NOW())";
    try 
    {
      // Creo connessione con il database
      connect = get_connect();     
      // Creo lo statment per eseguire query
      Statement stm = connect.createStatement();   
      // eseguo la query per l'autentificazione e ritorno se è presente nel DB  

      mylog.write(query);
      stm.executeUpdate(query);        

    } 
    catch (Exception e) 
    {
         mylog.write(e.getMessage());
         throw e;
    } 
    finally
    {
    } 
    }
}