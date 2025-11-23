package ui.admin;

import business.LibrarySystem;
import business.model.Branch;
import business.model.Library;
import business.model.people.Employee;
import business.security.BranchManagerRole;
import business.security.UserAccount;

import javax.swing.*;
import java.awt.*;

public class AdminEmployeePanel extends JPanel {

    private final LibrarySystem app;

    private JComboBox<Branch> branchBox;
    private JTextField nameField;
    private JTextField expField;
    private JTextField userField;
    private JPasswordField passField;
    private JButton assignBtn;

    public AdminEmployeePanel(LibrarySystem app) {
        this.app = app;
        buildUI();
        loadBranches();
        hookActions();
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel area = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6,6,6,6);
        g.anchor = GridBagConstraints.WEST;

        branchBox = new JComboBox<>();
        nameField = new JTextField(18);
        expField = new JTextField(18);
        userField = new JTextField(18);
        passField = new JPasswordField(18);

        int y = 0;

        g.gridx = 0; g.gridy = y;
        area.add(new JLabel("Branch"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        area.add(branchBox, g);
        y++;

        g.gridx = 0; g.gridy = y; g.fill = GridBagConstraints.NONE;
        area.add(new JLabel("Name"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        area.add(nameField, g);
        y++;

        g.gridx = 0; g.gridy = y;
        area.add(new JLabel("Experience"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        area.add(expField, g);
        y++;

        g.gridx = 0; g.gridy = y;
        area.add(new JLabel("Username"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        area.add(userField, g);
        y++;

        g.gridx = 0; g.gridy = y;
        area.add(new JLabel("Password"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        area.add(passField, g);
        y++;

        add(area, BorderLayout.CENTER);

        assignBtn = new JButton("Assign Manager + Account");
        JPanel bottom = new JPanel();
        bottom.add(assignBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    private void loadBranches() {
        if (app == null) return;
        DefaultComboBoxModel<Branch> m = new DefaultComboBoxModel<>();
        for (Branch b : app.getBranchDirectory().getAll()) {
            m.addElement(b);
        }
        branchBox.setModel(m);
    }

    private void hookActions() {
        assignBtn.addActionListener(e -> assignManager());
    }

    private void assignManager() {

        Branch target = (Branch) branchBox.getSelectedItem();
        if (target == null) {
            alert("Please select a branch.");
            return;
        }

        String nm = nameField.getText().trim();
        String xp = expField.getText().trim();
        String un = userField.getText().trim();
        String pw = new String(passField.getPassword());

        if (nm.isEmpty() || xp.isEmpty() || un.isEmpty() || pw.isEmpty()) {
            alert("All fields are required.");
            return;
        }

        int years;
        try {
            years = Integer.parseInt(xp);
        } catch (Exception ex) {
            alert("Experience must be an integer.");
            return;
        }

        UserAccount existed = app.getUserAccountDirectory().find(un);
        if (existed != null) {
            alert("Username already exists.");
            return;
        }

        Employee emp = app.getEmployeeDirectory().create(nm, years);

        target.setManager(emp);
        Library lib = target.getLibrary();
        if (lib != null) {
            lib.setManager(emp);
        }

        app.getUserAccountDirectory().create(un, pw, new BranchManagerRole());

        alert("Manager assigned to branch: " + target);

        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        expField.setText("");
        userField.setText("");
        passField.setText("");
    }

    private static void alert(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
