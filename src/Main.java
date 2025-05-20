import ConfGUI.LoginForm;
import ConfGUI.RegisterForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //swing no es seguro para diferentes ventanas
        //con esto lo que hago es que el programa se ejecute de una manera segura
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginForm loginForm=new LoginForm();
                loginForm.setVisible(true);



            }
        });
    }

}
