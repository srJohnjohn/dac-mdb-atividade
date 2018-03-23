/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorys;

import edu.ifpb.dac.Pedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author recursive
 */
@Stateless
public class PedidoRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public void add(Pedido pedido){
        em.persist(pedido);
    }
    
    public void remove(Pedido pedido){
        em.remove(pedido);
    }
    
    public void update(Pedido pedido){
        em.merge(pedido);
    }
    
    public List<Pedido> list(){
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }
    
    public Pedido buscarPorId(int id) {
        return em.find(Pedido.class, id);
    }
}
