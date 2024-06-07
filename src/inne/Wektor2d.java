package inne;

public class Wektor2d {

    public Wektor2d(int y, int x){

        this.y = y;
        this.x = x;

    }

    public int getY(){

        return y;

    }

    public int getX(){

        return x;

    }

    public boolean equals(Wektor2d v){

        return y == v.y && x == v.x;

    }

    public Wektor2d dodaj(Wektor2d v){

        return new Wektor2d(v.y + y, v.x + x);

    }

    public void dodajEq(Wektor2d v){

        y += v.y;
        x += v.x;

    }

    public boolean pozaGranicami(int wysokosc, int szerokosc) {

        return y < 0 || x < 0 || y >= wysokosc || x >= szerokosc;

    }


    private int y, x;

}