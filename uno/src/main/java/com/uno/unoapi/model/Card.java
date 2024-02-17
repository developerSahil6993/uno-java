package com.uno.unoapi.model;

import com.uno.unoapi.enums.Color;
import com.uno.unoapi.enums.Type;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class Card {

    private Integer number;
    private Type cardType;
    private Color cardColor;

    private String uuid = UUID.randomUUID().toString();
}
