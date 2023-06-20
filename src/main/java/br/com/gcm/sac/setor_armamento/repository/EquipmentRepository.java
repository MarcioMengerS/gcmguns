package br.com.gcm.sac.setor_armamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>{
    // @Query("select u from Equipment u where u.loanEqu = 0")
    // @Query(value = "select * from Equipment where loanEqu = false", nativeQuery = true)
    List<Equipment> findByCategory(NameEnum loanEqu);
    // List<Equipment> findByLoanEqu(Boolean loanEqu);
}