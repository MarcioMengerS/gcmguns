package br.com.gcm.sac.setor_armamento.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.model.Address;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.service.AddressService;
import br.com.gcm.sac.setor_armamento.service.GcmService;

@RestController
public class AddressController {
    
    @Autowired 
    AddressService addService;
    @Autowired
    GcmService gcmService;

    @PostMapping("/add-address/{id_Gcm}")
    public Address saveAddress(@RequestBody Address addr, @PathVariable Integer id_Gcm){
        
        List<Address> end = new ArrayList<Address>();

        Gcm gcm = new Gcm();
        gcm = gcmService.findById(id_Gcm);
        end = gcm.getEnderecos();
        end.add(addr);
        gcm.setEnderecos(end);
        gcmService.save(gcm);
        return null;//gcm.getCard();
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<String> excluirPorID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(addService.deleteById(id));
    }
}
