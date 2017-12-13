package Coursework.Controllers;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Objects.Book;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML TextField tfID, tfBookTitle;
    @FXML ComboBox cbGenre;
    @FXML Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbGenre.setPromptText("Please select Fiction/Non-Fiction");
        tfID.setText((Book.fictionArrayList.size() + Book.nonFictionArrayList.size()) + "");
    }

    @FXML
    private void btnFictionOnAction(ActionEvent e) {
        cbGenre.setPromptText("Please select a Fiction Genre from the list");
        cbGenre.getItems().removeAll(TypeOfNonFiction.values());
        cbGenre.getItems().removeAll(TypeOfFiction.values());
        cbGenre.getItems().addAll(TypeOfFiction.values());
    }

    @FXML
    private void btnNonFictionOnAction(ActionEvent e) {
        cbGenre.setPromptText("Please select a Non-Fiction Genre from the list");
        cbGenre.getItems().removeAll(TypeOfNonFiction.values());
        cbGenre.getItems().removeAll(TypeOfFiction.values());
        cbGenre.getItems().addAll(TypeOfNonFiction.values());
    }

    @FXML
    private void btnAddBookOnAction(ActionEvent e) {
        if(tfBookTitle.getText().isEmpty()) {

            return;
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent e) {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }
}
