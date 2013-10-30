/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author ANDre1
 */
import java.io.Serializable;

public class User implements Serializable {

 private String name;

 private String password;

 public String getName() {

 return name;

 }

 public void setName(String name) {

 this.name = name;

 }

 public String getPrice() {

 return password;

 }

 public void setPrice(String password) {

 this.password = password;

 }

}
