package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gcm.sac.setor_armamento.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
    
}
