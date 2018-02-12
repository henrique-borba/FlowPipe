package org.br.dataslack.flowpipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Node {

    final static Logger LOGGER = LogManager.getLogger(Node.class);
    final static NodeConfig config = NodeConfig.load();

    private String name;

    Node() {
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            this.setName(addr.getHostName());
            LOGGER.debug("Name: " + this.getName());
        }
        catch (UnknownHostException ex)
        {
            LOGGER.debug("Não foi possível associar um nome ao nó. Verifique se o HOSTNAME está acessível ou" +
                    "utilize a variável de configuração hostname.");
        }
    }


    public static Node getNode() {
        return new Node();
    }


    public String getName() {
        return this.name;
    }

    private void setName(String value) {
        this.name = value;
    }



}
