package br.com.gcm.sac.setor_armamento.service;

import java.util.List;
import org.springframework.stereotype.Service;
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
    public void deleteById(Integer id){
        gcmRepository.deleteById(id);
    }

//######################################## Métodos de Busca ###################################
    //Busca pelo id objeto GCM no repository/BD
    public Gcm findById(Integer id) {
        return gcmRepository.findById(id).get();
    }

    //Método retorna se existe ou não objeto GCM no BD
    public Boolean existById(Integer id){
        return gcmRepository.existsById(id);
    }

    //Busca no BD pelo trecho do nome do GCM
    public List<Gcm> findByNameContaining(String nome) {
        return gcmRepository.findByNomeContaining(nome);
    }

    //Busca no BD lista de GCM com idade maior que...
    public Gcm findByNumber(Short numero) {
        return gcmRepository.findByNumero(numero);
    }
}
