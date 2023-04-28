package com.pcic;

public class Application extends Device {

    public Application(ReceiverIdentifier id) {
        super(id);
    }

    @Override
    public void receiveMessage(Message message) {
        byte[] payload = message.getPayload().getBytes();
        System.out.println("Application received message: " + new String(payload));
    }

    @Override
    public void sendMessage(Message message) {
        PCIC pcic = Main.getPCIC();
        pcic.receiveMessage(message);
    }
}
