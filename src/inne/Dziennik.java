
import java.util.Vector;

public class Dziennik {

    public Dziennik(){

        komunikaty = new Vector<String>();

    }


    public void wpisz(String komunikat){

        komunikaty.add(komunikat);

    }

    public void czysc(){

        komunikaty.clear();

    }

    public String wypisz(){

        StringBuilder out = new StringBuilder();

        for(String komunikat : komunikaty){

            out.append(komunikat).append("\n");

        }

        return out.toString();

    }



    private final Vector<String> komunikaty;
}

