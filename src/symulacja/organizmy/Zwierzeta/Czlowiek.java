package symulacja.organizmy.Zwierzeta;
import symulacja.organizmy.Organizm;
import symulacja.organizmy.Zwierze;
import inne.*;
import java.awt.*;

public class Czlowiek extends Zwierze {

    public static final int SILA = 5;
    public static final int INICJATYWA = 4;
    public static final int SPECJALNY_TURY = 5;
    public static final int SPECJALNY_MNIEJ = 2;
    public static final double P_MNIEJ = 0.5;


    public Czlowiek(Wektor2d polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }
    @Override
    public boolean niesmiertelny(){
        if(turySpecjalne > 0) return true;
        else return false;
    }

    @Override
    public void akcja(){

        int zasieg = 1;



         if(turySpecjalne>0) turySpecjalne--;




        swiat.getDziennik().wpisz("Pozostale tury specjalne: " + turySpecjalne);



        switch(swiat.popRuch()){

            case GORA:
                zmienPolozenie(new Wektor2d(zasieg * -1, 0));
                break;

            case DOL:
                zmienPolozenie(new Wektor2d(zasieg, 0));
                break;

            case PRAWO:
                zmienPolozenie(new Wektor2d(0, zasieg));
                break;

            case LEWO:
                zmienPolozenie(new Wektor2d(0, -1 * zasieg));
                break;

            case SPECJALNY:

                if(turySpecjalne == 0){
                    swiat.getDziennik().wpisz("Aktywowano specjalna umiejetnosc");
                    turySpecjalne = SPECJALNY_TURY;

                }

                break;

        }

    }

    public void setTurySpecjalne(int turySpecjalne){

        this.turySpecjalne = turySpecjalne;

    }


    @Override
    public String toString() {
        return "czlowiek";
    }

    @Override
    public Color rysowanie() {
        return new Color(255,253,150);
    }


    public int getTurySpecjalne() {

        return turySpecjalne;

    }

    @Override
    public Organizm kopia() {
        return new Czlowiek(polozenie);
    }

    private int turySpecjalne = 0;

}