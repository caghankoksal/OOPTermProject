package si.um.opj.koksal.logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame{
    private JButton bottonMsg;
    private JPanel panelMain;
    private JPanel secondMain;
    private JButton HEEY;
    private JButton PUFFButton;
    private JTabbedPane DENEME;

    private JTextField item1;
    private JTextField item2 ;
    private JTextField item3 ;
    private JPasswordField passwordField;

    private JButton reg ;
    private JButton custom ;





    public App() {
        super("The Title");
        setLayout(new FlowLayout());

        item1 = new JTextField(10);
        add(item1);

        item2 = new JTextField("Enter text here") ;
        add(item2) ;

        item3 = new JTextField("uneditale",20) ;
        item3.setEditable(false);
        add(item3);

        passwordField = new JPasswordField("mypass") ;
        add(passwordField) ;

        thehandler handler = new thehandler() ;
        item1.addActionListener(handler);
        item2.addActionListener(handler);
        item3.addActionListener(handler) ;
        passwordField.addActionListener(handler);

    }
    private class thehandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String string = "" ;
            if(e.getSource() == item1)
            {
                string = String.format("field 1 : %s",e.getActionCommand());

            }
            if(e.getSource() == item2)
            {
                string = String.format("field 2 : %s",e.getActionCommand());

            }
            else if(e.getSource() == item3)
            {
                string = String.format("field 1 : %s",e.getActionCommand());

            }
            else if(e.getSource() == passwordField)
            {
                string = String.format("password field is : %s",e.getActionCommand());

            }

            JOptionPane.showMessageDialog(null,string);
        }
    }

    public static void main(String[] args) {

        App App = new App();
        App.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App.setSize(350,100);
        App.setVisible(true);
    }
}
