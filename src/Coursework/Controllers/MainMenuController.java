package Coursework.Controllers;

import Coursework.Handlers.DialogBoxHandler;
import Coursework.Handlers.FileHandler;
import Coursework.Program;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         MainMenuController
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Controller for FXMLs/MainMenu.fxml
 ******************************************************/

public class MainMenuController implements Initializable {
    @FXML ImageView ivLogoImage;
    @FXML Button btnAddBook, btnDisplayBooks, btnDisplayLoans, btnQuit;
    @FXML Label lblLeftStatus, lblRightStatus;
    @FXML MenuItem miFileQuit, miEditImport, miEditExport, miHelpAbout, miHelpHelp;

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

        lblLeftStatus.setText("");
        lblRightStatus.setText("Developed by Melissa Brennan (B00714027)");
    }

    @FXML
    private void btnAddNewBookOnAction(ActionEvent e) {
        Program p = new Program();
        p.loadFXML("FXMLs/AddBook.fxml", "Add Book", false, true);
    }

    @FXML
    private void btnDisplayAvailableBooksOnAction(ActionEvent e) {
        Program p = new Program();
        p.loadFXML("FXMLs/DisplayAvailableBooks.fxml", "Display Available Books", false, true);
    }

    @FXML
    private void btnDisplayBooksOnLoanOnAction(ActionEvent e) {
        Program p = new Program();
        p.loadFXML("FXMLs/DisplayBooksOnLoan.fxml", "Display Books on Loan", false, true);
    }

    @FXML
    private void btnQuitOnAction(ActionEvent e) {
        Program.exitProgram();
    }

    @FXML
    private void miFileQuitOnAction(ActionEvent e) {
        Program.exitProgram();
    }

    @FXML
    private void miEditImportOnAction(ActionEvent e) {
        DialogBoxHandler.ShowMessageDialog("Please Note", "Please only import files exported via this program.", JOptionPane.INFORMATION_MESSAGE);
        FileHandler.selectFile();
    }

    @FXML
    private void miEditExportOnAction(ActionEvent e) {
        FileHandler.writeBooksToFile();
        DialogBoxHandler.ShowMessageDialog("Export Books to File", "Books exported successfully!", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void miHelpAboutOnAction(ActionEvent e) {
        Program p = new Program();
        p.loadFXML("FXMLs/AboutDialog.fxml", "About Book Loaning System", false, true);
    }
}