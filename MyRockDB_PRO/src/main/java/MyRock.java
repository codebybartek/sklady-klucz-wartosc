import org.apache.commons.lang.SerializationUtils;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MyRock {
    static {
        RocksDB.loadLibrary();
    }
    final  String NAME = "first-db";
    File dbDir;
    static RocksDB db;

    public MyRock(){
        final Options options = new Options();
        options.setCreateIfMissing(true);
        dbDir = new File("/tmp/rocks-db", NAME);
        try {
            Files.createDirectories(dbDir.getParentFile().toPath());
            Files.createDirectories(dbDir.getAbsoluteFile().toPath());
            db = RocksDB.open(options, dbDir.getAbsolutePath());
        } catch(IOException | RocksDBException ex) {
            System.out.println("Error initializng RocksDB, check configurations and permissions, exception: {}, message: {}, stackTrace: {}");
        }
        System.out.println("RocksDB initialized and ready to use");
    }

    public void addClient(String key, Client client){
        System.out.println("Dodawanie klientow");
        try {
            db.put(key.getBytes(), SerializationUtils.serialize(client));
        } catch (RocksDBException e) {
            System.out.println("Error saving entry in RocksDB, cause: {}, message: {}");
        }
    }

    public void find(String key){
        System.out.println("find");
        try {
            Client val22 = (Client) SerializationUtils.deserialize(db.get(key.getBytes()));
            System.out.println("Szukany kleint");
            System.out.println(val22.getFullname() + " " + val22.getAddress() + " " + val22.getPESEL() + " " + val22.getBirthyear());
        } catch (RocksDBException e) {
            System.out.println("Error retrieving the entry in RocksDB from key: {}, cause: {}, message:");
        }
    }

    public void delete(String key){
        System.out.println("delete");
        String result = null;
        try {
            db.delete(key.getBytes());
            System.out.println(key+ "was deleted");
        } catch (RocksDBException e) {
            System.out.println("Error retrieving the entry in RocksDB from key: {}, cause: {}, message:");
        }
    }

    public void showAll(){
        RocksIterator iter = db.newIterator();
        iter.seekToFirst();
        while (iter.isValid()) {
            String key22 = new String(iter.key());
            Client val22 = (Client) SerializationUtils.deserialize(iter.value());
            System.out.println(key22 + " -> " + val22.getFullname() + " " + val22.getAddress() + " " + val22.getPESEL() + " " + val22.getBirthyear());
            iter.next();
        }
    }

    public void updateClient(String klientId, String newFullName){
        RocksIterator iter = db.newIterator();
        iter.seekToFirst();
        while (iter.isValid()) {
            String key22 = new String(iter.key());
            System.out.println(key22 + " ffff " + klientId);
            if(klientId.equals(key22) ){
                Client client = (Client) SerializationUtils.deserialize(iter.value());
                client.setFullname(newFullName);
                try {
                db.delete(klientId.getBytes());
                db.put(klientId.getBytes(), SerializationUtils.serialize(client));
                } catch (RocksDBException e) {
                    System.out.println("Error retrieving the entry in RocksDB from key: {}, cause: {}, message:");
                }
                return;
            }
        }
    }
}
