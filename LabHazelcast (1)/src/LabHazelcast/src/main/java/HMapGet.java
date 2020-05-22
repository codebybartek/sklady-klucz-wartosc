import com.hazelcast.client.config.ClientConfig;

import java.net.UnknownHostException;
import java.util.Map.Entry;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HMapGet {
    public static void main( String[] args ) throws UnknownHostException {
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        IMap<Long, Student> students = client.getMap( "students" );
        System.out.println("All student: ");
        for(Entry<Long, Student> e : students.entrySet()){
        	System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
}