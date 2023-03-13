package br.com.gcm.sac.setor_armamento.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GcmDTO {

    private Integer id;
    private String name;
    private Short number;
    private String cpf;
    private LocalDate birthDate;
    private LocalDate admissionDate;
    private String email;

    public GcmDTO(Gcm gcm){
        this.id = gcm.getId();
        this.name = gcm.getNome();
        this.number = gcm.getNumero();
        this.cpf = gcm.getCpf();
        this.birthDate = gcm.getDataNas();
        this.admissionDate = gcm.getDataAdmis();
        this.email = gcm.getEmail();
    }
    //Método static pertence a classe e não ao objeto instanciado ex. GcmDTO.convertList(List gcm)
    public static List<GcmDTO> convertList(List<Gcm> gcms){
        return gcms.stream().map(GcmDTO::new).collect(Collectors.toList());
    }
}
