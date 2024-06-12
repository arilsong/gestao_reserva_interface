package org.sistema.reserva.service;

import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.dao.QuartoDAO;
import org.sistema.reserva.entity.Quarto;

import java.util.List;

public class QuartoService {
    QuartoDAO quartoDAO = new QuartoDAO();

    public void adicionarQuarto(int num_quarto,String  nome,String tipo,int qtd_leitos,String tamanho,Double preco,String descricao){
        Quarto quarto = new Quarto();

        quarto.setNum_quarto(num_quarto);
        quarto.setNome(nome);
        quarto.setTipo(tipo);
        quarto.setQtd_leitos(qtd_leitos);
        quarto.setTamanho(tamanho);
        quarto.setPreco(preco);
        quarto.setDescricao(descricao);

        quartoDAO.adcionarQuarto(quarto);
    }

    public List<Quarto> listarQuarto(){
//        String estado = null;
//        if(quarto.getEstado()){
//            estado = "Reservado";
//        }else{
//            estado = "Livre";
//        }

        List<Quarto> quartos = quartoDAO.listarQuarto();
        for (Quarto quarto : quartos){
            System.out.println(quarto.getTipo());
        }
        return quartos;
    }

    public Quarto umQuarto(int numeroQuarto){
        Quarto quarto =  quartoDAO.umQuarto(numeroQuarto);
        return quarto;
    }


    public void atualizarQuarto(int num_quarto,String  nome,String tipo,int qtd_leitos,String tamanho,Double preco,String descricao,int num_qOld){
        Quarto quarto = new Quarto();

        quarto.setNum_quarto(num_quarto);
        quarto.setNome(nome);
        quarto.setTipo(tipo);
        quarto.setQtd_leitos(qtd_leitos);
        quarto.setTamanho(tamanho);
        quarto.setPreco(preco);
        quarto.setDescricao(descricao);

        quartoDAO.actualizarQuarto(quarto, num_qOld);

    }

    public void removerquarto(int num_quarto){
        quartoDAO.removerQuarto(num_quarto);
    }

    public void actualizarEstadoQuarto(boolean estado, int num_quarto){
        quartoDAO.actualizarEstadoQuarto(estado, num_quarto);
    }
}
