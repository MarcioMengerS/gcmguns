package br.com.gcm.sac.setor_armamento.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date devolution; //devolução
    private Date removal; //empréstimo

    @ManyToOne
    @JoinColumn(name = "gcm_id")
    private Gcm gcm;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
