package com.pcic;

/**
 * message class
 * */

public class Message  implements Payload {
    //receiver
    private final Payload payload;
    //port
    private final PortIdentifier portIdentifier;
    //ID
    private final ReceiverIdentifier receiverIdentifier;
     //constructor
     public Message(Payload payload, ReceiverIdentifier receiverIdentifier, PortIdentifier portIdentifier) {
         this.payload = payload;
         this.receiverIdentifier = receiverIdentifier;
         this.portIdentifier = portIdentifier;
     }
     //get receiver
     public ReceiverIdentifier getReceiverIdentifier() {
         return receiverIdentifier;
     }
    //port number
    public PortIdentifier getPortIdentifier() {
        return portIdentifier;
    }
    public Payload getPayload() {
        return payload;
    }

    @Override
    public byte[] getBytes() {
        return payload.getBytes();
    }
}
