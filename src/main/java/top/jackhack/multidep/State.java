package top.jackhack.multidep;

public class State {
    public int added;
    public int v;
    public int pos;
    public int w;

    public State(int added, int v, int w, int pos) {

        this.added = added;
        this.v = v;
        this.w = w;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "State(" + Integer.toBinaryString(added) + ", " + v + ", " + w + ", " + pos+")";
    }
}
