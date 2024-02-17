package com.uno.unoapi.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@ToString
public class Player {

    private String name;
    private String uuid = UUID.randomUUID().toString();

    private List<Card> holdingCards;
}
