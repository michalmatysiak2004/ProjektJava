package symulacja.organizmy.rosliny;
import inne.*;
import symulacja.organizmy.Roslina;

import java.awt.*;

public class Mlecz extends Roslina {

    public static final int SILA = 0;
    public static final int PROBY = 3;

    public Mlecz(Wektor2d polozenie) {
        super(polozenie, SILA);
    }

    @Override
    public void akcja(){

        for(int i = 0; i < PROBY; i++){

            rozsiej();

        }

    }

    @Override
    public String toString() {
        return "mlecz";
    }

    @Override
    public Color rysowanie() {
        return Color.YELLOW;
    }

    @Override
    public Mlecz kopia() {
        return new Mlecz(polozenie);
    }
}