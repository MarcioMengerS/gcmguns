package br.com.gcm.sac.setor_armamento.dto;
import java.time.ZonedDateTime;
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
    private ZonedDateTime devolution;
    private ZonedDateTime removal;
    private Integer id_gcm;   
    private String name_gcm;
    private Integer id_equipment;
    private NameEnum equipment_category;

    public LoanDTO(Loan loan){
        this.id = loan.getId();
        this.devolution = loan.getDevolution();
        this.removal = loan.getRemoval();
        this.id_gcm = loan.getGcm().getId();
        this.name_gcm = loan.getGcm().getNome();
        this.id_equipment= loan.getEquipment().getId();
        this.equipment_category = loan.getEquipment().getCategory();
    }
    //Método static pertence a classe e não ao objeto instanciado ex. LoanDTO.convertList(List loan)
    public static List<LoanDTO> convertList(List<Loan> loan){
        return loan.stream().map(LoanDTO::new).collect(Collectors.toList());
    }
}
