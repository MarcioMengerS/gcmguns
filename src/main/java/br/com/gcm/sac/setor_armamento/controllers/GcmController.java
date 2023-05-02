package br.com.gcm.sac.setor_armamento.controllers;
import java.net.URISyntaxException;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.dto.GcmDTO;
import br.com.gcm.sac.setor_armamento.service.GcmService;
import br.com.gcm.sac.setor_armamento.service.EquipmentService;

@RestController
@RequestMapping("/gcm")
public class GcmController {

    @Autowired
    GcmService gcmService;
 
    @Autowired
    EquipmentService equipmentService;
    
    //cria objeto GCM no BD
    @PostMapping
    public ResponseEntity<GcmDTO> save(@RequestBody @Valid Gcm gcm) throws URISyntaxException {
        GcmDTO gcmDto = new GcmDTO();
        Gcm gcm2 = gcmService.findByNumber(gcm.getNumero());
        //Se objeto não existir no BD salva
        if(null == gcm2){
            BeanUtils.copyProperties(gcmService.save(gcm), gcmDto);
            return  ResponseEntity.status(HttpStatus.CREATED).body(gcmDto);
        }else{
            BeanUtils.copyProperties(gcm2, gcmDto);
            return ResponseEntity.status(HttpStatus.FOUND).body(gcmDto);
        }
    }

    //Lista todos ojetos GCMs do BD
    @GetMapping
    public ResponseEntity<List<GcmDTO>> listAll(){
        return ResponseEntity.ok().body(GcmDTO.convertList(gcmService.listAll()));
    }

    //Pesquisa no BD objeto GCM por ID
    @GetMapping("/{id}") //localhost:8080/1
    public ResponseEntity<GcmDTO> findById(@PathVariable Integer id) {
        GcmDTO gcmDto = new GcmDTO();
        Gcm gcm = new Gcm();
        gcm = gcmService.findById(id);
        BeanUtils.copyProperties(gcm, gcmDto);
        return ResponseEntity.ok().body(gcmDto);
    }

    //Modifica dados do objeto GCM cadastrado no BD
    @PutMapping("/{id}")
    public ResponseEntity<GcmDTO> updateById(@RequestBody Gcm gcm, @PathVariable Integer id){
        GcmDTO gcmDto = new GcmDTO();
        Gcm gcm2 = new Gcm();
        gcm2 = gcmService.update(gcm, id);
        BeanUtils.copyProperties(gcm2, gcmDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gcmDto);
    }

    //Exclui Objeto do BD
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gcmService.deleteById(id));
    }

//########################## Métodos de Busca Avançados ###################################
    //Pesquisa por parte do nome do GCM
    @GetMapping("/name")//localhost:8080/name?nome=menger
    public ResponseEntity<List<GcmDTO>> findByNameContaining(@RequestParam("nome") String nome){
        return ResponseEntity.ok().body(GcmDTO.convertList(gcmService.findByNameContaining(nome)));
    }

    //Pesquisa GCMs por numero
    @GetMapping("/num/{number}")//localhost:8080/717
    public ResponseEntity<GcmDTO> getGcmByNumber(@PathVariable Short number){
        GcmDTO gcmDto = new GcmDTO();
        Gcm gcm = new Gcm();
        gcm = gcmService.findByNumber(number);
        BeanUtils.copyProperties(gcm, gcmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gcmDto);
    }
    
    //Calcula idade do GCM
    @GetMapping("/idade/{numero}")
    public Integer devolveIdade(@PathVariable Short numero){
        Gcm gm = gcmService.findByNumber(numero);
        return gm.calcularIdade(); //model.Gcm
    }

    //calcula tempo de serviço em anos
    @GetMapping("/tempo-serv/{numero}")
    public int calculaTempoServ(@PathVariable Short numero){
        Gcm gm2 = gcmService.findByNumber(numero);
        return gm2.calcularAnosServico(); //model.Gcm
    }
}