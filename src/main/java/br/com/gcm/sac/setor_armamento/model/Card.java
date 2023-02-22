package br.com.gcm.sac.setor_armamento.model;

import br.com.gcm.sac.setor_armamento.configuration.PacketListener;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import com.fazecast.jSerialComm.SerialPort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String card;

    public static String devicePortName = "USB-SERIAL CH340";
    public static SerialPort arduinoPort = null;
    public static InputStream arduinoStream = null;
    public static int PACKET_SIZE_IN_BYTES = 8;



    public void obtemCard(){
        int len = SerialPort.getCommPorts().length;
        SerialPort serialPorts[] = new SerialPort[len];
        serialPorts = SerialPort.getCommPorts();

        for (int i = 0; i < len; i++) {

            String portName = serialPorts[i].getDescriptivePortName();
            System.out.println(serialPorts[i].getSystemPortName() + ": " + portName + ": " + i);

            if (portName.contains(devicePortName)) {
                arduinoPort = serialPorts[i];
                arduinoPort.openPort();
                System.out.println("connected to: " + portName + "[" + i + "]");
                break;
            }
        }

        PacketListener listener = new PacketListener();
        System.out.println("A porta de comunicação estará aberta por 10 segundos!");
        //Monitora a porta serial
        arduinoPort.addDataListener(listener);
//--------give it a rest--------------------------------   
       try {
           Thread.sleep(10000);//tempo que porta USB de comunicação ficará aberta
       } catch (Exception e) {
           e.printStackTrace();
       }
       arduinoPort.removeDataListener();
       arduinoPort.closePort();
       System.out.println("Porta de comunicação Fechada!!!");
       card = listener.getTag();
       System.out.println("TAG capturada com sucesso: "+ card);
    }
}
