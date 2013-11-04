/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Mattia
 */
public class product
{
    public String id;
    public String name;
    public String id_user;
    public String quantity;
    public String category;
    public String UM;
    public String price;
    public String photo;
    public product(String id_,String name_, String user_, String quantity_, String category_, String um_, String price_, String photo_ ){
        id = id_;
        name = name_;
        id_user = user_;
        quantity = quantity_;
        category = category_;
        UM = um_;
        price = price_;
        photo = photo_;
    }
}
