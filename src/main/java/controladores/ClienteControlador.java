/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Repositorys.ClienteRepository;
import edu.ifpb.dac.Cliente;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import recebimento.CardCredit;

/**
 *
 * @author recursive
 */
@Named
@SessionScoped
public class ClienteControlador implements Serializable{
    
    private Cliente cliente;
    @Inject
    private ClienteRepository cr;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteRepository getCr() {
        return cr;
    }

    public void setCr(ClienteRepository cr) {
        this.cr = cr;
    }
    
    public String login(String nome, String email){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "UsuarioLogandoNoSistema");
        cliente = cr.buscarPorNome(nome);
        if(cliente != null){
            return "home.xhtml";
        }
        return "index.xhtml";
    }
    
    public String cadastrar(String nome, String email){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, "CadastrarUsuario");
        Cliente cli = new Cliente(nome, email);
        cr.add(cli);
        return "index.xhtml";
    }

}
