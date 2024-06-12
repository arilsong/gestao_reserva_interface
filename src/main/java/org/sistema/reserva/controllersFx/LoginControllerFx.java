package org.sistema.reserva.controllersFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.controllersFx.Tela_Principal.TelaPrincipalFx;
import org.sistema.reserva.validations.MessageFx;

import java.io.IOException;

public class LoginControllerFx {

    @FXML
    private Hyperlink criarConta;

    @FXML
    private Hyperlink trocaDeLogin;

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField senha;

    @FXML
    private Text texto;



    private TelaPrincipalFx telaPrincipalFx;

    public void setTelaPrincipalFx(TelaPrincipalFx telaPrincipalFx) {
        this.telaPrincipalFx = telaPrincipalFx;
    }

    @FXML
    private void criarConta(ActionEvent event) throws IOException {
        Parent criarContaRoot = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/tela_criarConta.fxml"));
        Scene  criarConta = new Scene(criarContaRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(criarConta);
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        String emailInput = email.getText();
        String senhaInput = senha.getText();
        MessageFx messageFx =  new MessageFx();

        //se logar com email poreque é cliente
        if(email.getPromptText().equals("Email")){
            //se login for verdadeiro
            if(new AuthManager().authenticateCliente(emailInput, senhaInput)){
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
               telaPrincipalFx.aculizarbotao();
                messageFx.showSucess("Login com sucesso");
            }else{
                messageFx.showError("Email ou senha incoreto");
            }
        }else {
            if(new AuthManager().authenticateFuncionario(emailInput, senhaInput)){
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                telaPrincipalFx.aculizarbotao();
                messageFx.showSucess("Login com sucesso");
            }else{
                messageFx.showError("Email ou senha incoreto");
            }
        }



    }

    @FXML
    void trocaLogin(ActionEvent event) {
        if(trocaDeLogin.getText().equals("Logar como funcionario")){
            email.setPromptText("Username");
            texto.setText("Faça login como funcionario");
            trocaDeLogin.setText("Fazer login como Cliente");
        }else{
            email.setPromptText("Email");
            texto.setText("Faça login");
            trocaDeLogin.setText("Logar como funcionario");
        }

    }

}
