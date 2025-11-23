package ui.admin;

import business.LibrarySystem;
import business.directory.CustomerDirectory;
import business.model.people.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminCustomerPanel extends JPanel {

    private final LibrarySystem sys;
    private final CustomerDirectory repo;

    private JTable table;
    private JTextField userField;
    private JTextField nameField;
    private JButton addBtn;
    private JButton reloadBtn;

    public AdminCustomerPanel(LibrarySystem sys) {
        this.sys = sys;
        this.repo = sys.getCustomerDirectory();
        initView();
        refresh();
        wireEvents();
    }

    private void initView() {
        setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,5,5);

        g.gridx = 0; g.gridy = 0; g.anchor = GridBagConstraints.EAST;
        form.add(new JLabel("Username:"), g);
        g.gridx = 1; g.anchor = GridBagConstraints.WEST;
        userField = new JTextField(12);
        form.add(userField, g);

        g.gridx = 0; g.gridy = 1; g.anchor = GridBagConstraints.EAST;
        form.add(new JLabel("Full Name:"), g);
        g.gridx = 1; g.anchor = GridBagConstraints.WEST;
        nameField = new JTextField(12);
        form.add(nameField, g);

        g.gridx = 0; g.gridy = 2; g.gridwidth = 2; g.anchor = GridBagConstraints.CENTER;
        JPanel btns = new JPanel();
        addBtn = new JButton("Create Customer");
        reloadBtn = new JButton("Refresh");
        btns.add(addBtn);
        btns.add(reloadBtn);
        form.add(btns, g);

        add(form, BorderLayout.SOUTH);
    }

    private void refresh() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Username", "Full Name"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
            @Override public Class<?> getColumnClass(int c) {
                return (c == 0) ? Integer.class : String.class;
            }
        };

        for (Customer ct : repo.getAll()) {
            model.addRow(new Object[] {
                    ct.getId(),
                    ct.getUsername(),
                    ct.getFullName()
            });
        }

        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void wireEvents() {
        reloadBtn.addActionListener(e -> refresh());
        addBtn.addActionListener(e -> addCustomer());
    }

    private void addCustomer() {
        String u = userField.getText().trim();
        String fn = nameField.getText().trim();

        if (u.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username required");
            return;
        }

        if (fn.isEmpty()) fn = u;

        Customer newCus;

        try {
            newCus = repo.create(u, fn);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        refresh();
        userField.setText("");
        nameField.setText("");
        JOptionPane.showMessageDialog(this, "Customer created: " + newCus);
    }
}
