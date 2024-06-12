package org.sistema.reserva.controllersFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DashControllerFx {

    @FXML
    private Button btn_acomodacao;

    @FXML
    private Button btn_funcionarios;

    @FXML
    private Button btnReserva;

    @FXML
    private Button btnHome;

    @FXML
    private BorderPane pane;

    private VBox acomodacaoPanel;
    private VBox reservaPanel;
    private VBox funcionarioPanel;

    public void initialize() {
        // Carregue os novos VBox a partir dos arquivos FXML
        try {
            acomodacaoPanel = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/dashboard/dash.acmdc.fxml"));
            funcionarioPanel = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/dashboard/dash.func.fxml"));
            reservaPanel = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/dashboard/dash.reserva.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pane.setCenter(acomodacaoPanel);
    }

    @FXML
    void adicionar(ActionEvent event) {


    }

    @FXML
    void btnClicado(ActionEvent event) {
        Button botaoClicado = (Button) event.getSource();

        if(botaoClicado == btn_acomodacao){
            pane.setCenter(acomodacaoPanel);
        }

        if(botaoClicado == btn_funcionarios){
            pane.setCenter(funcionarioPanel);
        }


        if(botaoClicado == btnReserva){
            pane.setCenter(reservaPanel);
        }

        if(botaoClicado == btnHome){
            try {
                Parent parent = null;
                parent = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/tela_principal/tela_principal.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
