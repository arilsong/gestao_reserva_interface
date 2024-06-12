package org.sistema.reserva.controllersFx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.entity.Cliente;
import org.sistema.reserva.service.ClienteService;
import org.sistema.reserva.validations.IsEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrianContaControllerFx {

    @FXML
    private TextField emailFld;

    @FXML
    private TextField nomeFld;

    @FXML
    private PasswordField senhaFld;

    @FXML
    private TextField sobrenomeFld;

    @FXML
    private TextField telefoneFld;

    // ao editar
    public void initialize() {
        Cliente cliente = AuthManager.getClienteLogado();
        if(cliente != null){
            nomeFld.setText(cliente.getNome());
            sobrenomeFld.setText(cliente.getSobreNome());
            emailFld.setText(cliente.getEmail());
            senhaFld.setText(cliente.getSenha());
            telefoneFld.setText(cliente.getTelefone());
        }
    }


    @FXML
    void voltar(ActionEvent event) throws IOException {
        Parent criarContaRoot = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/telaLogin.fxml"));
        Scene criarConta = new Scene(criarContaRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(criarConta);
        stage.show();
    }

    @FXML
    private void criarConta(ActionEvent event){
        ArrayList<TextField> campos = new ArrayList<>(List.of(nomeFld, sobrenomeFld, emailFld, senhaFld, telefoneFld));

        // a validacao retorna true ou false
        // se for false conerte os dados e envie para base de dados
        if(!IsEmpty.isEmpty(campos)) {
            String nome = nomeFld.getText();
            String sobrenome = sobrenomeFld.getText();
            String email = emailFld.getText();
            String senha = senhaFld.getText();
            String telefone = telefoneFld.getText();

            ClienteService clienteService = new ClienteService();
            clienteService.criarConta(nome, sobrenome, email, senha, telefone);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }
    @FXML
    private void editarConta(ActionEvent event){

        ArrayList<TextField> campos = new ArrayList<>(List.of(nomeFld, sobrenomeFld, emailFld, senhaFld, telefoneFld));

        if(!IsEmpty.isEmpty(campos)) {
            String nome = nomeFld.getText();
            String sobrenome = sobrenomeFld.getText();
            String email = emailFld.getText();
            String senha = senhaFld.getText();
            String telefone = telefoneFld.getText();

            ClienteService clienteService = new ClienteService();
            clienteService.atualizarCliente(nome, sobrenome, email, senha, telefone);


        }

    }
    @FXML
    private void cancelar(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
