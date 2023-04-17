package br.com.gcm.sac.setor_armamento.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import br.com.gcm.sac.setor_armamento.service.CardService;
import br.com.gcm.sac.setor_armamento.service.GcmService;
import br.com.gcm.sac.setor_armamento.model.Card;
import br.com.gcm.sac.setor_armamento.model.Gcm;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;;

@RestController
public class CardController {

    @Autowired
    CardService tagService;
    @Autowired
    GcmService gcmService;

    @PostMapping("/savecard/{idGcm}")
    public Card saveCard(@PathVariable Integer idGcm){ /*passar id do GCM para salvar o relacionamento*/
        Card tag = new Card();
        Gcm gcm = gcmService.findById(idGcm); //busca gcm no banco

        tag.obtemCard();//entra no método que abre a porta de comunicação e forma objeto Card
        //salva gcm na Tag
        //tag.setGcm(gcm);
        //tagService.save(tag);

        //Salva Tag no Gcm
        //gcm.setCard(tag);
        gcm = gcmService.save(gcm);
        return null;
    }

    @GetMapping("/listcards")
    public ResponseEntity<List<Card>> listarTodosCards(){
        return ResponseEntity.ok().body(tagService.listAll());
    }

    @DeleteMapping("/deletecard/{id}")
    public ResponseEntity<String> excluirPorID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(tagService.deleteById(id));
    }

    @GetMapping("/findcard/{id}") //localhost:8080/findcard/2
    public ResponseEntity<Card> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(tagService.findById(id));
    }
}