package kafka;

import com.apm.asm.init.AsmInit;
import com.apm.asm.util.ToolsUtil;

public class TestKafkaUtil {
	public static void main(String[] args) {
		AsmInit.init();
		
		ToolsUtil.client.save(ToolsUtil.topic, ("test"));
	}
}
