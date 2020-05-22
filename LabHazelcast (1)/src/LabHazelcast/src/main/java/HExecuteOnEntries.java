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

		IMap<Long, Student> students = client.getMap("students");
		students.executeOnEntries(new HEntryProcessor());

		for (Entry<Long, Student> e : students.entrySet()) {
			System.out.println(e.getKey() + " => " + e.getValue());
		}
	}
}

class HEntryProcessor implements EntryProcessor<Long, Student, String>, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(Entry<Long, Student> e) {
		Student student = e.getValue();
		String name = student.getName();
		if (name.equals(name.toLowerCase())) {
			name = name.toUpperCase();
			student.setName(name);
		} else{
			name = name.toLowerCase();
			student.setName(name);
		}
		
		System.out.println("Processing = " + student);
		e.setValue(student);
		
		return name;
	}
}
