import java.net.UnknownHostException;
import java.util.Collection;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

public class HPredicate {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap<Long, Student> students = client.getMap("students");

		Predicate<?,?> namePredicate = Predicates.equal( "name", "KOWALSKI" );
		Predicate<?,?> birthyearPredicate = Predicates.lessThan("birthyear", 2000);

		Collection<Student> kowalski = students.values(Predicates.and(namePredicate, birthyearPredicate));
		for (Student s : kowalski) {
			System.out.println(s);
		}
	}
}
