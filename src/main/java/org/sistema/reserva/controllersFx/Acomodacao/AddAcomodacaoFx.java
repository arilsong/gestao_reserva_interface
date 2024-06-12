package org.sistema.reserva.controllersFx.Acomodacao;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.sistema.reserva.dao.QuartoDAO;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.service.QuartoService;
import org.sistema.reserva.validations.IsEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddAcomodacaoFx {
    @FXML
    private Text titulo;

    @FXML
    private Button btnAdicionar;


    @FXML
    private Button btnCancelar;

    @FXML
    private TextField descricaoFld;

    @FXML
    private TextField nAcomodacaoFld;

    @FXML
    private TextField nomeFld;

    @FXML
    private TextField precoFld;

    @FXML
    private TextField qntdLeitosFld;

    @FXML
    private TextField tamanhoFld;

    @FXML
    private ChoiceBox<String> tipoFld;

    int numeroQAntigo = 0;

    public void initialize() {
        // Inicialize o ChoiceBox com itens
        ObservableList<String> items = FXCollections.observableArrayList("Quarto", "Suite");
        tipoFld.setItems(items);

        //chamada da funcao assim que a tela inicializar
        addNumericListener(nAcomodacaoFld);
        addNumericListener(qntdLeitosFld);
        addDecimalListener(precoFld);
    }


    //funcao para o campo aceitar apenas numero
    private void addNumericListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    //funcao para aceitar apenas numero e poento no caso de double
    private void addDecimalListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
    }

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
            ArrayList<TextField> campos = new ArrayList<>(List.of(nAcomodacaoFld, nomeFld, qntdLeitosFld, precoFld, descricaoFld, tamanhoFld));

            // a validacao retorna true ou false
            // se for false conerte os dados e envie para base de dados
            if(!IsEmpty.isEmpty(campos)){
                int num_quarto = Integer.parseInt(nAcomodacaoFld.getText());
                String  nome = nomeFld.getText();
                String tipo = tipoFld.getValue();
                int qtd_leitos = Integer.parseInt(qntdLeitosFld.getText());
                Double preco = Double.parseDouble(precoFld.getText());
                String descricao = descricaoFld.getText();
                String tamanho = tamanhoFld.getText();

                QuartoService quartoService = new QuartoService();
                quartoService.adicionarQuarto(num_quarto, nome, tipo, qtd_leitos, tamanho, preco, descricao);

            }
        }

        if(nomeBtn.equals("Editar")){
            //adiciona todos os campos para numa lisata para validar num clasee validacao
            ArrayList<TextField> campos = new ArrayList<>(List.of(nAcomodacaoFld, nomeFld, qntdLeitosFld, precoFld, descricaoFld, tamanhoFld));

            if(!IsEmpty.isEmpty(campos)){
                int num_quarto = Integer.parseInt(nAcomodacaoFld.getText());
                String  nome = nomeFld.getText();
                String tipo = tipoFld.getValue();
                int qtd_leitos = Integer.parseInt(qntdLeitosFld.getText());
                Double preco = Double.parseDouble(precoFld.getText());
                String descricao = descricaoFld.getText();
                String tamanho = tamanhoFld.getText();
                System.out.println(nome);
                QuartoService quartoService = new QuartoService();
                quartoService.atualizarQuarto(num_quarto, nome, tipo, qtd_leitos, tamanho, preco, descricao, numeroQAntigo);

            }
        }

    }

    public void editarPanel(Quarto quarto) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sistema/reserva/views/dashboard/addAcomodacao.fxml"));
        Parent root = loader.load();
        numeroQAntigo = quarto.getNum_quarto();
        AddAcomodacaoFx controller = loader.getController();

        //o valor padrao da tela era "adicionar" entao mudei o texto para editar
        controller.btnAdicionar.setText("Editar");
        //muda o tilulo
        controller.titulo.setText("Editar Acomodacao");

        //ao editar preeche todos os campos com os valore da acomodacao  que deseja editar
        controller.nAcomodacaoFld.setText(Integer.toString(quarto.getNum_quarto()));
        controller.nomeFld.setText(quarto.getNome());
        controller.tipoFld.setValue(quarto.getTipo());
        controller.qntdLeitosFld.setText(Integer.toString(quarto.getQtd_leitos()));
        controller.tamanhoFld.setText(quarto.getTamanho());
        controller.precoFld.setText(Double.toString(quarto.getPreco()));
        controller.descricaoFld.setText(quarto.getDescricao());


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
