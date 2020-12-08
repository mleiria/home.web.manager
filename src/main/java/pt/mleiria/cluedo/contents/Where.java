package pt.mleiria.cluedo.contents;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Where implements Card {

    APOSENTOS_DE_CERCEI("Aposentos de Cercei", GameType.A_FORTALEZA_VERMELHA),
    APOSENTOS_DO_REI_TOMMEN("Aposentos do Rei Tommen", GameType.A_FORTALEZA_VERMELHA),
    CAMARAS_DA_GUARDA_REAL("Camaras da Guarda Real", GameType.A_FORTALEZA_VERMELHA),
    CAMARA_DO_CONSELHO("Camara do Conselho", GameType.A_FORTALEZA_VERMELHA),
    CELAS_DA_PRISAO("Celas da Prisao", GameType.A_FORTALEZA_VERMELHA),
    JARDINS("Jardins", GameType.A_FORTALEZA_VERMELHA),
    LABORATORIO_DE_QYBURN("Laboratorio de Qyburn", GameType.A_FORTALEZA_VERMELHA),
    LABORATORIO_DO_GRANDE_MEISTRE_PYCELLE("Laboratorio do Grande Meistre Pycelle", GameType.A_FORTALEZA_VERMELHA),
    PATIO_DE_TREINO("Patio de Treino", GameType.A_FORTALEZA_VERMELHA),
    PAVILHAO("Pavilhao", GameType.A_FORTALEZA_VERMELHA),
    TORRE_DA_MAO_DO_REI("Torre da Mao do Rei", GameType.A_FORTALEZA_VERMELHA);


    private final String value;
    private final GameType gameType;

    Where(String value, GameType gameType) {
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
