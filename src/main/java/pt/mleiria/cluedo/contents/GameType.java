package pt.mleiria.cluedo.contents;

public enum GameType {
    A_FORTALEZA_VERMELHA("A Fortaleza Vermelha"),
    MEESEEN("Meesene");


    private final String value;

    GameType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
