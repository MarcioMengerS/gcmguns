package br.com.gcm.sac.setor_armamento.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
//@RequestMapping("v1/")
public class GcmController {

    @Autowired
    GcmService gcmService;
    
    //cria objeto GCM no BD
    @PostMapping("/save")
    public ResponseEntity<Gcm> salvar(@RequestBody @Valid Gcm gcm) throws URISyntaxException {
        URI location = new URI("/sac");
        //Se objeto existir no banco retorna o objeto senão salva
        if(null == gcmService.findByNumber(gcm.getNumero())){
            return ResponseEntity.created(location).body(gcmService.save(gcm));
        }else{
            return ResponseEntity.ok().body(gcmService.findByNumber(gcm.getNumero()));
        }
    }

    //Lista todos ojetos GCMs do BD
    @GetMapping("/listall")
    public ResponseEntity<List<Gcm>> listarTodos(){
        return ResponseEntity.ok().body(gcmService.listAll());
    }

    //Exclui Objeto do BD
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> excluirPorID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gcmService.deleteById(id));
    }

    //Modifica dados do objeto GCM cadastrado no BD
    @PutMapping("/modify")
    public Gcm alterar(@RequestBody Gcm gcm){
        return gcmService.save(gcm);
    }

//######################################## Métodos de Busca ###################################
    //Pesquisa no BD objeto GCM por ID
    @GetMapping("/find/{id}") //localhost:8080/find/10
    public ResponseEntity<Gcm> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gcmService.findById(id));
    }

    //Pesquisa por parte do nome do GCM
    @GetMapping("/name")//localhost:8080/name?nome=superman
    public List<Gcm> buscarPorParteNome(@RequestParam("nome") String nome) {
        return gcmService.findByNameContaining(nome);
    }

    //Pesquisa GCMs por numero
    @GetMapping("/{numero}")//localhost:8080/717
    public Gcm buscarGcmPorNum(@PathVariable Short numero){
        return gcmService.findByNumber(numero);
    }
    
    //Calcula idade do GCM
    @GetMapping("/idade/{numero}")
    public Integer devolveIdade(@PathVariable Short numero){
        Gcm gm = gcmService.findByNumber(numero);
        return gm.calcularIdade();
    }

    //calcula tempo de serviço em anos
    @GetMapping("/tempo-serv/{numero}")
    public int calculaTempoServ(@PathVariable Short numero){
        Gcm gm2 = gcmService.findByNumber(numero);
        return gm2.calcularAnosServico();
    }
}