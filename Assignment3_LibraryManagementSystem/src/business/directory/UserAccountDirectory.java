/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.directory;

import business.security.Role;
import business.security.UserAccount;
import java.util.ArrayList;
import java.util.List;

//@author Zhu jiayu
public class UserAccountDirectory {
    private final List<UserAccount> list = new ArrayList<>();

    public UserAccount create(String username, String password, Role role) {
        UserAccount ua = new UserAccount(username, password, role);
        list.add(ua);
        return ua;
    }
    public UserAccount find(String username) {
        return list.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }
    public UserAccount authenticate(String u, String p) {
        return list.stream().filter(x -> x.authenticate(u, p)).findFirst().orElse(null);
    }
    public List<UserAccount> getAll() { return list; }
}
