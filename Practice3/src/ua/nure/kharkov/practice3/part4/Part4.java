package ua.nure.kharkov.practice3.part4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
    	System.out.println(hash("password to hash","MD5"));
    	System.out.println();
    }
    public static String hash(String input,String algorithm) throws NoSuchAlgorithmException{
    	MessageDigest digest = MessageDigest.getInstance(algorithm);
    	digest.update(input.getBytes());
    	byte [] hash = digest.digest();
    	String hex;
    	StringBuilder str = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] < 0) {
				hex = Integer.toHexString(hash[i]);
				hex = hex.substring(hex.length() - 2, hex.length());
				str.append(hex);
				str.append(" ");
				continue;
			}
			str.append(Integer.toHexString(hash[i]));
			str.append(" ");
		}
    	return str.toString().toUpperCase();
    }
}
