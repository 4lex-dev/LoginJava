package ConfGUI;

import PackObj.Components;
import SQL.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends Form{
    private int loginAttempts = 0;

    public LoginForm() {
        super("Login Synapse Dynamics");
        //He hecho lo de la herencia para ahorrarme el tener que poner lo mismo
        addGuiComponents();



    }

    private void addGuiComponents(){

        JLabel loginlabel=new JLabel("Login");
        //Aqui modificamos los valores manualmente para ponerlos a la posición que queramos
        loginlabel.setBounds(0,25,520,100);
        //Para cambiar el color del texto
        loginlabel.setForeground(Components.TEXT_COLOR);
        //Ajustamos tamaño del texto
        loginlabel.setFont(new Font("Dialog",Font.BOLD,40));
        //Centramos el texto y el boton en si
        loginlabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Para que salga en pantalla,si no se hace no sale nada en la pantalla
        add(loginlabel);
        JLabel userlabel=new JLabel("User");
        userlabel.setBounds(30,150,400,25);
        userlabel.setForeground(Components.TEXT_COLOR);
        userlabel.setFont(new Font("Dialog",Font.PLAIN,18));
        //Aqui creamos un componente para que el usuario pueda escribir su nombre
        //En vez de label o etiqueta de texto usamos un campo de texto
        JTextField userField=new JTextField();
        userField.setBounds(30,185,450,55);
        userField.setBackground(Components.SECONDARY_COLOR );
        userField.setForeground(Components.TEXT_COLOR);
        userField.setFont(new Font("Dialog",Font.PLAIN,24));
        add(userlabel);
        add(userField);

        //Aquí he reciclado el código ya que hay que hacer lo mismo

        JLabel passwordlabel =new JLabel("Password");
        //Excepto algunos pequeños cambios como la posición
        passwordlabel.setBounds(30,335,400,25);
        passwordlabel.setForeground(Components.TEXT_COLOR);
        passwordlabel.setFont(new Font("Dialog",Font.PLAIN,18));
        //Creamos un passwordfield que la diferencia de texfield es que no se muestra el texto cuando lo escribes
        //Si no que sale como una contraseña
        JPasswordField passwordField =new JPasswordField();
        passwordField.setBounds(30,365,450,55);
        passwordField.setBackground(Components.SECONDARY_COLOR );
        passwordField.setForeground(Components.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,24));
        add(passwordlabel);
        add(passwordField);
        //Aqui creo el boton del login
        JButton loginboton=new JButton("Login");
        loginboton.setFont(new Font("Dialog",Font.BOLD,18));
        loginboton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginboton.setBackground(Components.TEXT_COLOR);
        loginboton.setBounds(125,520,250,50);
        loginboton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                if (DataBase.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginForm.this, "¡Login exitoso!");
                    loginAttempts = 0; // Reinicia contador
                } else {
                    loginAttempts++;
                    if (loginAttempts >= 3) {
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "Demasiados intentos fallidos. Acceso bloqueado.");
                        loginboton.setEnabled(false); // Desactiva el botón
                    } else {
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "Login incorrecto (" + loginAttempts + " de 3 intentos)");
                    }
                }
            }
        });

        // Botón de login
        add(loginboton);

// Enlace a registro
        JLabel registrolabel = new JLabel("Not a user? Register Here!");
        registrolabel.setBounds(125, 580, 250, 30);
        registrolabel.setHorizontalAlignment(SwingConstants.CENTER);
        registrolabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registrolabel.setForeground(Components.TEXT_COLOR);
        registrolabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginForm.this.dispose();
                new RegisterForm().setVisible(true);
            }
        });
        add(registrolabel);

// Enlace a cambio de contraseña
        JLabel changePassLabel = new JLabel("¿Forgot your password?");
        changePassLabel.setBounds(125, 610, 250, 30);
        changePassLabel.setHorizontalAlignment(SwingConstants.CENTER);
        changePassLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePassLabel.setForeground(Components.TEXT_COLOR);
        changePassLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ChangePasswordForm().setVisible(true);
            }
        });
        add(changePassLabel);

    }}
