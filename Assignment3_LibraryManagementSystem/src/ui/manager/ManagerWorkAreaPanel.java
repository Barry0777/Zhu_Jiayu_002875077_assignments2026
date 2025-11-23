/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.manager;

import business.LibrarySystem;
import business.model.Library;
import business.model.book.Author;
import business.model.book.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xuanliliu
 */
public class ManagerWorkAreaPanel extends javax.swing.JPanel {
 private final LibrarySystem system;
    private final Library library; // 当前馆
    
    
     private javax.swing.JPanel authorsPanel;
    private javax.swing.JTable tblAuthors;
    private javax.swing.JScrollPane spAuthors;
    private javax.swing.JTextField tfAuthorName;
    private javax.swing.JButton btnAddAuthor;
    
    public ManagerWorkAreaPanel(LibrarySystem system, Library library) {
        this.system = system;
        this.library = library;
        initComponents();
        

        initBooksTab();
        initAuthorsTab(); 
        initRentalsTab();
        bindEvents();
    }
    private void initBooksTab() {
    if (system == null) return;
    populateAuthorCombo();
    refreshBookTable();
}


    private void populateAuthorCombo() {
    DefaultComboBoxModel<Author> m = new DefaultComboBoxModel<>();
    for (Author a : system.getAuthorDirectory().getAll()) {
        m.addElement(a);
    }
    cbAuthor.setModel(m);
}

    
    /** 初始化 Authors 这个 tab：表格 + 文本框 + Add 按钮 */
private void initAuthorsTab() {
    if (system == null) return;

    authorsPanel = new JPanel(new java.awt.BorderLayout());

    tblAuthors = new JTable();
    spAuthors = new JScrollPane(tblAuthors);
    authorsPanel.add(spAuthors, java.awt.BorderLayout.CENTER);

    JPanel form = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
    form.add(new JLabel("Name:"));
    tfAuthorName = new JTextField(15);
    form.add(tfAuthorName);
    btnAddAuthor = new JButton("Add Author");
    form.add(btnAddAuthor);
    authorsPanel.add(form, java.awt.BorderLayout.SOUTH);

    booksPanel.insertTab("Authors", null, authorsPanel, "Manage Authors", 1);

    refreshAuthorsTable();
}


/** 刷新 Authors 表格数据 */
private void refreshAuthorsTable() {
    DefaultTableModel m = new DefaultTableModel(
            new Object[]{"ID", "Name"}, 0) {
        @Override public boolean isCellEditable(int r,int c){ return false; }
        @Override public Class<?> getColumnClass(int ci){ return ci==0?Integer.class:String.class; }
    };

    for (Author a : system.getAuthorDirectory().getAll()) {
        m.addRow(new Object[]{a.getId(), a.getName()});
    }
    tblAuthors.setModel(m);
    tblAuthors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
}



    private void refreshBookTable() {
    DefaultTableModel m = new DefaultTableModel(
            new Object[]{"Title","Author","Pages","Lang","Registered","Rented"}, 0) {
        @Override public boolean isCellEditable(int r,int c){ return false; }
        @Override public Class<?> getColumnClass(int ci){ return ci==5?Boolean.class:Object.class; }
    };

    for (Book b : system.getBookDirectory().getAll()) {
        if (b.getLibrary() != library) continue;
        m.addRow(new Object[]{
                b,
                b.getAuthor()==null? "-" : b.getAuthor().getName(),
                b.getPages(),
                b.getLanguage(),
                b.getRegisteredDate(),
                b.isRented()
        });
    }

    tblBooks.setModel(m);
    if (tblBooks.getColumnModel().getColumnCount() > 0) {
        tblBooks.getColumnModel().getColumn(0).setPreferredWidth(240);
    }
}


    private void onAddAuthor() {
    String name = JOptionPane.showInputDialog(this, "New author name:");
    if (name == null) return;

    name = name.trim();
    if (name.isEmpty()) {
        msg("Name required.");
        return;
    }

    Author a = system.getAuthorDirectory().create(name);
    populateAuthorCombo();
    cbAuthor.setSelectedItem(a);
    msg("Author created: " + a.getName());
}


