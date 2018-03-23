/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Repositorys.PedidoRepository;
import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Pedido;
import edu.ifpb.dac.Produto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import recebimento.CardCredit;

/**
 *
 * @author recursive
 */
@Named
@Stateless
public class PedidoControlador {
    
    private Pedido pedido;
    @Inject
    private PedidoRepository pr;
    private Cliente cliente;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoRepository getPr() {
        return pr;
    }

    public void setPr(PedidoRepository pr) {
        this.pr = pr;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String addProduto(Produto pro){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Mensagem recebida:{0} no sms", "adiciona produto");
        pedido.add(pro);
        return "home.xhtml";
    }
    
    public String removeProduto(Produto pro){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "Mensagem recebida:{0} no sms", "remover produto");
        pedido.remove(pro);
        return "home.xhtml";
    }
    
    public String finalizarPedido(){
        pedido.setCliente(this.cliente);
        pr.add(this.pedido);
        return "home.xhtml";
    }
    
    @PostConstruct
    public void init(){
        this.pedido = new Pedido();
    }
}
