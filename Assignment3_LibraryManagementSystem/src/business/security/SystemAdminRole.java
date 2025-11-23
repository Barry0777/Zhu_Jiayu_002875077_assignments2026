package business.security;

import business.LibrarySystem;
import ui.admin.AdminMainPanel;

import javax.swing.JPanel;

public class SystemAdminRole extends Role {

    @Override
    public JPanel createWorkArea(LibrarySystem system, String username) {
        JPanel panel = new AdminMainPanel(system);
        return panel;
    }
}
