import SQL.DataBase;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    static String testUser = "junit_test_user";
    static String initialPass = "test123";
    static String newPass = "newTest123";

    @BeforeAll
    static void setup() {
        System.out.println("TEST:");
        DataBase.deleteUser(testUser);
        boolean registrado = DataBase.register(testUser, initialPass);
        System.out.println("Usuario registrado: " + registrado);
    }


    @Test
    void testRegistroNuevoUsuario() {
        DataBase.deleteUser("junit_new_user");
        String user = "junit_new_user";
        boolean registrado = DataBase.register(user, "pass123");
        assertTrue(registrado, "El usuario debería registrarse correctamente");
    }


    @Test
    void testRegistroDuplicado() {
        boolean registrado = DataBase.register(testUser, initialPass);
        assertFalse(registrado, "No debería permitir registrar el mismo usuario");
    }

    @Test
    void testLoginCorrecto() {
        assertTrue(DataBase.validateLogin(testUser, newPass),
                "Login debería funcionar con la nueva contraseña");
    }


    @Test
    void testLoginIncorrecto() {
        assertFalse(DataBase.validateLogin(testUser, "wrongpass"), "Login debería fallar con contraseña incorrecta");
    }

    @Test
    void testCambioContraseñaExitoso() {
        boolean cambiado = DataBase.changePassword(testUser, newPass);
        assertTrue(cambiado, "Debería poder cambiar la contraseña");

        assertTrue(DataBase.validateLogin(testUser, newPass), "Login debería funcionar con la nueva contraseña");
    }

    @Test
    void testCambioContraseñaUsuarioInexistente() {
        boolean cambiado = DataBase.changePassword("usuario_fake", "pass123");
        assertFalse(cambiado, "No debería cambiar contraseña de un usuario inexistente");
    }

    @AfterAll
    static void cleanup() {
        // Podrías limpiar la base de datos si fuera necesario
    }
}

