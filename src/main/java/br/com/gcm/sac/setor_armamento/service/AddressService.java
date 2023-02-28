package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import br.com.gcm.sac.setor_armamento.model.Address;
import br.com.gcm.sac.setor_armamento.repository.AddressRepository;
import br.com.gcm.sac.setor_armamento.repository.GcmRepository;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GcmRepository gcmRepository;

    //Salva Endereço no AddressRepository
    public Address save(Address end){
        return addressRepository.save(end);
    }

    public String deleteById(Integer id){
        Address add = addressRepository.findById(id).get();
        addressRepository.deleteById(id);
        return String.format("%s foi deletado com sucesso", add.getLogradouro());
    }

    public List<Address> listAll(){
        return addressRepository.findAll();
    }

    public Address findById(Integer id) {
        /*opção 1: return gcmRepository.findById(id).get();*/
        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

}
