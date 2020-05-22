import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HMapPut {

	final private static Random r = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws UnknownHostException {
		Config config = HConfig.getConfig();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
		Map<Long, Client> patientsInstance = instance.getMap("clients_lib");
		Long key1 = (long) Math.abs(r.nextInt());
		Client client1 = new Client("Bartlomiej Koziel", 1997, 970302340, "Piekoszow, Kielecka 2");
		System.out.println("PUT " + key1 + " => " + client1);
        patientsInstance.put(key1, client1);

		Long key2 = (long) Math.abs(r.nextInt());
		Client client2 = new Client("Marian Tomczyk", 1992, 920616452, "Kielce, Warszawska 3");
        patientsInstance.put(key2, client2);
		System.out.println("PUT " + key2 + " => " + client2);

		Long key3 = (long) Math.abs(r.nextInt());
        Client client3 = new Client("Piotr Stepien", 1999, 990116510, "Sandomierz, Krakowska 2/12");
        patientsInstance.put(key3, client3);
        System.out.println("PUT " + key3 + " => " + client3);

        Long key4 = (long) Math.abs(r.nextInt());
        Client client4 = new Client("Malgorzata Foremniak", 1998, 980503144, "Kielce, Jagiellonśka 3");
        patientsInstance.put(key4, client4);
        System.out.println("PUT " + key4 + " => " + client4);

        Long key5 = (long) Math.abs(r.nextInt());
        Client client5 = new Client("Tomasz Kot", 1977, 771223539, "Gniezdziska, Gniezdziska 99");
        patientsInstance.put(key5, client5);
        System.out.println("PUT " + key5 + " => " + client5);

        Long key6 = (long) Math.abs(r.nextInt());
        Client client6 = new Client("Robert Kolasa", 1993, 930210145, "Lopuszno, Kielecka 2/3");
        patientsInstance.put(key6, client6);
        System.out.println("PUT " + key6 + " => " + client6);
	}
}
