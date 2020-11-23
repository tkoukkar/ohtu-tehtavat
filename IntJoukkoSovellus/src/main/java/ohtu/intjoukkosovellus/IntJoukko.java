
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetin on oltava positiivinen.");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Uuden taulukon on oltava vanhaa suurempi.");
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            
            if (alkioidenLkm % ljono.length == 0) {
                int[] taulukkoUusi = new int[alkioidenLkm + kasvatuskoko];
                
                kopioiTaulukko(ljono, taulukkoUusi);
                
                ljono = taulukkoUusi;
            }
            
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean poistoTehty = false;
        
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i]== luku) {
                poistoTehty = true;
                alkioidenLkm--;
            }
            
            if (poistoTehty) {
                if (i == alkioidenLkm) {
                    ljono[i] = 0;
                } else {
                    ljono[i] = ljono[i + 1];
                }
            }
        }
        
        return poistoTehty;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String palautettava = "{";
        
        if (alkioidenLkm > 0) {
            palautettava += ljono[0];
            
            for (int i = 1; i < alkioidenLkm; i++) {
                palautettava += ", ";
                palautettava += ljono[i];
            }
        }
        
        palautettava += "}";
        return palautettava;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                y.lisaa(aTaulu[i]);
            }
        }
        
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = a;
        IntJoukko poistettavat = leikkaus(a, b);
        
        int[] p = poistettavat.toIntArray();
        
        for (int i = 0; i < p.length; i++) {
            z.poista(p[i]);
        }
        
        return z;
    }
}