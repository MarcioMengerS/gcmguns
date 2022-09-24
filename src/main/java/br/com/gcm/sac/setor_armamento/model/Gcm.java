package br.com.gcm.sac.setor_armamento.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Positive
    private Short numero; //short: +32.767 a -32.767

    @NotNull(message = "Nome não deve ser nulo")
    @NotBlank
    @Length(max = 50)
    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAdmis;

    @Email(regexp = "[\\w-]+@([\\w-]+\\.)+[\\w-]+")
    private String email;
}
