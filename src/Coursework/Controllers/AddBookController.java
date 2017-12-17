package Coursework.Controllers;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Extensions.ArrayListWorker;
import Coursework.Handlers.DialogBoxHandler;
import Coursework.Handlers.FileHandler;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;
import Coursework.Program;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         AddBookController
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Controller for FXMLs/AddBook.fxml
 ******************************************************/

public class AddBookController implements Initializable {
    @FXML TextField tfID, tfBookTitle, tfBookAuthor, tfLoanHolder;
    @FXML CheckBox cbOutOnLoan;
    @FXML Button btnCancel;
    @FXML ComboBox cbGenre;
    @FXML DatePicker dpDateOfLoan;

    private boolean selectedFiction = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbGenre.setPromptText("Please select Fiction/Non-Fiction");
        tfID.setText((Book.fictionArrayList.size() + Book.nonFictionArrayList.size()) + "");

        //LocalDate localDate = LocalDate.now();
        dpDateOfLoan.setValue(LocalDate.now());

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
            DialogBoxHandler.ShowMessageDialog("Warning!", "Please enter the Title of the book.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(tfBookAuthor.getText().isEmpty()) {
            DialogBoxHandler.ShowMessageDialog("Warning!", "Please enter the Author of the book.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(cbGenre.getSelectionModel().getSelectedIndex() < 0) {
            DialogBoxHandler.ShowMessageDialog("Warning!", "Please select a genre from the list.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(cbOutOnLoan.isSelected()) {
            if(tfLoanHolder.getText().isEmpty()) {
                DialogBoxHandler.ShowMessageDialog("Warning!", "You have stated that this book is currently out on loan. Please specify who it is out on loan to.", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        if(selectedFiction) {
            if(!ArrayListWorker.doesListContainBook(Book.fictionArrayList, tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfFiction) cbGenre.getSelectionModel().getSelectedItem())) {
                Book.fictionArrayList.add(new Fiction(tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfFiction) cbGenre.getSelectionModel().getSelectedItem(), cbOutOnLoan.isSelected(), tfLoanHolder.getText(), dpDateOfLoan.getValue()));

                FileHandler.writeBooksToFile(); // Save the books to file.
                DialogBoxHandler.ShowMessageDialog("Action Successful","Your book (" + tfBookTitle.getText() + ") has been added successfully.", JOptionPane.INFORMATION_MESSAGE);
                btnCancelOnAction(null);
            } else {
                DialogBoxHandler.ShowMessageDialog("Info", "Book already exists in the list.", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if(!ArrayListWorker.doesListContainBook(Book.nonFictionArrayList, tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfNonFiction) cbGenre.getSelectionModel().getSelectedItem())) {
                Book.nonFictionArrayList.add(new NonFiction(tfBookTitle.getText(), tfBookAuthor.getText(), (TypeOfNonFiction) cbGenre.getSelectionModel().getSelectedItem(), cbOutOnLoan.isSelected(), tfLoanHolder.getText(), dpDateOfLoan.getValue()));

                FileHandler.writeBooksToFile(); // Save the books to file.
                DialogBoxHandler.ShowMessageDialog("Action Successful","Your book (" + tfBookTitle.getText() + ") has been added successfully.", JOptionPane.INFORMATION_MESSAGE);
                btnCancelOnAction(null);
            } else {
                DialogBoxHandler.ShowMessageDialog("Info", "Book already exists in the list.", JOptionPane.INFORMATION_MESSAGE);
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

    @FXML
    private void miFileQuitOnAction(ActionEvent e) {
        Program.exitProgram();
    }

    @FXML
    private void miHelpAboutOnAction(ActionEvent e) {
        Program p = new Program();
        p.loadFXML("FXMLs/AboutDialog.fxml", "About Book Loaning System", false, true);
    }
}
