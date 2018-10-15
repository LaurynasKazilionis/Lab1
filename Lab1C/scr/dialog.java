/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2individualus;

import java.util.Locale;
import studijosKTU.*;

public class Dialog {
    CustomerSelection aCustomer = new CustomerSelection();

   void bendravimasSuKlientu() {
      ListKTUx< Customer> selection = null;
      int varNr;  // skaičiavimo varijantas pasirenkamas nurodant jo numerį
      String dialogMeniu = "Pasirinkimas: "
            + "1-skaityti iš failo; 2-papildyti sąrašą; 3-jaunų atranka;\n    "
            + "4-atranka pagal aukštį; 5-auksčiausi klientai;" + "0-baigti skaičiavimus > ";
      while ((varNr = Ks.giveInt(dialogMeniu, 0, 6)) != 0) {
         if (varNr == 1) {
            aCustomer.allCustomers.load(Ks.giveFileName());
            aCustomer.allCustomers.println("Visų klientu sąrašas");
         } else {
            if (varNr == 2) {
               String customerData = Ks.giveString("Nurodykite kliento vardą, "+
                            "Pavarde, gimimo metus ir ūgį\n ");
               Customer a = new Customer();
               a.parse(customerData);
               String errorType = a.validate();
               if (errorType.isEmpty()) // dedame tik su gerais duomenimis
                   aCustomer.allCustomers.add(a);
               else
                 Ks.oun("!!! Customer list not excepted "+errorType);
            } else {  // toliau vykdomos atskiri atrankos metodai
               switch (varNr) {
                  case 3:
                     int nR = Ks.giveInt("Nurodykite naujų klientu metų ribą: ");
                     selection = aCustomer.selectYoungest(nR);
                     break;
                  case 4:
                     int r1 = Ks.giveInt("Nurodykite žemiausią ūgį: ");
                     int r2 = Ks.giveInt("Nurodykite aukščiausią ūgį: ");
                     selection = aCustomer.selectByHeight(r1, r2);
                     break;
                  case 5:
                     selection = aCustomer.tallestCustomers();
                     break;
                  case 6:

               }
               selection.println("Štai atrinktų klientu sąrašas");
               selection.save(Ks.giveString
                     ("Kur saugoti atrinktus klientus (jei ne-tai ENTER) ? "));
            }
         }
      }
   }
   public static void main(String[] args) {
          // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
      new Dialog().bendravimasSuKlientu();
   }
}
