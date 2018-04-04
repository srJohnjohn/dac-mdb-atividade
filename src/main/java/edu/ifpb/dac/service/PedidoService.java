/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.service;

import edu.ifpb.dac.Repositorys.PedidoRepository;
import edu.ifpb.dac.entity.Cliente;
import edu.ifpb.dac.entity.Pedido;
import edu.ifpb.dac.entity.Produto;
import edu.ifpb.dac.jms.CardCredit;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 *
 * @author recursive
 */
@JMSDestinationDefinition(
        name = "java:global/jms/Compras",
        interfaceName = "javax.jms.Topic",
        resourceAdapter = "jmsra",
        destinationName = "compras")
@Stateless
public class PedidoService {
    
    @Inject
    private PedidoRepository pr;
    private Pedido ped = new Pedido();
    
    @Resource(lookup = "java:global/jms/Compras")
    private Topic topic;
    @Inject
    private JMSContext context;
    
    public void pedidoConfirmado(List<Produto> produtos, Cliente cliente, BigDecimal valor){
        
        ped.setCliente(cliente);
        ped.setValorTotal(valor);
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, ped.getValorTotal().toString());
        Logger.getLogger(CardCredit.class.getName()).log(Level.INFO, ped.getCliente().getNome());
        inserirUnicos(produtos);
        pr.add(ped);
        enviarMensagem(ped);
        ped = new Pedido();
    }
    
    public void finalizarPedido(){
        
    }

    private void inserirUnicos(List<Produto> produtos) {
        for(int i =0;  i<produtos.size();i++){
            Produto produto = produtos.get(i);
            if(!ped.getProdutos().contains(produto)){
                ped.getProdutos().add(produto);
            }
        }
    }
    
    private void enviarMensagem(Pedido pedido){
        JMSProducer produtor = context.createProducer();
        produtor.setProperty("finalizacao", "compra").send(topic, pedido);


    }
    
}
