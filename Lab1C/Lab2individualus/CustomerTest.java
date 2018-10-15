/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2individualus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;
import studijosKTU.*;

public class CustomerTest{
    ListKTUx<Customer> testers = new ListKTUx<>(new Customer());

    void methodChooser(){
        checkIndividualCustomers();
        formCustomerList();
        viewList();
        updateList();
        checkCustomers();
        checkSorting();
    }

    void checkIndividualCustomers() {
        Customer a1 = new Customer("Jonas","Jonaitis",1997,170);
        Customer a2 = new Customer("Antanas","Jonatis",1992,166);
        Customer a3 = new Customer("Rokas","Rokaitis",1996,180);
        Customer a4 = new Customer();
        Customer a5 = new Customer();
        Customer a6 = new Customer();
        a4.parse("Mikas Jonaitis 1992 175");
        a5.parse("Eugenijus Ostapenko 1986 195");
        a6.parse("Zilvinas   Zvagulis  1995 185,3");

        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun("Average height between first 3 customers: "+
                (a1.getHeight()+a2.getHeight()+a3.getHeight())/3);
        Ks.oun(a4);
        Ks.oun(a5);
        Ks.oun(a6);
        //could have added new parameter to Soldier class named age
        Ks.oun("Average age between next 3 customers " + (((LocalDate.now().getYear() - a4.getBirthYear()) + (LocalDate.now().getYear() - a5.getBirthYear()) + (LocalDate.now().getYear() - a6.getBirthYear())))/3
                );
    }
    void formCustomerList() {
        Customer a1 = new Customer("Jonas","Lietuvis",1997,170);
        Customer a2 = new Customer("Antanas","Lietuvis",1992,166);
        Customer a3 = new Customer("Justas","Antanaitis",1996,180);
        testers.add(a1);
        testers.add(a2);
        testers.add(a3);
        testers.println("First 3 customers");
        testers.add("Maxas Piktas 1992 175");
        testers.add("Slavka Piktas 1986 195");
        testers.add("Jhon   Maiden  1995   185,3");

        testers.println("All 6 customers");
        testers.forEach(System.out::println);
        Ks.oun("Average height between first 3 customers: "+
                (testers.get(0).getHeight()+testers.get(1).getHeight()+
                 testers.get(2).getHeight())/3);


   }
    void viewList(){
        int sk=0;
        for (Customer a: testers){
            if (a.getName().compareTo("Antanas")==0)
                sk++;
        }
        Ks.oun("Number of customers named Antanas "+sk);
    }
    void updateList(){
        testers.add("Laurynas Piktas  1995  189,0");
        testers.add("Karolis Antanaitis   1998  180,0");
        testers.add("Antanas Pavarde  1999  177,0");
        testers.add("Antanas Tomaitis      1996  167,0");
        testers.println("List of tested Customers");
        testers.save("ban.txt");
    }
    void checkCustomers(){
        CustomerSelection aCustomer = new CustomerSelection();
        
        aCustomer.allCustomers.load("ban.txt");
        aCustomer.allCustomers.println("Bandomasis rinkinys");

        testers = aCustomer.selectYoungest(2001);

        testers = aCustomer.selectByHeight(175, 200);
        testers.println("Ūgis tarp 175 cm ir 200");

        testers = aCustomer.tallestCustomers();
        testers.println("Auksčiausi klientai");

        testers = aCustomer.selectByLastName("Piktas");
        testers.println("Turi būti tik Pikti");

        int sk=0;
        for (Customer a: testers){
                sk++;    // testuojame ciklo veikimą
        }
        Ks.oun("Lietuvių Antanų kiekis = "+sk);
    }
    void checkSorting(){
        CustomerSelection aps = new CustomerSelection();

        aps.allCustomers.load("ban.txt");
        Ks.oun("========"+aps.allCustomers.get(0));
        aps.allCustomers.println("Bandomasis rinkinys");
        aps.allCustomers.sortBuble(Customer.byHeight);
        aps.allCustomers.println("Rūšiavimas pagal aukštį");
        aps.allCustomers.sortBuble(Customer.byYearsAndHeight);
        aps.allCustomers.println("Rūšiavimas pagal Metus ir Aukštį");

        aps.allCustomers.sortBuble();
        aps.allCustomers.println("Rūšiavimas pagal compareTo - Aukštį");
    }


    public static void main(String... args) {
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new CustomerTest().methodChooser();
    }
}