package br.com.gcm.sac.setor_armamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gcm.sac.setor_armamento.model.Loan;
import java.util.List;


public interface LoanRepository extends JpaRepository<Loan, Integer>{
    List<Loan> findByGcmId(Integer number);
}
