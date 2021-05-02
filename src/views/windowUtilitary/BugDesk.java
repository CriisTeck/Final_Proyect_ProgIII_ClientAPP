package views.windowUtilitary;

import javax.swing.*;
import java.awt.*;

public class BugDesk {
    public static void showError(String message, Component frame) {
        JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void showAdvice(String message, Component frame) {
        JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }
}
