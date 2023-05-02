package br.com.gcm.sac.setor_armamento.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    LocalDateTime agora = LocalDateTime.now();

    LoanDTO loanDto = new LoanDTO();

    //Salva EQUIPAMENTO na carga do GUARDA
    @PostMapping("/{num_gcm}/{id_eq}")
    public LoanDTO emprestaEquipmentGcm(@PathVariable Integer id_eq, @PathVariable Short num_gcm){
        Equipment equipment = new Equipment();
        equipment = equipmentService.findById(id_eq);

        Gcm gcm = new Gcm();
        gcm = gcmService.findByNumber(num_gcm);
        
        Loan emp = new Loan();
        emp.setEquipment(equipment);
        emp.setGcm(gcm);
        emp.setRemoval(agora);

        BeanUtils.copyProperties(loanService.save(emp), loanDto);
        // List<Loan> list_emprestimo = new ArrayList<>();
        // list_emprestimo.add(emp);

        return loanDto;
    }
    
    @GetMapping
    public ResponseEntity<List<LoanDTO>> listAll(){
        return ResponseEntity.ok().body(LoanDTO.convertList(loanService.listAll()));
    }

    //Retorna todos equipamentos do GCM.
   // @GetMapping("/equipment/{id_gcm}")
    //public EquipmentDTO findEquipmentOfGcm(@PathVariable Integer id_gcm){
        // Equipment eq = new Equipment();
        // EquipmentDTO eqDto = new EquipmentDTO();
        // eq = gcmService.findById(id_gcm).getEquipment();
        // BeanUtils.copyProperties(eq, eqDto);
        // return eqDto;
    //}

    //Retorna de qual GM é o equipamento
    @GetMapping("/equipment/{numero}")
    public void findByNumber(@PathVariable Integer numero) {
        
    }
}
