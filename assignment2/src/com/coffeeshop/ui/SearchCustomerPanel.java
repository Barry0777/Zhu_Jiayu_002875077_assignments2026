package com.coffeeshop.ui;

import com.coffeeshop.model.Business;
import com.coffeeshop.model.Customer;
import com.coffeeshop.model.Order;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchCustomerPanel extends javax.swing.JPanel {

    private Business business;
    public SearchCustomerPanel(Business business) {
        initComponents();
        this.business = business;
    }

    private void clearDetails() {
        lblCustomerIdValue.setText("...");
        lblFirstNameValue.setText("...");
        lblLastNameValue.setText("...");
        lblContactValue.setText("...");

        DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
        model.setRowCount(0);
    }

    private void populateHistoryTable(String customerId) {
        DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
        model.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Order order : business.getOrderDirectory().getAllOrders()) {
            if (order.getCustomer().getCustomerId().equals(customerId)) {
                Object[] row = new Object[4];
                row[0] = order.getOrderId();
                row[1] = order.getProduct().getName();
                row[2] = order.getOrderDateTime().format(formatter);
                row[3] = order.getOrderStatus();
                model.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        panelDetails = new javax.swing.JPanel();
        lblCustomerIdTitle = new javax.swing.JLabel();
        lblCustomerIdValue = new javax.swing.JLabel();
        lblFirstNameTitle = new javax.swing.JLabel();
        lblFirstNameValue = new javax.swing.JLabel();
        lblLastNameTitle = new javax.swing.JLabel();
        lblLastNameValue = new javax.swing.JLabel();
        lblContactTitle = new javax.swing.JLabel();
        lblContactValue = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        lblHistory = new javax.swing.JLabel();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Search Customers");

        lblSearch.setText("Search by Name or ID:");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Customer ID", "First Name", "Last Name", "Contact"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomers);

        panelDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details and Order History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblCustomerIdTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCustomerIdTitle.setText("Customer ID:");

        lblCustomerIdValue.setText("...");

        lblFirstNameTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFirstNameTitle.setText("First Name:");

        lblFirstNameValue.setText("...");

        lblLastNameTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLastNameTitle.setText("Last Name:");

        lblLastNameValue.setText("...");

        lblContactTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblContactTitle.setText("Contact:");

        lblContactValue.setText("...");

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Order ID", "Product", "Date", "Status"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHistory);

        lblHistory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHistory.setText("Order History:");

        javax.swing.GroupLayout panelDetailsLayout = new javax.swing.GroupLayout(panelDetails);
        panelDetails.setLayout(panelDetailsLayout);
        panelDetailsLayout.setHorizontalGroup(
                panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDetailsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addGroup(panelDetailsLayout.createSequentialGroup()
                                                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelDetailsLayout.createSequentialGroup()
                                                                .addComponent(lblCustomerIdTitle)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblCustomerIdValue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblFirstNameTitle)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblFirstNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelDetailsLayout.createSequentialGroup()
                                                                .addComponent(lblLastNameTitle)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblLastNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblContactTitle)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblContactValue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblHistory))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelDetailsLayout.setVerticalGroup(
                panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelDetailsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCustomerIdTitle)
                                        .addComponent(lblCustomerIdValue)
                                        .addComponent(lblFirstNameTitle)
                                        .addComponent(lblFirstNameValue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLastNameTitle)
                                        .addComponent(lblLastNameValue)
                                        .addComponent(lblContactTitle)
                                        .addComponent(lblContactValue))
                                .addGap(18, 18, 18)
                                .addComponent(lblHistory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                                        .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblSearch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSearch)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(panelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSearch)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblCustomers.getModel();
        model.setRowCount(0);
        clearDetails();

        String searchText = txtSearch.getText().toLowerCase();

        Map<String, Customer> uniqueCustomers = new HashMap<>();

        for (Order order : business.getOrderDirectory().getAllOrders()) {
            Customer customer = order.getCustomer();
            if (customer.getCustomerId().toLowerCase().contains(searchText) ||
                    customer.getFirstName().toLowerCase().contains(searchText) ||
                    customer.getLastName().toLowerCase().contains(searchText)) {
                uniqueCustomers.put(customer.getCustomerId(), customer);
            }
        }

        for (Customer c : uniqueCustomers.values()) {
            Object[] row = new Object[4];
            row[0] = c.getCustomerId();
            row[1] = c.getFirstName();
            row[2] = c.getLastName();
            row[3] = c.getContact();
            model.addRow(row);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomersMouseClicked
        int selectedRow = tblCustomers.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        String customerId = (String) tblCustomers.getValueAt(selectedRow, 0);
        String firstName = (String) tblCustomers.getValueAt(selectedRow, 1);
        String lastName = (String) tblCustomers.getValueAt(selectedRow, 2);
        String contact = (String) tblCustomers.getValueAt(selectedRow, 3);

        lblCustomerIdValue.setText(customerId);
        lblFirstNameValue.setText(firstName);
        lblLastNameValue.setText(lastName);
        lblContactValue.setText(contact);

        populateHistoryTable(customerId);
    }//GEN-LAST:event_tblCustomersMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblContactTitle;
    private javax.swing.JLabel lblContactValue;
    private javax.swing.JLabel lblCustomerIdTitle;
    private javax.swing.JLabel lblCustomerIdValue;
    private javax.swing.JLabel lblFirstNameTitle;
    private javax.swing.JLabel lblFirstNameValue;
    private javax.swing.JLabel lblHistory;
    private javax.swing.JLabel lblLastNameTitle;
    private javax.swing.JLabel lblLastNameValue;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}