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
        IMap<Long, Client> clients_lib = client.getMap( "clients_lib" );
        System.out.println("Show all clients of library: ");
        for(Entry<Long, Client> e : clients_lib.entrySet()){
        	System.out.println(e.getKey() + " => " + e.getValue());
        }
    }
    public void getById(long id, IMap<Long, Client> clients_lib) throws UnknownHostException {
        System.out.println(clients_lib.get(id));
    }
}

