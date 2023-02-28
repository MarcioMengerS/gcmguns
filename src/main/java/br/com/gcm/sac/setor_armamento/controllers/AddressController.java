package br.com.gcm.sac.setor_armamento.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.model.Address;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.service.AddressService;
import br.com.gcm.sac.setor_armamento.service.GcmService;

@RestController
//@RequestMapping("/category")
public class AddressController {
    
    @Autowired 
    AddressService addService;
    @Autowired
    GcmService gcmService;

    @PostMapping("/add-address/{id_Gcm}")
    public ResponseEntity<Address> saveAddress(@RequestBody Address addr, @PathVariable Integer id_Gcm){
        Gcm gcm = gcmService.findById(id_Gcm);
        gcm.getAddress().add(addr);
        gcmService.save(gcm);
        return ResponseEntity.ok().body(addr);
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<String> excluirPorID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(addService.deleteById(id));
    }

    //Lista todos ojetos Address
    @GetMapping("/listall-address")
    public ResponseEntity<List<Address>> listarTodos(){
        return ResponseEntity.ok().body(addService.listAll());
    }

    @GetMapping("/find-address/{id}")
    public ResponseEntity<Address> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(addService.findById(id));
    }
}
