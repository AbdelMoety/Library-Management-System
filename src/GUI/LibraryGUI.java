package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import Main.start;
import Logic.Operations;
import Logic.adminOperations;
import models.*;

public class LibraryGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Student currentStudent;
    private boolean isAdminLoggedIn = false;
    
    // Components
    private JTextField studentIdField, studentNameField, studentYearField;
    private JTextField bookIdField, bookNameField, bookAuthorField, bookCountField;
    private JTextField adminIdField;
    private JPasswordField adminPasswordField;
    private JTable booksTable, historyTable, waitingListTable;
    private DefaultTableModel booksTableModel, historyTableModel, waitingListModel;
    private JTextArea outputArea;
    
    public LibraryGUI() {
        initializeGUI();
        setupComponents();
        setupEventHandlers();
    }
    
    private void initializeGUI() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        
        // Initialize data structures if not already done
        if (start.studentTables == null) {
            start.studentTables = new Data_Structures.hashTable[start.NUM_YEARS];
            for (int i = 0; i < start.NUM_YEARS; i++) {
                start.studentTables[i] = new Data_Structures.hashTable();
            }
        }
        
        // Initialize other data structures
        if (start.bookTree1 == null) {
            start.bookTree1 = new Data_Structures.bookTree();
        }
        if (start.undoStack == null) {
            start.undoStack = new Data_Structures.undoStack();
        }
    }
    
    private void setupComponents() {
        // Main Menu Panel
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "MENU");
        
        // Student Registration Panel
        JPanel registerPanel = createStudentRegistrationPanel();
        mainPanel.add(registerPanel, "REGISTER");
        
        // Student Login Panel
        JPanel studentLoginPanel = createStudentLoginPanel();
        mainPanel.add(studentLoginPanel, "STUDENT_LOGIN");
        
        // Student Dashboard Panel
        JPanel studentDashboard = createStudentDashboardPanel();
        mainPanel.add(studentDashboard, "STUDENT_DASHBOARD");
        
        // Admin Login Panel
        JPanel adminLoginPanel = createAdminLoginPanel();
        mainPanel.add(adminLoginPanel, "ADMIN_LOGIN");
        
        // Admin Dashboard Panel
        JPanel adminDashboard = createAdminDashboardPanel();
        mainPanel.add(adminDashboard, "ADMIN_DASHBOARD");
        
        // Book Browse Panel
        JPanel browsePanel = createBookBrowsePanel();
        mainPanel.add(browsePanel, "BROWSE_BOOKS");
        
        cardLayout.show(mainPanel, "MENU");
    }
    
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Library Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 102, 153));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Menu buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(50, 100, 50, 100));
        
        JButton registerBtn = createStyledButton("Register New Student", new Color(46, 125, 50));
        JButton studentLoginBtn = createStyledButton("Student Login", new Color(33, 150, 243));
        JButton adminLoginBtn = createStyledButton("Admin Login", new Color(255, 152, 0));
        JButton browseBtn = createStyledButton("Browse Books", new Color(156, 39, 176));
        
        // Add event listeners directly
        registerBtn.addActionListener(e -> cardLayout.show(mainPanel, "REGISTER"));
        studentLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "STUDENT_LOGIN"));
        adminLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "ADMIN_LOGIN"));
        browseBtn.addActionListener(e -> {
            refreshBrowseBooks();
            cardLayout.show(mainPanel, "BROWSE_BOOKS");
        });
        
        buttonPanel.add(registerBtn);
        buttonPanel.add(studentLoginBtn);
        buttonPanel.add(adminLoginBtn);
        buttonPanel.add(browseBtn);
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStudentRegistrationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Student Registration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        studentNameField = new JTextField(20);
        formPanel.add(studentNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Year (1-5):"), gbc);
        gbc.gridx = 1;
        studentYearField = new JTextField(20);
        formPanel.add(studentYearField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton registerBtn = createStyledButton("Register", new Color(46, 125, 50));
        formPanel.add(registerBtn, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        // Back button
        JButton backBtn = createStyledButton("Back to Menu", new Color(96, 125, 139));
        panel.add(backBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStudentLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Student Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        studentIdField = new JTextField(20);
        formPanel.add(studentIdField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton loginBtn = createStyledButton("Login", new Color(33, 150, 243));
        formPanel.add(loginBtn, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        // Back button
        JButton backBtn = createStyledButton("Back to Menu", new Color(96, 125, 139));
        panel.add(backBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStudentDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Title with student info
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Student Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        panel.add(titlePanel, BorderLayout.NORTH);
        
        // Main content with tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Borrow Books Tab
        JPanel borrowPanel = createBorrowBooksPanel();
        tabbedPane.addTab("Borrow Books", borrowPanel);
        
        // Return Books Tab
        JPanel returnPanel = createReturnBooksPanel();
        tabbedPane.addTab("Return Books", returnPanel);
        
        // History Tab
        JPanel historyPanel = createHistoryPanel();
        tabbedPane.addTab("Borrow History", historyPanel);
        
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        // Logout button
        JButton logoutBtn = createStyledButton("Logout", new Color(244, 67, 54));
        panel.add(logoutBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createBorrowBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Search form
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Book ID:"));
        JTextField searchField = new JTextField(15);
        searchPanel.add(searchField);
        JButton searchBtn = createStyledButton("Search & Borrow", new Color(46, 125, 50));
        searchPanel.add(searchBtn);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        // Available books table
        String[] columns = {"ID", "Name", "Author", "Available Count"};
        booksTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        booksTable = new JTable(booksTableModel);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(booksTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton borrowSelectedBtn = createStyledButton("Borrow Selected", new Color(33, 150, 243));
        JButton refreshBtn = createStyledButton("Refresh List", new Color(96, 125, 139));
        actionPanel.add(borrowSelectedBtn);
        actionPanel.add(refreshBtn);
        
        panel.add(actionPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createReturnBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Borrowed books table
        String[] columns = {"Book Name", "Borrow Date", "Due Date", "Days Left"};
        historyTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        historyTable = new JTable(historyTableModel);
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(historyTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Return button
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton returnBtn = createStyledButton("Return Selected Book", new Color(255, 152, 0));
        JButton refreshBtn = createStyledButton("Refresh", new Color(96, 125, 139));
        actionPanel.add(returnBtn);
        actionPanel.add(refreshBtn);
        
        panel.add(actionPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // History display
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Refresh button
        JButton refreshBtn = createStyledButton("Refresh History", new Color(96, 125, 139));
        panel.add(refreshBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAdminLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Admin Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Admin ID:"), gbc);
        gbc.gridx = 1;
        adminIdField = new JTextField(20);
        formPanel.add(adminIdField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        adminPasswordField = new JPasswordField(20);
        formPanel.add(adminPasswordField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton loginBtn = createStyledButton("Login", new Color(255, 152, 0));
        formPanel.add(loginBtn, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        // Back button
        JButton backBtn = createStyledButton("Back to Menu", new Color(96, 125, 139));
        panel.add(backBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAdminDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Tabbed pane for admin functions
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Book Management Tab
        JPanel bookMgmtPanel = createBookManagementPanel();
        tabbedPane.addTab("Book Management", bookMgmtPanel);
        
        // Student Management Tab
        JPanel studentMgmtPanel = createStudentManagementPanel();
        tabbedPane.addTab("Student Management", studentMgmtPanel);
        
        // Reports Tab
        JPanel reportsPanel = createReportsPanel();
        tabbedPane.addTab("Reports", reportsPanel);
        
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        // Logout button
        JButton logoutBtn = createStyledButton("Logout", new Color(244, 67, 54));
        panel.add(logoutBtn, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createBookManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Add book form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add/Modify Books"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        bookIdField = new JTextField(15);
        formPanel.add(bookIdField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        bookNameField = new JTextField(15);
        formPanel.add(bookNameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        bookAuthorField = new JTextField(15);
        formPanel.add(bookAuthorField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Count:"), gbc);
        gbc.gridx = 1;
        bookCountField = new JTextField(15);
        formPanel.add(bookCountField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = createStyledButton("Add Book", new Color(46, 125, 50));
        JButton deleteBtn = createStyledButton("Delete Book", new Color(244, 67, 54));
        JButton modifyBtn = createStyledButton("Modify Book", new Color(255, 152, 0));
        JButton undoBtn = createStyledButton("Undo Last Action", new Color(96, 125, 139));
        
        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(modifyBtn);
        buttonPanel.add(undoBtn);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        panel.add(formPanel, BorderLayout.NORTH);
        
        // Books list
        String[] columns = {"ID", "Name", "Author", "Count", "Available"};
        DefaultTableModel adminBooksModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable adminBooksTable = new JTable(adminBooksModel);
        JScrollPane scrollPane = new JScrollPane(adminBooksTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStudentManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Student ID:"));
        JTextField studentSearchField = new JTextField(15);
        searchPanel.add(studentSearchField);
        JButton searchStudentBtn = createStyledButton("Search Student", new Color(33, 150, 243));
        searchPanel.add(searchStudentBtn);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        // Student info display
        JTextArea studentInfoArea = new JTextArea(20, 50);
        studentInfoArea.setEditable(false);
        studentInfoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(studentInfoArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Report buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JButton allBooksBtn = createStyledButton("Show All Books", new Color(33, 150, 243));
        JButton authorBooksBtn = createStyledButton("Books by Author", new Color(46, 125, 50));
        JButton allStudentsBtn = createStyledButton("Show All Students", new Color(156, 39, 176));
        JButton overdueBtn = createStyledButton("Overdue Books", new Color(244, 67, 54));
        JButton waitingListBtn = createStyledButton("Waiting Lists", new Color(255, 152, 0));
        JButton statsBtn = createStyledButton("Library Statistics", new Color(96, 125, 139));
        
        buttonPanel.add(allBooksBtn);
        buttonPanel.add(authorBooksBtn);
        buttonPanel.add(allStudentsBtn);
        buttonPanel.add(overdueBtn);
        buttonPanel.add(waitingListBtn);
        buttonPanel.add(statsBtn);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        
        // Report display area
        JTextArea reportArea = new JTextArea(15, 50);
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(reportArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBookBrowsePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Browse Available Books", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Books display
        String[] columns = {"ID", "Name", "Author", "Available Copies"};
        DefaultTableModel browseModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable browseTable = new JTable(browseModel);
        JScrollPane scrollPane = new JScrollPane(browseTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton refreshBtn = createStyledButton("Refresh", new Color(33, 150, 243));
        JButton backBtn = createStyledButton("Back to Menu", new Color(96, 125, 139));
        controlPanel.add(refreshBtn);
        controlPanel.add(backBtn);
        
        panel.add(controlPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(180, 35));
        return button;
    }
    
    private void setupEventHandlers() {
        // Menu navigation handlers
        setupMenuHandlers();
        
        // Student registration handler
        setupStudentRegistrationHandlers();
        
        // Student login handlers
        setupStudentLoginHandlers();
        
        // Student dashboard handlers
        setupStudentDashboardHandlers();
        
        // Admin login handlers
        setupAdminLoginHandlers();
        
        // Admin dashboard handlers
        setupAdminDashboardHandlers();
        
        // Browse books handlers
        setupBrowseBooksHandlers();
    }
    
    private void setupMenuHandlers() {
        // We'll set up menu handlers when creating the menu panel
        // This avoids the casting issues
    }
    
    private void setupStudentRegistrationHandlers() {
        // Get the register panel components
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (p.getComponent(0) instanceof JLabel) {
                    JLabel label = (JLabel) p.getComponent(0);
                    if ("Student Registration".equals(label.getText())) {
                        setupRegistrationPanel(p);
                        break;
                    }
                }
            }
        }
    }
    
    private void setupRegistrationPanel(JPanel panel) {
        // Find and setup registration button
        findAndSetupButton(panel, "Register", e -> handleStudentRegistration());
        findAndSetupButton(panel, "Back to Menu", e -> cardLayout.show(mainPanel, "MENU"));
    }
    
    private void setupStudentLoginHandlers() {
        // Similar pattern for student login
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (hasTitle(p, "Student Login")) {
                    findAndSetupButton(p, "Login", e -> handleStudentLogin());
                    findAndSetupButton(p, "Back to Menu", e -> cardLayout.show(mainPanel, "MENU"));
                    break;
                }
            }
        }
    }
    
    private void setupStudentDashboardHandlers() {
        // Setup student dashboard events
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (hasTitle(p, "Student Dashboard")) {
                    findAndSetupButton(p, "Logout", e -> {
                        currentStudent = null;
                        cardLayout.show(mainPanel, "MENU");
                    });
                    break;
                }
            }
        }
    }
    
    private void setupAdminLoginHandlers() {
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (hasTitle(p, "Admin Login")) {
                    findAndSetupButton(p, "Login", e -> handleAdminLogin());
                    findAndSetupButton(p, "Back to Menu", e -> cardLayout.show(mainPanel, "MENU"));
                    break;
                }
            }
        }
    }
    
    private void setupAdminDashboardHandlers() {
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (hasTitle(p, "Admin Dashboard")) {
                    findAndSetupButton(p, "Logout", e -> {
                        isAdminLoggedIn = false;
                        cardLayout.show(mainPanel, "MENU");
                    });
                    break;
                }
            }
        }
    }
    
    private void setupBrowseBooksHandlers() {
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (hasTitle(p, "Browse Available Books")) {
                    findAndSetupButton(p, "Refresh", e -> refreshBrowseBooks());
                    findAndSetupButton(p, "Back to Menu", e -> cardLayout.show(mainPanel, "MENU"));
                    break;
                }
            }
        }
    }
    
    private boolean hasTitle(JPanel panel, String title) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (title.equals(label.getText())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void findAndSetupButton(Container container, String buttonText, ActionListener listener) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (buttonText.equals(button.getText())) {
                    button.addActionListener(listener);
                    return;
                }
            } else if (comp instanceof Container) {
                findAndSetupButton((Container) comp, buttonText, listener);
            }
        }
    }
    
    private void handleStudentRegistration() {
        try {
            String name = studentNameField.getText().trim();
            String yearText = studentYearField.getText().trim();
            
            if (name.isEmpty() || yearText.isEmpty()) {
                showMessage("Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int year = Integer.parseInt(yearText);
            if (year < 1 || year > 5) {
                showMessage("Year must be between 1 and 5", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Operations ops = new Operations();
            int newId = ops.newStudent(name, year);
            
            showMessage("Registration successful!\nYour Student ID is: " + newId, 
                       "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid year (1-5)", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            showMessage("Registration failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleStudentLogin() {
        try {
            String idText = studentIdField.getText().trim();
            if (idText.isEmpty()) {
                showMessage("Please enter your Student ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            
            // Find student in appropriate year table
            Student student = null;
            for (int i = 0; i < start.NUM_YEARS; i++) {
                student = start.studentTables[i].getUser(id);
                if (student != null) break;
            }
            
            if (student != null) {
                currentStudent = student;
                showMessage("Welcome, " + student.name + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                refreshStudentData();
                cardLayout.show(mainPanel, "STUDENT_DASHBOARD");
            } else {
                showMessage("Student ID not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid Student ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleAdminLogin() {
        try {
            String idText = adminIdField.getText().trim();
            String password = new String(adminPasswordField.getPassword());
            
            if (idText.isEmpty() || password.isEmpty()) {
                showMessage("Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            
            if (adminOperations.login(id, password)) {
                isAdminLoggedIn = true;
                showMessage("Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, "ADMIN_DASHBOARD");
            } else {
                showMessage("Invalid admin credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid Admin ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refreshStudentData() {
        // Refresh student dashboard data
        if (currentStudent != null) {
            // Update borrow history table and other student-specific data
        }
    }
    
    private void refreshBrowseBooks() {
        // Get the browse books panel and update its table
        Component[] panels = mainPanel.getComponents();
        for (Component panel : panels) {
            if (panel instanceof JPanel) {
                JPanel p = (JPanel) panel;
                if (hasTitle(p, "Browse Available Books")) {
                    // Find the table and update it
                    JTable table = findTable(p);
                    if (table != null) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0); // Clear existing data
                        
                        // Add books from the tree (this would need to be implemented)
                        // For now, add some sample data
                        if (start.bookTree1.root != null) {
                            populateTableFromTree(model, start.bookTree1.root);
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private void populateTableFromTree(DefaultTableModel model, models.tNode node) {
        if (node != null) {
            populateTableFromTree(model, node.left);
            
            // Add current node's book to table
            Object[] row = {
                node.book.id,
                node.book.name,
                node.book.author,
                node.book.count
            };
            model.addRow(row);
            
            populateTableFromTree(model, node.right);
        }
    }
    
    private JTable findTable(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTable) {
                return (JTable) comp;
            } else if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                if (scrollPane.getViewport().getView() instanceof JTable) {
                    return (JTable) scrollPane.getViewport().getView();
                }
            } else if (comp instanceof Container) {
                JTable table = findTable((Container) comp);
                if (table != null) return table;
            }
        }
        return null;
    }
    
    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    
    private void clearFields() {
        if (studentNameField != null) studentNameField.setText("");
        if (studentYearField != null) studentYearField.setText("");
        if (studentIdField != null) studentIdField.setText("");
        if (bookIdField != null) bookIdField.setText("");
        if (bookNameField != null) bookNameField.setText("");
        if (bookAuthorField != null) bookAuthorField.setText("");
        if (bookCountField != null) bookCountField.setText("");
        if (adminIdField != null) adminIdField.setText("");
        if (adminPasswordField != null) adminPasswordField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new LibraryGUI().setVisible(true);
        });
    }
}