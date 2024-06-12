package org.sistema.reserva.controllersFx.Tela_Principal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.sistema.reserva.entity.Hotel;
import org.sistema.reserva.service.HotelService;

public class SobreFx {

    @FXML
    private Label clasificacao;

    @FXML
    private Label contato;

    @FXML
    private Text descricao;

    @FXML
    private Label localizacao;

    @FXML
    private Label nome;

    Hotel hotel = new HotelService().infoHotel();

    public void initialize() {
        nome.setText(hotel.getNome());
        descricao.setText(hotel.getDescricao());
        contato.setText(hotel.getContato());
        localizacao.setText(hotel.getLocalizacao());
        clasificacao.setText(hotel.getClassificacao());
    }

}
