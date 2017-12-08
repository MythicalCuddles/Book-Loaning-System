package Coursework.Controllers;

import Coursework.Objects.Book;
import Coursework.Objects.NonFiction;
import Coursework.Objects.Fiction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAvailableBooksController implements Initializable{
    @FXML TableView tvBookTable;
    @FXML TableColumn<Object, String> tcBookId, tcBookTitle, tcBookAuthor, tcBookGenre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void btnDisplayFiction(ActionEvent event) {
        ResetTable();

        ObservableList<Fiction> books = FXCollections.observableArrayList();
        for(Fiction b : Book.fictionArrayList) { if(!b.isOutOnLoan()) { books.add(b); } }

        tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        tvBookTable.setItems(books);
    }

    @FXML
    private void btnDisplayNonFictionOnAction(ActionEvent event) {
        ResetTable();

        ObservableList<NonFiction> books = FXCollections.observableArrayList();
        for(NonFiction b : Book.nonFictionArrayList) { if(!b.isOutOnLoan()) { books.add(b); } }

        tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        tvBookTable.setItems(books);
    }

    private void ResetTable() {
        for ( int i = 0; i < tvBookTable.getItems().size(); i++) {
            tvBookTable.getItems().clear();
        }
    }
}
