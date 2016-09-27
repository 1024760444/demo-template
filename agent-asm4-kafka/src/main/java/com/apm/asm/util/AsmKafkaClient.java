package com.apm.asm.util;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 将数据保存到kafka。
 * @author yanghaitao
 *
 */
public class AsmKafkaClient {
	
	/**
	 * kafka消息生产者
	 */
	private Properties props = new Properties();
	private Producer<String, String> producer = null;
	
	/**
	 * 初始化，kafka消息生产者。
	 * @param servers kafka服务地址，例如：192.168.106.128:9092,192.168.106.129:9092,192.168.106.130:9092
	 */
	public AsmKafkaClient(String servers) {
		props.put("bootstrap.servers", servers);
		props.put("acks", "0");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("request.timeout.ms", 10);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<String, String>(props);
	}
	
	/**
	 * 将数据保存到kafka。
	 */
	public void save(String topic, String data) {
		if(producer == null) {
			producer = new KafkaProducer<String, String>(props);
		}
		producer.send(new ProducerRecord<String, String>(topic, data));
		producer.flush();
	}
	
	/**
	 * 关闭生产者流。
	 */
	public void close() {
		producer.close();
	}
}
