/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.controladores;

import edu.ifpb.dac.Repositorys.ClienteRepository;
import edu.ifpb.dac.entity.Cliente;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import edu.ifpb.dac.recebimento.CardCredit;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author recursive
 */
@Named
@RequestScoped
public class ClienteControlador implements Serializable{
    
    private Cliente cliente;
    @Inject
    private ClienteRepository cr;
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

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
    
    public String login(){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, this.cliente.getNome());
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, this.cliente.getEmail());
        this.cliente = cr.buscarPorNome(this.cliente.getNome());
        if(cliente != null){
            HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sessao.setAttribute("cliente", cliente);
            return "home.xhtml?faces-redirect=true";
        }
        this.mensagem = "login inexistente";
        this.cliente = new Cliente();
        return "index.xhtml?faces-redirect=true";
    }
    
    public String cadastrar(){
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, this.cliente.getNome());
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, this.cliente.getEmail());
        cr.add(this.cliente);
        this.mensagem = "cadastro realizado com susseso";
        this.cliente = new Cliente();
        return "index.xhtml?faces-redirect=true";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
    
    @PostConstruct
    public void init(){
        this.cliente = new Cliente();
    }
}
