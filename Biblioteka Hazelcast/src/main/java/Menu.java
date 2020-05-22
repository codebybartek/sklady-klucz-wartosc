import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws UnknownHostException {
        ClientConfig clientConfig=HConfig.getClientConfig();
        HazelcastInstance client=HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Long, Client> clients=client.getMap("clients_lib");

        Config config=HConfig.getConfig();
        HazelcastInstance instance=Hazelcast.newHazelcastInstance(config);
        Map<Long, Client> clientsInstance=instance.getMap("clients_lib");
        HMapPut hMapPut=new HMapPut();
        HMapGet hMapGet=new HMapGet();
        HMapRemove hMapRemove=new HMapRemove();
        HExecuteOnEntries hExecuteOnEntries=new HExecuteOnEntries();
        HMapUpdate hMapUpdate=new HMapUpdate();
        HPredicate hPredicate=new HPredicate();
        HAgregate hAgregate=new HAgregate();


        do {
            Scanner scanner=new Scanner(System.in);
            System.out.println("<--------------BIBLIOTEKA-------------->");
            System.out.println("-- 1. Dodaj klientow z listy");
            System.out.println("-- 2. Wyświetl wszystkich klientow");
            System.out.println("-- 3. Wyświetl klienta o podanym ID");
            System.out.println("-- 4. Wyszukaj klientow urodzonych w podanych latach");
            System.out.println("-- 5. Usuń wybranego klienta");
            System.out.println("-- 6. Usuń wszystkich klientow");
            System.out.println("-- 7. Aktualizuj");
            System.out.println("-- 8. Wyświetl średni rok urodzenia klientow");
            System.out.println("-- 9. Powiększ rok urodzenia klientow o 1");


            System.out.println("Podaj numer operacji do wykonania: ");
            String option=scanner.nextLine();
            switch (option) {
                case "1":
                    HMapPut.main(args);
                    break;

                case "2":
                    HMapGet.main(args);
                    break;

                case "3":
                    System.out.println("Podaj ID klienta do wyświetlenia: ");
                    long id=scanner.nextLong();
                    hMapGet.getById(id, clients);
                    break;

                case "4":
                    System.out.println("Rok urodzenia większy niż: ");
                    int minBirthyear=scanner.nextInt();
                    System.out.println("Rok urodzenia mniejszy niż4: ");
                    int maxBirthyear=scanner.nextInt();
                    hPredicate.findByYear(minBirthyear, maxBirthyear);
                    break;

                case "5":
                    HMapGet.main(args);
                    System.out.println("Którego klienta usunąć? Podaj ID: ");
                    id=scanner.nextLong();
                    hMapRemove.removeClient(id, clients);
                    break;

                case "6":
                    HMapEvict.main(args);
                    break;

                case "7":
                    System.out.println("Podaj id klienta do aktualizacji: ");
                    long patietnId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Podaj nowe imię i nazwisko: ");
                    String newFullName = scanner.nextLine();
                    hMapUpdate.updatePatientFullName(patietnId, newFullName, instance);
                    break;

                case "8":
                    HAgregate.main(args);
                    break;

                case "9":
                    HExecuteOnEntries.main(args);
                    break;

                default:
                    System.out.println("Błędna intrukcja");
            }
        } while (true);


    }
}
