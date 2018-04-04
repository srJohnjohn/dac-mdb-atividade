/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.jms;

import edu.ifpb.dac.entity.Cliente;
import edu.ifpb.dac.entity.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author recursive
 */
@Stateless
public class EnviarEmail {
    
    private final String EMAIL = "exemplo@email.com";
    private final String SENHA = "exemplo";
    private final Email email = new SimpleEmail();
    
    @PostConstruct
    public void init(){
        email.setHostName("smtp.googlemail.com");
        email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
        email.setTLS(true);
        email.setSSL(true);
    }
    
    public void send(String mensagem, Cliente destinatario){
        try {
            email.setFrom(EMAIL);
            email.addTo(destinatario.getEmail());
            email.setMsg(mensagem);      
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
