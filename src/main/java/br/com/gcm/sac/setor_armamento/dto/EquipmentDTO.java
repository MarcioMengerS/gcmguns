package br.com.gcm.sac.setor_armamento.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gcm.sac.setor_armamento.enuns.CaliberEnum;
import br.com.gcm.sac.setor_armamento.enuns.CategoryEnum;
import br.com.gcm.sac.setor_armamento.enuns.GenderEnum;
import br.com.gcm.sac.setor_armamento.enuns.LevelProtectionEnum;
import br.com.gcm.sac.setor_armamento.enuns.SizeEnum;
import br.com.gcm.sac.setor_armamento.enuns.WearEnum;
import br.com.gcm.sac.setor_armamento.model.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {
    
    private Integer id;
    private CategoryEnum category;

    private CaliberEnum caliber;
    private Integer register;
    private String number;
    private String specie;
    private WearEnum wear;
    private String brand;
    private String model;
    private String operation;
    private String numberOfPipes;
    private String soulInformation;

    private SizeEnum size;
    private LevelProtectionEnum levelOfProtection;
    private GenderEnum gender;

    private String distance;
    private String jetSystem;
    private String agent;

    private String info;

    private LocalDate expirationDate;
    private LocalDate manufacturingDate;

    private Boolean available;

    public EquipmentDTO(Equipment equip){
        this.id = equip.getId();
        this.category = equip.getCategory();

        this.caliber = equip.getCaliber();
        this.register = equip.getRegister();

        this.number = equip.getNumber();
        this.brand = equip.getBrand();

        this.available = equip.getAvailable();
    }

    //Método static pertence a classe e não ao objeto instanciado ex. HandcuffDTO.convertList(List hc)
    public static List<EquipmentDTO> convertList(List<Equipment> eqs){
        return eqs.stream().map(EquipmentDTO::new).collect(Collectors.toList());
    }
}