package pt.mleiria.cluedo.contents;

import java.util.Arrays;
import java.util.List;

public enum What implements Card {


    ADAGA_DO_SICARIO_ASSASSINO("Adaga do Sicario Assassino", GameType.A_FORTALEZA_VERMELHA),
    ARAKH("Arakh", GameType.A_FORTALEZA_VERMELHA),
    BESTA("Besta", GameType.A_FORTALEZA_VERMELHA),
    FRASCO_DE_VENENO("Frasco de Veneno", GameType.A_FORTALEZA_VERMELHA),
    HOMEM_SEM_ROSTO("Homem sem Rosto", GameType.A_FORTALEZA_VERMELHA),
    MACHADO_DE_GUERRA("Machado de Guerra", GameType.A_FORTALEZA_VERMELHA);


    private final String value;
    private final GameType gameType;

    What(String value, GameType gameType) {
        this.value = value;
        this.gameType = gameType;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public GameType getGameType() {
        return gameType;
    }


}
