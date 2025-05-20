package ConfGUI;

import PackObj.Components;
import SQL.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterForm extends Form{
    public RegisterForm()  {
        super("Register");
        addGuiComponents();

    }
    private void addGuiComponents(){
        JLabel registerlabel =new JLabel("Register");

        registerlabel.setBounds(0,25,520,100);
        registerlabel.setForeground(Components.TEXT_COLOR);
        registerlabel.setFont(new Font("Dialog",Font.BOLD,40));
        registerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerlabel);
        JLabel userlabel=new JLabel("User");
        userlabel.setBounds(30,150,400,25);
        userlabel.setForeground(Components.TEXT_COLOR);
        userlabel.setFont(new Font("Dialog",Font.PLAIN,18));

        JTextField userField=new JTextField();
        userField.setBounds(30,185,450,55);
        userField.setBackground(Components.SECONDARY_COLOR );
        userField.setForeground(Components.TEXT_COLOR);
        userField.setFont(new Font("Dialog",Font.PLAIN,24));
        add(userlabel);
        add(userField);


        JLabel passwordlabel =new JLabel("Password");
        passwordlabel.setBounds(30,255,400,25);
        passwordlabel.setForeground(Components.TEXT_COLOR);
        passwordlabel.setFont(new Font("Dialog",Font.PLAIN,18));

        JPasswordField passwordField =new JPasswordField();
        passwordField.setBounds(30,285,450,55);
        passwordField.setBackground(Components.SECONDARY_COLOR );
        passwordField.setForeground(Components.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog",Font.PLAIN,24));
        add(passwordlabel);
        add(passwordField);

        //Creamos una opción para una nueva contraseña
        JLabel repasswordlabel =new JLabel(" Re-enter password");
        repasswordlabel.setBounds(30,365,400,25);
        repasswordlabel.setForeground(Components.TEXT_COLOR);
        repasswordlabel.setFont(new Font("Dialog",Font.PLAIN,18));

        JPasswordField repasswordField =new JPasswordField();
        repasswordField.setBounds(30,395,450,55);
        repasswordField.setBackground(Components.SECONDARY_COLOR );
        repasswordField.setForeground(Components.TEXT_COLOR);
        repasswordField.setFont(new Font("Dialog",Font.PLAIN,24));
        add(repasswordlabel);
        add(repasswordField);


        JButton registroboton =new JButton("Register");
        registroboton.setFont(new Font("Dialog",Font.BOLD,18));
        registroboton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registroboton.setBackground(Components.TEXT_COLOR);
        registroboton.setBounds(125,520,250,50);
        registroboton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuarname=userField.getText();
                String password=new String(passwordField.getPassword());
                String rePassword=new String(repasswordField.getPassword());

                if (validateUserInput(usuarname,password,rePassword)){
                    if (DataBase.register(usuarname,password)){
                        RegisterForm.this.dispose();
                        LoginForm loginForm=new LoginForm();
                        loginForm.setVisible(true);
                        JOptionPane.showMessageDialog(loginForm,"Registered Account Successfully!!");
                    }else{
                        JOptionPane.showMessageDialog(RegisterForm.this,"Error:Username already taken");
                    }
                }else{
                    JOptionPane.showMessageDialog(RegisterForm.this,"Error : Username has to be at least 4 characters\n"+
                            "and/or Passwords must match");
                }
            }
        });
        add(registroboton);

        JLabel loginlabel =new JLabel("Have an account?Login here!");
        loginlabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginlabel.setForeground(Components.TEXT_COLOR);
        loginlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//cuando haces click sucede esto:
                RegisterForm.this.dispose();//Cierra la ventana de login
                new LoginForm().setVisible(true);//Se crea una ventana de registro y se vuelve visible
            }
        });
        loginlabel.setBounds(125,600,250,30);
        add(loginlabel);



    }
    private boolean validateUserInput(String username,String password,String rePassword){
        if (username.length()==0||password.length()==0||rePassword.length()==0)return false;
        //el usuario tiene que tener minimo 4 letras
        if (username.length()<4)return false;
        //si la contraseña no es como la volvimos a introducir también nos da falso
        if (!password.equals(rePassword))return false;
        return true;



    }
}


