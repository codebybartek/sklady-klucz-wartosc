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
		Map<Long, Student> students = instance.getMap("students");
		Long key1 = (long) Math.abs(r.nextInt());
		Student student1 = new Student("Kowalski", 1999);
		System.out.println("PUT " + key1 + " => " + student1);
		students.put(key1, student1);
		Long key2 = (long) Math.abs(r.nextInt());
		Student student2 = new Student("Nowak", 2002);
		students.put(key2, student2);
		System.out.println("PUT " + key2 + " => " + student2);
	}
}