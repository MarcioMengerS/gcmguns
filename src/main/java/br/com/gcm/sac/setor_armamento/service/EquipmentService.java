package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.repository.EquipmentRepository;

@Service
public class EquipmentService {
    
    @Autowired
    private EquipmentRepository handcuffRepository;

    public Equipment save(Equipment hc){
        return handcuffRepository.save(hc);
    }

    public List<Equipment> listAll(){
        return handcuffRepository.findAll();
    }   

    public Equipment findById(Integer id){
        return handcuffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

    public String deleteById(Integer id){
        Equipment handcuff = handcuffRepository.findById(id).get();
        handcuffRepository.deleteById(id);
        return String.format("Algema %s de número %s foi excluída com sucesso", handcuff.getBrand(), handcuff.getNumber());
    }

}