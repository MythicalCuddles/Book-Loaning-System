package Coursework.Controllers;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Extensions.ArrayListWorker;
import Coursework.Extensions.StringWorker;
import Coursework.Handlers.DialogBoxHandler;
import Coursework.Handlers.FileHandler;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;
import Coursework.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         DisplayBooksOnLoanController
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Controller for FXMLs/DisplayBooksOnLoan.fxml
 ******************************************************/

public class DisplayBooksOnLoanController implements Initializable {
    @FXML TableView tvBookTable;
    @FXML TableColumn<Object, String> tcBookId, tcBookTitle, tcBookAuthor, tcBookGenre, tcLoanHolder, tcDateOfLoan;
    @FXML Button btnDisplayFiction, btnDisplayNonFiction;
    @FXML TextField tfSearchID, tfSearchTitle, tfSearchAuthor;
    @FXML ComboBox cbGenre;
    String defaultButtonStyle;

    boolean lookingAtFiction = false,
            lookingAtNonFiction = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultButtonStyle = btnDisplayFiction.getStyle();

        tvBookTable.setPlaceholder(new Label("There are no available books out on loan."));
        FillTableWithAll();

        cbGenre.getItems().add("- Any -");
        cbGenre.getItems().add("- Fiction -");
        cbGenre.getItems().addAll(TypeOfFiction.values());
        cbGenre.getItems().add("- Non-Fiction -");
        cbGenre.getItems().addAll(TypeOfNonFiction.values());

        tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tcLoanHolder.setCellValueFactory(new PropertyValueFactory<>("loanHolder"));
        tcDateOfLoan.setCellValueFactory(new PropertyValueFactory<>("dateOfLoan"));
    }

    private void FillTableWithAll() {
        IntStream.range(0, tvBookTable.getItems().size()).forEach(i -> tvBookTable.getItems().clear()); // Removes all data from table.

        if(lookingAtFiction) {
            ObservableList<Fiction> books = FXCollections.observableArrayList();
            for(Fiction b : Book.fictionArrayList) { if(b.isOutOnLoan()) { books.add(b); } }

            tvBookTable.setItems(books);
        } else if(lookingAtNonFiction) {
            ObservableList<NonFiction> books = FXCollections.observableArrayList();
            for(NonFiction b : Book.nonFictionArrayList) { if(b.isOutOnLoan()) { books.add(b); } }

            tvBookTable.setItems(books);
        } else {
            ObservableList<Object> books = FXCollections.observableArrayList();
            for(Fiction f : Book.fictionArrayList) { if(f.isOutOnLoan()) { books.add(f); } }
            for(NonFiction n : Book.nonFictionArrayList) { if(n.isOutOnLoan()) { books.add(n); } }

            tvBookTable.setItems(books);
        }
    }

    @FXML
    private void btnDisplayFictionOnAction(ActionEvent event) {
        lookingAtNonFiction = false;
        btnDisplayNonFiction.setStyle(defaultButtonStyle);

        lookingAtFiction = !lookingAtFiction;
        btnDisplayFiction.setStyle(defaultButtonStyle);
        if(lookingAtFiction) btnDisplayFiction.setStyle("-fx-background-color: #1db727; ");

        FillTableWithAll();
    }

    @FXML
    private void btnDisplayNonFictionOnAction(ActionEvent event) {
        lookingAtFiction = false;
        btnDisplayFiction.setStyle(defaultButtonStyle);

        lookingAtNonFiction = !lookingAtNonFiction;
        btnDisplayNonFiction.setStyle(defaultButtonStyle);
        if(lookingAtNonFiction) btnDisplayNonFiction.setStyle("-fx-background-color: #1db727; ");

        FillTableWithAll();
    }

    @FXML
    private void btnReturnBookOnAction(ActionEvent e) {
        if(tvBookTable.getSelectionModel().getSelectedItem() == null) {
            DialogBoxHandler.ShowMessageDialog("Note", "Please select a book before performing any actions.", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            if(tvBookTable.getItems().size() > 0) {
                Fiction fiction = null;
                NonFiction nonFiction = null;
                int pos = -1;

                try {
                    fiction = (Fiction) tvBookTable.getSelectionModel().getSelectedItem();
                    pos = ArrayListWorker.getIndexOfIdForFiction(Book.fictionArrayList, fiction.getId());
                } catch(Exception exception) { }

                try {
                    nonFiction = (NonFiction) tvBookTable.getSelectionModel().getSelectedItem();
                    pos = ArrayListWorker.getIndexOfIdForNonFiction(Book.nonFictionArrayList, nonFiction.getId());
                } catch (Exception exception) { }

                if(fiction == null && nonFiction == null) {
                    DialogBoxHandler.ShowMessageDialog("Error", "The selected item could not be parsed into the Book. Please try again.", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(fiction != null) {
                    Fiction bookFromArray = Book.fictionArrayList.get(pos);
                    bookFromArray.setOutOnLoan(false);
                    bookFromArray.setLoanHolder(null);
                    bookFromArray.setDateOfLoan(null);
                    Book.fictionArrayList.set(pos, bookFromArray);

                    DialogBoxHandler.ShowMessageDialog("Book Successfully Updated", "The Book, " + bookFromArray.getTitle() + " has been returned successfully!", JOptionPane.INFORMATION_MESSAGE);

                    FileHandler.writeBooksToFile(); // Save the books to file.
                } else if(nonFiction != null) {
                    NonFiction bookFromArray = Book.nonFictionArrayList.get(pos);
                    bookFromArray.setOutOnLoan(false);
                    bookFromArray.setLoanHolder(null);
                    bookFromArray.setDateOfLoan(null);
                    Book.nonFictionArrayList.set(pos, bookFromArray);

                    DialogBoxHandler.ShowMessageDialog("Book Successfully Updated", "The Book, " + bookFromArray.getTitle() + " has been returned successfully!", JOptionPane.INFORMATION_MESSAGE);

                    FileHandler.writeBooksToFile(); // Save the books to file.
                } else {
                    DialogBoxHandler.ShowMessageDialog("Error", "An error has occurred whilst trying to set the book to returned. Please try again.", JOptionPane.ERROR_MESSAGE);
                }


                FillTableWithAll();
            } else {
                DialogBoxHandler.ShowMessageDialog("Warning!", "Please select the type of books to display before being able to return a book.", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException exception) {
            DialogBoxHandler.ShowMessageDialog("Warning!", "Before you can return a book, you need to select the book.", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception) {
            DialogBoxHandler.ShowMessageDialog("Error!", "An unexpected error has occurred. Please try again.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent e) {
        IntStream.range(0, tvBookTable.getItems().size()).forEach(i -> tvBookTable.getItems().clear()); // Removes all the data from the table.

        int searchID = -1;

        try {
            searchID = Integer.parseInt(tfSearchID.getText());
        } catch (RuntimeException exception) {
            if(tfSearchID.getText().isEmpty()) {
                searchID = -1;
            } else {
                DialogBoxHandler.ShowMessageDialog("Warning", "An invalid character/number has been entered into the Search ID box, please amend.", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (Exception exception) { }

        String searchTitle = tfSearchTitle.getText(),
                searchAuthor = tfSearchAuthor.getText();

        tvBookTable.setPlaceholder(new Label("No books were found with the search criteria provided."));

        ObservableList<Object> books = FXCollections.observableArrayList();

        for(Fiction b : Book.fictionArrayList) {
            if(meetSearchCriteria(b, searchID, searchTitle, searchAuthor)) {
                if(cbGenre.getSelectionModel().getSelectedIndex() == 1) {
                    books.add(b);
                }
                else if(cbGenre.getSelectionModel().getSelectedIndex() <= 0 || b.getGenre() == cbGenre.getSelectionModel().getSelectedItem()) {
                    books.add(b);
                }
            }
        }

        for(NonFiction b : Book.nonFictionArrayList) {
            if(meetSearchCriteria(b, searchID, searchTitle, searchAuthor)) {
                if(cbGenre.getSelectionModel().getSelectedIndex() == 9) {
                    books.add(b);
                }
                else if(cbGenre.getSelectionModel().getSelectedIndex() <= 0 || b.getGenre() == cbGenre.getSelectionModel().getSelectedItem()) {
                    books.add(b);
                }
            }
        }

        tvBookTable.setItems(books);

        btnDisplayFiction.setStyle(defaultButtonStyle);
        btnDisplayNonFiction.setStyle(defaultButtonStyle);
        lookingAtNonFiction = false;
        lookingAtFiction = false;
    }

    private boolean meetSearchCriteria(Book b, int searchID, String searchTitle, String searchAuthor) {
        if(b.isOutOnLoan()) {
            if(StringWorker.searchContains(searchTitle, b.getTitle())) {
                if(StringWorker.searchContains(searchAuthor, b.getAuthor())) {
                    return b.getId() == searchID || searchID == -1;
                }
            }
        }

        return false;
    }

    @FXML
    private void miFileQuitOnAction(ActionEvent e) {
        Program.exitProgram();
    }

    @FXML
    private void miHelpAboutOnAction(ActionEvent e) {
        Program p = new Program();
        p.loadFXML("FXMLs/AboutDialog.fxml", "About Book Loaning System", false, true);
    }
}
