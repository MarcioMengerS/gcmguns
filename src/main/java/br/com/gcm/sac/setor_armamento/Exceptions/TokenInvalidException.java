package br.com.gcm.sac.setor_armamento.exceptions;

public class TokenInvalidException extends RuntimeException{
    public TokenInvalidException(String message){
        super(message);
    }
}
