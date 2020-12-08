package pt.mleiria.cluedo.contents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Who implements Card{

    CERSEI_LANNISTER("Cercei Lannister", GameType.A_FORTALEZA_VERMELHA),
    DAENERYS_TARGARYEN("Daenerys Targaryen", GameType.MEESEEN),
    JAIME_LANNISTER("Jaime Lannister", GameType.A_FORTALEZA_VERMELHA),
    MARGAERY_TYRELL("Margaery Tyrell", GameType.A_FORTALEZA_VERMELHA),
    PETYR_BAELISH("Petyr Baelish", GameType.A_FORTALEZA_VERMELHA),
    SANSA_STARK("Sansa Stark", GameType.A_FORTALEZA_VERMELHA),
    TYRION_LANNISTER("Tyrion Lannister", GameType.A_FORTALEZA_VERMELHA);






    private final String value;
    private final GameType gameType;

    Who(String value, GameType gameType) {
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
