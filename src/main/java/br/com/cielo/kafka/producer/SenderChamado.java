package br.com.cielo.kafka.producer;

import br.com.cielo.kafka.br.com.cielo.kafka.model.Chamado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SenderChamado {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.foo}")
    private String topic;

    public void send() {

        Chamado chamado = new Chamado();


        for (int i = 0; i < 100; i++) {

            chamado.setIdChamado(new Long(i));
            chamado.setNome("SIGOTRIX_CIELO - " + new Long(i));
            chamado.setSituacaoChamado("ABERTA - " + new Long(i));

            //System.out.println(chamado.toString());

            Message<Chamado> message = MessageBuilder
                    .withPayload(chamado)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();

            kafkaTemplate.send(message);

            LOG.info("Envio de chamado GTEC", chamado.toString(), topic);

        }
    }
}