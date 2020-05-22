import java.net.UnknownHostException;
import java.util.Collection;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

public class HPredicate {

    public void findByYear(int minBirthyear, int maxBirthyear) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap<Long, Client> clients_lib = client.getMap("clients_lib");


		Predicate<?,?> minBirthyearPredicate = Predicates.greaterThan("birthyear", minBirthyear);
		Predicate<?,?> maxBirthyearPredicate = Predicates.lessThan("birthyear", maxBirthyear);


		Collection<Client> client2 = clients_lib.values(Predicates.and(minBirthyearPredicate, maxBirthyearPredicate));
		for (Client s : client2 ) {
			System.out.println(s);
		}
	}
}
