/**
 * Esta clase se encaga de la gestion de la base de datos
 * y el manejo de errores en la clase LoginView.
 */

package com.furniturestore.models.dao;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.controllers.SceneController;
import com.furniturestore.models.entity.users.User;
import com.others.formsSystem.UserType;
import com.others.sceneSystem.Scenes;
import com.repository.DataBase;

public class LoginDAO {

    /**
     * Este metodo se encarga de la gestion de inicio de sesion
     * con la base de datos. Define el tipo de usuario ingresado,
     * lo asigna al servicio y el programa se mueve en base a este.
     *
     * @param isUsernameValid Si el nombre de usuario ingresado es valido.
     * @param isPasswordValid Si la contraseña ingresada es valida.
     * @param userData Datos del usuario ingresado.
     * @return retorna un 0 o un 1 para la gestion del manejo de errores en la GUI, si no solo se cambia la escena.
     */
    public int onLoginApp(boolean isUsernameValid, boolean isPasswordValid , String[] userData){
        return isValidField(isUsernameValid, isPasswordValid, userData);
    }

    /**
     * Valida los campos dados.
     * @param isUsernameValid Si el nombre de usuario es valido.
     * @param isPasswordValid Si la contraseña es valida.
     * @param userData Los datos del usuario.
     * @return Devuelve los estados de la validacion.
     */
    private int isValidField(boolean isUsernameValid, boolean isPasswordValid, String[] userData) {
        if (isUsernameValid && isPasswordValid)
            return stateOfUser(userData);
        return -1;
    }

    /**
     * Comprueba que un usuario exista.
     * @param userData Son los datos del usuario a comprobar.
     * @return Devuelve true/false en funcion de su existencia.
     */
    private int stateOfUser(String[] userData){
        setUserType(userData[0]);

        UserType userType = getUser(userData).getUserType();

        int state = (DataBase.getInstance().getRepository().stateOfUser( getUser(userData) ));

        if ( state==1 ) {
            System.out.println("Type of user logged: " + userType);
            FurnitureStoreApp.setUserType(userType);
            FurnitureStoreApp.setUsername(userData[0]);
            if (!getUser(userData).getUserType().equals(UserType.root)) SceneController.switchScene(Scenes.index);
            else SceneController.switchScene(Scenes.userForm);
            return state;
        }
        return state;
    }


    /**
     * Establece el tipo de usuario ingresador, esto lo hace mediante una validacion en el
     * primero simbolo del nombre, los usuarios root contienen un "/" al principio, los
     * administradores "." y los vendedores nada.
     * @param username Es el nombre de usuario ingresado.
     */
    private void setUserType(String username) {
        if ( isRoot(username) )       DataBase.getInstance().getRepository().setUserType(UserType.root);
        else if ( isAdmin(username) ) DataBase.getInstance().getRepository().setUserType(UserType.admin);
        else DataBase.getInstance().getRepository().setUserType(UserType.vendor);
    }

    /**
     * Si es root segun "/" al princpio del nombre.
     * @param username Nombre de usuario.
     * @return Devuelve true (es root) o no.
     */
    private boolean isRoot(String username) {
        return (String.valueOf(username.charAt(0))).equals("/");
    }

    /**
     * Si es admin segun "." al princpio del nombre.
     * @param username Nombre de usuario.
     * @return Devuelve true (es admin) o no.
     */
    private boolean isAdmin(String username) {
        return (String.valueOf(username.charAt(0))).equals(".");
    }

    /**
     * Devuelve un usuario con los datos recibidos por la verificacion.
     * @param userData Datos ingresados por GUI.
     * @return devuelve un usuario con estos datos.
     */
    private User getUser(String[] userData) {
        UserType userType = DataBase.getInstance().getRepository().getUserType(userData);
        return new User(userData[0].substring(userType.getBeginIndex()), userData[1], userType);
    }
}
