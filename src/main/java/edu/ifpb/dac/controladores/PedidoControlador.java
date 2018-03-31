/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.entity.Cliente;
import edu.ifpb.dac.entity.Pedido;
import edu.ifpb.dac.entity.Produto;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import edu.ifpb.dac.service.PedidoService;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author recursive
 */

@Named
@RequestScoped
public class PedidoControlador implements Serializable{
    
    private Pedido pedido;
    private Cliente cliente;
    
    @Inject
    private PedidoService ps;

    public PedidoService getPs() {
        return ps;
    }

    public void setPs(PedidoService ps) {
        this.ps = ps;
    }
   
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String pedidoConfirmado(List<Produto> produtos, BigDecimal bd){
        ps.pedidoConfirmado(produtos, cliente, bd);
        return "home.xhtml?faces-redirect=true";
    }
    
    public String finalizarPedido(){
        ps.finalizarPedido();
        return "confirmacao.xhtml?faces-redirect=true";
    }

    @PostConstruct
    public void init(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sessao = (HttpSession) externalContext.getSession(false);
        this.cliente = (Cliente) sessao.getAttribute("cliente");
        this.pedido = new Pedido();
    }

}
