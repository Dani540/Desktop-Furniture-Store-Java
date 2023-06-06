/**
 * Esta clase se encarga de la interaccion con los elementos graficos en la GUI y las validaciones de campo.
 * Contiene una instancia para logica de los elementos.
 */


package com.furniturestore.views;

import com.furniturestore.models.dao.LoginDAO;
import com.others.formsSystem.TextFormatter;
import com.others.sceneSystem.AuxiliaryWindow;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginView {
    private final TextField usernameTextField;
    private final TextField passwordTextField;
    private final PasswordField passwordPasswordField;
    private final Button showPasswordButton;
    private final Label incompleteDataLabel;
    private final LoginDAO loginDAO;

    /**
     * El constructor nos ayuda a gestionar todos los elementos graficos a nivel de clase.
     *
     * @param usernameTextField Es el TextField donde se introduce el nombre de usuario.
     * @param passwordTextField Es el TextField donde se introduce la contraseña y esta es visible al usuario.
     * @param passwordPasswordField Es el PasswordField donde se introduce la contraseña  y esta no es visible.
     * @param showPasswordButton Es el boton para alternar la visibilidad de la contraseña.
     * @param loginButton Es el boton para iniciar sesion.
     * @param incompleteDataLabel Es la etiqueta para el manejo de mensajes de error en la GUI.
     */

    public LoginView(TextField usernameTextField, TextField passwordTextField, PasswordField passwordPasswordField, Button showPasswordButton, Button loginButton, Label incompleteDataLabel) {
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
        this.passwordPasswordField = passwordPasswordField;
        this.showPasswordButton = showPasswordButton;
        this.incompleteDataLabel = incompleteDataLabel;

        loginDAO = new LoginDAO();
    }

    public void initialize(){
        new TextFormatter().stringLimitFormatter(11, usernameTextField, passwordTextField);
    }

    /**
     * Este metodo se encarga de la gestion del inicio de sesion.
     * Contiene la validacion de campos, los datos de estos y el manejo de mensajes de error en GUI.
     */

    public void onLoginButton() {
        boolean [] fieldValidation = fieldValidation();
        String [] userData = { usernameTextField.getText(),
                passwordTextField.isDisable() ? passwordPasswordField.getText() : passwordTextField.getText()};

        int loggedState = loginDAO.onLoginApp(fieldValidation[0], fieldValidation[1], userData);    // -1 = Campos vacios, 0 = No existe, 1 = Existe, 2 = Contraseña Invalida

        if (loggedState == -1) incompleteDataLabel.setText( generateIncompleteText(fieldValidation[0], fieldValidation[1]) );
        else if (loggedState == 2) incompleteDataLabel.setText("Incorrect Password!");
        else incompleteDataLabel.setText("User not Found!");
    }

    /**
     * Este metodo se encarga unicamente de la validacion de los campos
     * para el inicio de sesion (Campos no vacios).
     *
     * @return devuelve un arreglo de boleanos con dos valores:
     * - Si el nombre de usuario es valido.
     * - Si la contraseña es valida.
     */
    private boolean[] fieldValidation() {
        boolean isUsernameValid = isValidField(usernameTextField);
        boolean isPasswordValid = passwordPasswordField.isDisable() ?
                isValidField(passwordTextField)
                : isValidField(passwordPasswordField);
        return new boolean[]{isUsernameValid, isPasswordValid};
    }

    /**
     * Este metodo se encarga de la validacion de un campo de texto.
     * Al tener dos tipos de "Field" (Text y Password), alterno entre
     * el tipo que entra, verifico a que corresponde y devuelo la validacion
     * segun esto.
     *
     * @param field Es el campo que entra, tomando su clase padre, para
     *              posteriormente identificar su subclase.
     * @return retorna la valides del campo (campo no vacio).
     */
    private boolean isValidField(Node field){
        return (field instanceof TextField aux) ? !aux.getText().isEmpty() : !( (PasswordField) (field) ).getText().isEmpty();
    }

    /**
     * Este metodo devuelve una cadena con el mensaje en el caso de que
     * el inicio de sesion genere uno.
     *
     * @param isUsernameValid Validacion del campo nombre de usuario.
     * @param isPasswordValid Validacion del campo contraseña.
     * @return  retorna el mensaje correspondiente al elemento vacio.
     */
    private String generateIncompleteText(boolean isUsernameValid, boolean isPasswordValid) {
        String statement;
        if      (!isUsernameValid && isPasswordValid) statement = "Username Empty!";
        else if (isUsernameValid && !isPasswordValid) statement = "Password Empty!";
        else statement = ("Username & Password Empty!");
        return statement;
    }

    /**
     * Este metodo alterna la visibilidad de la contraseña
     * en funcion al nodo visual habilitado en el elemento.
     * Solo se llama al momento de presionar el boton para esto.
     */
    public void showPassword() {
        String eye = showPasswordButton.getText().equals("<o>") ? "<ø>" : "<o>";
        String aux = passwordPasswordField.isDisable() ? passwordTextField.getText() : passwordPasswordField.getText();

        showPasswordButton.setText(eye);

        passwordPasswordField.setVisible(!passwordPasswordField.isVisible());
        passwordTextField.setVisible(!passwordTextField.isVisible());

        passwordTextField.setDisable( !passwordTextField.isDisable() );
        passwordPasswordField.setDisable( !passwordPasswordField.isDisable() );

        passwordPasswordField.setText(aux);
        passwordTextField.setText(aux);
    }

    /**
     * Este metodo despliega la ventana de confirmacion para cerrar el programa
     * y lo hace llamando al metodo onExitButton de la clase Abstracta "AuxiliaryWindow"
     */

    public void onExitButton() {
        AuxiliaryWindow.onExitWindow();
    }

    /**
     * Este metodo recibe la presion de una tecla.
     * Se usa para hacer el ingreso de usuario mediante el boton "Enter".
     * @param event Es el boton pulsado.
     */
    public void onLoginButton(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) onLoginButton();
    }
}
