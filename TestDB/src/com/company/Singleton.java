package com.company;

public class Singleton {

    private static Integer angka;

    public static int getAngka(){

        if(angka == null){
            System.out.println("Angka dibuat");
            angka = 6969;
        }
        return angka;
    }

}
