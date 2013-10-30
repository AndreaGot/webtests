/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;

public class Product implements Serializable {

 private String name;
 private Float price;

 public String getName() {
 return name;
 }

 public void setName(String name) {
 this.name = name;
 }

 public Float getPrice() {
 return price;
 }

 public void setPrice(Float price) {
 this.price = price;
 }

}