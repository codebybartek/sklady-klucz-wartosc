import java.net.UnknownHostException;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;

public class HInstance {

    public static void main(String[] args) throws UnknownHostException {
        Config config = HConfig.getConfig();
		Hazelcast.newHazelcastInstance(config);
	}
}