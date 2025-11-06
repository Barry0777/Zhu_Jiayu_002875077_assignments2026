package com.coffeeshop.ui;

import com.coffeeshop.model.Business;
import com.coffeeshop.model.Product;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




public class ManageProductsPanel extends javax.swing.JPanel {
    private Business business;

    public ManageProductsPanel(Business business) {
        initComponents();
        this.business = business;
        populateTable();
    }

    
    
    
    
    
    
    
    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        if (business != null && business.getProductCatalog() != null) {
            for (Product p : business.getProductCatalog().getAllProducts()) {
                Object[] row = new Object[6];
                row[0] = p.getProductId();
                row[1] = p.getName();
                row[2] = p.getCategory();
                row[3] = p.getPrice();
                row[4] = p.getStockNumber();
                row[5] = p.getPrepTime();
                model.addRow(row);
            }
        }
    }

    private void clearFields() {
        txtProductId.setText("");
        txtName.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtPrepTime.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        lblProductId = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblCategory = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        lblPrepTime = new javax.swing.JLabel();
        txtPrepTime = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Product ID", "Name", "Category", "Price", "Stock", "Prep Time (min)"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Manage Products");

        lblProductId.setText("Product ID:");

        lblName.setText("Name:");

        lblCategory.setText("Category:");

        lblPrice.setText("Price:");

        lblStock.setText("Stock:");

        lblPrepTime.setText("Prep Time:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnAdd)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnUpdate)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnDelete)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnClear))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(lblProductId, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(txtProductId)
                                                                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(lblCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                        .addComponent(lblPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(txtCategory)
                                                                        .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(lblStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(lblPrepTime, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPrepTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 20, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblProductId)
                                        .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblCategory)
                                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblStock)
                                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblName)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPrice)
                                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPrepTime)
                                        .addComponent(txtPrepTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdd)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnDelete)
                                        .addComponent(btnClear))
                                .addContainerGap(118, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtProductId.getText().isBlank() || txtName.getText().isBlank() || txtCategory.getText().isBlank() || txtPrice.getText().isBlank() || txtStock.getText().isBlank() || txtPrepTime.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Product p : business.getProductCatalog().getAllProducts()) {
            if (p.getProductId().equals(txtProductId.getText())) {
                JOptionPane.showMessageDialog(this, "Product ID already exists.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        double price;
        int stock, prepTime;
        try {
            price = Double.parseDouble(txtPrice.getText());
            stock = Integer.parseInt(txtStock.getText());
            prepTime = Integer.parseInt(txtPrepTime.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Price, Stock, and Prep Time.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product newProduct = new Product(txtProductId.getText(), txtName.getText(), txtCategory.getText(), price, stock, prepTime);
        business.getProductCatalog().addProduct(newProduct);

        JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        populateTable();
        clearFields();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblProducts.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a product to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String productId = (String) tblProducts.getValueAt(selectedRow, 0);
        Product productToUpdate = null;
        for (Product p : business.getProductCatalog().getAllProducts()) {
            if (p.getProductId().equals(productId)) {
                productToUpdate = p;
                break;
            }
        }

        if (productToUpdate == null) {
            JOptionPane.showMessageDialog(this, "Product not found. Please refresh.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!productToUpdate.getProductId().equals(txtProductId.getText())) {
            JOptionPane.showMessageDialog(this, "Product ID cannot be changed.", "Update Error", JOptionPane.ERROR_MESSAGE);
            txtProductId.setText(productToUpdate.getProductId());
            return;
        }

        if (txtName.getText().isBlank() || txtCategory.getText().isBlank() || txtPrice.getText().isBlank() || txtStock.getText().isBlank() || txtPrepTime.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "All fields except Product ID must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        int stock, prepTime;
        try {
            price = Double.parseDouble(txtPrice.getText());
            stock = Integer.parseInt(txtStock.getText());
            prepTime = Integer.parseInt(txtPrepTime.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Price, Stock, and Prep Time.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        productToUpdate.setName(txtName.getText());
        productToUpdate.setCategory(txtCategory.getText());
        productToUpdate.setPrice(price);
        productToUpdate.setStockNumber(stock);
        productToUpdate.setPrepTime(prepTime);

        JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        populateTable();
        clearFields();
    }//GEN-LAST:event_btnUpdateActionPerformed

   
    
    
    
    
    
    
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblProducts.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (dialogResult != JOptionPane.YES_OPTION) {
            return;
        }

        String productId = (String) tblProducts.getValueAt(selectedRow, 0);

        Product productToDelete = null;
        for (Product p : business.getProductCatalog().getAllProducts()) {
            if (p.getProductId().equals(productId)) {
                productToDelete = p;
                break;
            }
        }

        if (productToDelete != null) {
            business.getProductCatalog().removeProduct(productToDelete);
            JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            populateTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        int selectedRow = tblProducts.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        
        
        
        
       
        
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
       
        
        
        txtProductId.setText(model.getValueAt(selectedRow, 0).toString());
        txtName.setText(model.getValueAt(selectedRow, 1).toString());
        txtCategory.setText(model.getValueAt(selectedRow, 2).toString());
        txtPrice.setText(model.getValueAt(selectedRow, 3).toString());
        txtStock.setText(model.getValueAt(selectedRow, 4).toString());
        txtPrepTime.setText(model.getValueAt(selectedRow, 5).toString());
    }//GEN-LAST:event_tblProductsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrepTime;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProductId;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrepTime;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}