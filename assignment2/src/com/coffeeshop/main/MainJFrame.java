package com.coffeeshop.main;

import com.coffeeshop.model.Business;
import com.coffeeshop.model.Customer;
import com.coffeeshop.model.Order;
import com.coffeeshop.model.Product;
import com.coffeeshop.ui.AddOrderPanel;
import com.coffeeshop.ui.ListOrdersPanel;
import com.coffeeshop.ui.ManageProductsPanel;
import com.coffeeshop.ui.SearchCustomerPanel;
import java.awt.CardLayout;
import java.time.LocalDateTime;

public class MainJFrame extends javax.swing.JFrame {
    private Business business;
    private CardLayout cardLayout;

    public MainJFrame() {
        initComponents();
        this.business = new Business();
        populateInitialData();
        mainWorkArea.add("addOrder", new AddOrderPanel(business));
        mainWorkArea.add("manageProducts", new ManageProductsPanel(business));
        mainWorkArea.add("searchCustomer", new SearchCustomerPanel(business));
        mainWorkArea.add("listOrders", new ListOrdersPanel(business));
        this.cardLayout = (CardLayout) mainWorkArea.getLayout();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        controlPanel = new javax.swing.JPanel();
        btnAddOrder = new javax.swing.JButton();
        btnManageProducts = new javax.swing.JButton();
        btnSearchCustomers = new javax.swing.JButton();
        btnListOrders = new javax.swing.JButton();
        mainWorkArea = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coffee Shop POS System");

        btnAddOrder.setText("Add Customer + Order");
        btnAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOrderActionPerformed(evt);
            }
        });

        btnManageProducts.setText("Manage Products");
        btnManageProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageProductsActionPerformed(evt);
            }
        });

        btnSearchCustomers.setText("Search Customers");
        btnSearchCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchCustomersActionPerformed(evt);
            }
        });

        btnListOrders.setText("List Orders");
        btnListOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
                controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAddOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(btnManageProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSearchCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnListOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
                controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(controlPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnManageProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearchCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnListOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(376, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(controlPanel);

        mainWorkArea.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(mainWorkArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOrderActionPerformed
        cardLayout.show(mainWorkArea, "addOrder");
    }//GEN-LAST:event_btnAddOrderActionPerformed

    private void btnManageProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageProductsActionPerformed
        cardLayout.show(mainWorkArea, "manageProducts");
    }//GEN-LAST:event_btnManageProductsActionPerformed

    private void btnSearchCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchCustomersActionPerformed
        cardLayout.show(mainWorkArea, "searchCustomer");
    }//GEN-LAST:event_btnSearchCustomersActionPerformed

    private void btnListOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListOrdersActionPerformed
        cardLayout.show(mainWorkArea, "listOrders");
    }//GEN-LAST:event_btnListOrdersActionPerformed

    private void populateInitialData() {
        business.getProductCatalog().addProduct(new Product("101", "Latte", "COFFEE", 4.25, 50, 3));
        business.getProductCatalog().addProduct(new Product("102", "Cappuccino", "COFFEE", 4.50, 50, 3));
        business.getProductCatalog().addProduct(new Product("103", "Green Tea", "TEA", 3.25, 40, 2));
        business.getProductCatalog().addProduct(new Product("104", "Blueberry Muffin", "PASTRY", 2.95, 30, 1));
        business.getProductCatalog().addProduct(new Product("105", "Croissant", "PASTRY", 2.75, 35, 1));

        Customer c1 = new Customer("201", "John", "Doe", "1234567890");
        Product p1 = business.getProductCatalog().getAllProducts().get(0);
        Order o1 = new Order("501", c1, p1);
        business.getOrderDirectory().addOrder(o1);

        Customer c2 = new Customer("202", "Jane", "Smith", "0987654321");
        Product p2 = business.getProductCatalog().getAllProducts().get(2);
        Order o2 = new Order("502", c2, p2);
        business.getOrderDirectory().addOrder(o2);

        Customer c3 = new Customer("203", "Peter", "Jones", "1122334455");
        Product p3 = business.getProductCatalog().getAllProducts().get(1);
        Order o3 = new Order("503", c3, p3);
        business.getOrderDirectory().addOrder(o3);

        System.out.println("Initial data populated successfully.");
    }

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddOrder;
    private javax.swing.JButton btnListOrders;
    private javax.swing.JButton btnManageProducts;
    private javax.swing.JButton btnSearchCustomers;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel mainWorkArea;
    // End of variables declaration//GEN-END:variables
}