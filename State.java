import java.util.ArrayList;
import java.util.Iterator;

public class State {

    private ArrayList<Person> people;
    int difficulty;


    public State(ArrayList<Person> people) {
        this.people = people;
        difficulty = this.difficulty();
    }



    public int difficulty() {
        int difficultySum = 0;
        for(int i = 0 ; i<people.size() ; i++){
            for(int j = 0 ; j<people.size() ; j++){
                if(i!=j) {
                    if (people.get(i).vorodtime.getHour() == people.get(j).vorodtime.getHour()) {
                        if (people.get(i).vorodtime.getMinute() == people.get(j).vorodtime.getMinute()
                                || people.get(i).getEntranceTime().getMinute() <= people.get(j).vorodtime.getMinute() + 16
                                || people.get(i).getEntranceTime().getMinute() >= people.get(j).vorodtime.getMinute()) {
                            if (people.get(i).komod.shomare - people.get(j).komod.shomare == 1 || people.get(j).komod.shomare - people.get(i).komod.shomare == 1) {

                               if(people.get(i).komod.shomare !=(Main.rakhkanNum/3)-1 && people.get(i).komod.shomare !=(Main.rakhkanNum/3)*2-1) {

                                   difficultySum++;
                               }
                            }
                        }
                    }
                    if (people.get(i).khorojtime.getHour() == people.get(j).khorojtime.getHour()) {
                        if (people.get(i).khorojtime.getMinute() == people.get(j).khorojtime.getMinute()
                                || people.get(i).khorojtime.getMinute() <= people.get(j).khorojtime.getMinute() + 5
                                || people.get(i).khorojtime.getMinute() >= people.get(j).khorojtime.getMinute()) {
                            if (people.get(i).komod.shomare - people.get(j).komod.shomare == 1 || people.get(j).komod.shomare - people.get(i).komod.shomare == 1) {
                                if(!(people.get(i).komod.shomare ==Main.rakhkanNum/3 && people.get(j).komod.shomare==Main.rakhkanNum-1)|| !(people.get(i).komod.shomare ==(Main.rakhkanNum/3)*2 && people.get(j).komod.shomare==(Main.rakhkanNum/3)*2-1)) {

                                    difficultySum++;
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int k=0; k<people.size() ; k++){
            if(people.get(k).ghad>175 && people.get(k).komod.shomare % 2 ==1)
                difficultySum++;
            else if(people.get(k).ghad <= 175 && people.get(k).komod.shomare % 2 ==0)
                difficultySum++;
        }

        return difficultySum;
    }

    public ArrayList<State> successor() throws CloneNotSupportedException {

        ArrayList<State> states = new ArrayList<>();

        for(int i = 0 ; i<people.size() ; i++){
            for(int j=0 ; j < people.size() ; j++){
                if(i!=j) {
                    ArrayList<Person> newPeople = this.clonedListPeople(people);
                    people.get(i).komod = people.get(j).komod;
                    State state = new State(newPeople);
                    if (state.isValid()) {
                        states.add(state);
                    }
                }
            }
        }

        return states;

    }

    public boolean isValid(){
        for(int i =0 ; i<people.size() ; i++){
            for(int j =0 ;j<people.size() ; j++){
                if(i!=j) {
                    if (people.get(i).komod.shomare == people.get(j).komod.shomare){
                        if(people.get(i).vorodtime.compareTo(people.get(j).vorodtime)>0){
                            if(people.get(j).khorojtime.compareTo(people.get(i).vorodtime)>0){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    public ArrayList<Person> clonedListPeople(ArrayList<Person> list) throws CloneNotSupportedException{
        ArrayList<Person> people = new ArrayList<>();
        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext()) {
            people.add((Person) iterator.next().clone());
        }
        return people;
    }

}
