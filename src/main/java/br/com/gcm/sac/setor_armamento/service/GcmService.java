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
    private final GcmRepository gcmRepository;

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
       
    //} catch (Exception e) {
        //    return Optional.empty();
       // }
    }
}
