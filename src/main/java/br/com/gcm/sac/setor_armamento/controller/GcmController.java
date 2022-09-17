package br.com.gcm.sac.setor_armamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.service.GcmService;

@RestController
public class GcmController {

    @Autowired
    GcmService gcmService;
    
    //Salva objeto GM no BD
    @PostMapping("/add")
    public Gcm salvar(@RequestBody Gcm gcm) {
        return gcmService.save(gcm);
    }

    //Lista todos ojetos GCMs do BD
    @GetMapping("/list")
    public List<Gcm> listarTodos(){
        return gcmService.listAll();
    }

    //Exclui Objeto do BD
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> excluirPorID(@PathVariable("id") Integer id) {
        if(gcmService.existById(id)){
            gcmService.deleteById(id);
            return new ResponseEntity<>("Objeto excluído com sucesso", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //Modifica dados do objeto GCM cadastrado no BD
    @PutMapping("/modify")
    public Gcm alterar(@RequestBody Gcm gcm){
        return gcmService.save(gcm);
    }

//######################################## Métodos de Busca ###################################
    //Pesquisa no BD objeto GCM por ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Gcm> encontarPorId(@PathVariable Integer id) {
        if(gcmService.existById(id))
            return new ResponseEntity<Gcm>(gcmService.findById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //Pesquisa por parte do nome do GCM
    @GetMapping("/name")//localhost:8080/name?nome=marcio
    public List<Gcm> pesquisaPorTrechoNome(@RequestParam("nome") String nome) {
        return gcmService.findByNameContaining(nome);
    }

    //Pesquisa GCMs que possuem idade maior que....
    @GetMapping("/number/{numero}")//localhost:8080/number/717
    public Gcm pesquisaIdadeGcmMaiorQue(@PathVariable Short numero){
        return gcmService.findByNumber(numero);
    }
}