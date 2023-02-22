package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gcm.sac.setor_armamento.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
    
}
