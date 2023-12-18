package br.com.gcm.sac.setor_armamento.model;

import java.time.LocalDate;
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
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import br.com.gcm.sac.setor_armamento.enuns.CaliberEnum;
import br.com.gcm.sac.setor_armamento.enuns.CategoryEnum;
import br.com.gcm.sac.setor_armamento.enuns.GenderEnum;
import br.com.gcm.sac.setor_armamento.enuns.LevelProtectionEnum;
import br.com.gcm.sac.setor_armamento.enuns.SizeEnum;
import br.com.gcm.sac.setor_armamento.enuns.WearEnum;
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
    private CategoryEnum category;// categoria =arma
    private Integer assetNumber; //número de patrimônio (todos itens)

    @Enumerated(value = EnumType.STRING)
    private CaliberEnum caliber; //calibre =arma
    private Integer register; //número do registro =arma
    private String number; //número de série =arma =colete
    private String specie; //espécie =arma
    @Enumerated(value = EnumType.STRING)
    private WearEnum wear; //porte =colete
    private String brand; //marca =arma =colete =espargidor =lanterna =spark
    private String model; //modelo =arma =colete =espargidor =lanterna =spark
    private String operation; //funcionamento =arma
    private String numberOfPipes; //quantidade de canos =arma
    private String soulInformation; //Tipo de alma =arma

    @Enumerated(value = EnumType.STRING)
    private SizeEnum size; //tamanho =colete =espargidor
    @Enumerated(value = EnumType.STRING)
    private LevelProtectionEnum levelOfProtection; //nível de proteção =colete
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender; // gênero que vai utilizar =colete

    private String distance; //alcance =espargidor
    private String jetSystem; //sistema de jato =espargidor
    private String agent; //agente impregnante =espargidor

    private String info; //informações adicionais

    @Future(message = "A data deve estar no futuro!")
    private LocalDate expirationDate; //data de  validade =colete
    @Past(message = "A data deve estar no Passado!")
    private LocalDate manufacturingDate; //data de fabricação =colete

    private Boolean available = true; //equipamento disponível == true

    //Atributo do tipo Empréstimo(loan) com o mapeamento bidirecional
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<Loan> loan;
}