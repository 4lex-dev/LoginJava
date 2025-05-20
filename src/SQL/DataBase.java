package SQL;

import PackObj.Components;

import java.sql.*;

public class DataBase {
    //registramos nuevos usuarios en la base de datos
    //true es que se ha realizado
    //false es que no
public static boolean register(String username,String password){
   try {
       //si el usuario no ha sido detectado
       if (!checkUser(username)) {
           Connection connection = DriverManager.getConnection(Components.DB_URL,
                   Components.DB_USERNAME, Components.DB_PASSWORD);
           PreparedStatement insertuser = connection.prepareStatement(
                   "INSERT INTO " + Components.DB_USERS_TABLE_NAME + "(username,password) VALUES(?,?)");

           insertuser.setString(1,username);// Reemplaza el primer "?" con el nombre de usuario
           insertuser.setString(2, password);// Segundo "?" con la contraseña

           insertuser.executeUpdate();
           return true;
       }
   }catch (SQLException e){
       e.printStackTrace();
       //Si algo sale mal (por ejemplo, falla la conexión o la consulta), atrapa el error
       //printStack muestra los detalles del error en la consola
   }
return false;
}
public static boolean checkUser(String username){
    try{
        //Se conecta a la base de datos usando la URL, usuario y contraseña
        Connection connection= DriverManager.getConnection(Components.DB_URL,
                Components.DB_USERNAME, Components.DB_PASSWORD);
        PreparedStatement checkUserExits = connection.prepareStatement(
                "SELECT * FROM "+ Components.DB_USERS_TABLE_NAME+" WHERE USERNAME = ?");
        //Se prepara una consulta SQL para buscar si hay un usuario con ese nombre
        //Usa un ? como espacio reservado
        checkUserExits.setString(1,username);
        //Esta línea reemplaza el ? de la consulta SQL con el valor real del usuario que estamos buscando\*
        // ,se usa setString porque es un texto, y el numero 1 indica que es el primer ? en la consulta. Así evitamos errores

        ResultSet resultSet = checkUserExits.executeQuery();
        //Aquí se ejecuta la consulta y guarda el resultado en resultset
        //ResultSet es como una tabla en memoria, si el usuario no existe result estara vacio
        if (!resultSet.isBeforeFirst()){
            //verifica si hay alguna fila antes de empezar a leer resultados
            return false;
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return true;

}
//este método valida si el usuario y la contraseña del login existe en la base de datos
public static boolean validateLogin(String username,String password){
    try {
        Connection connection= DriverManager.getConnection(Components.DB_URL,
                Components.DB_USERNAME, Components.DB_PASSWORD);
        PreparedStatement validateuser=connection.prepareStatement(
                "SELECT * FROM " + Components.DB_USERS_TABLE_NAME+" WHERE USERNAME = ? AND PASSWORD = ?"
        );
        validateuser.setString(1,username);
        validateuser.setString(2,password);
        ResultSet resultSet= validateuser.executeQuery();
        if (!resultSet.isBeforeFirst()){
            return false;
        }

    }catch (SQLException e){
        e.printStackTrace();
    }
return true;
}
    public static boolean changePassword(String username, String newPassword) {
        try {
            if (checkUser(username)) {
                Connection connection = DriverManager.getConnection(Components.DB_URL,
                        Components.DB_USERNAME, Components.DB_PASSWORD);
                PreparedStatement update = connection.prepareStatement(
                        "UPDATE " + Components.DB_USERS_TABLE_NAME + " SET password = ? WHERE username = ?"
                );
                update.setString(1, newPassword);
                update.setString(2, username);
                update.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
