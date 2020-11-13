import java.sql.Time;
import java.time.LocalTime;

public class Person implements Cloneable {

    float ghad;

   komod komod;
    LocalTime vorodtime;
    LocalTime  khorojtime;


    public Person(float height, LocalTime entranceTime, LocalTime exitTime) {
        this.ghad = height;
        this.vorodtime = entranceTime;
        this.khorojtime = exitTime;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = null;
        person.ghad = this.ghad;
        person.vorodtime = this.vorodtime;
        person.khorojtime = this.khorojtime;
        komod komod=new komod();
        person.komod=komod;
        return person;
    }

    public float getHeight() {
        return ghad;
    }

    public void setHeight(float height) {
        this.ghad = height;
    }

    public LocalTime getEntranceTime() {
        return vorodtime;
    }

    public void setVorodtime(LocalTime entranceTime) {
        this.vorodtime = entranceTime;
    }

    public LocalTime getKhorojtime() {
        return khorojtime;
    }

    public void setKhorojtime(LocalTime exitTime) {
        this.khorojtime = exitTime;
    }
}
