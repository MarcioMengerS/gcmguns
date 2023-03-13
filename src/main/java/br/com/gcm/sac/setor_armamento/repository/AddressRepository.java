package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gcm.sac.setor_armamento.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
}