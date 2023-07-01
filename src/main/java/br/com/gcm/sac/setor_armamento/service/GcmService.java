package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.repository.GcmRepository;

@Service
public class GcmService {

    //Criação de construtor e atributo recomendado pela documentação
    //Atributo da classe GcmService
    private final GcmRepository gcmRepository;

    //Construtor
    public GcmService(GcmRepository gcmRepository){
        this.gcmRepository = gcmRepository;
    }

    //Salva ou edita GCM no repository/BD
    public Gcm save(Gcm gcm){
        return gcmRepository.save(gcm);
    }

    //Retorna a lista de todos GCMs do repository/BD
    public List<Gcm> listAll(){
        return gcmRepository.findAll();
    }

    //Exclui objeto GCM do repository/BD
    public String deleteById(Integer id){
        Gcm gcm = gcmRepository.findById(id).get();
        gcmRepository.deleteById(id);
        return String.format("%s foi deletado com sucesso", gcm.getNome());
    }

    public Gcm update(Gcm g, Integer id){
        Gcm gcm = gcmRepository.findById(id).get();
        gcm.setNumero(g.getNumero());
        gcm.setNome(g.getNome());
        gcm.setCpf(g.getCpf());
        gcm.setDataNas(g.getDataNas());
        gcm.setDataAdmis(g.getDataAdmis());
        gcm.setEmail(g.getEmail());
        gcm.setTag(g.getTag());
        gcm.setTransactionPassword(g.getTransactionPassword());
        return gcmRepository.save(gcm);
    }

//######################################## Métodos de Busca ###################################
    //Busca pelo id objeto GCM no repository/BD
    public Gcm findById(Integer id) {
        /*opção 1: return gcmRepository.findById(id).get();*/
        return gcmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

    //Busca no BD pelo trecho do nome do GCM
    public List<Gcm> findByNameContaining(String nome) {
        return gcmRepository.findByNomeContaining(nome);
    }

    //Busca no BD por numero do GCM
    public Gcm findByNumber(Short numero) {
        return gcmRepository.findByNumero(numero);
    }

    public Boolean searchGcmTag(Short numberGcm, String tag){
        Gcm gm = gcmRepository.findByNumero(numberGcm);
        String tagGcm = gm.getTag();
        if(tagGcm.equalsIgnoreCase(tag)){
            return true;
        }else{
            return false;
        }
    }
    //Quantidade total de Guardas cadastrados no sistema
    public Long totalGcm() {
        return gcmRepository.count();
    }
}
