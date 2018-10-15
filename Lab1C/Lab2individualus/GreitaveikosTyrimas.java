/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2individualus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;
/*
 */
public class GreitaveikosTyrimas {
    Customer[] customerBase1;
    ArrayList<Integer> aList = new ArrayList();
    LinkedList<Integer> lList = new LinkedList();
    ListKTU<Customer> aSeries = new ListKTU<>();
    ListKTU<Customer> bSeries = new ListKTU<>();
    Random ag = new Random();  // atsitiktinių generatorius
    //int[] tiriamiKiekiai = {2_000, 4_000, 8_000, 16_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
    static int[] tiriamiKiekiai = {10_000, 20_000, 40_000, 80_000};
    void generateCustomers(int kiekis){
       String[][] am = { // galimų automobilių markių ir jų modelių masyvas
          {"Piktas", "Antanas", "Juozas", "Rimas", "Dominykas"},
          {"Albinaitis", "Hansas", "Erichas", "Adolfas", "Josephas", "Albert"},
          {"Paques", "Milanas", "Maksas"},
          {"Kalanta", "Igoris", "Viktoras", "Leonidas"},
          {"Donelaitis", "Abdulas", "Alkan", "Saydas", "Imanas"},
          {"Pajacas", "Zbignevas", "Albinas", "Vladas"}
       };
        customerBase1= new Customer[kiekis];
        ag.setSeed(2017);
        for(int i=0;i<kiekis;i++){
            int ta = ag.nextInt(am.length);        // tautybės indeksas  0..
            int vi = ag.nextInt(am[ta].length-1)+1;// modelio indeksas 1..
            customerBase1[i]= new Customer(am[ta][0], am[ta][vi],
                1970 +         // metai tarp 1994 ir 2013
                1 + ag.nextInt(10),      // rida tarp 6000 ir 228000
                166 + ag.nextDouble()*2); // kaina tarp 1000 ir 351_000
        }
        Collections.shuffle(Arrays.asList(customerBase1));
        aSeries.clear();
        for(Customer a: customerBase1) aSeries.add(a);
//        for(Customer b: customerBase1) bSeries.add(b);
//        bSeries.addAll(5, aSeries);
//        Object[] a = aSeries.toArray();
//        bSeries.remove(aSeries.get(2));
//        Object[] b = bSeries.toArray();
//        
    }
    
    void generateIntegeratArray(int kiekis){
        ag.setSeed(2017);
        for(int i = 0; i < kiekis; i++){
            aList.add((ag.nextInt()));
            lList.add(ag.nextInt());
        }
        
    }
    
    void paprastasTyrimas(int elementųKiekis){
// Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        generateCustomers(elementųKiekis);
        ListKTU<Customer> aSeries2 = aSeries.clone();
        ListKTU<Customer> aSeries3 = aSeries.clone();
        ListKTU<Customer> aSeries4 = aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sortSystem();
        long t3=System.nanoTime();
        aSeries2.sortSystem(Customer.byHeight);
        long t4=System.nanoTime();
        aSeries3.sortBuble();
        long t5=System.nanoTime();
        aSeries4.sortBuble(Customer.byHeight);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }
// sekančio tyrimo metu gaunama normalizuoti įvertinimai su klase TimeKeeper
    void sisteminisTyrimas(){
    // Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
           generateCustomers(kiekis);
           ListKTU<Customer> aSeries2 = aSeries.clone();
           ListKTU<Customer> aSeries3 = aSeries.clone();
           ListKTU<Customer> aSeries4 = aSeries.clone();

    //  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sortSystem();
            tk.finish("SysBeCom");
            aSeries2.sortSystem(Customer.byHeight);
            tk.finish("SysSuCom");
            aSeries3.sortBuble();
            tk.finish("BurBeCom");
            aSeries4.sortBuble(Customer.byHeight);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
    void sisteminisTyrimasKelimu(){
        double skaicius;
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for(int kiekis : tiriamiKiekiai)
        {
            generateCustomers(kiekis);
        tk.start();
        for(Customer s : aSeries)
            skaicius = s.getHeight() * s.getHeight();
        tk.finish("x*1.0/3");
        for(Customer s : aSeries)
            Math.pow(s.getHeight(),1.0/3);
        tk.finish("math.pow(x,1.0/3)");
        tk.seriesFinish();
        }
        
    }
    void sisteminisTyrimasArrayirLinked(){
        
         Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for(int kiekis : tiriamiKiekiai){
           // generateSoldiers(kiekis);
        tk.start();
        for(int i = 0; i < kiekis;i++)
            aList.add((aList.size() / 2), i);
        tk.finish("ArrayList<Integer> add");
         for(int i = 0; i < kiekis;i++)
            lList.add((lList.size() / 2), i);
         tk.finish("LinkedList<Integer> add");
        tk.seriesFinish();
        }
    }
    void paprastasTyrimasKėlimas(int elementųKiekis){
        // Paruošiamoji tyrimo dalis
        double skaicius;
        long t0 = System.nanoTime();
        generateCustomers(elementųKiekis);
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        for (Customer s : aSeries) {
            //Math.sqrt(s.getHeight());
            skaicius = s.getHeight() * 1.0/3;
        }
        long t3 = System.nanoTime();
        for (Customer s : aSeries) {
            Math.pow(s.getHeight(),1.0/3);
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);
    }
    void paprastasTyrimasArrayirList(int elementųKiekis){
       // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        //generateIntegeratArray(elementųKiekis);
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        for(int i=0;i<elementųKiekis;i++)
        {
        aList.add((aList.size() / 2),i);
        }
        long t3 = System.nanoTime();
        for(int i=0;i<elementųKiekis;i++)
        {
        lList.add((lList.size() / 2),i);
        }
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9); 
    }
    
    
    void tyrimoPasirinkimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateCustomers(20);
        for(Customer a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n: tiriamiKiekiai) 
            paprastasTyrimas(n);
        // sekančio tyrimo metu gaunama normalizuoti įvertinimai
        sisteminisTyrimas();
        
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - skaicius = s.getHeight()*s.getHeight");
        Ks.oun("4 - Math.pow(s.getHeight(),1.0/3)");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : tiriamiKiekiai) {
            paprastasTyrimasKėlimas(n);
        }
        sisteminisTyrimasKelimu();
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - ArrayList<Integer> add");
        Ks.oun("4 - LinkedList<Integer> add");
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : tiriamiKiekiai) {
            paprastasTyrimasArrayirList(n);
        }
        sisteminisTyrimasArrayirLinked();
    }
   public static void main(String[] args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new GreitaveikosTyrimas().tyrimoPasirinkimas();
   }    
}