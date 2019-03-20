package ru.anikanov.tm.utils;

import ru.anikanov.tm.entity.Session;

public class SignatureUtil {
    public static String sign(String name, String salt, int cycle){
        String result=name;
        for (int i=0;i<cycle;i++){
            result=PasswordHash.makehash(result+salt+result);
        }
        return result;
    }
 /*   public static Session sign(Session session){
        if
    }*/
}
