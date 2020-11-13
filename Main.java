import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class Main {
    public static int rakhkanNum;
    public static void main(String[] args) throws CloneNotSupportedException {

       int peapleNum;
        Scanner scanner=new Scanner(System.in);

         rakhkanNum = scanner.nextInt();
       peapleNum= scanner.nextInt();

        ArrayList<Person> people=new ArrayList<>();

        for(int i = 0 ; i < peapleNum ; i++){
            int height = scanner.nextInt();
            String entranceTime=scanner.next();
            LocalTime enT = LocalTime.parse(entranceTime);
            String exitTime = scanner.next();
            LocalTime exT = LocalTime.parse(exitTime);
            Person person = new Person(height,enT,exT);
            people.add(person);
        }



        for(int i=0;i<rakhkanNum ; i++) {
            for (int j = 0; j < peapleNum; j++) {
                people.get(i).komod.shomare = i;
            }
        }

        if(peapleNum>rakhkanNum) {
            for (int i = peapleNum - rakhkanNum; i < rakhkanNum; i++) {
                for (int j = 0; j < peapleNum-rakhkanNum; j++) {
                    if (people.get(i).vorodtime.compareTo(people.get(j).vorodtime) > 0) {
                        if (people.get(j).vorodtime.compareTo(people.get(i).khorojtime) < 0) {

                            people.get(i).komod.shomare = people.get(j).komod.shomare;
                            j = people.size();
                        }
                    }
                    else if(people.get(i).vorodtime.compareTo(people.get(j).vorodtime) > 0){
                        if(people.get(i).vorodtime.compareTo(people.get(j).khorojtime)>0){

                            people.get(i).komod.shomare = people.get(j).komod.shomare;

                            j = people.size();
                        }
                    }
                }
            }
        }


        State op=new State(people);
System.out.println(firstChoiceHillClimbing(op));




    }

    public static State firstChoiceHillClimbing(State firstState) throws CloneNotSupportedException {
        int min=Integer.MAX_VALUE;
        State bestState = null;
        ArrayList<State> states = firstState.successor();
        for(int i=0;i<states.size();i++){
            if(states.get(i).difficulty < min){
                min=states.get(i).difficulty;
                bestState=states.get(i);
            }
        }
        if(bestState.difficulty<=firstState.difficulty){
            firstChoiceHillClimbing(bestState);
        }
        else{
            return bestState;
        }
        return null;
    }

    public static State randomWalk(State firstState) throws CloneNotSupportedException {
        int min = Integer.MAX_VALUE;
        State bestState =null;
        ArrayList<State> states = firstState.successor();
        while (bestState.difficulty != 0) {
            for (int i = 0; i < states.size(); i++) {
                if (states.get(i).difficulty < min) {
                    min = states.get(i).difficulty;
                    bestState = states.get(i);
                }
            }
            int random = (int) (Math.random()*((states.size()-0)+1)) +0;
            randomWalk(states.get(random));
        }
        return bestState;
    }


}
