package com.pcic;

import java.util.HashMap;
import java.util.Map;

public class MotherBoard extends Device {
    private Map<Integer, Device> devices = new HashMap<>();

    public MotherBoard(ReceiverIdentifier id) {
        super(id);
    }

    @Override
    public void receiveMessage(Message message) {
        int portNumber = message.getPortIdentifier().getPortNumber();
        if (!devices.containsKey(portNumber)) {
            throw new IllegalArgumentException("Invalid port number: " + portNumber);
        }
        devices.get(portNumber).receiveMessage(message);
    }

    @Override
    public void sendMessage(Message message) {
        int receiverId = message.getReceiverIdentifier().getId();
        if (receiverId == -1) {
            for (Device device : devices.values()) {
                device.receiveMessage(message);
            }
        } else {
            int portNumber = message.getPortIdentifier().getPortNumber();
            if (!devices.containsKey(portNumber)) {
                throw new IllegalArgumentException("Invalid port number: " + portNumber);
            }
            devices.get(portNumber).receiveMessage(message);
        }
    }

    public void addDevice(Device device, int portNumber) {
        devices.put(portNumber, device);
    }
}

