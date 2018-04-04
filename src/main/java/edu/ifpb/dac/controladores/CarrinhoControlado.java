/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.entity.Produto;
import edu.ifpb.dac.service.CarrinhoService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author recursive
 */
@Named
@SessionScoped
public class CarrinhoControlado implements Serializable{
    
    @Inject
    private CarrinhoService cs;
    private List<Produto> produtos;
    private BigDecimal bd;

    public BigDecimal getBd() {
        return bd;
    }

    public void setBd(BigDecimal bd) {
        this.bd = bd;
    }

    public CarrinhoService getCs() {
        return cs;
    }

    public void setCs(CarrinhoService cs) {
        this.cs = cs;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public String add(Produto pro){
        cs.addProduto(pro);
        atulizarLista();
        return "home.xhtml?faces-redirect=true";
    }

    public String remove(Produto pro){
        cs.removeProduto(pro);
        atulizarLista();
        return "home.xhtml?faces-redirect=true";
    }
    
    private void atulizarLista(){
        bd = cs.valorPedido();
        produtos = cs.getProdutos();
        
    }
    
    public String zerarCarrinho(){
        this.produtos = new ArrayList<>();
        return "home.xhtml?faces-redirect=true";
    }
    
}
