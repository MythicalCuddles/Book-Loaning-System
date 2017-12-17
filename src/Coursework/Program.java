package Coursework;

import Coursework.Enums.TypeOfFiction;
import Coursework.Enums.TypeOfNonFiction;
import Coursework.Handlers.DialogBoxHandler;
import Coursework.Handlers.FileHandler;
import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         Program
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Main Entry Point for the Program.
                    -> Contains method to open new FXML's (Opening new stages on the program)
 ******************************************************
 Future Improvements to consider:
 -  DisplayBooksOnLoan - Adding search fields to search for STUDENT NAME and/or DATE OF LOAN
 -  Delete/Amend Books
 ******************************************************/

public class Program extends Application {
    private static Image iconLogo = new Image("http://scm.ulster.ac.uk/~B00714027/files/icons/books.png");

    @Override
    public void start(Stage stage) {
        FileHandler.ensureExistsAndLoad(); // Checks to see if the book files exist.
        System.out.println("[DEBUG] " + Book.getNoOfBooks() + " Books Loaded From File."); // Console info for debugging

        loadFXML("FXMLs/MainMenu.fxml", "Book System", true, false); // Calls method below to load MainMenu FXML
    }
    public static void main(String[] args) { // Entry point for the Program.
        launch(args); // Calls the Override for the main method in JavaFX.
    }

    public void loadFXML(String resource, String title, boolean closeAllOnClose, boolean alwaysOnTop) { // Method to load FXML's
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resource)); // Declare and Initialize FXMLLoader by loading FXML resource passed to method.

            Scene scene = new Scene(root); // Declare and Initialize Stage Object with FXML Parent.
            Stage stage = new Stage(); // Declare and Initialize Stage Object.

            stage.setScene(scene); // Set the stage's scene as scene defined above.
            stage.setTitle(title); // Set the title of the stage as string passed through method.
            stage.setResizable(false); // Disable the user from expanding the size of the window.
            stage.getIcons().add(iconLogo); // Add an icon to the window from the Image defined above.
            stage.setAlwaysOnTop(alwaysOnTop); // If passed true - The window will appear on-top. [NOTE: DialogBoxHandler needs Dialogs to be AlwaysOnTop = TRUE if this is true]

            if(closeAllOnClose) {// If true, when the stage is closed, all other windows from the program will close too.
                stage.setOnCloseRequest(e -> { // Lambda expression for OnCloseRequest Event for stage.
                    exitProgram(); // Call exitProgram Method below.
                });
            }

            stage.show(); // Show the stage defined above.
        } catch (IOException exception) { // IOException: FXMLLoader expected.
            DialogBoxHandler.ShowMessageDialog("Error!", "An error occurred whilst trying to load " + resource + "! Please try reinstalling the program.", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) { // Catch any other errors.
            DialogBoxHandler.ShowMessageDialog("Error!", "An unexpected error has occurred. Please try reinstalling the program.", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void exitProgram() { // Method to check if the user wants to exit the program.
        int option = DialogBoxHandler.ShowConfirmationDialog("About to Quit", "Are you sure you want to exit the program?", JOptionPane.YES_NO_OPTION); // Ask the user if they are sure they want to quit.
        // OPTION = 0 (Yes was clicked) // OPTION = -1 (X was clicked) // OPTION = 1 (No was clicked)
        if(option == 0) {
            FileHandler.writeBooksToFile(); // Save the books to file.
            // Call methods to Exit the Program.
            Platform.exit();
            System.exit(0);
        }
    }
}
