import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HMapUpdate {
    public void updatePatientFullName(long id, String fullName, HazelcastInstance instance) {
        IMap<Object, Object> clients_lib=instance.getMap("clients_lib");
        Client clientToUpdate = (Client) clients_lib.get(id);
        clientToUpdate.setFullname(fullName);
        clients_lib.set(id, clientToUpdate);
    }
}
