package br.com.gcm.sac.setor_armamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Gcm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//int +-2.147.483.647

    @Column(nullable = false) //Não pode ser nulo
    private Short numero; //short: +-32.767

    @Column(nullable = false) //Não pode ser nulo
    private String nome;

    private String email;
}
