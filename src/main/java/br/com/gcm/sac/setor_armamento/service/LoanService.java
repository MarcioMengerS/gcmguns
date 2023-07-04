package br.com.gcm.sac.setor_armamento.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.model.Loan;
import br.com.gcm.sac.setor_armamento.repository.EquipmentRepository;
import br.com.gcm.sac.setor_armamento.repository.LoanRepository;

@Service
public class LoanService {
 
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    
    public Loan saveLoanEquip(Loan loan){
        return loanRepository.save(loan);
    }

    public List<Loan> listAll(){
        return loanRepository.findAll();
    }
    //Busca todos os equipamento de um GCM
    public List<Loan> findByGcmId(Integer id_gcm){
        return loanRepository.findByGcmId(id_gcm);
    }

    public List<Loan> findByEquipmentId(Integer id_equip){
        return loanRepository.findByEquipmentId(id_equip);
    }

    public Loan update(Integer id_loan, Integer id_equipment){
        ZonedDateTime dataHoraZoneNow = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        Equipment equipment = new Equipment();
        equipment = equipmentRepository.findById(id_equipment).get();
        equipment.setAvailable(true);

        Loan loan = new Loan();
        loan = loanRepository.findById(id_loan).get();
        loan.setEquipment(equipment);
        loan.setDevolution(dataHoraZoneNow);
        loanRepository.save(loan);
        return loan;
    }
}