package views.windowUtilitary;

import jiconfont.icons.font_awesome.FontAwesome;

import javax.swing.*;
import java.awt.*;

import static jiconfont.swing.IconFontSwing.buildIcon;
import static jiconfont.swing.IconFontSwing.register;

public class ViewUtils {
    public static void showError(String message, Component frame) {
        JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void showAdvice(String message, Component frame) {
        JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    public static Icon createIcon(FontAwesome iconCode, int size, Color color){
        register(FontAwesome.getIconFont());
        return buildIcon(iconCode, size, color);
    }
}
