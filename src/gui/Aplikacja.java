package gui;

import gui.Wizualizacja;
import symulacja.Swiat;
import inne.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Aplikacja extends JFrame {


    public static final String TYTUL = "Michal Matysiak 198395";
    public static final int DOMYSLNA_WYSOKOSC = 1700;
    public static final int DOMYSLNA_SZEROKOSC = 2000;
    private JTextArea dziennikTextArea;
    public static final int wymiary[] = new int[2];
    public Aplikacja(int wysokosc, int szerokosc){

        setSize(szerokosc,wysokosc);
        setMinimumSize(new Dimension(szerokosc,wysokosc));

        revalidate();

        setTitle(TYTUL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int[] wymiary = zapytajOWymiarySwiata();
        wizualizacja = new Wizualizacja(DOMYSLNA_WYSOKOSC * 8/10, Swiat.Bazowy(Swiat.Typ.Kartezjanski,wymiary[0],wymiary[1]));
        menedzerPlikow = new MenedzerPlikow();

        inicjujMenuGorne();
        inicjujPanelGlowny();

    }

    private int[] zapytajOWymiarySwiata() {
        JTextField wysokoscField = new JTextField(5);
        JTextField szerokoscField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(2, 2));
        myPanel.add(new JLabel("Wysokość:"));
        myPanel.add(wysokoscField);
        myPanel.add(new JLabel("Szerokość:"));
        myPanel.add(szerokoscField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Podaj wymiary świata", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int wysokosc = Integer.parseInt(wysokoscField.getText());
                int szerokosc = Integer.parseInt(szerokoscField.getText());
                if (wysokosc > 0 && szerokosc > 0) {
                    return new int[]{wysokosc, szerokosc};
                } else {
                    JOptionPane.showMessageDialog(null, "Wymiary muszą być dodatnie!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Proszę podać poprawne liczby!");
            }
        }
        return null;
    }
    public void start(){

        setVisible(true);


    }


    private final Wizualizacja wizualizacja;
    private final MenedzerPlikow menedzerPlikow;

    private JButton turaButton;
    private JButton dziennikButton;
    private JMenuItem menuItemBazowy;

    private JMenuItem menuItemBazowyHex;

    private JMenuItem menuItemWczytaj;
    private JMenuItem menuItemZapisz;


    private void inicjujMenuGorne(){

        JMenuBar menuBar = new JMenuBar();

        JMenu menuNowy = new JMenu("Nowy");
        JMenu menuPlik = new JMenu("Plik");

        inicjujGuzikiMenuGornego();

        menuNowy.add(menuItemBazowy);
        menuNowy.add(menuItemBazowyHex);

        menuPlik.add(menuItemWczytaj);
        menuPlik.add(menuItemZapisz);

        menuBar.add(menuNowy);
        menuBar.add(menuPlik);

        setJMenuBar(menuBar);

    }

    private void inicjujGuzikiMenuGornego(){

        menuItemBazowy = new JMenuItem("bazowy_kartezjanski");
        menuItemBazowyHex = new JMenuItem("bazowy_hex");
        menuItemWczytaj = new JMenuItem("wczytaj");
        menuItemZapisz = new JMenuItem("zapisz");

        menuItemBazowy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wizualizacja.setSwiat(Swiat.Bazowy(Swiat.Typ.Kartezjanski,wymiary[0],wymiary[1]));

            }

        });


        menuItemBazowyHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wizualizacja.setSwiat(Swiat.Bazowy(Swiat.Typ.Hex,wymiary[0],wymiary[1]));

            }
        });

        menuItemWczytaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("wybierz plik do wczytania");

                int rv = fc.showOpenDialog(null);

                if(rv == JFileChooser.APPROVE_OPTION){

                    File plik = fc.getSelectedFile();
                    Swiat sw = menedzerPlikow.wczytaj(plik);

                    if(sw != null){

                        wizualizacja.setSwiat(sw);

                    }

                }


            }

        });

        menuItemZapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("wybierz plik do zapisu");

                int rv = fc.showOpenDialog(null);

                if(rv == JFileChooser.APPROVE_OPTION){

                    File plik = fc.getSelectedFile();
                    menedzerPlikow.zapisz(wizualizacja.getSwiat(), plik);

                }

            }

        });


    }

    private void inicjujPanelGlowny() {
        inicjujGuziki();

        JPanel panelGuziki = new JPanel();
        panelGuziki.setLayout(new GridLayout(1, 2));
        panelGuziki.add(turaButton);

        dziennikTextArea = new JTextArea();
        dziennikTextArea.setEditable(false);
        JScrollPane dziennikScrollPane = new JScrollPane(dziennikTextArea);

        JLabel labelDziennik = new JLabel("Dziennik:");
        labelDziennik.setHorizontalAlignment(SwingConstants.CENTER); // Wyśrodkowanie napisu

        JPanel panelDziennik = new JPanel(new BorderLayout());
        panelDziennik.add(labelDziennik, BorderLayout.NORTH);
        panelDziennik.add(dziennikScrollPane, BorderLayout.CENTER);

        JSplitPane mainSplitPane = new JSplitPane();
        mainSplitPane.setEnabled(false);
        mainSplitPane.setDividerLocation(DOMYSLNA_SZEROKOSC * 3 / 4);
        mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(DOMYSLNA_WYSOKOSC * 8 / 10);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        splitPane.addMouseListener(wizualizacja);
        splitPane.setTopComponent(wizualizacja);
        splitPane.setBottomComponent(panelGuziki);

        mainSplitPane.setLeftComponent(splitPane);
        mainSplitPane.setRightComponent(panelDziennik); // Zmieniono dziennikScrollPane na panelDziennik

        add(mainSplitPane);
    }

    private void inicjujGuziki(){

        turaButton = new JButton("nastepna tura");
        dziennikButton = new JButton("dziennik");


        turaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dziennikTextArea.setText(wizualizacja.getDziennik().wypisz());
                wizualizacja.nastepnaTura();

            }


        });

        dziennikButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                dziennikTextArea.setText(wizualizacja.getDziennik().wypisz());
            }

        });



    }


}
