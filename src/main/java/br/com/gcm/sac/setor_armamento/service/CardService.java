package br.com.gcm.sac.setor_armamento.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import javax.websocket.Session;

import br.com.gcm.sac.setor_armamento.model.Card;
import br.com.gcm.sac.setor_armamento.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    //Salva cartão no BD
    public Card save(Card tag){
        return cardRepository.save(tag);
    }

    //Mostrar todas as tags
    public List<Card> listAll(){
        return cardRepository.findAll();
    }

    //Excluir tag do Banco de Dados
    public String deleteById(Integer id){
        Card tag = cardRepository.findById(id).get();
        cardRepository.deleteById(id);
        return String.format("%s foi deletado com sucesso", tag.getCard());
    }

    //buscar tag no Banco de Dados
    public Card findById(Integer id) {
        return cardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }
}
