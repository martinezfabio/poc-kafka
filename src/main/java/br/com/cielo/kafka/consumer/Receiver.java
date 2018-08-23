package br.com.cielo.kafka.consumer;

import br.com.cielo.kafka.br.com.cielo.kafka.model.Chamado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Receiver {

	private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

	// RECEIVER STRING
	//@KafkaListener(topics = "${app.topic.foo}")
	//public void listen(@Payload String message) {

	//	LOG.info("received message='{}'", message);
	//	System.out.println(message);
	//}

	//RECEIVER JSON
	@KafkaListener(topics = "${app.topic.foo}")
	//@Transactional
	public void listen(@Payload Chamado message) {

		try {

			if (message.getIdChamado() == 20){
				throw new Exception();
			}
			LOG.info("received message='{}'", message);
			System.out.println(message);

		} catch (Exception e) {
			e.printStackTrace();
			//System.exit(1);

		}

	}

}