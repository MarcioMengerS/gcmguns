package br.com.gcm.sac.setor_armamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{
    List<Equipment> findByCategory(NameEnum name);
}