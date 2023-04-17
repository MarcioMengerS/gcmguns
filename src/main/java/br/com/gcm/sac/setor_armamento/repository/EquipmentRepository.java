package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gcm.sac.setor_armamento.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{
    
}
