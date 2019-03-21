package ru.anikanov.tm.utils;

import ru.anikanov.tm.entity.Session;

public class SignatureUtil {
    public static String sign(String value, String salt, int cycle) {
        String result = value;
        for (int i=0;i<cycle;i++){
            result=PasswordHash.makehash(result+salt+result);
        }
        return result;
    }
  /*  public static String sign(Object object,){

    }*/
}
