package org.br.dataslack.flowpipe.config_gateways;

import java.util.Map;

public class YAMLGateway extends ConfigGateway implements Gateway {

    @Override
    public boolean init(Map<String, String> pipe_conf) {
        return false;
    }

    @Override
    public Config load() {
        return null;
    }
}
