import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Map.Entry;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.EntryProcessor;

public class HExecuteOnEntries {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

		IMap<Long, Client> clients_lib = client.getMap("clients_lib");
		clients_lib.executeOnEntries(new HEntryProcessor());

		for (Entry<Long, Client> e : clients_lib.entrySet()) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
	}
}

class HEntryProcessor implements EntryProcessor<Long, Client, Integer>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Integer process(Entry<Long, Client> e) {
		Client client = e.getValue();
		int birthyear = client.getBirthyear();
		client.setBirthyear(birthyear+1);

		System.out.println("Processing = " + client);
		e.setValue(client);

		return birthyear;
	}
}
