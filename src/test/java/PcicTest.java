import com.pcic.*;
import org.junit.Test;

public class PcicTest {

    private static PCIC pcic = new PCIC();
    public static PCIC getPCIC() {
        return pcic;
    }



    @Test
    public void testPcic(){

        SSD  ssd=new SSD(new ReceiverIdentifier(0x10F));
        pcic.addDevice(ssd );
        SCSIConverter scsiConverter=new SCSIConverter(new ReceiverIdentifier(0x0F));
        pcic.addDevice(scsiConverter);
        Message message = new Message(new Payload() {
            @Override
            public byte[] getBytes() {
                return "Hello, world!".getBytes();
            }
        }, new ReceiverIdentifier(0x0F), new PortIdentifier(82));
        pcic.sendMessage(message);

    }

    @Test
    public void testSata(){
        MotherBoard sataController = new MotherBoard(new ReceiverIdentifier(0x0F));
        SSD ssd = new SSD(new ReceiverIdentifier(999));
        sataController.addDevice(ssd, 82);
        SCSIConverter scsiConverter=new SCSIConverter(new ReceiverIdentifier(888));
        sataController.addDevice(scsiConverter, 81);

        pcic.addDevice(new SSD(new ReceiverIdentifier(0x10F)));
        pcic.addDevice(sataController);
        Message message = new Message(new Payload() {
            @Override
            public byte[] getBytes() {
                return "Hello, world!".getBytes();
            }
        }, new ReceiverIdentifier(0x0F), new PortIdentifier(82));
        pcic.sendMessage(message);
    }


    @Test
    public void testBroadcast(){
        BroadcastDevice broadcastDevice = new BroadcastDevice(new ReceiverIdentifier(0x0F));
        broadcastDevice.addDevice(new SCSIConverter(new ReceiverIdentifier(0x0F)));
        broadcastDevice.addDevice(new SSD(new ReceiverIdentifier(0x0F)));
        pcic.addDevice(broadcastDevice );
        SCSIConverter scsiConverter=new SCSIConverter(new ReceiverIdentifier(0x10F));
        pcic.addDevice(scsiConverter);
        Message message = new Message(new Payload() {
            @Override
            public byte[] getBytes() {
                return "Hello, world!".getBytes();
            }
        }, new ReceiverIdentifier(0x0F), new PortIdentifier(82));

        pcic.sendMessage(message);

    }



    @Test
    public void testAll(){
        MotherBoard sataController = new MotherBoard(new ReceiverIdentifier(0x0F));
        BroadcastDevice broadcastDevice = new BroadcastDevice(new ReceiverIdentifier(0x0F));
        broadcastDevice.addDevice(new Application(new ReceiverIdentifier(0x0F)));
        broadcastDevice.addDevice(new SCSIConverter(new ReceiverIdentifier(0x0F)));
        broadcastDevice.addDevice(new SSD(new ReceiverIdentifier(0x0F)));
        sataController.addDevice(broadcastDevice, 82);
        sataController.addDevice(new SCSIConverter(new ReceiverIdentifier(0x0F)), 81);
        pcic.addDevice(sataController);
        pcic.addDevice(new SSD(new ReceiverIdentifier(0x10F)));
        Message message = new Message(new Payload() {
            @Override
            public byte[] getBytes() {
                return "Hello, world!".getBytes();
            }
        }, new ReceiverIdentifier(0x0F), new PortIdentifier(82));
        pcic.sendMessage(message);

        Message message2 = new Message(new Payload() {
            @Override
            public byte[] getBytes() {
                return "Hello, world!".getBytes();
            }
        }, new ReceiverIdentifier(-1), new PortIdentifier(82));
        pcic.sendMessage(message2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPort() {
        MotherBoard sataController = new MotherBoard(new ReceiverIdentifier(0x0F));
        BroadcastDevice broadcastDevice = new BroadcastDevice(new ReceiverIdentifier(0x0F));
        broadcastDevice.addDevice(new Application(new ReceiverIdentifier(0x0F)));
        broadcastDevice.addDevice(new SCSIConverter(new ReceiverIdentifier(0x0F)));
        broadcastDevice.addDevice(new SSD(new ReceiverIdentifier(0x0F)));
        sataController.addDevice(broadcastDevice, 82);
        sataController.addDevice(new SCSIConverter(new ReceiverIdentifier(0x0F)), 81);
        pcic.addDevice(sataController);
        pcic.addDevice(new SSD(new ReceiverIdentifier(0x10F)));
        Message message2 = new Message(new Payload() {
            @Override
            public byte[] getBytes() {
                return "Hello, world!".getBytes();
            }
        }, new ReceiverIdentifier(0x0F), new PortIdentifier(8080));
        pcic.sendMessage(message2);
    }

}
