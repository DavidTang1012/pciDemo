package com.pcic;

import java.util.ArrayList;
import java.util.List;

public class PCIC {

    //list of devices in the motherboard
    private List<Device> devices = new ArrayList<>();

    /**
     * device
     * receiverId
     * portMapping
     * */
    public void addDevice(Device device) {
        devices.add(device);
    }


    /**
     *portNumber
     * */
    public void sendMessage(Message message) {
        int receiverId = message.getReceiverIdentifier().getId();
        //iterate
        for (Device device : devices) {

            if (device.getReceiverIdentifier().getId() == receiverId || device.getReceiverIdentifier().getId() == -1) {
                if (device instanceof BroadcastDevice) {
                    device.sendMessage(message);
                } else {
                    if (device.getReceiverIdentifier().getId() == receiverId) {
                        device.receiveMessage(message);
                    }
                }
            }
        }
    }

    public void receiveMessage(Message message) {
        sendMessage(message);
    }
}
