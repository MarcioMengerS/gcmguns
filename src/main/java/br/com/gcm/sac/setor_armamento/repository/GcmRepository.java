package br.com.gcm.sac.setor_armamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gcm.sac.setor_armamento.model.Gcm;

@Repository
public interface GcmRepository extends JpaRepository<Gcm, Integer>{
    Gcm findByNumero(Short numero);
    List<Gcm> findByNomeContaining(String nome);
    //List<Gcm> findByNumeroGreaterThan(Short numero);
}
