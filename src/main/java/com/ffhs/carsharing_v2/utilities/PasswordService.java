package com.ffhs.carsharing_v2.utilities;

import java.util.*;
import java.security.*;
import java.nio.charset.StandardCharsets;

public final class PasswordService {
    public String encrypt(String pass){
        MessageDigest md = null;
    
        try{
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            md.update(pass.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] raw = md.digest();
        return  Base64.getEncoder().encodeToString(raw);
    }
}
