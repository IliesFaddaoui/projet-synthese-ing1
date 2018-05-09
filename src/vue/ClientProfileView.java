package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClientProfileView extends JFrame {

    private JLabel connectionText = new JLabel("Please log in to see your profile: ");
    private JLabel espada = new JLabel("PhyGit Mall");
    private Font police = new Font("Arial", Font.BOLD, 14);
    private Font policeEspada = new Font("Arial", Font.BOLD, 28);
    private JLabel labelConnection;
    public JTextField jtfPseudo = new JTextField("pseudo");
    public JTextField jtfPassword = new JTextField("password");
    private boolean isConnected = false;
    private int idClientConnected = 0;
    private JButton connectionButton = new JButton("Connection");
    private JPanel container = new JPanel();

    public ClientProfileView(){
        this.setTitle("PhyGit Mall: My Profile");
        this.setSize(600,600);
        this.setResizable(false);
        espada.setFont(policeEspada);

        jtfPseudo.getFont().deriveFont(Font.ITALIC);
        jtfPseudo.setForeground(Color.gray);
        jtfPseudo.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField t1 = ((JTextField)e.getSource());
                t1.setText("");
                t1.getFont().deriveFont(Font.PLAIN);
                t1.setForeground(Color.black);
                t1.removeMouseListener(this);
            }
        });
        jtfPassword.getFont().deriveFont(Font.ITALIC);
        jtfPassword.setForeground(Color.gray);
        jtfPassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField t2 = ((JTextField)e.getSource());
                t2.setText("");
                t2.getFont().deriveFont(Font.PLAIN);
                t2.setForeground(Color.black);
                t2.removeMouseListener(this);
            }
        });
        JPanel top = new JPanel();
        JPanel west = new JPanel();
        JPanel east = new JPanel();
        JPanel center = new JPanel();
        JPanel bot = new JPanel();

        top.setPreferredSize(new Dimension(100,250));
        center.setPreferredSize(new Dimension(100,60));
        west.setPreferredSize(new Dimension(100,600));
        east.setPreferredSize(new Dimension(100,600));
        bot.setPreferredSize(new Dimension(100,250));

        GridLayout layoutCenter = new GridLayout(2,1);
        GridLayout layoutTop = new GridLayout(1,2);
        top.setLayout(layoutTop);
        center.setLayout(layoutCenter);
        top.add(espada);
        top.add(connectionText);
        container.setLayout(new BorderLayout());
        center.add(jtfPseudo);
        center.add(jtfPassword);

        bot.add(connectionButton);

        container.add(top, BorderLayout.NORTH);
        container.add(center, BorderLayout.CENTER);
        container.add(bot, BorderLayout.SOUTH);
        container.add(east, BorderLayout.EAST);
        container.add(west, BorderLayout.WEST);
        this.setContentPane(container);
        this.setVisible(true);


    }

    public static void main(String[] args){
        ClientProfileView p1 = new ClientProfileView();
    }
}
