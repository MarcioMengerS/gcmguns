package br.com.gcm.sac.setor_armamento.configuration;

import br.com.gcm.sac.setor_armamento.model.Card;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacketListener implements SerialPortPacketListener{
    private String tagHex="";

    public String getTag() {
        return tagHex;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public int getPacketSize() {
        return Card.PACKET_SIZE_IN_BYTES;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String str = new String(newData).split("\n", 2)[0].replaceAll("\\s+", " ");
        int byteSize = 0;
        try {
            byteSize = str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PacketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (byteSize == Card.PACKET_SIZE_IN_BYTES) {
            //System.out.println("(Received data of size: " + byteSize + ")" + str);
            System.out.println("Received data: " + str);
            this.tagHex = str;
        }
    }
}
