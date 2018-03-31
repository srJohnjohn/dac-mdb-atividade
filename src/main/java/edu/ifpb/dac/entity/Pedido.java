package edu.ifpb.dac.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ricardo Job
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private BigDecimal valorTotal;

    @OneToMany
    private List<Produto> produtos;
    
    @ManyToOne
    private Cliente cliente;

    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(Produto produto) {
        this.produtos.add(produto);
    }

    public void remove(Produto produto) {
        this.produtos.remove(produto);
    }
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}
