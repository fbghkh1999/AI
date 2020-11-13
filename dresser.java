

public class komod implements Cloneable {

    int shomare;



    @Override
    protected Object clone() throws CloneNotSupportedException {
        komod KOMOD = new komod();
        KOMOD.shomare = this.shomare;
        return KOMOD;
    }

    public int getShomare() {
        return shomare;
    }

    public void setShomare(int shomare) {
        this.shomare = shomare;
    }




}
