package br.iesb.poo.restapi.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3764283314596178549L;

    public CustomerNotFoundException(UUID id){
        super("Usuário não encontrado: " + id);
    }
}
