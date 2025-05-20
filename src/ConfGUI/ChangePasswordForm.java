package ConfGUI;

import PackObj.Components;
import SQL.DataBase;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordForm extends Form {
    public ChangePasswordForm() {
        super("Password Synapse Dynamics");
        setLayout(null);

        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setBounds(0, 20, 520, 40);
        titleLabel.setForeground(Components.TEXT_COLOR);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(50, 90, 150, 25);
        userLabel.setForeground(Components.TEXT_COLOR);
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(50, 120, 400, 40);
        userField.setFont(new Font("Dialog", Font.PLAIN, 18));
        userField.setBackground(Components.SECONDARY_COLOR);
        userField.setForeground(Components.TEXT_COLOR);
        add(userField);

        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(50, 180, 200, 25);
        newPassLabel.setForeground(Components.TEXT_COLOR);
        newPassLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(newPassLabel);

        JPasswordField newPassField = new JPasswordField();
        newPassField.setBounds(50, 210, 400, 40);
        newPassField.setFont(new Font("Dialog", Font.PLAIN, 18));
        newPassField.setBackground(Components.SECONDARY_COLOR);
        newPassField.setForeground(Components.TEXT_COLOR);
        add(newPassField);

        JButton changeBtn = new JButton("Change password");
        changeBtn.setBounds(150, 300, 200, 45);
        changeBtn.setBackground(Components.TEXT_COLOR);
        changeBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        changeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        changeBtn.addActionListener(e -> {
            String user = userField.getText();
            String newPass = new String(newPassField.getPassword());

            if (DataBase.changePassword(user, newPass)) {
                JOptionPane.showMessageDialog(this, "Contraseña cambiada con éxito");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al cambiar la contraseña");
            }
        });

        add(changeBtn);
    }
}

