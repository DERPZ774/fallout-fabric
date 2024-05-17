package com.derpz.nukaisles.functions;

public class Main {
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
