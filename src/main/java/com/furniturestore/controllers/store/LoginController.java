/**
 * Controlador encargado de la pantalla de ingreso de sesion (login).
 * Esta clase gestiona la logica y interaccion con la GUI de la pantalla "login".
 *
 * @author Daniel Palma
 * @version 1.0
 * @since 20/5/23
 *
 */

package com.furniturestore.controllers.store;

import com.furniturestore.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class LoginController{
    @FXML
    private BorderPane allContainer;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    public PasswordField passwordPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    public Button showPassButton;
    @FXML
    public Label incompleteDataLabel;
    private LoginView loginView;

    /**
     * Inicializa el controlador de la pantalla de inicio de sesión.
     * Se ejecuta automáticamente al cargar el archivo FXML correspondiente.
     * Crea una instancia de LoginView y establece los campos y botones asociados.
     */

    public void initialize(){
        loginView = new LoginView(usernameTextField, passwordTextField, passwordPasswordField, showPassButton, loginButton, incompleteDataLabel);
    }

    /**
     * Maneja el evento del botón de inicio de sesión.
     * Llama al método onLoginApp() de LoginView para realizar la lógica de inicio de sesión.
     */
    @FXML
    private void onLoginButton(){
        loginView.onLoginButton();
    }
    /**
     * Este metodo recibe la presion de una tecla en el panel principal del stage.
     * Se usa para hacer el ingreso de usuario mediante el boton "Enter" con el metodo
     * correspondiente en la instancia de LoginView.
     * @param event Es el boton pulsado.
     */
    @FXML
    public void onLoginButton(KeyEvent event){
        loginView.onLoginButton(event);
    }

    /**
     * Maneja el evento del botón de mostrar/ocultar contraseña.
     * Llama al método showPassword() de LoginView para alternar la visibilidad de la contraseña.
     */
    @FXML
    public void showPassword() {
        loginView.showPassword();
    }

    /**
     * Maneja el evento del botón de salir del programa
     * Llama al metodo onExitButton() de LoginView para desplegar un cuadro de confirmacion
     */
    @FXML
    public void onExitButton() {
        loginView.onExitButton();
    }
}
