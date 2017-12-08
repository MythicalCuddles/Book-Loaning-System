package Coursework.Controllers;

import Coursework.Program;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML ImageView ivLogoImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ivLogoImage.setImage(new Image("http://astronoteen.org/wp-content/uploads/2015/04/Star-Tau-Ceti-640x120.jpg"));
    }

    @FXML
    private void btnAddNewBookOnAction(ActionEvent e) throws Exception {
    }

    @FXML
    private void btnDisplayAvailableBooksOnAction(ActionEvent e) throws Exception {
        Program p = new Program();
        p.loadFXML("FXMLs/DisplayAvailableBooks.fxml", "Display Available Books", false);
    }

    @FXML
    private void btnDisplayBooksOnLoanOnAction(ActionEvent e) throws Exception {

    }
}