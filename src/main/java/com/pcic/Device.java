package com.pcic;

//abstract class of devices
public abstract class Device {

    //ID of devices
    private ReceiverIdentifier receiverIdentifier;

    public Device(ReceiverIdentifier receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }

    //get the id
    public ReceiverIdentifier getReceiverIdentifier() {
        return receiverIdentifier;
    }

    //receive message
    public abstract void receiveMessage(Message message);
   //send message
    public abstract void sendMessage(Message message);
}
