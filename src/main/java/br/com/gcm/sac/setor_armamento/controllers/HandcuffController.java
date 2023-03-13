package br.com.gcm.sac.setor_armamento.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.model.Handcuff;
import br.com.gcm.sac.setor_armamento.service.HandcuffService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/handcuff")
public class HandcuffController {

    @Autowired
    HandcuffService handcuffService;

    @PostMapping
    public ResponseEntity<Handcuff> save(@RequestBody @Valid Handcuff hc) throws URISyntaxException {
        URI location = new URI("/hcuff");
        return ResponseEntity.created(location).body(handcuffService.save(hc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Handcuff> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(handcuffService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return ResponseEntity.ok().body(handcuffService.deleteById(id));
    }
}
