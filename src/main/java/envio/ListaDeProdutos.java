/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envio;

import edu.ifpb.dac.Produto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author recursive
 */
@Named
@Singleton
public class ListaDeProdutos {
    
    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    @PostConstruct
    public void itens(){
        BigDecimal banana = new BigDecimal("2.5");
        produtos.add(new Produto("banana", banana));
        BigDecimal maca = new BigDecimal("5.0");
        produtos.add(new Produto("maca", maca));
        BigDecimal melancia = new BigDecimal("7.5");
        produtos.add(new Produto("maca", melancia));
    }
    
    
    
}
