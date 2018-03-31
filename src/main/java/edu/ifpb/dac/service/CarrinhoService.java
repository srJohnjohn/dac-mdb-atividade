/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.service;

import edu.ifpb.dac.entity.Produto;
import edu.ifpb.dac.recebimento.CardCredit;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;

/**
 *
 * @author recursive
 */
@Stateful
public class CarrinhoService implements Serializable{
    
    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "AdicionaProdutoCarrinho");
        produtos.add(produto);
        Integer i = produtos.size();
        String qtde =  i.toString();
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, qtde);
    }
    
    public void removeProduto(Produto produto){
        produtos.remove(produto);
    }
    
    public BigDecimal valorPedido(){
        BigDecimal bd = new BigDecimal("0");
        for (int i = 0; i < produtos.size(); i++) {
            bd = bd.add(produtos.get(i).getPreco());
        }
        return bd;
    }
    
}
