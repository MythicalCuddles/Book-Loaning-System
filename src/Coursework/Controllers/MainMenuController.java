package Coursework.Controllers;

import Coursework.Program;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML ImageView ivLogoImage;
    @FXML Button btnAddBook, btnDisplayBooks, btnDisplayLoans, btnQuit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ivLogoImage.setImage(new Image("http://scm.ulster.ac.uk/~B00714027/Files/Software%20Development%20II/CW3/logo.png"));

        btnAddBook.setContentDisplay(ContentDisplay.TOP);
        btnDisplayBooks.setContentDisplay(ContentDisplay.TOP);
        btnDisplayLoans.setContentDisplay(ContentDisplay.TOP);
        btnQuit.setContentDisplay(ContentDisplay.TOP);

        btnAddBook.setGraphic(new ImageView("http://scm.ulster.ac.uk/~B00714027/Files/Software%20Development%20II/CW3/addbook.png"));
        btnDisplayBooks.setGraphic(new ImageView("http://scm.ulster.ac.uk/~B00714027/Files/Software%20Development%20II/CW3/searchbooks.png"));
        btnDisplayLoans.setGraphic(new ImageView("http://scm.ulster.ac.uk/~B00714027/Files/Software%20Development%20II/CW3/loanbook.png"));
        btnQuit.setGraphic(new ImageView("http://scm.ulster.ac.uk/~B00714027/Files/Software%20Development%20II/CW3/exit.png"));
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

    @FXML
    private void btnQuitOnAction(ActionEvent e) {
        Platform.exit();
        System.exit(0);
    }
}