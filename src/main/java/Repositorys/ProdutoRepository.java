/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorys;

import edu.ifpb.dac.Cliente;
import edu.ifpb.dac.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author recursive
 */
@Stateless
public class ProdutoRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public void add(Produto prod){
        em.persist(prod);
    }
    
    public void remove(Produto prod){
        em.remove(prod);
    }
    
    public void update(Produto prod){
        em.merge(prod);
    }
    
    public List<Produto> list(){
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }
    
    public Produto buscarPorId(int id) {
        return em.find(Produto.class, id);
    }
    
    public Produto buscarPorNome(String nome) {
        List<Produto> produtos = list();
        Produto pro = null;
        for (Produto produto : produtos) {
            if (produto.getDescricao() == nome){
                pro = produto;
            }
        }
        return pro;
    }
}
