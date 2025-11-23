package business.security;

import business.LibrarySystem;
import business.model.Branch;
import business.model.Library;
import ui.manager.ManagerWorkAreaPanel;

import javax.swing.JPanel;

public class BranchManagerRole extends Role {

    @Override
    public JPanel createWorkArea(LibrarySystem system, String username) {

        Library target = null;

        for (Branch br : system.getBranchDirectory().getAll()) {
            Library ref = br.getLibrary();
            if (ref != null) {
                target = ref;
                break;
            }
        }

        if (target == null) {
            var libraries = system.getLibraryDirectory().getAll();
            if (!libraries.isEmpty()) {
                target = libraries.get(0);
            }
        }

        return new ManagerWorkAreaPanel(system, target);
    }
}
