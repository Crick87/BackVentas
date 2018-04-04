package com.pythonteam.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class Hash {
    private MessageDigest md;
    private final char[] HEXADECIMALES = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
    public static Hash hash = new Hash();
    public Hash(){
        md = null;
    }
    public String encriptar (String usuario, String password)
    {
        String cadena = usuario+":"+password+":"+Calendar.getInstance().getTime();
        try
        {
            md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(cadena.getBytes());
            StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++)
            {
                int low = (int)(bytes[i] & 0x0f);
                int high = (int)((bytes[i] & 0xf0) >> 4);
                strCryptMD5.append(HEXADECIMALES[high]);
                strCryptMD5.append(HEXADECIMALES[low]);
            }
            return strCryptMD5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}