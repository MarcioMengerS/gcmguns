package br.com.gcm.sac.setor_armamento.model;

import java.time.ZonedDateTime;

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
    private ZonedDateTime devolution; //devolução
    private ZonedDateTime removal; //empréstimo

    @ManyToOne
    @JoinColumn(name = "gcm_id")
    private Gcm gcm;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
