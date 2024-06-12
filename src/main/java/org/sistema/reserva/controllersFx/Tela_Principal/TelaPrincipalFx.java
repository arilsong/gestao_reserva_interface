package org.sistema.reserva.controllersFx.Tela_Principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.controllersFx.LoginControllerFx;

import java.io.IOException;
import java.util.Objects;

public class TelaPrincipalFx {

    @FXML
    private Button btnAcomodacoes;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnSobre;


    @FXML
    private BorderPane pane;

    @FXML
    private HBox hbox;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnDashBoard;

    @FXML
    private Button btnLogin;


//    private Button btnSair = new Button();
//    private Button btnDashBoard = new Button();
//    private Button btnLogin = new Button();


    private AcomodacoesFx acomodacoesController;

    private VBox homePanel;
    private VBox acomodacaoPanel;
    private VBox perfilPanel;
    private VBox sobrePanel;

    @FXML
    private void btnClicado(ActionEvent event){
        Button botaoClicado = (Button) event.getSource();


        if(botaoClicado == btnHome){
            pane.setCenter(homePanel);
        }

        if(botaoClicado == btnAcomodacoes){
            try {
                acomodacaoPanel = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/sistema/reserva/views/tela_principal/tela_acomodacoes.fxml")));
                pane.setCenter(acomodacaoPanel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(botaoClicado == btnSobre){
            pane.setCenter(sobrePanel);
        }

        if(botaoClicado == btnPerfil){
            try {
                perfilPanel = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/sistema/reserva/views/tela_principal/perfil.fxml")));
                pane.setCenter(perfilPanel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        if(botaoClicado.getText().equals("Login")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sistema/reserva/views/telaLogin.fxml"));
                Parent root = loader.load();

                LoginControllerFx loginController = loader.getController();
                loginController.setTelaPrincipalFx(this);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(botaoClicado == btnSair){
            AuthManager authManager = new AuthManager();
            authManager.logoutFunc();
            authManager.logoutCliente();
            aculizarbotao();
        }

        if(botaoClicado == btnDashBoard){
            try {
                Parent parent = null;
                parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/sistema/reserva/views/dashboard/dashboard.fxml")));
                Scene  scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //executa ao inicializar a tela
    public void initialize() {
        // Carregue os novo VBox a partir dos arquivos FXML
        try {
            homePanel = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/sistema/reserva/views/tela_principal/tela_home.fxml")));
            aculizarbotao();
            pane.setCenter(homePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aculizarbotao() {
        if(AuthManager.getClienteLogado() != null || AuthManager.getFuncionarioLogado() != null){
            //remover botao login
            btnLogin.setVisible(false);
            btnLogin.setManaged(false);

            //adicionando botao sair
            btnSair.setVisible(true);
            btnSair.setManaged(true);

            if(AuthManager.getFuncionarioLogado() != null){
                btnDashBoard.setVisible(true);
                btnDashBoard.setManaged(true);
            }else{
                btnDashBoard.setVisible(false);
                btnDashBoard.setManaged(false);
                btnPerfil.setVisible(true);
            }
        }else {
            //adicioando botao login
            btnLogin.setVisible(true);
            btnLogin.setManaged(true);

            //removendo botao sair
            btnSair.setVisible(false);
            btnSair.setManaged(false);

            btnDashBoard.setVisible(false);
            btnDashBoard.setManaged(false);

            btnPerfil.setVisible(false);

        }
    }
}
