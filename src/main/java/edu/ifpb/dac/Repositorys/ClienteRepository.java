/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.Repositorys;

import edu.ifpb.dac.entity.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author recursive
 */
@Stateless
public class ClienteRepository implements Serializable{
    
    @PersistenceContext()
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public void add(Cliente cli){
        em.persist(cli);
    }
    
    public void remove(Cliente cli){
        em.remove(cli);
    }
    
    public void update(Cliente cli){
        em.merge(cli);
    }
    
    public List<Cliente> list(){
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
    
    public Cliente buscarPorId(int id) {
        return em.find(Cliente.class, id);
    }
    
    public Cliente buscarPorNome(String nome) {
        List<Cliente> clientes = list();
        Cliente cli = null;
        for (Cliente cliente : clientes) {
            if (cliente.getNome() == null ? nome == null : cliente.getNome().equals(nome)){
                cli = cliente;
            }
        }
        return cli;
    }
}
