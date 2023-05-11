package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.repository.EquipmentRepository;

@Service
public class EquipmentService {
    
    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment save(Equipment hc){
        return equipmentRepository.save(hc);
    }

    public List<Equipment> listAll(){
        return equipmentRepository.findAll();
    }

    public List<Equipment> listByCategory(NameEnum name){
        return equipmentRepository.findByCategory(name);
    } 

    public Equipment findById(Integer id){
        return equipmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

    public String deleteById(Integer id){
        Equipment handcuff = equipmentRepository.findById(id).get();
        equipmentRepository.deleteById(id);
        return String.format("Algema %s de número %s foi excluída com sucesso", handcuff.getBrand(), handcuff.getNumber());
    }

}