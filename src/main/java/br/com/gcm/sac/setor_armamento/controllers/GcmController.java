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
import br.com.gcm.sac.setor_armamento.model.dto.GcmDTO;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.model.Handcuff;
import br.com.gcm.sac.setor_armamento.service.GcmService;
import br.com.gcm.sac.setor_armamento.service.HandcuffService;

@RestController
@RequestMapping("/gcm")
public class GcmController {

    @Autowired
    GcmService gcmService;
 
    @Autowired
    HandcuffService handcuffService;
    
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

    //Exclui Objeto do BD
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPorID(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gcmService.deleteById(id));
    }

    //Modifica dados do objeto GCM cadastrado no BD
    @PutMapping("/{id}")
    public ResponseEntity<Gcm> updateById(@RequestBody Gcm gcm, @PathVariable Integer id){
        return ResponseEntity.ok().body(gcmService.update(gcm, id));
    }

    //Pesquisa no BD objeto GCM por ID
    @GetMapping("/{id}") //localhost:8080/find/10
    public ResponseEntity<Gcm> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(gcmService.findById(id));
    }

//########################## Métodos de Busca Avançados ###################################
    //Pesquisa por parte do nome do GCM
    @GetMapping("/name")//localhost:8080/name?nome=menger
    public List<Gcm> buscarPorParteNome(@RequestParam("nome") String nome) {
        return gcmService.findByNameContaining(nome);
    }

    //Pesquisa GCMs por numero
    @GetMapping("/num/{numero}")//localhost:8080/717
    public Gcm buscarGcmPorNum(@PathVariable Short numero){
        return gcmService.findByNumber(numero);
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

    ///////////////////////////teste///////////////////////
    @PostMapping("/{num_gcm}/handcuff/{id_hc}")
    public void saveHandcuffGcm(@PathVariable Integer id_hc, @PathVariable Short num_gcm){
        Handcuff handcuff = new Handcuff();
        handcuff = handcuffService.findById(id_hc);

        Gcm gcm = new Gcm();
        gcm = gcmService.findByNumber(num_gcm);
        
        gcm.setHandcuff(handcuff);
        handcuff.setGcm(gcm);

        gcmService.save(gcm);
    }

    @GetMapping("/handcuff/{id_gcm}")
    public Handcuff findHandcuffOfGcm(@PathVariable Integer id_gcm){
        Handcuff hc = new Handcuff();
        hc = gcmService.findById(id_gcm).getHandcuff();
        Handcuff hcDto = new Handcuff();
        hcDto.setBrand(hc.getBrand());
        hcDto.setId(hc.getId());
        hcDto.setNumber(hc.getNumber());
        return hcDto;

    }

}