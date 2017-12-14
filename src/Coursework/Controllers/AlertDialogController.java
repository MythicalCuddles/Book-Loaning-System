package Coursework.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertDialogController implements Initializable {
    @FXML Label messageLabel, detailsLabel;
    @FXML Button okButton, cancelButton;

    String title, text, okButtonText, cancelButtonText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setText(title);
        detailsLabel.setText(text);
        okButton.setText(okButtonText);
        cancelButton.setText(cancelButtonText);
    }


    public void loadAlertDialog(String text, String title) throws Exception {
        loadAlertDialog(text, title, "Ok", "Cancel");
    }
    public void loadAlertDialog(String text, String title, String okButtonText, String cancelButtonText) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLs/AlertDialog.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);

        stage.show();

        this.title = title;
        this.text = text;
        this.okButtonText = okButtonText;
        this.cancelButtonText = cancelButtonText;
    }

    @FXML
    private void btnCancelOnAction(ActionEvent e) {

    }

    @FXML
    private void btnOkOnAction(ActionEvent e) {

    }
}
