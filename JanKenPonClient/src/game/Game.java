/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import jankenponclient.Client;

/**
 *
 * @author INSECT
 */
public class Game extends javax.swing.JFrame {

    public static Game ThisGame;
    public Thread tmr_slider;
    public int RivalSelection = -1;
    public int myselection = -1;
    ImageIcon icons_right[];
    ImageIcon icons_left[];
    ImageIcon icons[];
    Random rand;

    /**
     * Creates new form Game
     */
    @SuppressWarnings("empty-statement")
    public Game() {
        initComponents();
        ThisGame = this;
        rand = new Random();
        try {
            icons_right = new ImageIcon[4];
            icons_right[0] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/tas_right.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons_right[1] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/kagit_right.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons_right[2] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/makas_right.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));

            icons_left = new ImageIcon[4];
            icons_left[0] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/tas_left.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons_left[1] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/kagit_left.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons_left[2] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/makas_left.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));

            icons = new ImageIcon[4];
            icons[0] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/wait.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons[1] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/lose.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons[2] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/win.png"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));
            icons[3] = new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/tie.jpeg"))).getImage().getScaledInstance(lbl_gamer1.getWidth(), lbl_gamer1.getHeight(), Image.SCALE_DEFAULT));

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_gamer1.setIcon(icons[0]);
        lbl_gamer2.setIcon(icons[0]);
        tmr_slider = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    if (RivalSelection == -1 || myselection == -1) {
                        int g = rand.nextInt(2);
                        lbl_gamer2.setIcon(icons_right[g]);
                    } else {
                        lbl_gamer2.setIcon(icons_right[RivalSelection]);

                        Thread.sleep(4000);
                        if (myselection == 0 && RivalSelection == 2) {
                            lbl_gamer1.setIcon(icons[2]);
                            lbl_gamer2.setIcon(icons[1]);
                        } else if (myselection < RivalSelection) {
                            lbl_gamer1.setIcon(icons[1]);
                            lbl_gamer2.setIcon(icons[2]);
                        } else if (myselection > RivalSelection) {
                            lbl_gamer1.setIcon(icons[2]);
                            lbl_gamer2.setIcon(icons[1]);
                        } else {
                            lbl_gamer1.setIcon(icons[3]);
                            lbl_gamer2.setIcon(icons[3]);
                        }
                        tmr_slider.stop();
                        Reset();
                        
                        

                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    
    public void Reset()
    {
        if (Client.socket!=null) {
            if (Client.socket.isConnected())
            {
                Client.Stop();
            }
        }
        lbl_gamer1.setIcon(icons[0]);
        btn_connect.setEnabled(true);
        txt_name.setEnabled(true);
        btn_pick.setEnabled(false);
        btn_send_message.setEnabled(false);
        rbtn_kagit.setEnabled(false);
        rbtn_makas.setEnabled(false);
        rbtn_tas.setEnabled(false);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnl_gamer2 = new javax.swing.JPanel();
        lbl_gamer2 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        btn_connect = new javax.swing.JButton();
        btn_pick = new javax.swing.JButton();
        pnl_gamer1 = new javax.swing.JPanel();
        lbl_gamer1 = new javax.swing.JLabel();
        txt_receive = new java.awt.TextArea();
        txt_send = new java.awt.TextArea();
        btn_send_message = new javax.swing.JButton();
        txt_rival_name = new javax.swing.JTextField();
        rbtn_tas = new javax.swing.JRadioButton();
        rbtn_kagit = new javax.swing.JRadioButton();
        rbtn_makas = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_gamer2.setBackground(new java.awt.Color(255, 153, 153));
        pnl_gamer2.setForeground(new java.awt.Color(51, 255, 0));
        pnl_gamer2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_gamer2.setText("jLabel2");
        pnl_gamer2.add(lbl_gamer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 250));
        lbl_gamer2.getAccessibleContext().setAccessibleName("");

        getContentPane().add(pnl_gamer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 251, 259));

        txt_name.setText("Name");
        getContentPane().add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 73, -1));

        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });
        getContentPane().add(btn_connect, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 160, -1));

        btn_pick.setText("Pick");
        btn_pick.setEnabled(false);
        btn_pick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pickActionPerformed(evt);
            }
        });
        getContentPane().add(btn_pick, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 70, 90));

        pnl_gamer1.setBackground(new java.awt.Color(255, 153, 153));
        pnl_gamer1.setForeground(new java.awt.Color(51, 255, 0));
        pnl_gamer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_gamer1.setText("jLabel1");
        pnl_gamer1.add(lbl_gamer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 250));
        lbl_gamer1.getAccessibleContext().setAccessibleName("");

        getContentPane().add(pnl_gamer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 259));
        getContentPane().add(txt_receive, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 250, 128));
        txt_receive.getAccessibleContext().setAccessibleName("");

        getContentPane().add(txt_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 250, 128));
        txt_send.getAccessibleContext().setAccessibleName("");

        btn_send_message.setText("Send");
        btn_send_message.setEnabled(false);
        btn_send_message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send_messageActionPerformed(evt);
            }
        });
        getContentPane().add(btn_send_message, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 60, -1));

        txt_rival_name.setEditable(false);
        txt_rival_name.setText("Rival");
        txt_rival_name.setEnabled(false);
        getContentPane().add(txt_rival_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 110, -1));

        buttonGroup1.add(rbtn_tas);
        rbtn_tas.setSelected(true);
        rbtn_tas.setText("Tas");
        rbtn_tas.setEnabled(false);
        rbtn_tas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_tasActionPerformed(evt);
            }
        });
        getContentPane().add(rbtn_tas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        buttonGroup1.add(rbtn_kagit);
        rbtn_kagit.setText("Kağıt");
        rbtn_kagit.setEnabled(false);
        rbtn_kagit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_kagitActionPerformed(evt);
            }
        });
        getContentPane().add(rbtn_kagit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        buttonGroup1.add(rbtn_makas);
        rbtn_makas.setText("Makas");
        rbtn_makas.setEnabled(false);
        rbtn_makas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtn_makasActionPerformed(evt);
            }
        });
        getContentPane().add(rbtn_makas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        // TODO add your handling code here:
        Client.Start("127.0.0.1", 2000);
        lbl_gamer1.setIcon(icons_left[0]);
        btn_connect.setEnabled(false);
        txt_name.setEnabled(false);
        btn_pick.setEnabled(true);
        btn_send_message.setEnabled(true);
        rbtn_kagit.setEnabled(true);
        rbtn_makas.setEnabled(true);
        rbtn_tas.setEnabled(true);


    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_pickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pickActionPerformed
        // TODO add your handling code here:
        rbtn_kagit.setEnabled(false);
        rbtn_makas.setEnabled(false);
        rbtn_tas.setEnabled(false);
        btn_pick.setEnabled(false);

        if (rbtn_tas.isSelected()) {
            myselection = 0;
        } else if (rbtn_kagit.isSelected()) {
            myselection = 1;
        } else if (rbtn_makas.isSelected()) {
            myselection = 2;
        }
        Message msg = new Message(Message.Message_Type.Selected);
        msg.content = myselection;
        Client.Send(msg);


    }//GEN-LAST:event_btn_pickActionPerformed

    private void btn_send_messageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send_messageActionPerformed
        // TODO add your handling code here:
        
        Message msg = new Message(Message.Message_Type.Text);
        msg.content = txt_send.getText();
        Client.Send(msg);
        txt_send.setText("");

    }//GEN-LAST:event_btn_send_messageActionPerformed

    private void rbtn_makasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_makasActionPerformed
        // TODO add your handling code here:
        lbl_gamer1.setIcon(icons_left[2]);
    }//GEN-LAST:event_rbtn_makasActionPerformed

    private void rbtn_tasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_tasActionPerformed
        // TODO add your handling code here:
        lbl_gamer1.setIcon(icons_left[0]);
    }//GEN-LAST:event_rbtn_tasActionPerformed

    private void rbtn_kagitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtn_kagitActionPerformed
        // TODO add your handling code here:
        lbl_gamer1.setIcon(icons_left[1]);
    }//GEN-LAST:event_rbtn_kagitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Client.Stop();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_connect;
    public javax.swing.JButton btn_pick;
    public javax.swing.JButton btn_send_message;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JLabel lbl_gamer1;
    public javax.swing.JLabel lbl_gamer2;
    private javax.swing.JPanel pnl_gamer1;
    private javax.swing.JPanel pnl_gamer2;
    private javax.swing.JRadioButton rbtn_kagit;
    private javax.swing.JRadioButton rbtn_makas;
    private javax.swing.JRadioButton rbtn_tas;
    public javax.swing.JTextField txt_name;
    public java.awt.TextArea txt_receive;
    public javax.swing.JTextField txt_rival_name;
    public java.awt.TextArea txt_send;
    // End of variables declaration//GEN-END:variables
}
