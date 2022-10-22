package br.com.gcm.sac.setor_armamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gcm.sac.setor_armamento.model.Address;
import br.com.gcm.sac.setor_armamento.repository.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    //Salva Endereço no AddressRepository
    public Address save(Address end){
    return addressRepository.save(end);
    }

}
