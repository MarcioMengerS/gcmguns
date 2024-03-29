package br.com.gcm.sac.setor_armamento.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.model.Address;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.service.AddressService;
import br.com.gcm.sac.setor_armamento.service.GcmService;

@RestController
@RequestMapping("/address")
public class AddressController {
    
    @Autowired 
    AddressService addService;
    @Autowired
    GcmService gcmService;

    //Cadastra endereço no respectivo GCM
    @PostMapping("/{id_Gcm}")
    public ResponseEntity<Address> save(@RequestBody Address addr, @PathVariable Integer id_Gcm){
        Gcm gcm = gcmService.findById(id_Gcm);
        gcm.setAddress(addService.save(addr));
        gcmService.save(gcm);
        return ResponseEntity.ok().body(addr);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(addService.deleteById(id));
    }

    @GetMapping
    public ResponseEntity<List<Address>> listAll(){
        return ResponseEntity.ok().body(addService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> finById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(addService.findById(id));
    }

    //Busca ENDEREÇO de um GCM específico
    @GetMapping("/gcm/{id_gcm}")
    public Address getAddress(@PathVariable Integer id_gcm) {
        Gcm gcm = gcmService.findById(id_gcm);
        int id_address = gcm.getAddress().getId();
        Address address = addService.findById(id_address); 
        return address;
    }
}
