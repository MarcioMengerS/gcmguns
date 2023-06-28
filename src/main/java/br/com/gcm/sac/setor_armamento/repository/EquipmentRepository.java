package br.com.gcm.sac.setor_armamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{
    // @Query("select u from Equipment u where u.available = true")
    // @Query(value = "select * from Equipment where loanEqu = false", nativeQuery = true)
    List<Equipment> findByCategoryAndAvailable(NameEnum categoria, Boolean boolean1);
    // List<Equipment> findByLoanEqu(Boolean loanEqu);
}