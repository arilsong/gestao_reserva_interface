package org.sistema.reserva.validations;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class IsEmpty {
    public static boolean isEmpty(ArrayList<TextField> campos){
        // recebe os campos e verifica se esta vazio
        //se for vazio retrna verdadei senao retorna falso
        for(TextField campo : campos){
            if(campo.getText().isEmpty()){
                //mostra o erro
                new MessageFx().showError("Preencha todos os campos");
                return true;
            }
            return false;
        }
        return true;
    }

    static void showError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Preencha todos os campos");
        alert.showAndWait();
    }
}
