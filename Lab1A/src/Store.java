
package lab1.a;

import java.util.*;


/*
U1-11. Juvelyrikos parduotuvė. Turite UAB „Blizgučiai“ parduodamų žiedų sąrašą. Duomenų
faile pateikta informacija apie žiedus: gamintojas, pavadinimas, metalas, svoris, dydis, praba, kaina.
 Raskite brangiausią žiedą, ekrane atspausdinkite jo pavadinimą, metalą, skersmenį, svorį ir
prabą.
  Raskite, kiek žiedų yra aukščiausios prabos, rezultatą atspausdinkite ekrane.
kiekį.
 Sudarykite balto aukso žiedų sąrašą, į failą „BA300.csv“ įrašykite
visus duomenis apie šiuos žiedus.
 Raskite žiedus, kurių kaina iki 500 eurų, į failą įrašykite visus duomenis apie šiuos
žiedus.
 */
public class Store {
    public String Name;
    private List<Ring> rings;

    private List<String> input = Arrays.asList(new String[]{
            "UAB_Sidabrita  Pirmasis            Sidabras    9 12.0 600 102",
            "UAB_Auksita    Antras              Sidabras    8 13.0 750 109",
            "UAB_Ziedai     Trecias             Platina     7 14.5 750 1000",
            "UAB_Pakabuciai Ketvirtas           Bronza      6 15.0 800 4000",
    });
    public Store(String Name) {
        this.Name = Name;
        rings = new ArrayList<>();
    }
    void print(List<Ring>rings){
        for(Ring d : rings)
            System.out.println(d);
    }

    private void trial1(){
        System.out.println("***** Parduotuvė: " + Name + " vienetiniai žiedai");
        Ring si1 = new Ring("UAB_Sidabrita","Pirmasis Pliues","Auksas",7,10.0,150,800);
        Ring si2 = new Ring("UAB_Auksita","Antras Plius","Sidabras",8,10.0,500,630);
        System.out.println(si1);
        System.out.println(si2);

    }
    private void trial2(){
        rings.add(new Ring("UAB_Sidabrita","Pirmasis","Baltas auksas",2,10.0,650,160));
        rings.add(new Ring("UAB_Ziedai","Pirmasis2","Sidabras",3,11.0,800,180));
        rings.add(new Ring("UAB_Auksita","Grazus","Platina",5,12.0,400,100));
        rings.add(new Ring("UAB_Pakabuciai","NeGrazus","Auksas",4,13.0,999,200));
        System.out.println("***** Parduotuvė: " + Name + " iš konstruktoriaus");
        print(rings);
    }
    private void  trial3(){
        for(String data:input)
            rings.add(Ring.parse(data));
        System.out.println("***** Parduotuvė: " + Name + " po papildymo iš eilučių");
        print(rings);
    }
    public Ring mostExpensiveRing(){
        Integer max = 0;
        Ring ringMax = null;
        for(Ring ri : rings)
            if(ri.price > max){
                max = ri.price;
                ringMax = ri;
            }
            return ringMax;
    }
    public Integer numberOfHighestHallmarks(){
        Integer max = 0;
        Integer hallmarkCount = 0;
        for(Ring ri : rings) {
            if (ri.hallmark > max) {
                max = ri.hallmark;
            }
        }
        for(Ring ri : rings) {
            if (ri.hallmark.compareTo(max) == 0) {
                hallmarkCount++;
            }
        }
        return hallmarkCount;
    }

    
    public List<Ring> filterRingsByPrice(int highestPrice){
        List<Ring> filteredRings = new ArrayList<>();
        for(Ring ri : rings)
            if(ri.price<highestPrice)
                filteredRings.add(ri);
        return filteredRings;
    }
    
    public void whiteGoldMetal(List<String> listOfWhiteGoldMetal){
        String variable1 = "";
        String variable2 = "";
        for(Ring ri : rings){
            if(ri.metal =="Baltas auksas")
            {
                variable1 = Integer.toString(ri.weight);
                variable2 = Integer.toString(ri.price);

                Collections.addAll(listOfWhiteGoldMetal,ri.manufacturer,ri.name,ri.metal,variable1,variable2);
            }

                
                
        }


    }
    public void reportShopRings(){
        System.out.println("***** Parduotuvės " + Name + " ATASKAITA");
        System.out.println("----- Brangiausias  žiedas yra:");
        System.out.println(mostExpensiveRing());
        System.out.println("----- Aukščiausios prabos žiedų kiekis yra:");
        System.out.println(numberOfHighestHallmarks());
        List<String> listOfWhiteGoldMetal = new ArrayList<>();
        whiteGoldMetal(listOfWhiteGoldMetal);
        System.out.println("----- Balto aukso žiedai:");
        for(String metalList : listOfWhiteGoldMetal)
            System.out.println(metalList);
        int maximumPrice = 500;
        
        System.out.println("-----Žiedai, kurių dydžiai yra tarp " + maximumPrice + " eurų");
        print(filterRingsByPrice(maximumPrice));

    }


    public static void main(String... args)
    {
        Store shop1 = new Store("UAB Blizgučiai");
        shop1.trial1();
        shop1.trial2();
        shop1.trial3();
        shop1.reportShopRings();
        System.out.println("Bandymai atlikti");
    }
}
