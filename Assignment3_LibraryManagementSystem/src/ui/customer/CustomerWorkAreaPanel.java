/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.customer;

import business.LibrarySystem;
import business.model.Rental.RentalRecord;
import business.model.Rental.RentalStatus;


import javax.swing.*;
/**
 *
 * @author xuanliliu
 */
public class CustomerWorkAreaPanel extends javax.swing.JPanel {
private final LibrarySystem system;
    private final String username;
    
    private javax.swing.JPanel historyPanel;
    private javax.swing.JTable tblHistory;
    private javax.swing.JScrollPane spHistory;
    private javax.swing.JComboBox<business.model.Branch> cbBranch;


    public CustomerWorkAreaPanel() {
    this.system = null;
    this.username = null;
    initComponents();
}
    
    public CustomerWorkAreaPanel(business.LibrarySystem system, String username) {
    this.system = system;
    this.username = username;
    initComponents();

    // tab 标题改成 Browse / Return / History 风格
    tabbed.setTitleAt(0, "Browse");
    tabbed.setTitleAt(1, "Return");
    jLabel2.setText("Return");

    // 按钮文字改成 Rent Book / Logout
    btnBrowse.setText("Rent Book");
    jButton1.setText("Logout");

    // ★ 重建 Browse 页布局：上 Branch，下按钮，中间表格
    rebuildBrowseTabLayout();

    // ★ 初始化 Branch 下拉框
    initBranchCombo();

    // 初始化三个 tab
    initBrowseTab();    // 里面会 reloadBrowseTable
    initRentalsTab();   // 里面会创建 History tab 并加载数据
    bindCustomerEvents();
}
    
    /** 把 Browse 页改成：上 Branch 下拉，中间表格，底部按钮（Rent Book + Logout） */
private void rebuildBrowseTabLayout() {
    JPanel northBar = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    JLabel branchLabel = new JLabel("Branch:");
    cbBranch = new javax.swing.JComboBox<>();
    northBar.add(branchLabel);
    northBar.add(cbBranch);

    JPanel actions = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
    actions.add(btnBrowse);
    actions.add(jButton1);

    browsePanel.removeAll();
    browsePanel.setLayout(new java.awt.BorderLayout());
    browsePanel.add(northBar, java.awt.BorderLayout.PAGE_START);
    browsePanel.add(spBrowse, java.awt.BorderLayout.CENTER);
    browsePanel.add(actions, java.awt.BorderLayout.PAGE_END);

    browsePanel.revalidate();
    browsePanel.repaint();
}



/** 初始化 Branch 下拉选项，并联动刷新表格 */
private void initBranchCombo() {
    if (system == null) return;

    DefaultComboBoxModel<business.model.Branch> boxModel = new DefaultComboBoxModel<>();
    for (business.model.Branch b : system.getBranchDirectory().getAll()) {
        boxModel.addElement(b);
    }
    cbBranch.setModel(boxModel);

    cbBranch.setRenderer((list, item, i, sel, focus) -> {
        String txt = (item == null)
                ? ""
                : item.getName() + " (Branch #" + item.getId() + ")";
        return new javax.swing.JLabel(txt);
    });

    cbBranch.addActionListener(evt -> reloadBrowseTable(""));
}


/** 根据 Book 找到它所在的 Branch（通过 library 反查） */
private business.model.Branch findBranchOfBook(business.model.book.Book bk) {
    if (bk == null || system == null) return null;

    for (business.model.Branch br : system.getBranchDirectory().getAll()) {
        if (br.getLibrary() == bk.getLibrary()) return br;
    }
    return null;
}




