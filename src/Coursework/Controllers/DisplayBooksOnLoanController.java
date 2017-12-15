package Coursework.Controllers;

import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class DisplayBooksOnLoanController implements Initializable {
    @FXML TableView tvBookTable;
    @FXML TableColumn<Object, String> tcBookId, tcBookTitle, tcBookAuthor, tcBookGenre, tcLoanHolder, tcDateOfLoan;
    @FXML Button btnDisplayFiction, btnDisplayNonFiction;
    String defaultButtonStyle;

    boolean lookingAtFiction = false,
            lookingAtNonFiction = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultButtonStyle = btnDisplayFiction.getStyle();

        tvBookTable.setPlaceholder(new Label("There are no available books out on loan."));
        FillTableWithAll();

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
}
