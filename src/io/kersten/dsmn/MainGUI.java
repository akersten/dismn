package io.kersten.dsmn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {
    // IntelliJ makes these and hides them somewhere
    private JTextArea textArea1;
    private JTextField textField1;
    private JPanel rootPanel;

    public void addMessage(String message) {
        textArea1.append(message + "\n");
        textArea1.setCaretPosition(textArea1.getText().length());
    }

    public MainGUI() {
        super("dismn");
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
                    textField1.setText("");
                }
            }
        });


        this.textField1.requestFocus();
    }
}
