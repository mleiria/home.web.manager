package pt.mleiria.cluedo.contents;

public class Guess {

    private final Who who;
    private final Where where;
    private final What what;

    public Guess(final Who who, final Where where, final What what){
        this.who = who;
        this.where = where;
        this.what = what;
    }

    public Who getWho() {
        return who;
    }

    public Where getWhere() {
        return where;
    }

    public What getWhat() {
        return what;
    }
}
