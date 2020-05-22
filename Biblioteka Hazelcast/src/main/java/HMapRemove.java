import java.net.UnknownHostException;
import java.util.Map;

public class HMapRemove {
    public void removeClient(long id, Map<Long, Client> clients_lib ) throws UnknownHostException {
        clients_lib.remove(id);
    }
}
