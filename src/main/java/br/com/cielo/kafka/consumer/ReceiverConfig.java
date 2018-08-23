package br.com.cielo.kafka.consumer;

import br.com.cielo.kafka.br.com.cielo.kafka.model.Chamado;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ReceiverConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		// Deserializer string
		//props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		//props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		//Deserializer json
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		//auto commit
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,4);

		props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");

		// Consumir a fila toda
		//props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


		// consumir apenas os ainda não consumidos.
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

		return props;
	}

	//deserializer string
	//@Bean
	//public ConsumerFactory<String, String> consumerFactory() {
	//	return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	//}

	//deserializer Json
	@Bean
	public ConsumerFactory<String, Chamado> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
				consumerConfigs(),
				new StringDeserializer(),
				new JsonDeserializer<>(Chamado.class));
	}

	// deserializer string
	//@Bean
	//public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
	//	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	//	factory.setConsumerFactory(consumerFactory());

	//	return factory;
	//}

	// Deserializer Json
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Chamado> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Chamado> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}