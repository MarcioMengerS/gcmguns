package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gcm.sac.setor_armamento.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{
    
}
