package pt.mleiria.cluedo.contents;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Card<T> {

    GameType getGameType();

    String getValue();


}
