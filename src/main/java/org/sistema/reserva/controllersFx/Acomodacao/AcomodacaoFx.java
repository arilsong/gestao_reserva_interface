package org.sistema.reserva.controllersFx.Acomodacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.fxml.Initializable;
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
import org.sistema.reserva.dao.QuartoDAO;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.service.QuartoService;
import org.sistema.reserva.validations.MessageFx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AcomodacaoFx{

    @FXML
    private TableView<Quarto> tabelaAcomodacao;

    @FXML
    private Button addAcomodacao;

    @FXML
    private ImageView btnRefesh;

    @FXML
    private TableColumn<Quarto, String> descricaoCol;

    @FXML
    private TableColumn<Quarto, String> nomeCol;

    @FXML
    private TableColumn<Quarto, String> numeroCol;

    @FXML
    private TableColumn<Quarto, Double> precoCol;

    @FXML
    private TableColumn<Quarto, Integer> qntdLeitoCol;

    @FXML
    private TableColumn<Quarto, String> estadoCol;

    @FXML
    private TableColumn<Quarto, String> tamanhoCol;

    @FXML
    private TableColumn<Quarto, String> tipoCol;

    @FXML
    private TableColumn<Quarto, String> editCol;

    Quarto quarto = null;
    ObservableList<Quarto> listaQuartos;

    @FXML
    void btnClicado(ActionEvent event) {
        Button botaoClicado = (Button) event.getSource();

        //evento para adicionar acomodacao
        //abre uma nova tela para ensirir dados
        if(botaoClicado == addAcomodacao){
            try {
                Parent loader = FXMLLoader.load(getClass().getResource("/org/sistema/reserva/views/dashboard/addAcomodacao.fxml"));
                Scene  scene = new Scene(loader);
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
        if(listaQuartos != null){
            listaQuartos.clear();
        }
        listaQuartos = new QuartoDAO().listarQuarto();
        caregarDados();
    }

    private void caregarDados() {
        if(tabelaAcomodacao != null){
            nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            numeroCol.setCellValueFactory(new PropertyValueFactory<>("num_quarto"));
            qntdLeitoCol.setCellValueFactory(new PropertyValueFactory<>("qtd_leitos"));
            precoCol.setCellValueFactory(new PropertyValueFactory<>("preco"));
            descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
            tamanhoCol.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
            tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

            tabelaAcomodacao.setItems(listaQuartos);

            Callback<TableColumn<Quarto, String>, TableCell<Quarto, String>> cellFactory = (TableColumn<Quarto, String> param) -> {
                // make cell containing buttons
                final TableCell<Quarto, String> cell = new TableCell<Quarto, String>() {
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
                                Quarto quarto = getTableView().getItems().get(getIndex());
                                new QuartoService().removerquarto(quarto.getNum_quarto());
                            });

                            editButton.setOnAction(event -> {
                                Quarto quarto = getTableView().getItems().get(getIndex());
                                try {
                                    //chama a funcao editar panel que foenece a tela editar e pasa o objeto quarto para ser usado depois
                                    new AddAcomodacaoFx().editarPanel(quarto);
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
