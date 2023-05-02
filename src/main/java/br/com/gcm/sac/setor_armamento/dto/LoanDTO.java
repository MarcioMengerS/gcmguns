package br.com.gcm.sac.setor_armamento.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
import br.com.gcm.sac.setor_armamento.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    private Integer id;
    private LocalDateTime devolution;
    private LocalDateTime removal;
    private String nome_gcm;
    private NameEnum name_equ;

    public LoanDTO(Loan loan){
        this.id = loan.getId();
        this.devolution = loan.getDevolution();
        this.removal = loan.getRemoval();
        this.nome_gcm = loan.getGcm().getNome();
        this.name_equ = loan.getEquipment().getName();
    }
    //Método static pertence a classe e não ao objeto instanciado ex. LoanDTO.convertList(List loan)
    public static List<LoanDTO> convertList(List<Loan> loan){
        return loan.stream().map(LoanDTO::new).collect(Collectors.toList());
    }
}
