package br.com.gcm.sac.setor_armamento.Security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Converte a senha que vem do frontend para uma hash de dezesseis números
public class HashMD5 {
    public static String md5(String senha) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
        return hash.toString(16);
    }
}