 private void initBrowseTab() {
    if (tblBrowse.getSelectionModel() != null) {
        tblBrowse.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    reloadBrowseTable(tfKeyword.getText().trim());
}


    private void reloadBrowseTable(String key) {
    if (system == null) return;

    business.model.Branch chosen =
            cbBranch == null ? null : (business.model.Branch) cbBranch.getSelectedItem();

    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object[]{"SN", "Book", "Author", "Branch", "Available"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
        @Override public Class<?> getColumnClass(int idx) {
            return idx == 4 ? Boolean.class : Object.class;
        }
    };

    String kw = key == null ? "" : key.toLowerCase();

    for (business.model.book.Book bk : system.getBookDirectory().getAll()) {

        business.model.Branch bookBr = findBranchOfBook(bk);
        if (chosen != null && bookBr != chosen) continue;

        if (!kw.isEmpty()) {
            String title = bk.getName() == null ? "" : bk.getName().toLowerCase();
            String auth = bk.getAuthor() == null ? "" : bk.getAuthor().getName().toLowerCase();
            if (!title.contains(kw) && !auth.contains(kw)) continue;
        }

        model.addRow(new Object[]{
                bk.getSerial(),
                bk,
                (bk.getAuthor() == null ? "-" : bk.getAuthor().getName()),
                (bookBr == null ? "-" : bookBr.getName()),
                !bk.isRented()
        });
    }

    tblBrowse.setModel(model);
    if (tblBrowse.getColumnModel().getColumnCount() > 0) {
        tblBrowse.getColumnModel().getColumn(1).setPreferredWidth(220);
    }
}



    // ========= Rentals Tab =========
    private void initRentalsTab() {
    if (tblRentals.getSelectionModel() != null) {
        tblRentals.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    initHistoryTab();
    reloadMyRentals();
}


    private void reloadMyRentals() {
    if (system == null) return;

    // ---- 先做逾期判定 ----
    java.time.LocalDate today = java.time.LocalDate.now();
    for (RentalRecord r : system.getRentalDirectory().getAll()) {
        if (r.getReturnDate() == null && today.isAfter(r.getDueDate())) {
            r.setStatus(RentalStatus.OVERDUE);
        }
    }

    // Return 表（只显示当前未归还）
    javax.swing.table.DefaultTableModel modelReturn = new javax.swing.table.DefaultTableModel(
            new Object[]{"Book","Start","Due","Return","Status"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };

    // History 表（显示所有记录）
    javax.swing.table.DefaultTableModel modelHistory = new javax.swing.table.DefaultTableModel(
            new Object[]{"Book","Start","Due","Return","Status"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };

    for (RentalRecord r : system.getRentalDirectory().getAll()) {
        if (!username.equals(r.getCustomerUsername())) continue;

        Object[] row = new Object[]{
                r.getBook(),
                r.getStartDate(),
                r.getDueDate(),
                r.getReturnDate() == null ? "-" : r.getReturnDate(),
                r.getStatus()
        };

        // 所有记录进 History
        modelHistory.addRow(row);

        // 只有 active（BORROWED） 的进 Return
        if (r.isActive()) {
            modelReturn.addRow(row);
        }
    }

    // 填充 Return 表（tblRentals）
    tblRentals.setModel(modelReturn);
    if (tblRentals.getColumnModel().getColumnCount() > 0) {
        tblRentals.getColumnModel().getColumn(0).setPreferredWidth(220);
    }

    // 填充 History 表
    if (tblHistory != null) {
        tblHistory.setModel(modelHistory);
        if (tblHistory.getColumnModel().getColumnCount() > 0) {
            tblHistory.getColumnModel().getColumn(0).setPreferredWidth(220);
        }
    }
}

/** 创建 History tab（只负责 UI 布局，数据之后由 reloadMyRentals 填充） */
private void initHistoryTab() {
    historyPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
    tblHistory = new javax.swing.JTable();
    spHistory = new javax.swing.JScrollPane(tblHistory);
    historyPanel.add(spHistory, java.awt.BorderLayout.CENTER);
    tabbed.addTab("History", historyPanel);
}



    // ========= 事件绑定 =========
    private void bindCustomerEvents() {
    if (system == null) return;

    btnSearch.addActionListener(evt ->
            reloadBrowseTable(tfKeyword.getText().trim()));

    btnBrowse.addActionListener(evt -> onBorrow());

    btnReturn.addActionListener(evt -> onReturn());
}


    // ========= 借书 =========
    private void onBorrow() {
    if (system == null) return;

    int row = tblBrowse.getSelectedRow();
    if (row < 0) { msg("Please select a book."); return; }

    business.model.book.Book bk =
            (business.model.book.Book) tblBrowse.getValueAt(row, 0);

    if (bk.isRented()) { msg("This book has already been borrowed."); return; }

    int count = 0;
    for (RentalRecord r : system.getRentalDirectory().getAll()) {
        if (!username.equals(r.getCustomerUsername())) continue;

        if (r.isActive()) {
            count++;
            if (r.getBook() == bk) {
                msg("You already borrowed this book.");
                return;
            }
        }
    }
    if (count >= 3) {
        msg("You already have 3 active loans.");
        return;
    }

    bk.setRented(true);
    java.time.LocalDate now = java.time.LocalDate.now();
    RentalRecord rec = system.getRentalDirectory().create(
            bk, username, now, now.plusDays(14));
    rec.setStatus(RentalStatus.BORROWED);

    reloadBrowseTable(tfKeyword.getText().trim());
    reloadMyRentals();
    msg("Borrowed: " + bk);
}




    // ========= 归还 =========
    private void onReturn() {
    if (system == null) return;

    int row = tblRentals.getSelectedRow();
    if (row < 0) { msg("Please select a rental."); return; }

    business.model.book.Book target =
            (business.model.book.Book) tblRentals.getValueAt(row, 0);

    RentalRecord active = null;
    for (RentalRecord r : system.getRentalDirectory().getAll()) {
        if (username.equals(r.getCustomerUsername()) &&
            r.getBook() == target &&
            r.getStatus() == RentalStatus.BORROWED) {
            active = r;
            break;
        }
    }

    if (active == null) { msg("No active rental found."); return; }

    target.setRented(false);
    active.setReturnDate(java.time.LocalDate.now());
    active.setStatus(RentalStatus.RETURNED);

    reloadBrowseTable(tfKeyword.getText().trim());
    reloadMyRentals();
    msg("Returned: " + target);
}


    // ========= 弹窗工具 =========
    private static void msg(String s) {
        javax.swing.JOptionPane.showMessageDialog(null, s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbed = new javax.swing.JTabbedPane();
        browsePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfKeyword = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnBrowse = new javax.swing.JButton();
        spBrowse = new javax.swing.JScrollPane();
        tblBrowse = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        rentalsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        spRentals = new javax.swing.JScrollPane();
        tblRentals = new javax.swing.JTable();
        btnReturn = new javax.swing.JButton();

        jLabel1.setText("KeyWord");

        btnSearch.setText("Search");

        btnBrowse.setText("Borrow");

        tblBrowse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book", "Author", "Page", "Language", "Registered", "Rented"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        spBrowse.setViewportView(tblBrowse);

        jButton1.setText("Log out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout browsePanelLayout = new javax.swing.GroupLayout(browsePanel);
        browsePanel.setLayout(browsePanelLayout);
        browsePanelLayout.setHorizontalGroup(
            browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(49, 49, 49))
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(spBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(btnBrowse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(78, 78, 78))
        );
        browsePanelLayout.setVerticalGroup(
            browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browsePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(41, 41, 41)
                .addComponent(spBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(browsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowse)
                    .addComponent(jButton1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tabbed.addTab("Browse", browsePanel);

        jLabel2.setText("My Rentals");

        tblRentals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book", "Start", "Due", "Return", "Status"
            }
        ));
        spRentals.setViewportView(tblRentals);

        btnReturn.setText("Return");

        javax.swing.GroupLayout rentalsPanelLayout = new javax.swing.GroupLayout(rentalsPanel);
        rentalsPanel.setLayout(rentalsPanelLayout);
        rentalsPanelLayout.setHorizontalGroup(
            rentalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentalsPanelLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(rentalsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReturn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spRentals, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        rentalsPanelLayout.setVerticalGroup(
            rentalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rentalsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addComponent(spRentals, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReturn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbed.addTab("My Rentals", rentalsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbed, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabbed)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
    if (win != null) {
        win.dispose();                      // 关掉 WorkAreaFrame
    }
    new ui.LoginFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel browsePanel;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel rentalsPanel;
    private javax.swing.JScrollPane spBrowse;
    private javax.swing.JScrollPane spRentals;
    private javax.swing.JTabbedPane tabbed;
    private javax.swing.JTable tblBrowse;
    private javax.swing.JTable tblRentals;
    private javax.swing.JTextField tfKeyword;
    // End of variables declaration//GEN-END:variables
}
