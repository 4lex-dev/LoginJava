package ConfGUI;

import PackObj.Components;

import javax.swing.*;

public class Form extends JFrame {

    public Form(String title)  {
        super(title);
        //Tamaño de la ventana
        setSize(520,680);

        setIconImage(new ImageIcon(getClass().getResource("/ConfGUI/Logo.jpeg")).getImage());

        //Aqui configuro el GUI para que cuando se cierre la pestaña se acabe el proceso
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Esta función sirve para definir como se colocan los elementos automaticamente
        //Y al ponerlo en null lo quitamos para ponerlo manualmente
        setLayout(null);

        //Este método es para colocar la posicion de la pantalla en base al componente que le digamos
        //Al ponerlo null se centra automáticamente en el centro
        setLocationRelativeTo(null);

        //Bloquea el cambio de tamaño en la ventana
        setResizable(false);
        //Esto para cambiar el fondo
        getContentPane().setBackground(Components.PRIMARY_COLOR);
        setVisible(true);


    }

}

