package io.kersten.dsmn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainGUI extends JFrame {
    // IntelliJ makes these and hides them somewhere
    private JTextArea textArea1;
    private JTextField textField1;
    private JPanel rootPanel;
    private JComboBox comboBox1;

    private Transmitter host;

    public void addMessage(String message) {
        textArea1.append(message + "\n");
        textArea1.setCaretPosition(textArea1.getText().length());
    }

    public void addBroadcastGroup(String group) {
        comboBox1.addItem(group);
        comboBox1.setSelectedIndex(0);
    }

    public MainGUI(final Transmitter host) {
        super("dismn");

        this.host = host;


        this.getContentPane().add(rootPanel);


        this.setSize(new Dimension(512, 192));

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);


        this.textField1.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        host.transmit((String)comboBox1.getSelectedItem(), textField1.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    textField1.setText("");

                }
            }
        });


        this.textField1.requestFocus();
    }
}
