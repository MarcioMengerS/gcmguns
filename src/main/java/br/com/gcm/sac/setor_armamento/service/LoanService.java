package br.com.gcm.sac.setor_armamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gcm.sac.setor_armamento.model.Loan;
import br.com.gcm.sac.setor_armamento.repository.LoanRepository;

@Service
public class LoanService {
 
    @Autowired
    private LoanRepository loanRepository;
    
    public Loan save(Loan loan){
        return loanRepository.save(loan);
    }

    public List<Loan> listAll(){
        return loanRepository.findAll();
    }
    //Busca todos os equipamento de um GCM
    public List<Loan> findByGcmId(Integer num){
        return loanRepository.findByGcmId(num);
    }
}