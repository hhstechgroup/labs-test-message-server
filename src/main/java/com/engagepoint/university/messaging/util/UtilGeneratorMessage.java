package com.engagepoint.university.messaging.util;

import com.engagepoint.university.messaging.entity.User;

import java.util.*;

public class UtilGeneratorMessage {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    public static Date getRandomDate(){
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2010, 2014);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        Date date = gc.getTime();

        return date;
    }

    public static Collection<User> getRandomRecieverCollection(){
        Collection<User> userCollection = new ArrayList<User>();
        userCollection.add(genUser());
        userCollection.add(genUser());
        userCollection.add(genUser());

        return userCollection;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static User genUser(){
        User randUser = new User();
        randUser.setName(randomString(8));
        return randUser;
    }

    private static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
