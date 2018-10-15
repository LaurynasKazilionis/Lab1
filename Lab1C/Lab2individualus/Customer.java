/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2individualus;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

public class Customer implements KTUable<Customer> {
    
    // bendri duomenys visiems klientams, kurie nori pasivazineti amerikietiskais kalneliais
    final static private int minimumAge = 15;
    final static private int currentYear  = LocalDate.now().getYear();
    final static private double minHeight =     165.1;

    // kiekvieno kliento individualūs duomenys
    private String name;
    private String lastName;
    private int birthYear;  
    private double height; 

    
    public Customer() {
    }
    public Customer(String name, String lastName,
                        int birthYear,double height){
        this.name = name;
        this.lastName = lastName;
        this.birthYear = birthYear;

        this.height = height;
    }
    public Customer(String dataString){
        this.parse(dataString);
    }
    @Override
    public Customer create(String dataString) {
        Customer a = new Customer();
        a.parse(dataString);
        return a;
    }
    @Override
    public final void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            name   = ed.next();
            lastName = ed.next();
            birthYear= ed.nextInt();
            setHeight(ed.nextDouble());
        } catch (InputMismatchException  e) {
            Ks.ern("Bad data format about Soldier-> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Missing data about Soldier -> " + dataString);
        }
    }
    @Override
    public String validate() {
        String errotType = "";
        if (LocalDate.now().getYear() - birthYear < minimumAge)
           errotType = "You can't come on this ride, you need to be atleast: " +
                   minimumAge + " years old";                  
        if(birthYear > currentYear){
            errotType = "Wrong date of birth";
        }
        if (height < minHeight )
            errotType += "Your height must be atleast :"+minHeight + " to ride this ride";
        return errotType;
    }
    @Override
    public String toString(){  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s  %7d %8.1f %s",
               name, lastName, birthYear, height, validate());
    };
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public int getBirthYear() {
        return birthYear;
    }

    public double getHeight() {
        return height;
    }
    // keisti bus galima tik ūgį - kiti parametrai pastovūs
    public void setHeight(double height) {
        this.height = height;
    }
    @Override
    public int compareTo(Customer a) { 
        // lyginame pagal ūgį
        double otherHeight = a.getHeight();
        if (height < otherHeight) return -1;
        if (height > otherHeight) return +1;
        return 0;
    }
    public final static Comparator<Customer> byNameAndlastName =
              new Comparator<Customer>() {
       @Override
       public int compare(Customer a1, Customer a2) {
          // pradžioje pagal vardus, o po to pagal tautybę
          int cmp = a1.getName().compareTo(a2.getName());
          if(cmp != 0) return cmp;
          return a1.getLastName().compareTo(a2.getLastName());
       }
    };
    public final static Comparator byHeight = new Comparator() {
       @Override
       public int compare(Object o1, Object o2) {
          double k1 = ((Customer) o1).getHeight();
          double k2 = ((Customer) o2).getHeight();
          // didėjanti tvarka, pradedant nuo mažiausios
          if(k1<k2) return -1;
          if(k1>k2) return 1;
          return 0;
       }
    };
    public final static Comparator byYearsAndHeight = new Comparator() {
       @Override
       public int compare(Object o1, Object o2) {
          Customer a1 = (Customer) o1;
          Customer a2 = (Customer) o2;
          // metai mažėjančia tvarka, esant vienodiems lyginamas ūgis
          if(a1.getBirthYear() < a2.getBirthYear()) return 1;
          if(a1.getBirthYear() > a2.getBirthYear()) return -1;
          if(a1.getHeight() < a2.getHeight()) return 1;
          if(a1.getHeight() > a2.getHeight()) return -1;
          return 0;
       }
    };
    // metodas main = tiesiog paprastas pirminis kareivių išbandymas
    public static void main(String... args){
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT")); 
        Customer a1 = new Customer("Petras","Petraitis", 1997, 175);
        Customer a2 = new Customer("Tomas","Tomaitis", 2015, 102);
        Customer a3 = new Customer();
        Customer a4 = new Customer();
        a3.parse("AntanasAntanaitis 1994 166,5");
        a4.parse("Justas Kopustas 1990 6 166,8");
        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun(a4);
    }    
}
