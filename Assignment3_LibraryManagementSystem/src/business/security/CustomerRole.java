package business.security;

import business.LibrarySystem;
import ui.customer.CustomerWorkAreaPanel;

import javax.swing.JPanel;

public class CustomerRole extends Role {

    @Override
    public JPanel createWorkArea(LibrarySystem system, String username) {
        JPanel panel = new CustomerWorkAreaPanel(system, username);
        return panel;
    }
}
