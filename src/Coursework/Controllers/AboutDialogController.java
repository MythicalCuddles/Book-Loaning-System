package Coursework.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutDialogController implements Initializable {
    @FXML Label lblProductName, lblVersion, lblAuthor, lblOther;
    @FXML TextArea taDetails;
    @FXML ImageView ivImage;
    @FXML Button btnClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblProductName.setText("Book Loaning System");
        lblVersion.setText("Version 1.0.0.0");
        lblAuthor.setText("Developed by Melissa Brennan");
        lblOther.setText("Student ID: B00714027");

        taDetails.setText("Book Loaning System is a system that allows teachers to record which fiction and non-fiction books are lent out to their pupils. " +
                "The system contains many different features such as the option to add books from inside the program, or via a text file, display the books available and out on loan " +
                "along with the options to search for books, take a book out on loan, or return a book. You can navigate to Help via the menu bar " +
                "for any more information or learning how to set things up.\n\n(Book Loaning System - COM187 - Coursework 3)");
        taDetails.wrapTextProperty().setValue(true);

        ivImage.setImage(new Image("http://scm.ulster.ac.uk/~B00714027/Files/Software%20Development%20II/CW3/aboutbox-.png"));
    }

    @FXML
    private void btnCloseOnAction(ActionEvent e) {
        ((Stage) btnClose.getScene().getWindow()).close();
    }
}
