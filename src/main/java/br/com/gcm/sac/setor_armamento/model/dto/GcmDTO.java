package br.com.gcm.sac.setor_armamento.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gcm.sac.setor_armamento.model.Address;
import br.com.gcm.sac.setor_armamento.model.Gcm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GcmDTO {

    private Integer id;
    private String nome;
    private Short numero;
    private String cpf;
    private LocalDate dataNas;
    private LocalDate dataAdmis;
    private String email;
    private Address address;
    //private String tag;

    public GcmDTO(Gcm gcm){
        this.id = gcm.getId();
        this.nome = gcm.getNome();
        this.numero = gcm.getNumero();
        this.cpf = gcm.getCpf();
        this.dataNas = gcm.getDataNas();
        this.dataAdmis = gcm.getDataAdmis();
        this.email = gcm.getEmail();
        this.address = gcm.getAddress();
        //this.tag = gcm.getTag();
    }
    //Método static pertence a classe e não ao objeto instanciado ex. GcmDTO.convertList(List gcm)
    public static List<GcmDTO> convertList(List<Gcm> gcms){
        return gcms.stream().map(GcmDTO::new).collect(Collectors.toList());
    }
}
