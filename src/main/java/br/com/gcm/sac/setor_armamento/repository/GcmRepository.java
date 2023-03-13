package br.com.gcm.sac.setor_armamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gcm.sac.setor_armamento.model.Gcm;

public interface GcmRepository extends JpaRepository<Gcm, Integer>{
    Gcm findByNumero(Short numero);
    List<Gcm> findByNomeContaining(String nome);
    //Método que retorna numero maior que...
    //List<Gcm> findByNumeroGreaterThan(Short numero);
}
