package br.com.gcm.sac.setor_armamento.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import br.com.gcm.sac.setor_armamento.enuns.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Roles implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @Enumerated(EnumType.STRING)
    // @Column(nullable = false, unique = true)
	private RoleEnum name;

    @Override
    public String getAuthority() {
        return this.name.toString();
    }
}
