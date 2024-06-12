package org.sistema.reserva.controllersFx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.sistema.reserva.entity.Reserva;
import org.sistema.reserva.service.QuartoService;
import org.sistema.reserva.service.ReservaService;

import java.sql.Date;

public class ReservaFx {

    @FXML
    private ImageView btnRefesh;

    @FXML
    private TableColumn<Reserva, Date> dataCheckinCol;

    @FXML
    private TableColumn<Reserva, Date> dataCheckoutCol;

    @FXML
    private TableColumn<Reserva, String> emailCol;


    @FXML
    private TableColumn<Reserva, String> nomeClienteCol;

    @FXML
    private TableColumn<Reserva, Integer> numeroAcoCol;

    @FXML
    private TableView<Reserva> tabelaReserva;

    @FXML
    private TableColumn<Reserva, String> telefoneCol;

    @FXML
    private TableColumn<Reserva, String> tipoAcomodacaoCol;

    @FXML
    private TableColumn<Reserva, Double> valorCol;

    private ObservableList<Reserva> listaReserva;

    @FXML
    void refesh(MouseEvent event) {

    }


    //ações ao inicializar a tela
    public void initialize() {
        atualizarDados();
    }

    public void atualizarDados(){
        if(listaReserva != null){
            listaReserva.clear();
        }
        listaReserva = new ReservaService().listaReservas();
        carregarDados();
    }

    private void carregarDados() {

        if(tabelaReserva != null){
            // Para acessar propriedades aninhadas, precisamos de um cell value factory personalizado
            numeroAcoCol.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getQuarto().getNum_quarto()).asObject()
            );
            dataCheckoutCol.setCellValueFactory(new PropertyValueFactory<>("dataCheckout"));
            dataCheckinCol.setCellValueFactory(new PropertyValueFactory<>("dataCheckin"));
            valorCol.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
            tipoAcomodacaoCol.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getQuarto().getTipo())
            );

            nomeClienteCol.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getCliente().getNome() + " " + cellData.getValue().getCliente().getSobreNome())
            );

            emailCol.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getCliente().getEmail())
            );
            telefoneCol.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getCliente().getTelefone())
            );





            tabelaReserva.setItems(listaReserva);

        }
    }

}
