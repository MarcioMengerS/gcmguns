package br.com.gcm.sac.setor_armamento.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gcm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//int +-2.147.483.647

    @NotNull
    @Min(value=1)
    private Short numero; //short: +32.767 a -32.767

    @NotBlank(message = "Nome não pode estar em branco")
    @Length(max = 50)
    @Pattern(regexp = "^(?![ ])(?!.*[ ]{2})((?:e|da|do|das|dos|de|d'|D'|la|las|el|los)\\s*?|(?:[A-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð'][^\\s]*\\s*?)(?!.*[ ]$))+$")
    private String nome;

    @CPF
    private String cpf;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "A data deve estar no passado!")
    private LocalDate dataNas;

    @Past(message = "A data deve estar no passado!")
    private LocalDate dataAdmis;

    @Email(regexp = "[\\w-]+@([\\w-]+\\.)+[\\w-]+")
    private String email;

    private String tag;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true , optional = true)
    private Address address;
    
    // @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true , optional = true)
    // @JoinColumn(name = "card_id", referencedColumnName = "id")
    // private Card card;

    @OneToOne(mappedBy = "gcm")
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    public Integer calcularIdade(){
        LocalDate nascimento = LocalDate.of(this.dataNas.getYear(), this.dataNas.getMonth(), this.dataNas.getDayOfMonth());
        final LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(nascimento, dataAtual);
        return periodo.getYears();
    }

    public int calcularAnosServico() {
        LocalDate dAdmissao = LocalDate.of(this.dataAdmis.getYear(), this.dataAdmis.getMonth(), this.dataAdmis.getDayOfMonth());
        final LocalDate dataAtual = LocalDate.now();
        Period tempo = Period.between(dAdmissao, dataAtual);
        return tempo.getYears();
    }
}
