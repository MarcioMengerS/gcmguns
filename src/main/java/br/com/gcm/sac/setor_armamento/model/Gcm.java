package br.com.gcm.sac.setor_armamento.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
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

    @NotBlank(message = "Nome não pode estar em branco")
    @Length(max = 50)
    private String nome;

    @CPF
    private String cpf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAdmis;

    @Email(regexp = "[\\w-]+@([\\w-]+\\.)+[\\w-]+")
    private String email;

    public int calcularIdade(){
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
