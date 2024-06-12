package org.sistema.reserva.validations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MessageFx {

    @FXML
    private Button btn;

    @FXML
    private Label messageLabel;

    @FXML
    private void btnClicado(ActionEvent event) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void showError(String message){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sistema/reserva/views/message.fxml"));
            Parent root = loader.load();

            MessageFx controller = loader.getController();
            controller.messageLabel.setText(message);
            controller.messageLabel.setStyle("-fx-text-fill: red;");
            controller.btn.setStyle("-fx-background-color: red;");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void showSucess(String message){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sistema/reserva/views/message.fxml"));
            Parent root = loader.load();

            MessageFx controller = loader.getController();
            controller.messageLabel.setText(message);
            controller.messageLabel.setStyle("-fx-text-fill: green;");
            controller.btn.setStyle("-fx-background-color: green;");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
