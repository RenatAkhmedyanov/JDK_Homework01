import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    static final JTextArea log = new JTextArea();
    private final JPanel panelBottom = new JPanel(new GridLayout(1, 2));
    static boolean isServerWorking;

    public ServerWindow() {
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    log.append("Server already stopped\n");
                    LogOperations.writeToFile("Server already stopped");
                } else {
                    isServerWorking = false;
                    log.append("Server stopped\n");
                    LogOperations.writeToFile("Server stopped");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.append("Server already started\n");
                    LogOperations.writeToFile("Server already started");
                } else {
                    isServerWorking = true;
                    log.append("Server started\n");
                    try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
                        String line = reader.readLine();
                        while (line != null) {
                            line = reader.readLine();
                            log.append(line);
                            log.append("\n");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    LogOperations.writeToFile("Server started");
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Chat server");
        log.setEditable(false);
        add(log);
        JScrollPane scrolling = new JScrollPane(log);
        add(scrolling);
        panelBottom.add(btnStart);
        panelBottom.add(btnStop);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);

    }

}
