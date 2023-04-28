package com.pcic;

import java.util.Map;

public  abstract  class PCIDevice extends Device{

    private Map<Integer, PortIdentifier> portMappings;

    public PCIDevice(ReceiverIdentifier id, Map<Integer, PortIdentifier> portMappings) {
        super(id);
        this.portMappings = portMappings;
    }

    public void receiveMessageOnPort(PortIdentifier portId, Message message) {
        if (portMappings.containsKey(portId.getPortNumber())) {
            receiveMessage(message);
        } else {
            throw new IllegalArgumentException("Invalid port number: " + portId.getPortNumber());
        }
    }
}
