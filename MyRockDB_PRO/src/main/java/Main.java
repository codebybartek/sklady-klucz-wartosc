// Copyright (c) 2011-present, Facebook, Inc.  All rights reserved.
//  This source code is licensed under both the GPLv2 (found in the
//  COPYING file in the root directory) and Apache 2.0 License
//  (found in the LICENSE.Apache file in the root directory).

import java.io.File;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang.SerializationUtils;
import org.rocksdb.*;
import org.rocksdb.util.SizeUnit;


public class Main {



    public static void main(final String[] args) throws RocksDBException {

        MyRock myRock = new MyRock();

        //initialization

        String key = "klient11";
        Client client1 = new Client("Bartlomiej Koziel", 1997, 970302340, "Piekoszow, Kielecka 2");
        myRock.addClient(key, client1);
        String key2 = "klient22";
        Client client2 = new Client("Marian Tomczyk", 1992, 920616452, "Kielce, Warszawska 3");
        myRock.addClient(key2, client2);
        String key3 = "klient33";
        Client client3 = new Client("Piotr Stepien", 1999, 990116510, "Sandomierz, Krakowska 2/12");
        myRock.addClient(key3, client3);

        myRock.showAll();

        do {
            Scanner scanner=new Scanner(System.in);
            System.out.println("<--------------Biblioteka RocksDB-------------->");
            System.out.println("-- 1. Dodaj klienta");
            System.out.println("-- 2. Wyświetl wszystkich klientow");
            System.out.println("-- 3. Wyświetl klienta o podanym ID");
            System.out.println("-- 4. Usuń wybranego klienta");
            System.out.println("-- 5. Aktualizuj");
            System.out.println("-- 6. Powieksz rok urodzenia pacjentow");


            System.out.println("Podaj numer operacji do wykonania: ");
            String option=scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Podaj imie i nazwisko: ");
                    String name=scanner.nextLine();
                    System.out.println("Podaj date ur.: ");
                    String birthdate=scanner.nextLine();
                    System.out.println("Podaj PESEL: ");
                    String pesel=scanner.nextLine();
                    System.out.println("Podaj adres: ");
                    String address=scanner.nextLine();
                    Client client = new Client(name, Integer.parseInt(birthdate), Integer.parseInt(pesel), address);
                    myRock.addClient(getKey(5), client);
                    break;
                case "2":
                    myRock.showAll();
                    break;
                case "3":
                    System.out.println("Podaj klucz pacjenta do wyświetlenia: ");
                    String id=scanner.nextLine();
                    myRock.find(id);
                    break;
                case "4":
                    System.out.println("Którego klienta usunąć? Podaj klucz: ");
                    id=scanner.nextLine();
                    myRock.delete(id);
                    break;

                case "5":
                    System.out.println("Podaj id klienta do aktualizacji: ");
                    String klientId = scanner.nextLine();
                    System.out.println("Podaj nowe nazwisko: ");
                    String newFullName = scanner.nextLine();
                    myRock.updateClient(klientId,newFullName);
                    break;

                default:
                    System.out.println("Błędna intrukcja");
            }
        } while (true);
    }

    static String getKey(int n)
    {

        // lower limit for LowerCase Letters
        int lowerLimit = 97;

        // lower limit for LowerCase Letters
        int upperLimit = 122;

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(n);

        for (int i = 0; i < n; i++) {

            // take a random value between 97 and 122
            int nextRandomChar = lowerLimit
                    + (int)(random.nextFloat()
                    * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char)nextRandomChar);
        }

        // return the resultant string
        return r.toString();
    }

    /*public static void add(String key, Client client){
        System.out.println("save");
        try {
            db.put(key.getBytes(), SerializationUtils.serialize(client));
        } catch (RocksDBException e) {
            System.out.println("Error saving entry in RocksDB, cause: {}, message: {}");
        }
    }

    public void find(String key){
        System.out.println("find");
        String result = null;
        try {
            final byte[] val = db.get(key.getBytes());
            result = new String(val);
            System.out.println(result);
        } catch (RocksDBException e) {
            System.out.println("Error retrieving the entry in RocksDB from key: {}, cause: {}, message:");
        }
    }

    public static void delete(String key){
        System.out.println("delete");
        String result = null;
        try {
            db.delete(key.getBytes());
            System.out.println(key+ "was deleted");
        } catch (RocksDBException e) {
            System.out.println("Error retrieving the entry in RocksDB from key: {}, cause: {}, message:");
        }
    }*/
}