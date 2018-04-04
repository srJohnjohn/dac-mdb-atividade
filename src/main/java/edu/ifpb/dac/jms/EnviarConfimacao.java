/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.recebimento;

import edu.ifpb.dac.entity.Pedido;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 *
 * @author recursive
 */
//envia
@JMSDestinationDefinition(
        name = "java:global/jms/Confirmacao",
        interfaceName = "javax.jms.Queue",
        resourceAdapter = "jmsra",
        destinationName = "confirmada")
@Stateless
public class EnviarConfimacao {
    
    @Inject
    private JMSContext context;
    @Resource(lookup = "java:global/jms/Confimacao")
    private Queue fila;
    
    public void enviar(Pedido p){
        JMSProducer createProducer = context.createProducer();
        createProducer.send(fila, p);
    }
    
}
