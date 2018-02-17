package org.br.dataslack.flowpipe;

public class NodeConfig {

    protected String yaml_file;

    /**
     * Get Current YAML FlowPipe Config
     */
    NodeConfig() {

    }


    // TODO: 12-Feb-18 Implementar o carregamento de configurações
    public static NodeConfig load_current() {
        return new NodeConfig();
    }

}
