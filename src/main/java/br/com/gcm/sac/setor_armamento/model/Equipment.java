package br.com.gcm.sac.setor_armamento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.gcm.sac.setor_armamento.enuns.NameEnum;
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
    @Enumerated(value = EnumType.STRING)
    private NameEnum category;
    private String calibre;//caliber
    private Integer number;
    private String brand;

    //Atributo do tipo Empréstimo(loan) com o mapeamento bidirecional
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Loan> loan;
}