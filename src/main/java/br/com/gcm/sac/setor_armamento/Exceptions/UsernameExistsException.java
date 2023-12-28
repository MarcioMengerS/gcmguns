package br.com.gcm.sac.setor_armamento.exceptions;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(String message){
        super(message);
    }
}
