/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author barryzhu
 */
public class Person {
    
   
    private String name, age, gender, email, phone;

    public Person() {}

    public Person(String name, String age, String gender, String email, String phone) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public String getName() { return name; }     public void setName(String v) { name = v; }
    public String getAge() { return age; }       public void setAge(String v) { age = v; }
    public String getGender() { return gender; } public void setGender(String v) { gender = v; }
    public String getEmail() { return email; }   public void setEmail(String v) { email = v; }
    public String getPhone() { return phone; }   public void setPhone(String v) { phone = v; }

}
