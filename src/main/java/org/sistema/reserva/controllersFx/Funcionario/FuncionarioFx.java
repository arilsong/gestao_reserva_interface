package org.sistema.reserva.controllersFx.Funcionario;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.sistema.reserva.controllersFx.Acomodacao.AddAcomodacaoFx;
import org.sistema.reserva.dao.FuncionarioDAO;
import org.sistema.reserva.dao.QuartoDAO;
import org.sistema.reserva.entity.Funcionario;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.service.FuncionarioService;
import org.sistema.reserva.service.QuartoService;

import java.io.IOException;

public class FuncionarioFx {

    @FXML
    private Button addFuncionario;

    @FXML
    private ImageView btnRefesh;

    @FXML
    private TableColumn<Funcionario, String> cargoCol;

    @FXML
    private TableColumn<Funcionario, String> editCol;

    @FXML
    private TableColumn<Funcionario, String> emailCol;

    @FXML
    private TableColumn<Funcionario, String> enderecoCol;

    @FXML
    private TableColumn<Funcionario, String> nivelAcessoCol;

    @FXML
    private TableColumn<Funcionario, String> nomeCol;

    @FXML
    private TableColumn<Funcionario, String> nomeUsuarioCol;


    @FXML
    private TableColumn<Funcionario, String> sobrenomeCol;

    @FXML
    private TableView<Funcionario> tabelaAcomodacao;

    @FXML
    private TableColumn<Funcionario, String> telefoneCol;

    ObservableList<Funcionario> listaFuncionario;

    @FXML
    void btnClicado(ActionEvent event) {
        Button botaoClicado = (Button) event.getSource();


        if(botaoClicado == addFuncionario){
            try {
                Parent loader = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/dashboard/addFuncionario.fxml"));
                Scene scene = new Scene(loader);
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
    }

    //acoes ao incializar a tela
    public void initialize() {
        atualizarDados();
    }

    @FXML
    void refesh(MouseEvent event) {
        atualizarDados();
    }


    public void atualizarDados(){
        if(listaFuncionario != null){
            listaFuncionario.clear();
        }
        listaFuncionario = new FuncionarioDAO().listarFuncionario();
        caregarDados();
    }

    private void caregarDados() {
        if(tabelaAcomodacao != null){
            nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            sobrenomeCol.setCellValueFactory(new PropertyValueFactory<>("sobreNome"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            nomeUsuarioCol.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
            enderecoCol.setCellValueFactory(new PropertyValueFactory<>("endereco"));
            cargoCol.setCellValueFactory(new PropertyValueFactory<>("cargo"));
            nivelAcessoCol.setCellValueFactory(new PropertyValueFactory<>("nivel_de_acesso"));

            tabelaAcomodacao.setItems(listaFuncionario);

            Callback<TableColumn<Funcionario, String>, TableCell<Funcionario, String>> cellFactory = (TableColumn<Funcionario, String> param) -> {
                // make cell containing buttons
                final TableCell<Funcionario, String> cell = new TableCell<Funcionario, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        // that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Button deleteButton = new Button("Delete");
                            Button editButton = new Button("Edit");

                            deleteButton.setStyle("-fx-background-color: #ff3b1f; -fx-text-fill: white; -fx-font-weight: bold;");
                            editButton.setStyle("-fx-background-color: #4169E1; -fx-text-fill: white; -fx-font-weight: bold;");

                            deleteButton.setOnAction(event -> {
                                Funcionario funcionario = getTableView().getItems().get(getIndex());
                                new FuncionarioService().removerFuncionario(funcionario.getId());

                            });

                            editButton.setOnAction(event -> {
                                Funcionario funcionario = getTableView().getItems().get(getIndex());
                                //chama a funcao editar panel que foenece a tela editar e pasa o objeto quarto para ser usado depois
                                try {
                                    new addFuncionarioFx().editarPanel(funcionario);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                            HBox buttonsContainer = new HBox(editButton, deleteButton);
                            buttonsContainer.setAlignment(Pos.CENTER);
                            buttonsContainer.setSpacing(5);

                            setGraphic(buttonsContainer);
                            setText(null);
                        }
                    }
                };

                return cell;
            };

            editCol.setCellFactory(cellFactory);

        }
    }



}

