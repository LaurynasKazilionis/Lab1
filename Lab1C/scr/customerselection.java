/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2individualus;

import studijosKTU.*;

public class CustomerSelection {

    public ListKTUx<Customer> allCustomers = new ListKTUx<>(new Customer());
    private static final Customer exampleCustomer = new Customer();

    // suformuojamas sąrašas klientu pagal nurodytą amžiaus cenzą
    public ListKTUx<Customer> selectYoungest(int year) {
        ListKTUx<Customer> naujiAuto = new ListKTUx<>(exampleCustomer);
        for (Customer a : allCustomers) {
            if (a.getBirthYear() >= year) {
                naujiAuto.add(a);
            }
        }
        return naujiAuto;
    }
    // suformuojamas sąrašas klientų, kurių ūgis yra tarp ribų
    public ListKTUx<Customer> selectByHeight(int h1, int h2) {
        ListKTUx<Customer> averageCustomers = new ListKTUx(exampleCustomer);
        for (Customer a : allCustomers) {
            if (a.getHeight() >= h1 && a.getHeight() <= h2) {
                averageCustomers.add(a);
            }
        }
        return averageCustomers;
    }
    // suformuojamas sąrašas aukščiausių klientu
    public ListKTUx<Customer> tallestCustomers() {
        ListKTUx<Customer> tallestCustomer = new ListKTUx(exampleCustomer);
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        double maxHeight = 0;
        for (Customer a : allCustomers) {
            double height = a.getHeight();
            if (height >= maxHeight) {
                if (height > maxHeight) {
                    tallestCustomer.clear();
                    maxHeight = height;
                }
                tallestCustomer.add(a);
            }
        }
        return tallestCustomer;
    }
    // suformuojams sąrašas klientų, kurių duomenys atitinka nurodytus
    public ListKTUx<Customer> selectByLastName(String lastname) {
        ListKTUx<Customer> customerLastName = new ListKTUx(exampleCustomer);
        for (Customer a : allCustomers) {
            String fullLastName = a.getLastName()+ " " + a.getName();
            if (fullLastName.startsWith(lastname)) {
                customerLastName.add(a);
            }
        }
        return customerLastName;
    }

}
