package org.sistema.reserva.controllersFx.Tela_Principal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.entity.Reserva;
import org.sistema.reserva.service.ClienteService;
import org.sistema.reserva.service.QuartoService;
import org.sistema.reserva.service.ReservaService;

import java.io.IOException;
import java.sql.Date;


public class PerfilFx {
    @FXML
    private TableView<Reserva> tabela;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<Reserva, String> cancelarCol;

    @FXML
    private TableColumn<Reserva, Date> dataCheckoutCol;

    @FXML
    private TableColumn<Reserva, Date> dataChrckinCol;

    @FXML
    private Label nome;

    @FXML
    private TableColumn<Reserva, Integer> numeroCol;

    @FXML
    private TableColumn<Reserva, Double> precoTotalCol;

    @FXML
    private TableColumn<Reserva, String> tamanhoCol;

    ObservableList<Reserva> listaReserva;


    //ações ao inicializar a tela
    public void initialize() {
        atualizarDados();
        if(AuthManager.getClienteLogado() != null){
            nome.setText(AuthManager.getClienteLogado().getNome() + " " + AuthManager.getClienteLogado().getSobreNome());
        }
    }

    @FXML
    void btnAtuliza(MouseEvent event) {
        atualizarDados();
    }

    public void atualizarDados(){
        if(listaReserva != null){
            listaReserva.clear();
        }
        listaReserva = new ReservaService().listarReservaCliente();

        carregarDados();
    }

    private void carregarDados() {

        if(tabela != null){
            listaReserva = new ReservaService().listarReservaCliente();

            // Para acessar propriedades aninhadas, precisamos de um cell value factory personalizado
            numeroCol.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getQuarto().getNum_quarto()).asObject()
            );
            dataCheckoutCol.setCellValueFactory(new PropertyValueFactory<>("dataCheckout"));
            dataChrckinCol.setCellValueFactory(new PropertyValueFactory<>("dataCheckin"));
            precoTotalCol.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
            tamanhoCol.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getQuarto().getTamanho())
            );

            tabela.setItems(listaReserva);

            Callback<TableColumn<Reserva, String>, TableCell<Reserva, String>> cellFactory = (TableColumn<Reserva, String> param) -> {
                // make cell containing buttons
                final TableCell<Reserva, String> cell = new TableCell<Reserva, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        // that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Button deleteButton = new Button("Cancelar");
                            deleteButton.setStyle("-fx-background-color: #ff3b1f; -fx-text-fill: white; -fx-font-weight: bold;");

                            deleteButton.setOnAction(event -> {
                                Reserva reserva = getTableView().getItems().get(getIndex());
                                listaReserva.remove(reserva);
                                new QuartoService().actualizarEstadoQuarto(false, reserva.getQuarto().getNum_quarto());
                                new ReservaService().removerReseerva(reserva.getId());
                            });

                            HBox buttonsContainer = new HBox(deleteButton);
                            buttonsContainer.setAlignment(Pos.CENTER);
                            buttonsContainer.setSpacing(5);

                            setGraphic(buttonsContainer);
                            setText(null);
                        }
                    }
                };

                return cell;
            };

            cancelarCol.setCellFactory(cellFactory);
        }
    }


    @FXML
    void editar(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/sistema/reserva/views/tela_principal/editar_conta.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void excluir(MouseEvent event) {
        new ClienteService().removerCliente(AuthManager.getClienteLogado().getId());
        new AuthManager().logoutCliente();
    }
}


