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

 private String username;
 private String password;
 public String fullName;

 public String getUserName() {
 return username;
 }

 public void setUserName(String name) {
 this.username = name;
 }

 public String getFullName() {
 return fullName;
 }

 public void setFullName(String name) {
 this.fullName = name;
 }
 
 public String getPassword() {
 return password;
 }

 public void setPassword(String password) {
 this.password = password;
 }

 
}