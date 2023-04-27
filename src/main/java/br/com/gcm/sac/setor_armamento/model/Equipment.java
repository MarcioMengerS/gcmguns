package br.com.gcm.sac.setor_armamento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    private Integer number;
    private String brand;

    //Atributo do tipo Empréstimo(loan) com o mapeamento bidirecional
    @OneToMany(mappedBy="equipment", cascade = CascadeType.ALL)
    private List<Loan> loan;
    
}