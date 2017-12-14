package Coursework.Controllers;

import Coursework.Extensions.ArrayListWorker;
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

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class DisplayAvailableBooksController implements Initializable{
    @FXML TableView tvBookTable;
    @FXML TableColumn<Object, String> tcBookId, tcBookTitle, tcBookAuthor, tcBookGenre;

    boolean lookingAtFiction = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvBookTable.setPlaceholder(new Label("Please select an option from the left."));
    }

    @FXML
    private void btnDisplayFiction(ActionEvent event) {
        ResetTable();

        lookingAtFiction = true;

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

        lookingAtFiction = false;

        ObservableList<NonFiction> books = FXCollections.observableArrayList();
        for(NonFiction b : Book.nonFictionArrayList) { if(!b.isOutOnLoan()) { books.add(b); } }

        tcBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcBookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tcBookGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

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
                    Object f = tvBookTable.getSelectionModel().getSelectedItem();
                    System.out.println(f.toString());

                    int pos = ArrayListWorker.getIndexOfIdForFiction(Book.fictionArrayList, ((Fiction) f).getId());

                    System.out.println("[INFO] ArrayPos: " + pos);

                } else {
                    Object f = tvBookTable.getSelectionModel().getSelectedItem();
                    System.out.println(f.toString());

                    int pos = ArrayListWorker.getIndexOfIdForNonFiction(Book.nonFictionArrayList, ((NonFiction) f).getId());

                    System.out.println("[INFO] ArrayPos: " + pos);

                }
            } else {
                System.out.println("[INFO] Table OnMouseClicked Event called but the table contains no data.");
            }
        } catch (RuntimeException exception) {
            System.out.println("[ERROR] OnMouseClicked Event was called, but no data was selected.");
        } catch (Exception exception) {
            System.out.println("[ERROR] OnMouseClicked Event threw an unexpected exception.");
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent e) {

    }
}
