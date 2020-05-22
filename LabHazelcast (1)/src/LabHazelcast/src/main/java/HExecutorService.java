import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.Callable;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;

public class HExecutorService {

    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        final HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );

		IExecutorService executorService = client.getExecutorService("exec");
		executorService.submitToAllMembers(new HCallable());
	}
}

class HCallable implements Callable<Integer>, Serializable, HazelcastInstanceAware {
	private static final long serialVersionUID = 1L;	
	private transient HazelcastInstance instance;
	
	@Override
	public Integer call() {
		IMap<Long, Student> students = instance.getMap("students");
		Set<Long> keys = students.localKeySet();
		for (Long k : keys) {
			System.out.println("Instance " + instance + " " + k + " => " + students.get(k));
		}
		return keys.size();
	}

	@Override
	public void setHazelcastInstance(HazelcastInstance instance) {
		this.instance = instance;
	}
}