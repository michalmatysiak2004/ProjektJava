package symulacja.organizmy;

import symulacja.Swiat;

import inne.*;
import static java.lang.Math.random;

    abstract public class Zwierze extends Organizm {


        public Zwierze(Wektor2d polozenie, int sila, int inicjatywa) {

            super(polozenie, sila, inicjatywa);

        }


        @Override
        public void akcja() {

            losowyRuch(1);
        }

        @Override
        public void kolizja() {

            Organizm drugi = swiat.getKolidujacy(this);
            if (drugi == null) return;

            if (drugi.toString().equals(toString())) {

                rozmnozSie((Zwierze) drugi);

            } else {

                walcz(drugi);

            }

        }


        @Override
        public void nowaTura() {

            rozmnozylSie = false;

        }


        protected void losowyRuch(int zasieg) {

            if (czyMaDobryWech() && wszyscySasiedziSilniejsi()) {
                swiat.getDziennik().wpisz(toString() + " nie rusza sie na polu [ " +
                        this.polozenie.getX() + ", " + this.polozenie.getY() + " ]");
                return;

            }

            int[] koordynaty = {-1 * zasieg, 0, zasieg};

            Wektor2d przemieszczenie = new Wektor2d(0, 0);
            Wektor2d wczesniejsze = new Wektor2d(polozenie.getY(), polozenie.getX());

            do {

                int randX = koordynaty[(int) (random() * 3)];
                int randY = koordynaty[(int) (random() * 3)];

                przemieszczenie = new Wektor2d(randY, randX);

                zmienPolozenie(przemieszczenie);

            } while (wczesniejsze.equals(polozenie) ||
                    (czyMaDobryWech() &&
                            swiat.getKolidujacy(this) != null &&
                            swiat.getKolidujacy(this).getSila() > getSila()));

        }

        protected void zmienPolozenie(Wektor2d przemieszczenie) {

            if (swiat.getTyp() == Swiat.Typ.Hex && (przemieszczenie.equals(new Wektor2d(-1, -1)) || przemieszczenie.equals(new Wektor2d(1, -1)))) {

                return;

            }


            if (!polozenie.dodaj(przemieszczenie).pozaGranicami(swiat.getWysokosc(), swiat.getSzerokosc())) {

                wczesniejszePolozenie = new Wektor2d(polozenie.getY(), polozenie.getX());
                polozenie.dodajEq(przemieszczenie);

            }


        }

        private boolean rozmnozylSie = false;
        private Wektor2d wczesniejszePolozenie;
        public boolean niesmiertelny() {
            return false;
        }
        private void walcz(Organizm drugi) {
            if(niesmiertelny() == true){
                swiat.getDziennik().wpisz("Czlowiek jest niesmiertelny");
                this.cofnijSie();
                return;
            }
            if ( drugi.niesmiertelny()){
                swiat.getDziennik().wpisz("Czlowiek  jest niesmiertelny");
                return;
            }



            if (getSila() < drugi.getSila()) {

                if (czyOdbilAtak(drugi)) {

                    cofnijSie();
                    return;

                }

                swiat.getDziennik().wpisz(drugi.toString() + " zabija " + toString() + " na polu [ " +
                        this.polozenie.getX() + ", " + this.polozenie.getY() + " ]");

                zabij();
                dodajModyfikator(drugi);

            } else {

                if (drugi.czyOdbilAtak(this)) {

                    cofnijSie();
                    return;

                }

                swiat.getDziennik().wpisz(toString() + " zabija " + drugi.toString() + " na polu [ " +
                        this.polozenie.getX() + ", " + this.polozenie.getY() + " ]");

                drugi.zabij();
                drugi.dodajModyfikator(this);

            }


        }

        private void rozmnozSie(Zwierze drugi) {

            if (drugi.getWiek() == 0) {

                return;

            }


            Organizm org = kopia();

            cofnijSie();

            Wektor2d miejsceNarodzin = swiat.getWolnePoleObok(drugi.getPolozenie());
            if (miejsceNarodzin.equals(drugi.getPolozenie()) || rozmnozylSie || drugi.rozmnozylSie) {

                return;

            }

            org.setPolozenie(miejsceNarodzin);
            org.setWiek(-1);

            swiat.addOrganizm(org);
            swiat.getDziennik().wpisz(toString() + " romznaza sie   na polu  [ "
                    + this.polozenie.getX() + ", " + this.polozenie.getY() + " ]");
            rozmnozylSie = true;
            drugi.rozmnozylSie = true;


        }

        private void cofnijSie() {

            setPolozenie(wczesniejszePolozenie);


        }

        private boolean wszyscySasiedziSilniejsi() {

            for (int y = -1; y <= 1; y++) {

                for (int x = -1; x <= 1; x++) {

                    Wektor2d pol = new Wektor2d(y, x);

                    Organizm org = swiat.getOrganizmNaPozycji(polozenie.dodaj(pol));

                    if (org != this && (org == null || org.getSila() <= sila)) {

                        return false;

                    }

                }

            }

            return true;

        }





    }