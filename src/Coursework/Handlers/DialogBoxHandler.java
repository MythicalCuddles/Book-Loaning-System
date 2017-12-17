package Coursework.Handlers;

import javax.swing.*;

/*****************************************************
 Project Name:      B00714027 CW3
 File Name:         DialogBoxHandler
 Created by: 		Melissa Brennan
 Student No:        B00714027
 Comments:          Class of methods to handle Dialog Boxes
 ******************************************************/

public class DialogBoxHandler {

    public static String ShowInputDialog(String title, String message, int typeOfDialog)
    {
        JFrame frame = new JFrame(title);
        frame.setAlwaysOnTop(true);
        return JOptionPane.showInputDialog(frame, message, title, typeOfDialog);
    }

    public static void ShowMessageDialog(String title, String message, int typeOfDialog)
    {
        //typeOfDialog example: JOptionPane.INFORMATION_MESSAGE
        JFrame frame = new JFrame(title);
        frame.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(frame, message, title, typeOfDialog);
    }

    public static int ShowConfirmationDialog(String title, String message, int typeOfDialog) {
        JFrame frame = new JFrame(title);
        frame.setAlwaysOnTop(true);
        return JOptionPane.showConfirmDialog(frame, message, title, typeOfDialog);
    }
}