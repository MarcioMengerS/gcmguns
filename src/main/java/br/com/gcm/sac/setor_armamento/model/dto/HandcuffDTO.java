package br.com.gcm.sac.setor_armamento.model.dto;

import java.util.List;
import java.util.stream.Collectors;
import br.com.gcm.sac.setor_armamento.model.Handcuff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandcuffDTO {
    
    private Integer id;
    private Integer number;
    private String brand;

    public HandcuffDTO(Handcuff hc){
        this.id = hc.getId();
        this.number = hc.getNumber();
        this.brand = hc.getBrand();
    }

    //Método static pertence a classe e não ao objeto instanciado ex. HandcuffDTO.convertList(List hc)
    public static List<HandcuffDTO> convertList(List<Handcuff> hcs){
        return hcs.stream().map(HandcuffDTO::new).collect(Collectors.toList());
    }
}