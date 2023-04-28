package com.pcic;

import java.util.ArrayList;
import java.util.List;


//broadcast to all devices
public class BroadcastDevice extends Device {
    //store list of devices
    private List<Device> devices=new ArrayList<>();


    public BroadcastDevice(ReceiverIdentifier id) {
        super(id);
    }


    @Override
    public void receiveMessage(Message message) {
        for (Device device : devices) {
            device.receiveMessage(message);
        }
    }

    @Override
    public void sendMessage(Message message) {
            receiveMessage(message); // broadcast
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

}