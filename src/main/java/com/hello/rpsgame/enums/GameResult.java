package com.hello.rpsgame.enums;

public enum GameResult {
    WON("승"),
    LOST("패"),
    TIE("비김");

    private String commentary;

    GameResult(String commentary) {
        this.commentary = commentary;
    }

    public String getCommentary() {
        return commentary;
    }
}
