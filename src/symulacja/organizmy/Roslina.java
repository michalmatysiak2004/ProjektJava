package symulacja.organizmy;

import symulacja.organizmy.Organizm;

import static java.lang.Math.random;

public abstract class Roslina extends Organizm {

    static final int DOMYSLNA_INICJATYWA = 0;
    static final double P_ROZSIANIA = 0.05;


    public Roslina(Wektor2d polozenie, int sila) {
        super(polozenie, sila, DOMYSLNA_INICJATYWA);
    }

    public boolean niesmiertelny(){
        return false;
    }

    @Override
    public void akcja() {

        rozsiej();

    }

    @Override
    public void kolizja() {

    }



    @Override
    public void nowaTura() {

    }

    protected void rozsiej(){

        if(random() < P_ROZSIANIA){

            Wektor2d pNowy = swiat.getWolnePoleObok(polozenie);

            if(pNowy == polozenie) return;

            Organizm org = kopia();
            org.setWiek(0);

            org.setPolozenie(pNowy);

            swiat.addOrganizm(org);


        }


    }
}