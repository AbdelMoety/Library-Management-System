package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Logic.adminOperations;
import Logic.Operations;
import Main.start;
import models.Student;
import models.book;
import models.borrowedBook;

public class LibraryManagementGUI extends Application
{
    
    private Stage primaryStage;
    private Scene loginScene;
    private Scene mainScene;
    private TabPane mainTabPane;
    private Operations operations = new Operations();
    
    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Library Management System");
        
        createLoginScene();
        createMainScene();
        
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private void createLoginScene()
    {
        VBox loginLayout = new VBox(20);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(50));
        loginLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #667eea 0%, #764ba2 100%);");
        
        // Title
        Label titleLabel = new Label("Library Management System");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.WHITE);
        
        // Login form container
        VBox formContainer = new VBox(15);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(30));
        formContainer.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10;");
        formContainer.setMaxWidth(350);
        
        Label loginLabel = new Label("Admin Login");
        loginLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        loginLabel.setTextFill(Color.DARKSLATEGRAY);
        
        TextField idField = new TextField();
        idField.setPromptText("Admin ID");
        idField.setStyle("-fx-font-size: 14; -fx-padding: 10;");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-font-size: 14; -fx-padding: 10;");
        
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20; -fx-background-radius: 5;");
        loginButton.setPrefWidth(150);
        
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        
        loginButton.setOnAction(e -> {
            try {
                int adminId = Integer.parseInt(idField.getText());
                String password = passwordField.getText();
                
                if (adminOperations.login(adminId, password)) {
                    primaryStage.setScene(mainScene);
                    primaryStage.setMaximized(true);
                    primaryStage.setResizable(true);
                } else {
                    errorLabel.setText("Invalid credentials. Please try again.");
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter a valid admin ID.");
            }
        });
        
        formContainer.getChildren().addAll(loginLabel, idField, passwordField, loginButton, errorLabel);
        loginLayout.getChildren().addAll(titleLabel, formContainer);
        
        loginScene = new Scene(loginLayout, 500, 400);
    }
    
    private void createMainScene() {
        BorderPane mainLayout = new BorderPane();
        
        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #2c3e50;");
        
        Label headerLabel = new Label("Library Management System - Admin Panel");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        headerLabel.setTextFill(Color.WHITE);
        
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 8 15; -fx-background-radius: 5;");
        logoutButton.setOnAction(e -> {
            primaryStage.setScene(loginScene);
            primaryStage.setMaximized(false);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
        });
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        header.getChildren().addAll(headerLabel, spacer, logoutButton);
        
        // Main content with tabs
        mainTabPane = new TabPane();
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab adminTab = createAdminOperationsTab();
        Tab operationsTab = createOperationsTab();
        
        mainTabPane.getTabs().addAll(adminTab, operationsTab);
        
        mainLayout.setTop(header);
        mainLayout.setCenter(mainTabPane);
        
        mainScene = new Scene(mainLayout, 1000, 700);
    }
    
    private Tab createAdminOperationsTab()
    {
        Tab tab = new Tab("Admin Operations");
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // Add Book Section
        VBox addBookSection = createSection("Add Book");
        GridPane addBookGrid = new GridPane();
        addBookGrid.setHgap(10);
        addBookGrid.setVgap(10);
        
        TextField bookIdField = new TextField();
        TextField bookNameField = new TextField();
        TextField authorField = new TextField();
        TextField countField = new TextField();
        
        addBookGrid.add(new Label("Book ID:"), 0, 0);
        addBookGrid.add(bookIdField, 1, 0);
        addBookGrid.add(new Label("Book Name:"), 0, 1);
        addBookGrid.add(bookNameField, 1, 1);
        addBookGrid.add(new Label("Author:"), 2, 0);
        addBookGrid.add(authorField, 3, 0);
        addBookGrid.add(new Label("Count:"), 2, 1);
        addBookGrid.add(countField, 3, 1);
        
        Button addBookButton = createActionButton("Add Book");
        Label addBookResult = new Label();
        
        addBookButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(bookIdField.getText());
                String name = bookNameField.getText();
                String author = authorField.getText();
                int count = Integer.parseInt(countField.getText());
                
                if (adminOperations.addBook(id, name, author, count)) {
                    String result = start.getBookTree().add(new book(id, name, author, count));
                    addBookResult.setText(result);
                    addBookResult.setTextFill(Color.GREEN);
                    bookIdField.clear();
                    bookNameField.clear();
                    authorField.clear();
                    countField.clear();
                } else {
                    addBookResult.setText("Failed to add book.");
                    addBookResult.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                addBookResult.setText("Please enter valid numeric values.");
                addBookResult.setTextFill(Color.RED);
            }
        });
        
        addBookSection.getChildren().addAll(addBookGrid, addBookButton, addBookResult);
        
        // Delete Book Section
        VBox deleteBookSection = createSection("Delete Book");
        HBox deleteBookBox = new HBox(10);
        deleteBookBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField deleteIdField = new TextField();
        deleteIdField.setPromptText("Book ID");
        TextField deleteCountField = new TextField();
        deleteCountField.setPromptText("Count to delete");
        
        Button deleteBookButton = createActionButton("Delete Book");
        Label deleteBookResult = new Label();
        
        deleteBookButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(deleteIdField.getText());
                int count = Integer.parseInt(deleteCountField.getText());
                
                if (adminOperations.deleteBook(id, count)) {
                    deleteBookResult.setText("Book deleted successfully.");
                    deleteBookResult.setTextFill(Color.GREEN);
                    deleteIdField.clear();
                    deleteCountField.clear();
                } else {
                    deleteBookResult.setText("Failed to delete book. Check ID and count.");
                    deleteBookResult.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                deleteBookResult.setText("Please enter valid numeric values.");
                deleteBookResult.setTextFill(Color.RED);
            }
        });
        
        deleteBookBox.getChildren().addAll(new Label("Book ID:"), deleteIdField, 
                                          new Label("Count:"), deleteCountField, deleteBookButton);
        deleteBookSection.getChildren().addAll(deleteBookBox, deleteBookResult);
        
        // Modify Book Section
        VBox modifyBookSection = createSection("Modify Book ID");
        HBox modifyBookBox = new HBox(10);
        modifyBookBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField oldIdField = new TextField();
        oldIdField.setPromptText("Old Book ID");
        TextField newIdField = new TextField();
        newIdField.setPromptText("New Book ID");
        
        Button modifyButton = createActionButton("Modify Book ID");
        Label modifyResult = new Label();
        
        modifyButton.setOnAction(e -> {
            try {
                int oldId = Integer.parseInt(oldIdField.getText());
                int newId = Integer.parseInt(newIdField.getText());
                
                if (adminOperations.modifyBookID(oldId, newId)) {
                    modifyResult.setText("Book ID modified successfully.");
                    modifyResult.setTextFill(Color.GREEN);
                    oldIdField.clear();
                    newIdField.clear();
                } else {
                    modifyResult.setText("Failed to modify book ID.");
                    modifyResult.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                modifyResult.setText("Please enter valid numeric values.");
                modifyResult.setTextFill(Color.RED);
            }
        });
        
        modifyBookBox.getChildren().addAll(new Label("Old ID:"), oldIdField, 
                                          new Label("New ID:"), newIdField, modifyButton);
        modifyBookSection.getChildren().addAll(modifyBookBox, modifyResult);
        
        // Undo Operation Section
        VBox undoSection = createSection("Undo Last Operation");
        Button undoButton = createActionButton("Undo Last Operation");
        Label undoResult = new Label();
        
        undoButton.setOnAction(e -> {
            if (adminOperations.undoLastOperation()) {
                undoResult.setText("Last operation undone successfully.");
                undoResult.setTextFill(Color.GREEN);
            } else {
                undoResult.setText("No operations to undo or undo failed.");
                undoResult.setTextFill(Color.RED);
            }
        });
        
        undoSection.getChildren().addAll(undoButton, undoResult);
        
        // Show Books Section
        VBox showBooksSection = createSection("Show All Books");
        Button showBooksButton = createActionButton("Show All Books");
        TextArea booksDisplay = new TextArea();
        booksDisplay.setEditable(false);
        booksDisplay.setPrefRowCount(8);
        
        showBooksButton.setOnAction(e -> {
            booksDisplay.clear();
            if (start.getBookTree().getRoot() != null)
            {
                models.book[] books = start.getBookTree().showBooks(start.getBookTree().getRoot());
                StringBuilder historyText = new StringBuilder();
                for (int i = 0; i < books.length; i++)
                {
                historyText.append("Name: ").append(books[i].getName())
                            .append(", ID: ").append(books[i].getId())
                            .append(", Author: ").append(books[i].getAuthor())
                            .append(", Quantity: ").append(books[i].getCount())
                            .append("\n");
                }

                booksDisplay.setText(historyText.toString());
            }
            
            else
            {
                booksDisplay.setText("No books available.");
            }
        });
        
        showBooksSection.getChildren().addAll(showBooksButton, booksDisplay);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        content.getChildren().addAll(addBookSection, deleteBookSection, modifyBookSection, 
                                   undoSection, showBooksSection);
        
        tab.setContent(scrollPane);
        return tab;
    }
    
    private Tab createOperationsTab() {
        Tab tab = new Tab("Library Operations");
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // Add Student Section
        VBox addStudentSection = createSection("Add New Student");
        HBox addStudentBox = new HBox(10);
        addStudentBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField studentNameField = new TextField();
        studentNameField.setPromptText("Student Name");
        ComboBox<Integer> yearComboBox = new ComboBox<>();
        yearComboBox.getItems().addAll(1, 2, 3, 4, 5);
        yearComboBox.setPromptText("Year");
        
        Button addStudentButton = createActionButton("Add Student");
        Label addStudentResult = new Label();
        
        addStudentButton.setOnAction(e -> {
            String name = studentNameField.getText();
            Integer year = yearComboBox.getValue();
            
            if (name.isEmpty() || year == null) {
                addStudentResult.setText("Please fill in all fields.");
                addStudentResult.setTextFill(Color.RED);
                return;
            }
            
            int studentId = operations.newStudent(name, year);
            addStudentResult.setText("Student added successfully! ID: " + studentId);
            addStudentResult.setTextFill(Color.GREEN);
            studentNameField.clear();
            yearComboBox.setValue(null);
        });
        
        addStudentBox.getChildren().addAll(new Label("Name:"), studentNameField, 
                                          new Label("Year:"), yearComboBox, addStudentButton);
        addStudentSection.getChildren().addAll(addStudentBox, addStudentResult);
        
        // Borrow Book Section
        VBox borrowSection = createSection("Borrow Book");
        GridPane borrowGrid = new GridPane();
        borrowGrid.setHgap(10);
        borrowGrid.setVgap(10);
        
        TextField borrowStudentIdField = new TextField();
        TextField borrowBookIdField = new TextField();
        
        borrowGrid.add(new Label("Student ID:"), 0, 0);
        borrowGrid.add(borrowStudentIdField, 1, 0);
        borrowGrid.add(new Label("Book ID:"), 0, 1);
        borrowGrid.add(borrowBookIdField, 1, 1);
        
        Button borrowButton = createActionButton("Borrow Book");
        Label borrowResult = new Label();
        
        borrowButton.setOnAction(e -> {
            try {
                int studentId = Integer.parseInt(borrowStudentIdField.getText());
                int bookId = Integer.parseInt(borrowBookIdField.getText());
                Data_Structures.hashTable[] table = start.getStudenTables();
                
                // Find student
                Student student = null;
                for (int i = 0; i < start.getNumYears(); i++) {
                    student = table[i].getUser(studentId);
                    if (student != null) break;
                }
                
                if (student == null) {
                    borrowResult.setText("Student not found.");
                    borrowResult.setTextFill(Color.RED);
                    return;
                }
                
                book book = start.getBookTree().search(bookId);
                if (book == null) {
                    borrowResult.setText("Book not found.");
                    borrowResult.setTextFill(Color.RED);
                    return;
                }
                
                if (Operations.borrow(student, book)) {
                    borrowResult.setText("Book borrowed successfully!");
                    borrowResult.setTextFill(Color.GREEN);
                    borrowStudentIdField.clear();
                    borrowBookIdField.clear();
                } else {
                    borrowResult.setText("Cannot borrow book. Check availability or student's borrow limit.");
                    borrowResult.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                borrowResult.setText("Please enter valid numeric values.");
                borrowResult.setTextFill(Color.RED);
            }
        });
        
        borrowSection.getChildren().addAll(borrowGrid, borrowButton, borrowResult);
        
        // Return Book Section
        VBox returnSection = createSection("Return Book");
        GridPane returnGrid = new GridPane();
        returnGrid.setHgap(10);
        returnGrid.setVgap(10);
        
        TextField returnStudentIdField = new TextField();
        TextField returnBookIdField = new TextField();
        
        returnGrid.add(new Label("Student ID:"), 0, 0);
        returnGrid.add(returnStudentIdField, 1, 0);
        returnGrid.add(new Label("Book ID:"), 0, 1);
        returnGrid.add(returnBookIdField, 1, 1);
        
        Button returnButton = createActionButton("Return Book");
        Label returnResult = new Label();
        
        returnButton.setOnAction(e -> {
            try {
                int studentId = Integer.parseInt(returnStudentIdField.getText());
                int bookId = Integer.parseInt(returnBookIdField.getText());
                Data_Structures.hashTable[] table = start.getStudenTables();
                
                // Find student
                Student student = null;
                for (int i = 0; i < start.getNumYears(); i++) {
                    student = table[i].getUser(studentId);
                    if (student != null) break;
                }
                
                if (student == null) {
                    returnResult.setText("Student not found.");
                    returnResult.setTextFill(Color.RED);
                    return;
                }
                
                borrowedBook borrowedBook = student.getBorrowHistory().getBB(bookId);
                if (borrowedBook == null) {
                    returnResult.setText("This book is not in student's borrow history.");
                    returnResult.setTextFill(Color.RED);
                    return;
                }
                
                if (Operations.returnBook(student, borrowedBook)) {
                    returnResult.setText("Book returned successfully!");
                    returnResult.setTextFill(Color.GREEN);
                    returnStudentIdField.clear();
                    returnBookIdField.clear();
                } else {
                    returnResult.setText("Failed to return book.");
                    returnResult.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                returnResult.setText("Please enter valid numeric values.");
                returnResult.setTextFill(Color.RED);
            }
        });
        
        returnSection.getChildren().addAll(returnGrid, returnButton, returnResult);
        
        // Show Student History Section
        VBox historySection = createSection("Show Student History");
        HBox historyBox = new HBox(10);
        historyBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField historyStudentIdField = new TextField();
        historyStudentIdField.setPromptText("Student ID");
        
        Button showHistoryButton = createActionButton("Show History");
        TextArea historyDisplay = new TextArea();
        historyDisplay.setEditable(false);
        historyDisplay.setPrefRowCount(6);
        
        showHistoryButton.setOnAction(e -> {
            try {
                int studentId = Integer.parseInt(historyStudentIdField.getText());
                Data_Structures.hashTable[] table = start.getStudenTables();
                
                // Find student
                Student student = null;
                for (int i = 0; i < start.getNumYears(); i++)
                {
                    student = table[i].getUser(studentId);
                    if (student != null) break;
                }
                
                if (student == null)
                {
                    historyDisplay.setText("Student not found.");
                    return;
                }
                
                if (student.getBorrowHistory().is_Empty())
                {
                    historyDisplay.setText("No borrow history for this student.");
                }
                
                else
                {
                    models.borrowedBook[] borrowHis = operations.showHistory(student);

                    StringBuilder historyText = new StringBuilder();
                    for (int i = 0; i < borrowHis.length; i++) {
                    historyText.append("Name: ").append(borrowHis[i].getName())
                                .append(", ID: ").append(borrowHis[i].getId())
                                .append(", Borrow date: ").append(borrowHis[i].getBorrowDate())
                                .append(", Due date: ").append(borrowHis[i].getDueDate())
                                .append("\n");
                    }

                    historyDisplay.setText(historyText.toString());

                }
            } catch (NumberFormatException ex) {
                historyDisplay.setText("Please enter a valid student ID.");
            }
        });

        historyBox.getChildren().addAll(new Label("Student ID:"), historyStudentIdField, showHistoryButton);
        historySection.getChildren().addAll(historyBox, historyDisplay);

        // Show book waiting list Section
        VBox waitingListSection = createSection("Show book waiting list");
        HBox waitingListBox = new HBox(10);
        historyBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField waitingListBookIdField = new TextField();
        waitingListBookIdField.setPromptText("Book ID");
        
        Button showWaitingListButton = createActionButton("Show waiting list");
        TextArea WaitingListDisplay = new TextArea();
        WaitingListDisplay.setEditable(false);
        WaitingListDisplay.setPrefRowCount(6);
        
        showWaitingListButton.setOnAction(e -> {
            try
            {
                int bookId = Integer.parseInt(waitingListBookIdField.getText());
                
                // Find book
                book book = start.getBookTree().search(bookId);
                
                if (book == null)
                {
                    WaitingListDisplay.setText("Book not found.");
                    return;
                }
                
                if (book.getWaitingList().isEmpty())
                {
                    WaitingListDisplay.setText("No waiting list for this book.");
                }
                
                else
                {
                    models.Student[] waitingList = operations.displayBookWaitingList(bookId);

                    StringBuilder waitingListText = new StringBuilder();
                    for (int i = 0; i < waitingList.length; i++)
                    {
                    waitingListText.append("Name: ").append(waitingList[i].getName())
                                .append(", ID: ").append(waitingList[i].getId())
                                .append("\n");
                    }

                    WaitingListDisplay.setText(waitingListText.toString());

                }
            }
            
            catch (NumberFormatException ex)
            {
                historyDisplay.setText("Please enter a valid student ID.");
            }
        });

        waitingListBox.getChildren().addAll(new Label("Book ID:"), waitingListBookIdField, showWaitingListButton);
        waitingListSection.getChildren().addAll(waitingListBox, WaitingListDisplay);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        content.getChildren().addAll(addStudentSection, borrowSection, returnSection, historySection, waitingListSection);
        
        tab.setContent(scrollPane);
        return tab;

    }
    
    private VBox createSection(String title)
    {
        VBox section = new VBox(10);
        section.setPadding(new Insets(15));
        section.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-color: #ffffff; -fx-background-radius: 5;");
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.DARKSLATEGRAY);
        
        Separator separator = new Separator();
        
        section.getChildren().addAll(titleLabel, separator);
        return section;
    }
    
    private Button createActionButton(String text)
    {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 12; -fx-padding: 8 15; -fx-background-radius: 5;");
        return button;
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}