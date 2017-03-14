package com.bortnik;

import java.util.Scanner;

/**
 * Created by admin on 14.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        Thread tr1 = new Thread(new Consumer());
        Thread th2 = new Thread(new Producer());
        th2.start();
        tr1.start();
    }
}
