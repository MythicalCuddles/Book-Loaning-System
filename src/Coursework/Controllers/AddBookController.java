package Coursework.Controllers;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML TextField tfID, tfBookTitle, tfBookAuthor, tfLoanHolder;
    @FXML CheckBox cbOutOnLoan;
    @FXML Button btnCancel;
    @FXML ComboBox cbGenre;
    @FXML DatePicker dpDateOfLoan;

    boolean selectedFiction = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbGenre.setPromptText("Please select Fiction/Non-Fiction");
        tfID.setText((Book.fictionArrayList.size() + Book.nonFictionArrayList.size()) + "");

        LocalDate localDate = LocalDate.now();
        dpDateOfLoan.setValue(localDate);

        dpDateOfLoan.setDisable(true);
        tfLoanHolder.setDisable(true);
    }

    @FXML
    private void btnFictionOnAction(ActionEvent e) {
        cbGenre.setPromptText("Please select a Fiction Genre from the list");

        cbGenre.getItems().removeAll(TypeOfNonFiction.values());
        cbGenre.getItems().removeAll(TypeOfFiction.values());
        cbGenre.getItems().addAll(TypeOfFiction.values());

        selectedFiction = true;
    }

    @FXML
    private void btnNonFictionOnAction(ActionEvent e) {
        cbGenre.setPromptText("Please select a Non-Fiction Genre from the list");

        cbGenre.getItems().removeAll(TypeOfNonFiction.values());
        cbGenre.getItems().removeAll(TypeOfFiction.values());
        cbGenre.getItems().addAll(TypeOfNonFiction.values());

        selectedFiction = false;
    }

    @FXML
    private void btnAddBookOnAction(ActionEvent e) {
        if(tfBookTitle.getText().isEmpty()) {
            System.out.println("Unable to add book - Title is empty");
            return;
        }

        if(tfBookAuthor.getText().isEmpty()) {
            System.out.println("Unable to add book - Author is empty");
            return;
        }

        if(cbGenre.getSelectionModel().getSelectedIndex() < 0) {
            System.out.println("Unable to add book - Genre is not selected");
            return;
        }

        if(cbOutOnLoan.isSelected()) {
            if(selectedFiction) {
                Book.fictionArrayList.add(new Fiction(tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfFiction) cbGenre.getSelectionModel().getSelectedItem(), true, tfLoanHolder.getText(), dpDateOfLoan.getValue()));
            } else {
                Book.nonFictionArrayList.add(new NonFiction(tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfNonFiction) cbGenre.getSelectionModel().getSelectedItem(), true, tfLoanHolder.getText(), dpDateOfLoan.getValue()));
            }
        } else {
            if(selectedFiction) {
                Book.fictionArrayList.add(new Fiction(tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfFiction) cbGenre.getSelectionModel().getSelectedItem()));
            } else {
                Book.nonFictionArrayList.add(new NonFiction(tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfNonFiction) cbGenre.getSelectionModel().getSelectedItem()));
            }
        }
    }

    @FXML
    private void cbOutOnLoanOnAction(ActionEvent e) {
        if(cbOutOnLoan.isSelected()) {
            dpDateOfLoan.setDisable(false);
            tfLoanHolder.setDisable(false);
        } else if(!cbOutOnLoan.isSelected()) {
            dpDateOfLoan.setDisable(true);
            tfLoanHolder.setDisable(true);
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent e) {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }
}
