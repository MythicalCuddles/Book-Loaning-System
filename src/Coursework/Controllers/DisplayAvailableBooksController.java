package Coursework.Controllers;

import Coursework.Extensions.ArrayListWorker;
import Coursework.Handlers.DialogBoxHandler;
import Coursework.Objects.Book;
import Coursework.Objects.NonFiction;
import Coursework.Objects.Fiction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class DisplayAvailableBooksController implements Initializable{
    @FXML TableView tvBookTable;
    @FXML TableColumn<Object, String> tcBookId, tcBookTitle, tcBookAuthor, tcBookGenre;

    boolean lookingAtFiction = false;
    boolean lookingAtNonFiction = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvBookTable.setPlaceholder(new Label("Please select an option from the left."));

        tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    @FXML
    private void btnDisplayFiction(ActionEvent event) {
        ResetTable();

        lookingAtFiction = true;
        lookingAtNonFiction = false;

        ObservableList<Fiction> books = FXCollections.observableArrayList();
        for(Fiction b : Book.fictionArrayList) { if(!b.isOutOnLoan()) { books.add(b); } }

        //tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        //tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        //tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        //tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        tvBookTable.setItems(books);
    }

    @FXML
    private void btnDisplayNonFictionOnAction(ActionEvent event) {
        ResetTable();

        lookingAtFiction = false;
        lookingAtNonFiction = true;

        ObservableList<NonFiction> books = FXCollections.observableArrayList();
        for(NonFiction b : Book.nonFictionArrayList) { if(!b.isOutOnLoan()) { books.add(b); } }

        //tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        //tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        //tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        //tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        tvBookTable.setItems(books);
    }

    private void ResetTable() {
        IntStream.range(0, tvBookTable.getItems().size()).forEach(i -> tvBookTable.getItems().clear());
    }

    @FXML
    private void btnLoanBookOnAction(ActionEvent e) {
        try {
            if(tvBookTable.getItems().size() > 0) {
                if(lookingAtFiction) {
                    Fiction f = (Fiction) tvBookTable.getSelectionModel().getSelectedItem();
                    int pos = ArrayListWorker.getIndexOfIdForFiction(Book.fictionArrayList, f.getId());

                    String loaningTo = DialogBoxHandler.ShowInputDialog("Who is loaning the book?", "Please enter the name of the person taking the book out on loan.", JOptionPane.INFORMATION_MESSAGE);
                    if(loaningTo == null || (loaningTo != null && ("".equals(loaningTo))))
                    {
                        DialogBoxHandler.ShowMessageDialog("Loaning Cancelled", "No valid name was entered. The book has not been set as out on loan.", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    Fiction book = Book.fictionArrayList.get(pos);
                    book.setOutOnLoan(true);
                    book.setLoanHolder(loaningTo);
                    book.setDateOfLoan(LocalDate.now());
                    Book.fictionArrayList.set(pos, book);

                    ResetTable();
                    btnDisplayFiction(null);
                } else if(lookingAtNonFiction) {
                    NonFiction nonFiction = (NonFiction) tvBookTable.getSelectionModel().getSelectedItem();
                    int pos = ArrayListWorker.getIndexOfIdForNonFiction(Book.nonFictionArrayList, nonFiction.getId());

                    String loaningTo = DialogBoxHandler.ShowInputDialog("Who is loaning the book?", "Please enter the name of the person taking the book out on loan.", JOptionPane.INFORMATION_MESSAGE);
                    if(loaningTo == null || (loaningTo != null && ("".equals(loaningTo))))
                    {
                        DialogBoxHandler.ShowMessageDialog("Loaning Cancelled", "No valid name was entered. The book has not been set as out on loan.", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    NonFiction book = Book.nonFictionArrayList.get(pos);
                    book.setOutOnLoan(true);
                    book.setLoanHolder(loaningTo);
                    book.setDateOfLoan(LocalDate.now());
                    Book.nonFictionArrayList.set(pos, book);

                    ResetTable();
                    btnDisplayNonFictionOnAction(null);
                } else {
                    //TODO: Add message to select FICTION/NONFICTION
                }
            } else {
                DialogBoxHandler.ShowMessageDialog("Warning!", "Please select the type of books to display before being able to loan a book.", JOptionPane.WARNING_MESSAGE);
            }
        } catch (RuntimeException exception) {
            DialogBoxHandler.ShowMessageDialog("Warning!", "Before you can loan a book, you need to select the book.", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception) {
            DialogBoxHandler.ShowMessageDialog("Error!", "An unexpected error has occurred. Please try again.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent e) {
        ResetTable();

        if(lookingAtFiction) {
            ObservableList<Fiction> books = FXCollections.observableArrayList();
            for(Fiction b : Book.fictionArrayList) {
                if(!b.isOutOnLoan()) {
                    books.add(b);
                }
            }
            tvBookTable.setItems(books);
        } else if(lookingAtNonFiction) {
            ObservableList<NonFiction> books = FXCollections.observableArrayList();
            for(NonFiction b : Book.nonFictionArrayList) { if(!b.isOutOnLoan()) { books.add(b); } }
            tvBookTable.setItems(books);
        } else {
            //TODO: Add message to select FICTION/NONFICTION
        }
    }
}
