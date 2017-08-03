package data.network;

public class Pair {

    private final String first;

    private final String second;

    public Pair(String name, String value) {
        this.first = name;
        this.second = value;
    }

    String getFirst() {
        return first;
    }

    String getSecond() {
        return second;
    }
}