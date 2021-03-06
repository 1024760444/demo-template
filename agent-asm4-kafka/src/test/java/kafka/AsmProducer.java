package kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * kafka发布数据示例代码。
 * @author yanghaitao
 *
 */
public class AsmProducer {
	public static void asmProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.106.128:9092,192.168.106.129:9092,192.168.106.130:9092");
		props.put("acks", "0");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		producer.send(new ProducerRecord<String, String>("apm01", null, "asm4/Test.get | 1014 | 1469586100226"));
		producer.flush();
		producer.close();
	}

	public static void main(String[] args) {
		asmProducer();
	}
}
