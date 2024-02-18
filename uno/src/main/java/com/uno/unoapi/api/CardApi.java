package com.uno.unoapi.api;

import com.uno.unoapi.builder.DeckBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("uno")
public class CardApi {

    @Autowired
    DeckBuilder deckBuilder;

    @GetMapping("cards")
    public ResponseEntity<?> getCards(){
        DeckBuilder.init();
        return ResponseEntity.ok(DeckBuilder.getDeck());
    }
}
