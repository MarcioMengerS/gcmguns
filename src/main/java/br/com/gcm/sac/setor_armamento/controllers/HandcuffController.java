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

import br.com.gcm.sac.setor_armamento.model.dto.HandcuffDTO;
import br.com.gcm.sac.setor_armamento.model.Handcuff;
import br.com.gcm.sac.setor_armamento.service.HandcuffService;
import br.com.gcm.sac.setor_armamento.service.GcmService;
import br.com.gcm.sac.setor_armamento.model.dto.GcmDTO;
import br.com.gcm.sac.setor_armamento.model.Gcm;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/handcuff")
public class HandcuffController {

    @Autowired
    GcmService gcmService;

    @Autowired
    HandcuffService handcuffService;

    @PostMapping
    public ResponseEntity<HandcuffDTO> save(@RequestBody @Valid Handcuff hc) throws URISyntaxException {
        HandcuffDTO hcDto = new HandcuffDTO();
        BeanUtils.copyProperties(handcuffService.save(hc), hcDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(hcDto);
    }

    @GetMapping
    public ResponseEntity<List<HandcuffDTO>> listAll(){
        return ResponseEntity.ok().body(HandcuffDTO.convertList(handcuffService.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandcuffDTO> findById(@PathVariable Integer id) {
        HandcuffDTO handcuffDto = new HandcuffDTO();
        Handcuff handcuff = new Handcuff();
        handcuff = handcuffService.findById(id);
        BeanUtils.copyProperties(handcuff, handcuffDto);
        return ResponseEntity.ok().body(handcuffDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok().body(handcuffService.deleteById(id));
    }

    @GetMapping("/gcm/{id_hc}")
    public GcmDTO findGcmOfHandcuff(@PathVariable Integer id_hc) {
        Gcm gcm = new Gcm();
        GcmDTO gcmDto = new GcmDTO();
        gcm = handcuffService.findById(id_hc).getGcm();
        
        BeanUtils.copyProperties(gcm, gcmDto);
        return gcmDto;
    }
}