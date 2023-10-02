import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private final JTextArea log = new JTextArea();


    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Ivan Ivanovich");
    private final JPasswordField pfPswd = new JPasswordField("123456789");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBot = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private boolean isLogged;


    public ClientGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation(100, 200);
        setResizable(false);
        setTitle("Chat client");
        setVisible(true);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isLogged) {
                    log.append(tfLogin.getText() + ": " + tfMessage.getText() + "\n");
                    ServerWindow.log.append(tfLogin.getText() + ": " + tfMessage.getText() + "\n");
                    LogOperations.writeToFile(tfLogin.getText() + ": " + tfMessage.getText());
                    tfMessage.setText("");
                } else {
                    log.append("First you need to login. \n");
                    ServerWindow.log.append("First you need to login. \n");
                }
            }
        });
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isLogged) {
                        log.append(tfLogin.getText() + ": " + tfMessage.getText() + "\n");
                        ServerWindow.log.append(tfLogin.getText() + ": " + tfMessage.getText() + "\n");
                        LogOperations.writeToFile(tfLogin.getText() + ": " + tfMessage.getText());
                        tfMessage.setText("");
                    } else {
                        log.append("First you need to login. \n");
                        ServerWindow.log.append("First you need to login. \n");
                    }
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ServerWindow.isServerWorking) {
                    if (isLogged) {
                        log.append(tfLogin.getText() + " is already logged.\n");
                        ServerWindow.log.append(tfLogin.getText() + " is already logged.\n");
                        LogOperations.writeToFile(tfLogin.getText() + " is already logged.");
                    } else {
                        log.append(tfLogin.getText() + " has been logged.\n");
                        ServerWindow.log.append(tfLogin.getText() + " has been logged.\n");
                        LogOperations.writeToFile(tfLogin.getText() + " has been logged.");
                        isLogged = true;
                    }
                } else {
                    log.append("First you need to start server.\n");
                }
            }
        });

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(new JPanel());
        panelTop.add(tfLogin);
        panelTop.add(pfPswd);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);
        panelBot.add(tfMessage);
        panelBot.add(btnSend, BorderLayout.EAST);
        add(panelBot, BorderLayout.SOUTH);

        log.setEditable(false);
        add(log);
        JScrollPane scrolling = new JScrollPane(log);
        add(scrolling);
    }
}
