package symulacja.organizmy.Zwierzeta;

import symulacja.organizmy.Organizm;
import symulacja.organizmy.Zwierze;
import inne.*;
import java.awt.*;

public class Lis extends Zwierze {

    public static final int SILA = 3;
    public static final int INICJATYWA = 7;


    public Lis(Wektor2d polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public String toString() {
        return "lis";
    }

    @Override
    public Color rysowanie() {
        return Color.ORANGE;
    }

    @Override
    public Organizm kopia() {
        return new Lis(polozenie);
    }


    protected boolean czyMaDobryWech(){

        return true;

    }

}