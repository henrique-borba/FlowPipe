package org.br.dataslack.flowpipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class NodeConfig {

    private static final Logger LOGGER = LogManager.getLogger(NodeConfig.class);
    protected final Path HOME_PATH;
    private Map config = null;

      /**
     * Get Current YAML FlowPipe Config
     */
    NodeConfig(Path home) {
        this.HOME_PATH = home;
        Yaml yaml = new Yaml();
        try( InputStream in = Files.newInputStream( Paths.get(home.toString() + "/conf/flowpipe.yml") ) ) {
            this.config = (Map) yaml.load( in);
            LOGGER.debug( this.config.toString() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map map() {
        return this.config;
    }

    // TODO: 12-Feb-18 Implementar o carregamento de configurações
    public static NodeConfig load_current(Path home) {
        return new NodeConfig(home);
    }

}
