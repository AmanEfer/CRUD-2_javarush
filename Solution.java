package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    static SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat outputDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws Exception {
        //start here - начни тут

            switch (args[0]) {
                case "-c":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i += 3) {
                            String name = args[i];
                            Date bd = inputDate.parse(args[i + 2]);
                            Person person = args[i + 1].equals("м") ? Person.createMale(name, bd) : Person.createFemale(name, bd);
                            allPeople.add(person);
                            System.out.println(allPeople.indexOf(person));
                        }
                    }
                    break;
                case "-u":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i += 4) {
                            Person person = allPeople.get(Integer.parseInt(args[i]));
                            person.setName(args[i + 1]);
                            person.setSex(args[i + 2].equals("м") ? Sex.MALE : Sex.FEMALE);
                            person.setBirthDate(inputDate.parse(args[i + 3]));
                        }
                        break;
                    }
                case "-d":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            Person person = allPeople.get(Integer.parseInt(args[i]));
                            person.setName(null);
                            person.setSex(null);
                            person.setBirthDate(null);
                        }
                        break;
                    }
                case "-i":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            Person person = allPeople.get(Integer.parseInt(args[i]));
                            String name1 = person.getName();
                            String sex = person.getSex() == Sex.MALE ? "м" : "ж";
                            String bd1 = outputDate.format(person.getBirthDate());
                            System.out.printf("%s %s %s\n", name1, sex, bd1);
                        }
                        break;
                    }
            }
    }
}
