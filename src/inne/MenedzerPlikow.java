package inne;

import symulacja.organizmy.Organizm;
import symulacja.Swiat;
import symulacja.organizmy.Zwierzeta.*;
import symulacja.organizmy.rosliny.*;

import javax.swing.*;
import java.io.*;

public class MenedzerPlikow {


    public void zapisz(Swiat swiat, File plik){

        try {

            FileWriter out = new FileWriter(plik);



            String typStr = swiat.getTyp().equals(Swiat.Typ.Hex) ? "HEX" : "KART";



            out.write(swiat.getNrTury() + " " + swiat.getWysokosc() + " " + swiat.getSzerokosc()  + " " + typStr+ "\n");

            for(Organizm org : swiat.getOrganizmy()){

                out.write(org.toString() + " " +
                        org.getWiek() + " " +
                        org.getPolozenie().getY() + " " +
                        org.getPolozenie().getX());

                if(org instanceof Czlowiek){

                    out.write(" " + ((Czlowiek) org).getTurySpecjalne());

                }

                out.write("\n");

            }

            out.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"błąd zapisu","błąd",JOptionPane.ERROR_MESSAGE);

        }


    }

    public Swiat wczytaj(File plik){

        try {

            FileInputStream fs = new FileInputStream(plik);
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader in = new BufferedReader(new InputStreamReader(ds));

            String[] turaWysSzer = in.readLine().split(" ");

            Swiat.Typ typ = Swiat.Typ.Kartezjanski;

            if(turaWysSzer[3].equals("HEX")){

                typ = Swiat.Typ.Hex;

            }



            Swiat sw = new Swiat(Integer.parseInt(turaWysSzer[1]),Integer.parseInt(turaWysSzer[2]), typ);
            sw.setNrTury(Integer.parseInt(turaWysSzer[0]));


            String line;

            while((line = in.readLine()) != null){

                Organizm org = wczytajOrganizm(line);

                if(org != null){

                    sw.addOrganizm(org);

                }


            }

            ds.close();

            return sw;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"błąd wczytywania/składniowy","błąd",JOptionPane.ERROR_MESSAGE);

        }

        return null;

    }

    private Organizm wczytajOrganizm(String line) throws Exception {

        String[] atryb = line.split(" ");

        Organizm nowy = alokujPoNazwie(atryb[0]);

        if(nowy != null){

            nowy.setWiek(Integer.parseInt(atryb[1]));
            nowy.setPolozenie(new Wektor2d(Integer.parseInt(atryb[2]), Integer.parseInt(atryb[3])));

            if(nowy instanceof Czlowiek){

                ((Czlowiek) nowy).setTurySpecjalne(Integer.parseInt(atryb[4]));

            }

            return nowy;

        }

        return null;

    }

    private Organizm alokujPoNazwie(String nazwa){

        Wektor2d p0 = new Wektor2d(0,0);

        Organizm[] organizmy = {

                new Czlowiek(p0),
               new Wilk(p0),
                new Owca(p0),
                new Lis(p0),
                new Zolw(p0),
                new Antylopa(p0),
                new Trawa(p0),
                new Mlecz(p0),
                new Guarana(p0),
                new WilczeJagody(p0),
                new BarszczSosnowskiego(p0),

        };

        for(Organizm org : organizmy){
            System.out.println(org.toString());
            if(org.toString().equals(nazwa)){
                System.out.println("Znaleziono organizm: " + nazwa);
                return org.kopia();

            }

        }

        return null;
    }


}
