import java.net.InetAddress;
import java.net.UnknownHostException;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;

public class HConfig {

	public static Config getConfig() throws UnknownHostException {
		Config config = new Config();
		NetworkConfig network = config.getNetworkConfig();
		JoinConfig join = network.getJoin();
		join.getMulticastConfig().setEnabled(false);
		join.getTcpIpConfig().addMember(getIPAddress()).setEnabled(true);
		return config;
	}

	public static ClientConfig getClientConfig() throws UnknownHostException {
		ClientConfig config = new ClientConfig();
		ClientNetworkConfig network = config.getNetworkConfig();
		network.addAddress(getIPAddress());
		return config;
	}

	public static String getIPAddress() throws UnknownHostException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println("My IP Address: " + ip);
		return ip;
	}
}
