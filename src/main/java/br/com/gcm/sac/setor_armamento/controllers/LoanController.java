package br.com.gcm.sac.setor_armamento.controllers;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.dto.LoanDTO;
import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.model.Loan;
import br.com.gcm.sac.setor_armamento.service.EquipmentService;
import br.com.gcm.sac.setor_armamento.service.GcmService;
import br.com.gcm.sac.setor_armamento.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
    
    @Autowired
    GcmService gcmService;
 
    @Autowired
    EquipmentService equipmentService;
    
    @Autowired
    LoanService loanService;

    //Empresta EQUIPAMENTO para o GCM 
    @PostMapping("/{num_gcm}/{id_eq}")
    public LoanDTO emprestaEquipmentGcm(@PathVariable Integer id_eq, @PathVariable Short num_gcm){
        Equipment equipment = new Equipment();
        equipment = equipmentService.findById(id_eq);
        equipment.setAvailable(false);
        
        Gcm gcm = new Gcm();
        gcm = gcmService.findByNumber(num_gcm);
        
        Loan loan = new Loan();
        loan.setEquipment(equipment);
        loan.setGcm(gcm);
        //Instancia objeto com data e hora
        ZonedDateTime dataHoraZonaAgora = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        
        loan.setRemoval(dataHoraZonaAgora);

        loanService.saveLoanEquip(loan);
        LoanDTO loanDto = new LoanDTO(loan);

        return loanDto;
    }
    
    @GetMapping
    public ResponseEntity<List<LoanDTO>> listAll(){
        return ResponseEntity.ok().body(LoanDTO.convertList(loanService.listAll()));
    }

    //Retorna todos equipamentos que estão em posse do GCM.
   @GetMapping("/gcm_id/{id_gcm}")
    public ResponseEntity<List<LoanDTO>> findEquipmentOfGcm(@PathVariable Integer id_gcm){
        return ResponseEntity.ok().body(LoanDTO.convertList(loanService.findByGcmId(id_gcm)));
    }

    //Retorna todos os GM que estiveram em posse do equipamento
    @GetMapping("/equipment_id/{id_equipment}")
    public ResponseEntity<List<LoanDTO>> findByEquipmentId(@PathVariable Integer id_equipment) {
        return ResponseEntity.ok().body(LoanDTO.convertList(loanService.findByEquipmentId(id_equipment)));
    }

    //Atualiza objeto Loan com o horário de devolução e atributo available do equipamento
    @PutMapping("/devolve/{id_loan}/{id_equip}")
    public ResponseEntity<LoanDTO> updateById(@PathVariable Integer id_loan, @PathVariable Integer id_equip){
        LoanDTO loanDto = new LoanDTO();
        Loan loan = new Loan();
        loan = loanService.update(id_loan, id_equip);
        BeanUtils.copyProperties(loan, loanDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(loanDto);
    }
}
