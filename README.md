## PROJETO EM CONSTRUÇÃO. NÃO FUNCIONAL

# FlowPipe
FlowPipe é uma plataforma open-source para ***ingestão***
e processamento de dados em tempo real provenientes de diversas fontes.

Foi inicialmente desenvolvido pela DataSlack para 
utilização interna com o objetivo de simplificar o
processo de consumo de dados, ao mesmo tempo
permitindo o processamento paralelo de dados com o 
mínimo de ***delay***.


# Configuração

FlowPipe permite a integração de seus parâmetros de configuração
tanto em YAML como utilizando um banco de dados MySQL. Isso permite atualizar as configurações
do FlowPipe em tempo real com o mínimo de esforço ou sistemas agendados.

# Plugins

## Entrada (Inputs)
Os plugins de entrada permitem a conexão com o fluxo
inicial de dados (ex: Twitter API/Stream, RSS, etc.).

No cluster FlowPipe, os Inputs são executados apenas no nó
***master*** (master node)

## Saída (Outputs)
Os plugins de  (outputs) permitem a conexão com o banco de dados, sistema de arquivos ou outro sistema de armazenamento
para enviar os dados provenientes
dos filtros (filters) e entradas (inputs)

## Consumidor (Consumer)
Os Consumidores são camadas distribuídas de ingestão de dados de forma **assíncronca**. Diferente dos
filtros, um Consumer recebe os dados quando este
já foi enviado ao Output (saída), após o processamento, o consumer apenas atualiza os dados no output.

- Imagine que um determinado algoritmo cause um delay de 2 minutos até
chegar ao cliente (observador), ao utilizar este algoritmo no Consumer, apenas os dados dependentes do Consumer sofrem este delay.

## Filtros (Filters)
Os Filtros são camadas distribuídas de processamento, diferente do Consumidor (Consumer) os filtros são utilizados entre a Entrada e Saída, ou seja, 
os dados só serão enviados ao sistema de arquivos, banco e dados e etc após serem processados pelo filtro.
