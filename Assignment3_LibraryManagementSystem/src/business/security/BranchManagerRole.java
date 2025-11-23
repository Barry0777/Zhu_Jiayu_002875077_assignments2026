package business.security;

import business.LibrarySystem;
import business.model.Library;
import ui.manager.ManagerWorkAreaPanel;

import javax.swing.JPanel;

public class BranchManagerRole extends Role {

    @Override
    public JPanel createWorkArea(LibrarySystem system, String username) {

        // 1) 找到当前登录账户
        UserAccount ua = system.getUserAccountDirectory().find(username);

        Library lib = null;

        if (ua != null && ua.getLibraryId() != null) {
            // 2) 根据 libraryId 找到属于该 manager 的 library
            lib = system.getLibraryDirectory().findById(ua.getLibraryId());
        }

        // 3) 如果找不到，兜底给第一个 library（不会崩）
        if (lib == null && !system.getLibraryDirectory().getAll().isEmpty()) {
            lib = system.getLibraryDirectory().getAll().get(0);
        }

        return new ManagerWorkAreaPanel(system, lib);
    }
}
