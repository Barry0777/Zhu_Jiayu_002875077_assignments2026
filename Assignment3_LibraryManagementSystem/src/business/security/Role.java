package business.security;

import business.LibrarySystem;
import javax.swing.JPanel;

public abstract class Role {

    public abstract JPanel createWorkArea(LibrarySystem system, String username);

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
