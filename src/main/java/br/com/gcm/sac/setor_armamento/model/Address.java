package br.com.gcm.sac.setor_armamento.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements Serializable{

    private static final long serialVersionUid = -1314843665707276799L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Short numero;
    private String logradouro;
    private String bairro;
    private String cep;
    private String localidade;
    private String uf;
}
