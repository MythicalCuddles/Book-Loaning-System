package Coursework.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutDialogController implements Initializable {
    @FXML Button btnClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent e) {
        ((Stage) btnClose.getScene().getWindow()).close();
    }
}
