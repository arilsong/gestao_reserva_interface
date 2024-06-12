package org.sistema.reserva.controllersFx.Tela_Principal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.validations.MessageFx;

import java.time.LocalDate;
import java.util.Date;

public class BoxAcomodacaoFx {

    @FXML
    private AnchorPane anchor_pane;

    @FXML
    private Button btnReservar;

    @FXML
    private Label descricao;

    @FXML
    private Label nome;

    @FXML
    private Label numero;

    @FXML
    private Label preco;

    @FXML
    private Label qtdLeitos;

    @FXML
    private Label tamanho;

    @FXML
    private Label tipo;

    private Quarto quarto;
    private AcomodacoesFx acomodacoesFxController; // campo para armazenar a referência

    @FXML
    void btnClicado(MouseEvent event) {
        MessageFx messageFx = new MessageFx();
        //verifica se o cliente esta logado
        if(AuthManager.getClienteLogado() != null){
            // pega as datas do acomodacoesFx
            LocalDate dataCheckin = acomodacoesFxController.getCheckin();
            LocalDate dataCheckout = acomodacoesFxController.getCheckout();

            if(dataCheckin == null || dataCheckout == null){
                messageFx.showError("Preencha as datas antes de solicitar reserva");
            }else{
                quarto.reservar(quarto.getNum_quarto(), dataCheckin, dataCheckout);
                acomodacoesFxController.actualizarDados();
            }

        }else{
            messageFx.showError("Faça Login");
        }
    }

    public void setData(Quarto quarto){
        this.quarto = quarto;

        nome.setText(quarto.getNome());
        descricao.setText(quarto.getDescricao());
        numero.setText(Integer.toString(quarto.getNum_quarto()));
        preco.setText(Double.toString(quarto.getPreco()) + "  CVE");
        tamanho.setText(quarto.getTamanho());
        tipo.setText(quarto.getTipo());
        qtdLeitos.setText(Integer.toString(quarto.getQtd_leitos()));
    }

    //metodo para receber a referencia do acomdacaofx e usar os seus metodos
    public void setAcomodacoesFxController(AcomodacoesFx acomodacoesFxController) {
        this.acomodacoesFxController = acomodacoesFxController;
    }


}
