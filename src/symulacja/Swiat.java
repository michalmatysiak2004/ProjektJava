package symulacja;

import symulacja.organizmy.Zwierzeta.*;
import symulacja.organizmy.Organizm;
import symulacja.organizmy.rosliny.*;
import inne.*;
import java.util.Vector;

public class Swiat {


    public int getWysokosc() {

        return wysokosc;

    }

    public int getSzerokosc() {

        return szerokosc;

    }

    public Organizm getKolidujacy(Organizm org) {

        for(Organizm organizm : organizmy){

            if(org.getPolozenie().equals(organizm.getPolozenie())
                    && org != organizm
                    && org.isZywy()){

                return organizm;

            }

        }

        return null;

    }

    public Wektor2d getWolnePoleObok(Wektor2d p) {

        for(int dy = -1; dy <= 1; dy++){

            for(int dx = -1; dx <= 1; dx++){

                Wektor2d sprawdzanyPunkt = new Wektor2d(p.getY() + dy, p.getX() + dx);

                if(typ == Typ.Hex && ((dy == -1 && dx == -1) || (dy == 1 && dx == -1))){

                    continue;

                }


                if(!sprawdzanyPunkt.equals(p)
                        && getOrganizmNaPozycji(sprawdzanyPunkt) == null
                        && !sprawdzanyPunkt.pozaGranicami(wysokosc,szerokosc)){

                    return sprawdzanyPunkt;

                }


            }


        }
        return p;

    }

    public Dziennik getDziennik() {

        return dziennik;

    }

    public int getNrTury() {

        return nrTury;

    }

    public void setNrTury(int nrTury) {

        this.nrTury = nrTury;

    }

    public Typ getTyp() {
        return typ;
    }

    public enum Ruch {

        GORA,
        DOL,
        LEWO,
        PRAWO,
        SPECJALNY,
        STOJ

    }

    public enum Typ {

        Kartezjanski,
        Hex

    }


    public Swiat(int wysokosc, int szerokosc, Typ typ){

        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        organizmy = new Vector<>();
        dziennik = new Dziennik();

        this.typ = typ;

    }




    public void wykonajTure(){

        oglosNowaTure();
        nrTury++;
        ruchOrganizmow();
        pozbadzSieZwlok();

    }

    public void addOrganizm(Organizm organizm){

        organizm.setWiek(organizm.getWiek()+1);
        organizm.setSwiat(this);

        organizmy.add(organizm);

    }

    public Organizm getOrganizmNaPozycji(Wektor2d p){

        Organizm szukany = null;

        for(Organizm org : organizmy){

            if(org.getPolozenie().equals(p) && org.isZywy()){

                if(szukany == null || szukany.getSila() < org.getSila()){

                    szukany = org;

                }

            }

        }

        return szukany;

    }

    public void setRuch(Ruch ruch){

        this.ruch = ruch;

    }

    public Ruch popRuch(){

        Ruch obecny = ruch;
        ruch = Ruch.STOJ;

        return obecny;

    }

    public Ruch getRuch(){

        return ruch;

    }

    public Vector<Organizm> getOrganizmy() {

        return organizmy;

    }

    public static Wektor2d losujPozycje(int wysokoscswiata, int szerokoscswiata){

        int x = (int) (Math.random() * szerokoscswiata);
        int y = (int) (Math.random() * wysokoscswiata);

        Wektor2d pozycja =  new Wektor2d(y,x);
        if(pozycja.pozaGranicami(wysokoscswiata,szerokoscswiata)){
            return losujPozycje(wysokoscswiata,szerokoscswiata);
        }

        return pozycja;
    }
    public static Swiat Bazowy(Typ typ,int wysokoscswiata,int szerokoscswiata){


        Swiat swiat = new Swiat(
                wysokoscswiata,
                szerokoscswiata,
                typ);

        swiat.addOrganizm(new Czlowiek(new Wektor2d(0,0)));
        for(int i=0; i<0.2*wysokoscswiata*szerokoscswiata; i++){

            int choice = (int)(Math.random()*10);
            switch (choice){
                case 1:
                    swiat.addOrganizm(new Wilk(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 2:
                    swiat.addOrganizm(new Owca(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 3:
                    swiat.addOrganizm(new Lis(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 4:
                    swiat.addOrganizm(new Zolw(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 5:
                    swiat.addOrganizm(new Antylopa(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 6:
                    swiat.addOrganizm(new Trawa(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 7:
                    swiat.addOrganizm(new Mlecz(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 8:
                    swiat.addOrganizm(new Guarana(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 9:
                    swiat.addOrganizm(new WilczeJagody(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                case 10:
                    swiat.addOrganizm(new BarszczSosnowskiego(losujPozycje(wysokoscswiata,szerokoscswiata)));
                    break;
                default:

                    break;
            }

        }



        return swiat;
    }


    private final int wysokosc;
    private final int szerokosc;

    private int nrTury;
    private Ruch ruch = Ruch.STOJ;
    private Typ typ = Typ.Kartezjanski;

    private Dziennik dziennik;

    Vector<Organizm> organizmy;


    private void ruchOrganizmow(){

        organizmy.sort((Organizm o1, Organizm o2) -> {

            if(o1.getInicjatywa() == o2.getInicjatywa()){

                return o2.getWiek() - o1.getWiek();

            }
            return o2.getInicjatywa() - o1.getInicjatywa() ;
        });

        for(int i = 0; i < organizmy.size(); i++){

            Organizm organizm = organizmy.get(i);

            if(organizm.isZywy() ){

                organizm.akcja();
                organizm.kolizja();

            }

            organizm.starzejSie();

        }


    }


    private void pozbadzSieZwlok(){

        for(int i = 0; i < organizmy.size(); i++){

            if(!organizmy.get(i).isZywy()){

                organizmy.remove(i);
                pozbadzSieZwlok();
                break;

            }

        }

    }

    private void oglosNowaTure(){



        for(Organizm organizm : organizmy){

            organizm.nowaTura();

        }

    }

}