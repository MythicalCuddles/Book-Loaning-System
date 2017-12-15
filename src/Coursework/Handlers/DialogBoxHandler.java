package Coursework.Handlers;

import javax.swing.*;

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
        int option = JOptionPane.showConfirmDialog(frame, message, title, typeOfDialog);
        return option;
    }
}