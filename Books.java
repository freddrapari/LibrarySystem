/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.vu.books;
import net.ucanaccess.jdbc.UcanaccessDriver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryApp extends JFrame {
    private JTextField txtBookID, txtTitle, txtAuthor, txtYear;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private Connection connection;

    public LibraryApp() {
        // Initialize the GUI components
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Book ID:"));
        txtBookID = new JTextField();
        formPanel.add(txtBookID);
        formPanel.add(new JLabel("Title:"));
        txtTitle = new JTextField();
        formPanel.add(txtTitle);
        formPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        formPanel.add(txtAuthor);
        formPanel.add(new JLabel("Year:"));
        txtYear = new JTextField();
        formPanel.add(txtYear);

        // Buttons
        JButton btnAdd = new JButton("Add Book");
        JButton btnDelete = new JButton("Delete Book");
        JButton btnRefresh = new JButton("Refresh List");
        formPanel.add(btnAdd);
        formPanel.add(btnDelete);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.NORTH);

        // Table
        bookTable = new JTable();
        tableModel = new DefaultTableModel(new String[]{"BookID", "Title", "Author", "Year"}, 0);
        bookTable.setModel(tableModel);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        // Button actions
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBooks();
            }
        });

        // Database connection
        connectToDatabase();
        loadBooks();
    }

    private void connectToDatabase() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://Library.accdb");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
        String bookID = txtBookID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String year = txtYear.getText();

        try {
            String sql = "INSERT INTO Books (BookID, Title, Author, Year) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, bookID);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.set

