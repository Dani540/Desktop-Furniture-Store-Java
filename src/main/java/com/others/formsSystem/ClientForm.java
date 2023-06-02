package com.others.formsSystem;

import com.furniturestore.FurnitureStoreApp;
import com.furniturestore.models.dao.ClientFormDAO;
import com.others.RClient;
import com.others.sceneSystem.AuxiliaryWindow;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ClientForm extends Form {

    private final ClientFormDAO clientFormDAO;

    /**
     * Construye el formulario del cliente (logica);
     * @param incompleteData Es la etiqueta para el manejo de errores de forma grafica.
     * @param nodes Son los campos del formulaio.
     */
    public ClientForm(Label incompleteData, Node...nodes) {
        clientFormDAO = new ClientFormDAO();
        setDao(clientFormDAO);
        setIncompleteLabel(incompleteData);
        setData(nodes);

        setEnumType(UserType.client);
    }

    /**
     * Evento de accion para el boton de añadir.
     */
    @Override
    public void addButton() {
        TextField rutTextField = (TextField) getData()[0];
        if ( isValidField(rutTextField) && rutTextField.getText().length()==8) {    // Si el campo de rut esta llenado con 8 digitos.
            if (clientFormDAO.isClientExists(getData())){   // Si el cliente existe en la base de datos
                if (!getInfo(getData()).get(1).isEmpty() && !getInfo(getData()).get(2).isEmpty()){  // Si el nombre y apellido del cliente estan rellenados. Si no, hay que escribirlos.
                    loadInfo(getData()); // Se carga su informacion en el formulario
                    setRClient(); // Se establece como cliente temporal de la venta.
                    AuxiliaryWindow.onConfirmSale(); // Se genera la ventana auxiliar para confirmar la venta.
                }
                setIncompleteDataText();
            }else{  //Si el cliente no existe en la base de datos
                if (validateFields(getData()[1], getData()[2]).stream().allMatch(Boolean::booleanValue)){ // Si los campos del nombre y apellido estan rellenados.
                    addEntity(getData());   // Se añade el cliente con los datos introducidos a la base de datos.
                    loadInfo(getData()); // Se carga su informacion en el formulario
                    setRClient(); // Se establece como cliente temporal de la venta.
                    AuxiliaryWindow.onConfirmSale(); // Se genera la ventana auxiliar para confirmar la venta.
                }
                setIncompleteDataText();
            }
        }setIncompleteDataText();
    }


    /**
     * Este metodo establece los atributos del cliente en proceso.
     */
    private void setRClient() {
        TextField rutTextField = (TextField) getData()[0];
        String rut = rutTextField.getText();
        FurnitureStoreApp.rClient = new RClient(rutValidator(rut));
        FurnitureStoreApp.getRClient().setName(getInfo(getData()).get(1));
        FurnitureStoreApp.getRClient().setLastname(getInfo(getData()).get(2));
        FurnitureStoreApp.getRClient().setAge(Integer.parseInt(getInfo(getData()).get(3)));
    }

    /**
     * Genera el digito verificador de un rut.
     * @param rut Es el rut con los primeros 8 digitos sin puntos.
     * @return Devuelve una cadena con el rut en formato "xxxxxxxx-x"
     */
    private String rutValidator(String rut) {
        int result = getIteratorRut(rut);
        int value = getIntValue(result, 11);
        int verifier = 11 - (result - (value * 11));
        return rut + "-" + getVerifier(verifier);
    }

    /**
     * Maquina de estados para los digitos verificadores.
     * @param verifier Es el digito verificador a analizar.
     * @return Devuelve el digito verificador valido.
     */
    private String getVerifier(int verifier) {
        String aux = String.valueOf(verifier);
        switch (verifier){
            case 10 -> aux = "k";
            case 11 -> aux = "0";
        }
        return aux;
    }

    /**
     * Hace el calculo algoritmico del rut con la sucession de numeros de la formula de rut.
     * Inversion del rut -> multiplicacion de cada digito con la lista de digitos de la formula -> suma general.
     * @param rut Es el rut con los primeros 8 digitos sin puntos.
     * @return Devuelve el total segun la formula de calculo del rut.
     */
    private int getIteratorRut(String rut) {
        StringBuilder aux = new StringBuilder(rut).reverse();
        List<Integer> integers = List.of(2,3,4,5,6,7,2,3);
        return IntStream.range(0, integers.size()).mapToObj(i -> Integer.parseInt(String.valueOf(aux.charAt(i))) * integers.get(i)).reduce(0, Integer::sum);
    }

    /**
     * Obtiene solo la parte entera de un numero sin considerar aproximaciones ni decimales.
     * @param value Es el numero para dividir.
     * @param div Es el divisor.
     * @return Devuelve la parte entera sin aproximacion ni decimales.
     */
    private int getIntValue(int value, int div) {
        BigDecimal decimalBigDecimal = BigDecimal.valueOf(value/div);
        return decimalBigDecimal.intValueExact();
    }

    /**
     * Carga la informacion del cliente en los campos del formulario.
     * @param data
     */
    private void loadInfo(Node[] data){
        List<String> info = getInfo(data);
        IntStream.range(0, info.size()).forEach(i ->{
            ((TextField) data[i]).setText(info.get(i));
        });
    }

    /**
     * Obtiene la informacion de un cliente.
     * @param data Son los campos del formulario.
     * @return Devuelve la informacion del cliente.
     */
    private List<String> getInfo(Node[] data) {
        return clientFormDAO.getInstanceInfo(data);
    }

    /**
     * Devuelve el mensaje del campo correspondiente al id.
     * @param id Es el campo correspondiente del formulario.
     * @return Devuelve un String con el nombre del campo.
     */
    @Override
    public String getNoFieldValidString(int id) {
        String noValidField="";
        switch (id){
            case 0 -> noValidField = "Rut";
            case 1 -> noValidField = "Name";
            case 2 -> noValidField = "Last Name";
        }
        return noValidField;
    }
}
