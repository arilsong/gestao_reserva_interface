package org.sistema.reserva.controllersFx.Tela_Principal;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.sistema.reserva.dao.QuartoDAO;
import org.sistema.reserva.entity.Quarto;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AcomodacoesFx implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Label label1;

    @FXML
    private ScrollPane scroll;

    @FXML
    private DatePicker data_checkin;

    @FXML
    private DatePicker data_chekout;

    ObservableList<Quarto> listaQuarto = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actualizarDados();
    }

    public void actualizarDados(){
        if(listaQuarto != null && grid.getChildren() != null){
            listaQuarto.clear();
            grid.getChildren().clear();
        }
        QuartoDAO quartoDAO = new QuartoDAO();
        listaQuarto = quartoDAO.listarQuarto();

        carregarDados();

    }

    private void carregarDados(){
        int rows = 0;

        for(Quarto quarto : listaQuarto){
            //verifca se etado do quarto é falso(nao reservado) e mostra apenas os quertos nao reservados
            if(!quarto.isEstado()){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/org/sistema/reserva/views/tela_principal/box_acomodacao.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();


                    BoxAcomodacaoFx reservaController = fxmlLoader.getController();
                    //passa arefenrencia para o controlador boxAcomodacao para o boxControlador usar metodos dessa clase
                    reservaController.setAcomodacoesFxController(this);

                    reservaController.setData(quarto);

                    grid.add(anchorPane, 0, rows++);

                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage().toString());
                }
            }
        }
    }

    //metodos para pegar as datas
    //com fim de usar etse metos no boxAcomodacaoFx
    public LocalDate getCheckin(){
        return data_checkin.getValue();
    }

    public LocalDate getCheckout(){
        return data_chekout.getValue();
    }


}
