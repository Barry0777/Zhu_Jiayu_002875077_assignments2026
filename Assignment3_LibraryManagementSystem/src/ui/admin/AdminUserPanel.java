package ui.admin;

import business.LibrarySystem;
import business.security.UserAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminUserPanel extends JPanel {

    private final LibrarySystem core;
    private JTable table;
    private JButton reload;

    public AdminUserPanel(LibrarySystem core) {
        this.core = core;
        setupView();
        updateTable();
        linkEvents();
    }

    private void setupView() {
        setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scroller = new JScrollPane(table);
        add(scroller, BorderLayout.CENTER);

        reload = new JButton("Refresh");
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.add(reload);
        add(bottom, BorderLayout.SOUTH);
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Username", "Role"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        for (UserAccount ua : core.getUserAccountDirectory().getAll()) {
            model.addRow(new Object[]{
                    ua.getUsername(),
                    ua.getRole().getName()
            });
        }

        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void linkEvents() {
        reload.addActionListener(e -> updateTable());
    }
}
