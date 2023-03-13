package br.com.gcm.sac.setor_armamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gcm.sac.setor_armamento.model.Handcuff;
import br.com.gcm.sac.setor_armamento.repository.HandcuffRepository;

@Service
public class HandcuffService {
    
    @Autowired
    private HandcuffRepository handcuffRepository;

    public Handcuff save(Handcuff hc){
        return handcuffRepository.save(hc);
    }

    public Handcuff findById(Integer id){
        return handcuffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

    public String deleteById(Integer id){
        Handcuff handcuff = handcuffRepository.findById(id).get();
        handcuffRepository.deleteById(id);
        return String.format("Algema %s de número %s foi excluída com sucesso", handcuff.getBrand(), handcuff.getNumber());
    }

}