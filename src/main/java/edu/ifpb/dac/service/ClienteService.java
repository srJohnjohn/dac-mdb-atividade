/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.service;

import edu.ifpb.dac.Repositorys.ClienteRepository;
import edu.ifpb.dac.entity.Cliente;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author recursive
 */
@Stateless
public class ClienteService implements Serializable{
    
    @Inject
    private ClienteRepository cr;
    
    public void addCliente(Cliente cli){
        cr.add(cli);
    }
    
    public boolean loagar(Cliente cli){
        Cliente c = cr.buscarPorNome(cli.getNome());
        return c!=null;
    }
    
}
