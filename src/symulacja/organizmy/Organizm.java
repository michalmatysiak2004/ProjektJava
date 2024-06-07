package symulacja.organizmy;

import symulacja.Swiat;

import java.awt.*;
abstract public class Organizm {


    public Wektor2d getPolozenie(){

        return polozenie;

    }

    public void setPolozenie(Wektor2d polozenie){

        this.polozenie = polozenie;

    }


    public int getInicjatywa(){

        return inicjatywa;

    }

    public int getSila(){

        return sila;

    }

    public void setSila(int sila){

        this.sila = sila;

    }

    public boolean isZywy(){

        return zywy;

    }

    public void zabij(){

        swiat.getDziennik().wpisz(toString() + " umiera");

        zywy = false;

    }

    public int getWiek(){

        return wiek;

    }

    public void dodajModyfikator(Organizm organizm) {


    }

    public boolean czyOdbilAtak(Organizm org){

        return false;
    }

    public boolean czyUciekl(){

        return false;

    }

    public boolean ucieczka(){

        if(czyUciekl()){

            Wektor2d nowePole = swiat.getWolnePoleObok(polozenie);

            if(nowePole == polozenie){

                return false;

            }

            setPolozenie(nowePole);
            return true;

        }

        return false;

    }


    public void starzejSie() {

        wiek++;

    }


    public void setWiek(int wiek) {

        this.wiek = wiek;

    }

    public void setSwiat(Swiat swiat) {

        this.swiat = swiat;

    }

    @Override
    abstract public String toString();

    public abstract void akcja();
    public abstract void kolizja();
    public abstract Color rysowanie();
    public abstract boolean niesmiertelny();

    public abstract void nowaTura();

    public abstract Organizm kopia();

    protected int sila;
    protected int inicjatywa;
    protected int wiek = 0;
    protected boolean zywy = true;

    protected Swiat swiat;

    protected Wektor2d polozenie;

    protected Organizm(Wektor2d polozenie, int sila, int inicjatywa){

        this.polozenie = polozenie;
        this.sila = sila;
        this.inicjatywa = inicjatywa;

    }

    protected boolean czyMaDobryWech() {

        return false;

    }






}