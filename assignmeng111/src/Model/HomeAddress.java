/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author barryzhu
 */
public class HomeAddress {
   
    private String name, street, city, state, zip;

    public HomeAddress() {}

    public HomeAddress(String name, String street, String city, String state, String zip) {
        this.name = name; this.street = street; this.city = city; this.state = state; this.zip = zip;
    }

    public String getName() { return name; }       public void setName(String v) { name = v; }
    public String getStreet() { return street; }   public void setStreet(String v) { street = v; }
    public String getCity() { return city; }       public void setCity(String v) { city = v; }
    public String getState() { return state; }     public void setState(String v) { state = v; }
    public String getZip() { return zip; }         public void setZip(String v) { zip = v; }
}
 
