/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.Repositorys.ProdutoRepository;
import edu.ifpb.dac.entity.Produto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import edu.ifpb.dac.recebimento.CardCredit;


/**
 *
 * @author recursive
 */
@Named
@ApplicationScoped
public class ProdutoControlador implements Serializable{

    private Produto vitrine;
    private Produto produto = new Produto();
    private List<Produto> prateleireira;
    @Inject
    private ProdutoRepository pr;
    private String preco;

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Produto getVitrine() {
        return vitrine;
    }

    public void setVitrine(Produto vitrine) {
        this.vitrine = vitrine;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getPrateleireira() {
        return prateleireira;
    }

    public void setPrateleireira(List<Produto> prateleireira) {
        this.prateleireira = prateleireira;
    }

    public ProdutoRepository getPr() {
        return pr;
    }

    public void setPr(ProdutoRepository pr) {
        this.pr = pr;
    }

    public String cadastrarProduto(){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "CadastroProduto");
        BigDecimal preco = new BigDecimal(this.preco);
        this.produto.setPreco(preco);
        pr.add(this.produto);
        prateleireira = pr.list();
        produto = new Produto();
        atualizarVitrine();
        return "home.xhtml";
    }
    
    public String removerProduto(String nome){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "RemoverCadastro");
        Produto pro = pr.buscarPorNome(nome);
        pr.remove(pro);
        prateleireira = pr.list();
        atualizarVitrine();
        return "home.xhtml";
    }
    
    public void atualizarVitrine(){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "AtualizarVitrine");
        Collections.shuffle(prateleireira);
        vitrine = prateleireira.get(0);
        Collections.sort(prateleireira);
    }
    
    @PostConstruct
    public void inicio(){
//        BigDecimal bd = new BigDecimal("2.5");
//        prateleireira = pr.list();
//        prateleireira.add(new Produto("banana", bd));
//        prateleireira.add(new Produto("jaca", bd));
        prateleireira = pr.list();
        
    }
}
