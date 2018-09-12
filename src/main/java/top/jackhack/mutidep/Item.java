package top.jackhack.mutidep;

public class Item {
    public int id;
    public int w;
    public int v;
    public int dep;
    public int chd;
    public Item(int w, int v) {

        this.w = w;
        this.v = v;
    }

    public boolean check(int added) {
        return dep == (added & dep);
    }
}
