package br.com.gcm.sac.setor_armamento.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {
    
    private Integer id;
    private Integer number;
    private String brand;
    private NameEnum category;

    public EquipmentDTO(Equipment eq){
        this.id = eq.getId();
        this.number = eq.getNumber();
        this.brand = eq.getBrand();
        this.category = eq.getCategory();
    }

    //Método static pertence a classe e não ao objeto instanciado ex. HandcuffDTO.convertList(List hc)
    public static List<EquipmentDTO> convertList(List<Equipment> eqs){
        return eqs.stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }
}