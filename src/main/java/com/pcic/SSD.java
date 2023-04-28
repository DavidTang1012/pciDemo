package com.pcic;

public class SSD extends Device {
    public SSD(ReceiverIdentifier id) {
        super(id);
    }

    @Override
    public void receiveMessage(Message message) {
        byte[] payload = message.getPayload().getBytes();
        System.out.println("SSD received message: " + new String(payload));
    }

    @Override
    public void sendMessage(Message message) {
        PCIC pcic = Main.getPCIC();
        pcic.receiveMessage(message);
    }
}