package br.com.cielo.kafka;

import br.com.cielo.kafka.producer.SenderChamado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cielo.kafka.producer.Sender;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	//Envio de string
	//@Autowired
	//private Sender sender;


	//@Override
	//public void run(String... strings) throws Exception {
	//	for (int i = 0; i < 100; i++) {
	//		sender.send("Spring Kafka Producer and Consumer Example - " + i);
	//
	//	}
	//}


	//Envio json

	@Autowired
	private SenderChamado sender;

	@Override
	public void run(String... strings) throws Exception {
		sender.send();
	}
}
