package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gcm.sac.setor_armamento.enuns.CategoryEnum;
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

    public List<Equipment> listByCategory(CategoryEnum name){
        // Boolean equipDisponivel  = true; //variável para busca somente de equipamentos disponíveis
        //return equipmentRepository.findByCategoryAndAvailable(name, equipDisponivel);
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

    public Equipment update(Equipment e, Integer id){
        Equipment equipment = equipmentRepository.findById(id).get();
        equipment.setAgent(e.getAgent());
        equipment.setAssetNumber(e.getAssetNumber());
        equipment.setAvailable(e.getAvailable());
        equipment.setBrand(e.getBrand());
        equipment.setCaliber(e.getCaliber());
        equipment.setCategory(e.getCategory());
        equipment.setDistance(e.getDistance());
        equipment.setExpirationDate(e.getExpirationDate());
        equipment.setGender(e.getGender());
        equipment.setInfo(e.getInfo());
        equipment.setJetSystem(e.getJetSystem());
        equipment.setLevelOfProtection(e.getLevelOfProtection());
        equipment.setManufacturingDate(e.getManufacturingDate());
        equipment.setModel(e.getModel());
        equipment.setNumber(e.getNumber());
        equipment.setNumberOfPipes(e.getNumberOfPipes());
        equipment.setOperation(e.getOperation());
        equipment.setRegister(e.getRegister());
        equipment.setSize(e.getSize());
        equipment.setSoulInformation(e.getSoulInformation());
        equipment.setSpecie(e.getSpecie());
        equipment.setWear(e.getWear());
        return equipmentRepository.save(equipment);
    }

    public Long totalEqu() {
        return equipmentRepository.count();
    }
}