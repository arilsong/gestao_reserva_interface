package org.sistema.reserva.controllersFx.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.sistema.reserva.controllersFx.Acomodacao.AddAcomodacaoFx;
import org.sistema.reserva.entity.Funcionario;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.service.FuncionarioService;
import org.sistema.reserva.service.QuartoService;
import org.sistema.reserva.validations.IsEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addFuncionarioFx {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField idFld;

    @FXML
    private TextField cargoFld;

    @FXML
    private TextField emailFld;

    @FXML
    private TextField enderecoFld;

    @FXML
    private ChoiceBox<String> nivelAcesssoFld;

    @FXML
    private TextField nomeFld;

    @FXML
    private TextField nomeUsuarioFld;

    @FXML
    private TextField senhaFld;

    @FXML
    private TextField sobrenomeFld;

    @FXML
    private TextField telefoneFld;

    @FXML
    private Text titulo;


    @FXML
    void btnClicado(ActionEvent event) {
        Button botaoClicado = (Button) event.getSource();
        // pega o texto que esta no botao
        String nomeBtn = botaoClicado.getText();

        if(botaoClicado == btnCancelar){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

        //varifica se o texto do botao é igaul a adicionar porque usamos a mesma tela para editar e trocamos o texto do botao
        if(nomeBtn.equals("Adicionar")){
            //adiciona todos os campos para numa lisata para validar num clasee validacao
            ArrayList<TextField> campos = new ArrayList<>(List.of(nomeFld, sobrenomeFld, nomeUsuarioFld, emailFld, senhaFld, telefoneFld, cargoFld));

            // a validacao retorna true ou false
            // se for false conerte os dados e envie para base de dados
            if(!IsEmpty.isEmpty(campos)){
                String  nome = nomeFld.getText();
                String  sobrenome = sobrenomeFld.getText();
                String nomeUsuario = nomeUsuarioFld.getText();
                String email = emailFld.getText();
                String senha = senhaFld.getText();
                String telefone = telefoneFld.getText();
                String cargo = cargoFld.getText();
                String endereco = enderecoFld.getText();
                String nivel_de_acesso = nivelAcesssoFld.getValue();

                FuncionarioService funcionarioService = new FuncionarioService();
                funcionarioService.cadastrarFuncionario(nome, sobrenome, nomeUsuario, email, senha, telefone, endereco, cargo, nivel_de_acesso);

            }
        }

        if(nomeBtn.equals("Editar")){

            //adiciona todos os campos para numa lisata para validar num clasee validacao
            ArrayList<TextField> campos = new ArrayList<>(List.of(nomeFld, sobrenomeFld, nomeUsuarioFld, emailFld, senhaFld, telefoneFld, cargoFld));

            if(!IsEmpty.isEmpty(campos)){
                int id = Integer.parseInt(idFld.getText());;
                String  nome = nomeFld.getText();
                String  sobrenome = sobrenomeFld.getText();
                String nomeUsuario = nomeUsuarioFld.getText();
                String email = emailFld.getText();
                String senha = senhaFld.getText();
                String telefone = telefoneFld.getText();
                String cargo = cargoFld.getText();
                String endereco = enderecoFld.getText();
                String nivel_de_acesso = nivelAcesssoFld.getValue();
                FuncionarioService funcionarioService = new FuncionarioService();
                funcionarioService.atualizarFuncionario(id,nome, sobrenome, nomeUsuario, email, senha, telefone, endereco, cargo, nivel_de_acesso);
            }
        }
    }

    public void initialize() {
        // Inicialize o ChoiceBox com itens
        ObservableList<String> items = FXCollections.observableArrayList("normal", "admin");
        nivelAcesssoFld.setItems(items);

    }

    public void editarPanel(Funcionario funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sistema/reserva/views/dashboard/addFuncionario.fxml"));
        Parent root = loader.load();
        addFuncionarioFx controller = loader.getController();

        //o valor padrao da tela era "adicionar" entao mudei o texto para editar
        controller.btnAdicionar.setText("Editar");
        //muda o tilulo
        controller.titulo.setText("Editar Funcionario");

        //ao editar preeche todos os campos com os valore da acomodacao  que deseja editar
        controller.idFld.setText(Integer.toString(funcionario.getId()));
        controller.nomeFld.setText(funcionario.getNome());
        controller.sobrenomeFld.setText(funcionario.getSobreNome());
        controller.nomeUsuarioFld.setText(funcionario.getNomeUsuario());
        controller.emailFld.setText(funcionario.getEmail());
        controller.senhaFld.setText(funcionario.getSenha());
        controller.telefoneFld.setText(funcionario.getTelefone());
        controller.cargoFld.setText(funcionario.getCargo());
        controller.enderecoFld.setText(funcionario.getEndereco());
        controller.nivelAcesssoFld.setValue(funcionario.getNivel_de_acesso());


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

}
