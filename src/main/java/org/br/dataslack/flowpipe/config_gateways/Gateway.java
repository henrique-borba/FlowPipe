package org.br.dataslack.flowpipe.config_gateways;

import java.util.List;
import java.util.Map;

public interface Gateway {

    /**
     * Initialize the configuration gateway, in case of persistent connections, you should
     * initialize it here.
     *
     * @return
     */
    boolean init(Map<String, String> pipe_conf);

    /**
     * Load or Reload current configuration. In case of persistent connections, you should for example
     * query your initialized connection at init() here.
     *
     * @return
     */
    Config load();

    /**
     * Necessary for ConfigGateway abstract class
     */
    void setConfig(Config config);

    /**
     * Necessary for ConfigGateway abstract class
     */
    Config getConfig();

    /**
     * Necessary for ConfigGateway abstract class
     * @return
     */
    Config reload();

}
