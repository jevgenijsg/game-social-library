package com.tsicw.gamingsociallibrary.repository.domain;

public enum Genre {
    RPG("RPG"), STEALTH("STEALTH"), RTS("RTS"), FPS("FPS"), ARCADE("ARCADE");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
