package com.sqlitedb_copyexternaldb.utils;

import android.util.Base64;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public final class Decode2 {
    byte[] f10a = this.f12e.getBytes("UTF8");
    SecretKey f11b = this.f14g.generateSecret(this.mKeySpec);
    private String f12e;
    private String f13f = "DESede";
    private SecretKeyFactory f14g = SecretKeyFactory.getInstance(this.f13f);
    private Cipher mCipher = Cipher.getInstance(this.f13f);
    private KeySpec mKeySpec = new DESedeKeySpec(this.f10a);

    public Decode2(String str) throws Exception {
        this.f12e = str;
    }

    public final String decode(String str) {
        try {
            this.mCipher.init(2, this.f11b);
            return new String(this.mCipher.doFinal(Base64.decode(str, 0)));
        } catch (Exception str2) {
            str2.printStackTrace();
            return null;
        }
    }
}
