package com.jxxx.gaotang.conpoment.utils;

public class ByteUtils {
    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }
}
