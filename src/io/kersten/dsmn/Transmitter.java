package io.kersten.dsmn;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * dismn
 */
public class Transmitter implements Runnable {

    MainGUI gui;
    MulticastSocket socket;

    public Transmitter() {
        MainGUI gui = new MainGUI(this);


        // populate interfaces in the gui
        System.setProperty("java.net.preferIPv4Stack", "true");
        try
        {
            Enumeration<NetworkInterface> niEnum = NetworkInterface.getNetworkInterfaces();
            while (niEnum.hasMoreElements())
            {
                NetworkInterface ni = niEnum.nextElement();
                if (ni.isLoopback())
                    continue;

                System.out.println("new interface:" + ni.getDisplayName() + "\n");
                for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses())
                {


                    if (interfaceAddress.getBroadcast() != null)
                        gui.addBroadcastGroup(interfaceAddress.getBroadcast().toString());

                    System.out.println("address" + interfaceAddress.getAddress().getHostAddress() + " name:"
                            + interfaceAddress.getAddress().getHostName() + " prefix:"
                            + interfaceAddress.getNetworkPrefixLength() + " broadcast:"
                            + interfaceAddress.getBroadcast());

                }
            }
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
//
//        Enumeration<NetworkInterface> faces = null;
//
//        try {
//            faces = NetworkInterface.getNetworkInterfaces();
//            while (faces.hasMoreElements()) {
//                NetworkInterface f = faces.nextElement();
//
//                    continue;
//                for (InterfaceAddress interfaceAddress : f.getInterfaceAddresses()) {
//                    InetAddress broadcast = interfaceAddress.getBroadcast();
//                    if (broadcast != null) {
//
//                    }
//                }
//
//            }
//
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//


        try {
            socket = new MulticastSocket(12345);
        } catch (Exception e) {
            // whatever
            System.exit(1);
        }
    }

    public void transmit(String group, String msg) throws IOException {
        InetAddress addr = InetAddress.getByName(group);
        byte[] buf = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, addr, 12345);
        socket.send(packet);
    }

    @Override
    public void run() {

    }
}
