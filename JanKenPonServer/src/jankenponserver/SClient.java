/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jankenponserver;

import game.Message;
import static game.Message.Message_Type.Selected;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INSECT
 */
public class SClient {

    int id;
    public String name = "NoName";
    Socket soket;
    ObjectOutputStream sOutput;
    ObjectInputStream sInput;
    Listen listenThread;
    PairingThread pairThread;
    Thread l;
    SClient rival2;
    public boolean paired = false;

    public SClient(Socket gelenSoket, int id) {
        this.soket = gelenSoket;
        this.id = id;
        try {
            this.sOutput = new ObjectOutputStream(this.soket.getOutputStream());
            this.sInput = new ObjectInputStream(this.soket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.listenThread = new Listen(this);

        this.pairThread = new PairingThread(this);
        // Server.pairThread.start();    

    }

    public void Send(Message message) {
        try {
            this.sOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class Listen extends Thread {

        SClient TheClient;

        Listen(SClient TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {

            while (TheClient.soket.isConnected()) {
                try {

                    Message received = (Message) (TheClient.sInput.readObject());
                    switch (received.type) {
                        case Name:
                            TheClient.name = received.content.toString();
                            TheClient.pairThread.start();
                            break;
                        case Disconnect:
                            break;
                        case Text:
                            //gelen metni direkt rakibe gönder
                            Server.Send(TheClient.rival2, received);
                            break;
                        case Selected:
                            //gelen seçim yapıldı mesajını rakibe gönder
                            Server.Send(TheClient.rival2, received);
                            break;
                        case Bitis:
                            break;

                    }

                } catch (IOException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                    
                    Server.Clients.remove(TheClient);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int i = 0;

        }
    }

    class PairingThread extends Thread {
        SClient TheClient;

        PairingThread(SClient TheClient) {
            this.TheClient = TheClient;
        }
        public void run() {

            while (TheClient.soket.isConnected() && TheClient.paired==false) {
                try {
                    Server.pairTwo.acquire(1);
                    
                    if (!TheClient.paired) {   
                    SClient crival= null;
                    while (crival == null && TheClient.soket.isConnected()) {
                        for (SClient clnt : Server.Clients) {
                            if (TheClient!=clnt && clnt.rival2==null) {
                                crival=clnt;
                                crival.paired=true;
                                crival.rival2=TheClient;
                                TheClient.rival2= crival;
                                TheClient.paired=true;
                                break;
                            }
                        }
                        sleep(1000);
                    }
                    Message msg1 = new Message(Message.Message_Type.RivalConnected);
                    msg1.content = TheClient.name;
                    Server.Send(TheClient.rival2, msg1);

                    Message msg2 = new Message(Message.Message_Type.RivalConnected);
                    msg2.content = TheClient.rival2.name;
                    Server.Send(TheClient, msg2);
                    }
                    Server.pairTwo.release(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PairingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