   private void onAddBook() {
    String title = tfTitle.getText().trim();
    String lang  = tfLang.getText().trim();

    int pages;
    try {
        pages = Integer.parseInt(tfPages.getText().trim());
    } catch (Exception e) {
        msg("Pages must be a number.");
        return;
    }

    if (title.isEmpty()) {
        msg("Title required.");
        return;
    }

    Author a = (Author) cbAuthor.getSelectedItem();

    system.getBookDirectory().create(
            title,
            java.time.LocalDate.now(),
            pages,
            lang,
            a,
            library
    );

    tfTitle.setText("");
    tfPages.setText("");
    tfLang.setText("");

    refreshBookTable();
    msg("Book added to library " + library.getBuildingNo());
}



    // ========= Rentals Tab =========
    private void initRentalsTab() {
    if (system == null) return;
    refreshRentalsTable();
}


    private void refreshRentalsTable() {
    DefaultTableModel m = new DefaultTableModel(
            new Object[]{"Book","Customer","Start","Due","Return","Status"},0){
        @Override public boolean isCellEditable(int r,int c){ return false; }
    };

    for (business.model.Rental.RentalRecord r : system.getRentalDirectory().getAll()) {
        Book b = r.getBook();
        if (b == null || b.getLibrary() != library) continue;

        m.addRow(new Object[]{
                b,
                r.getCustomerUsername(),
                r.getStartDate(),
                r.getDueDate(),
                r.getReturnDate()==null? "-" : r.getReturnDate(),
                r.getStatus()
        });
    }

    tblRentals.setModel(m);
    if (tblRentals.getColumnModel().getColumnCount() > 0) {
        tblRentals.getColumnModel().getColumn(0).setPreferredWidth(240);
    }
}


    // ========= 事件 =========
    private void bindEvents() {
    if (system == null) return;

    btnNewAuthor.addActionListener(e -> onAddAuthor());
    btnAddBook.addActionListener(e -> onAddBook());

    if (btnAddAuthor != null) {
        btnAddAuthor.addActionListener(e -> onAddAuthorInTab());
    }
}

    /** Authors tab 下方文本框的新增逻辑 */
private void onAddAuthorInTab() {
    String name = tfAuthorName.getText().trim();
    if (name.isEmpty()) {
        msg("Name required.");
        return;
    }

    Author a = system.getAuthorDirectory().create(name);

    populateAuthorCombo();
    refreshAuthorsTable();

    tfAuthorName.setText("");
    msg("Author created: " + a.getName());
}



    // ========= 小工具 =========
    private static void msg(String s){
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

        booksPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfPages = new javax.swing.JTextField();
        tfTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfLang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbAuthor = new javax.swing.JComboBox<>();
        btnNewAuthor = new javax.swing.JButton();
        btnAddBook = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        sp1 = new javax.swing.JScrollPane();
        tblRentals = new javax.swing.JTable();

        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title", "Author", "Pages", "Lang", "Registered", "Rented"
            }
        ));
        jScrollPane1.setViewportView(tblBooks);

        jLabel1.setText("Tittle");

        jLabel2.setText("Pages");

        jLabel3.setText("Lang");

        jLabel4.setText("Author");

        btnNewAuthor.setText("New Author");

        btnAddBook.setText("Add Book");

        jButton1.setText("Log out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfLang, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnNewAuthor)
                        .addGap(89, 89, 89)
                        .addComponent(btnAddBook))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(tfPages, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cbAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tfLang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfPages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cbAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNewAuthor)
                    .addComponent(btnAddBook))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(34, 34, 34))
        );

        booksPanel.addTab("Books", jPanel1);

        tblRentals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Books", "Customer", "Start", "Due", "Return", "Status"
            }
        ));
        sp1.setViewportView(tblRentals);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        booksPanel.addTab("Rentals", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(booksPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(booksPanel)
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
    private javax.swing.JTabbedPane booksPanel;
    private javax.swing.JButton btnAddBook;
    private javax.swing.JButton btnNewAuthor;
    private javax.swing.JComboBox<business.model.book.Author> cbAuthor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane sp1;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTable tblRentals;
    private javax.swing.JTextField tfLang;
    private javax.swing.JTextField tfPages;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables
}
