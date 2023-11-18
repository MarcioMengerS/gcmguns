package br.com.gcm.sac.setor_armamento.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gcm implements Comparable<Gcm>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//+-2.147.483.647

    @NotNull
    @Min(value=1)
    private Short numero; //-+32.767

    @NotBlank(message = "Nome não pode estar em branco")
    @Size (min = 12, max = 50) 
    private String nome;

    @CPF
    private String cpf;

    @Past(message = "A data deve estar no passado!")
    private LocalDate dataNas;

    @Past(message = "A data deve estar no passado!")
    private LocalDate dataAdmis;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private String tag;

    private String transactionPassword;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true , optional = true)
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "gcm", cascade = CascadeType.ALL)
    private List<Loan> loan;

    @Override
    public int compareTo(Gcm o) {
        return numero.compareTo(o.numero);  
    }
}
