package br.com.gcm.sac.setor_armamento.controllers;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.dto.EquipmentDTO;
import br.com.gcm.sac.setor_armamento.enuns.CategoryEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;
import br.com.gcm.sac.setor_armamento.service.EquipmentService;
import br.com.gcm.sac.setor_armamento.service.GcmService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    GcmService gcmService;

    @Autowired
    EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<EquipmentDTO> save(@RequestBody @Valid Equipment hc) throws URISyntaxException {
        EquipmentDTO hcDto = new EquipmentDTO();
        BeanUtils.copyProperties(equipmentService.save(hc), hcDto);
        return  ResponseEntity.status(HttpStatus.OK).body(hcDto);
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> listAll(){
        return ResponseEntity.ok().body(EquipmentDTO.convertList(equipmentService.listAll()));
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<EquipmentDTO>> listByCategoy(@PathVariable CategoryEnum name){
        return ResponseEntity.ok().body(EquipmentDTO.convertList(equipmentService.listByCategory(name)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> findById(@PathVariable Integer id) {
        Equipment equipment = equipmentService.findById(id);
        EquipmentDTO equipmentDto = new EquipmentDTO(equipment);
        //BeanUtils.copyProperties(equipment, equipmentDto);
        System.out.println(equipmentDto);
        return ResponseEntity.ok().body(equipmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok().body(equipmentService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> update(@PathVariable Integer id, @RequestBody Equipment equip) {
        EquipmentDTO equipDto = new EquipmentDTO();
        Equipment equip2 = new Equipment();
        equip2 = equipmentService.update(equip, id);
        BeanUtils.copyProperties(equip2, equipDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipDto);
    }

    @GetMapping("/total")
    public Long totalEquip(){
        return equipmentService.totalEqu();
    }

    // @GetMapping("/gcm/{id_hc}")
    // public GcmDTO findGcmOfEquipment(@PathVariable Integer id_hc) {
    //     Gcm gcm = new Gcm();
    //     GcmDTO gcmDto = new GcmDTO();
    //     gcm = equipmentService.findById(id_hc).getGcm();
        
    //     BeanUtils.copyProperties(gcm, gcmDto);
    //     return gcmDto;
    // }
}