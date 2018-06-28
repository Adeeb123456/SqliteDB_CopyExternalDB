package com.sqlitedb_copyexternaldb.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public final class DecodeUtil {
    public static final char[] KEY_GRAMMAR = new char[]{'1', 'c', 'c', 'l', 'u', 'f', 'H', 'n', 'y', 'b', 'J', 'A', 'w', '9', 'M', '3', 'T', 'P', 'z', '7', 'I', 'Q', '=', '='};
    public static final char[] KEY_PHRASE = new char[]{'2', 'o', '/', 'z', 'I', '7', 'j', 'D', 'C', 'L', 'T', 'q', '+', '6', 'A', 'E', 'N', 'E', 'p', '8', 'o', 'g', '=', '='};
    public static String keycode_phrase = "ffucking4u";
    public static String keydecode_grammar = "fuckingyou";
    public static String keynative = "abcdef123456ghijkl654321";
    private Cipher mCipher = null;
    private SecretKey mSecretKey = null;

    public DecodeUtil(String str) {
        try {
            this.mSecretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes("UTF8")));
            this.mCipher = Cipher.getInstance("DES");
        } catch (Exception str2) {
            str2.printStackTrace();
        }
    }

    public final String decode(String str) {
       byte[] bytes = Base64.decode(str, 0);
        try {
            this.mCipher.init(2, this.mSecretKey);
            return new String(this.mCipher.doFinal(bytes), "UTF8");
        } catch (Exception str2) {
            str2.printStackTrace();
            return null;
        }
    }
}
