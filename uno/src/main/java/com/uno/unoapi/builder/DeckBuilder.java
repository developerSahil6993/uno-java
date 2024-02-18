package com.uno.unoapi.builder;

import com.uno.unoapi.enums.Color;
import com.uno.unoapi.enums.Type;
import com.uno.unoapi.model.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DeckBuilder {

    private static final Integer TOTAL_CARDS = 112;
    private static final Integer NUMBER_CARDS_PER_COLOR = 9;

    private static List<Card> deck = new ArrayList<>(TOTAL_CARDS);

    private static boolean isGameNotStarted = deck.isEmpty();

    public static List<Card> init(){
        if(isGameNotStarted){
            build();
        }
        return deck;
    }

    public static List<Card> getDeck() {
        return deck;
    }

    private static void build(){
        buildNumberCards();
        buildStopNextCards();
        buildReverseCards();
        buildWildCards();
        buildDrawTwoCards();
        buildDrawFourCards();
        System.out.println("Deck size :"+deck.size());
        Collections.shuffle(deck);
    }

    private static void buildWildCards() {
        for(Color color : Color.values()){
            Card card = getCard(color,Type.WILD, null);
            deck.add(card);
        }
    }

    private static void buildDrawTwoCards() {
        for(Color color : Color.values()){
            prepareDeckOfCardsOfType(color, Type.DRAW_TWO, null);
        }
    }

    private static void buildDrawFourCards() {
        for(Color color : Color.values()){
            prepareDeckOfCardsOfType(color, Type.DRAW_FOUR, null);
        }
    }

    private static void buildReverseCards() {
        for(Color color : Color.values()){
            prepareDeckOfCardsOfType(color, Type.REVERSE_TURN, null);
        }
    }

    private static void prepareDeckOfCardsOfType(Color color,Type type,Integer count) {
        Card card = getCard(color, type, count);
        Card card2 = getCard(color, type, count);;
        deck.add(card);
        deck.add(card2);
    }

    private static void buildStopNextCards() {
        for(Color color : Color.values()){
            prepareDeckOfCardsOfType(color, Type.SKIP_NEXT, null);
        }
    }

    private static void buildNumberCards() {
        for(Color color : Color.values()){
            Integer count = 0;
            while(count <= NUMBER_CARDS_PER_COLOR){
                if(count == 0) {
                    Card card = getCard(color,Type.NUMBER, count);
                    deck.add(card);
                }else{
                    prepareDeckOfCardsOfType(color, Type.NUMBER, count);
                }
                count++;
            }
        }
    }

    private static Card getCard(Color color,Type type, Integer count) {
        Card card = new Card();
        card.setCardColor(color);
        card.setCardType(type);
        if(count != null)
            card.setNumber(count);
        return card;
    }

}
