package com.uno.unoapi.service;

import com.uno.unoapi.builder.DeckBuilder;
import com.uno.unoapi.model.Card;
import com.uno.unoapi.model.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GameplayService {

    private static final Integer INIT_CARDS_PER_PLAYER = 7;

    private static List<Card> dealerCards = new LinkedList<>();

    private static List<Player> playingPlayers = new ArrayList<>();

    public void distributeCards(List<Player> players) {
        List<Card> originalDeck = DeckBuilder.getDeck();
        int totalDistributedCards = INIT_CARDS_PER_PLAYER * players.size();
        int count = 0;
        Map<String, List<Card>> playerCardMap = new HashMap<>();
        List<Card> playerDeckBuffer = new ArrayList<>();
        while (count < totalDistributedCards && totalDistributedCards < originalDeck.size()) {
            for (Player player : players) {
                if (player.getHoldingCards() == null || player.getHoldingCards().size() <= INIT_CARDS_PER_PLAYER) {
                    playerDeckBuffer = playerCardMap.get(player.getUuid());
                    if (playerDeckBuffer == null) {
                        List<Card> cards = new ArrayList<>();
                        cards.add(originalDeck.get(count));
                        playerCardMap.put(player.getUuid(), cards);
                        playerDeckBuffer = cards;
                    } else {
                        playerDeckBuffer.add(originalDeck.get(count));
                        playerCardMap.put(player.getUuid(), playerDeckBuffer);
                    }
                    player.setHoldingCards(playerDeckBuffer);
                }
                count++;
            }
        }
        originalDeck = originalDeck.stream().limit(originalDeck.size() - count).collect(Collectors.toList());
        dealerCards = originalDeck;
        playingPlayers = players;
    }

    public static void main(String[] args) {
        int n = 3;
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Player p1 = new Player();
            p1.setName("PLAYER-" + i);
            players.add(p1);
        }
        DeckBuilder.init();


        GameplayService gps = new GameplayService();
        gps.distributeCards(players);
        System.out.println(dealerCards.size());
        playingPlayers.forEach(it -> {
            System.out.println(it.getName() + " ::: cards => " + (it.getHoldingCards() != null ? it.getHoldingCards().size() : "Empty"));
        });
    }
}
