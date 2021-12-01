import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.*;

public class Test extends javax.swing.JFrame {
   
   private JFrame frame;
   private ImageIcon school;
   private JLabel myLabel;
   private JButton startButton;

   public Test() {


      //school = new ImageIcon("C:\\Users\\DANIEL\\Documents\\Daquiver's Quivers\\Java\\Transportation_App\\images\\leg1.jpg");
      myLabel = new JLabel(school);
      myLabel.setSize(658, 458);

      startButton = new JButton("Start");
      startButton.setBounds(50,50,100,50);

      myLabel.add(startButton);

      frame = new JFrame("Hello World");
      frame.add(myLabel);
      frame.setSize(658, 458);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
   public static void main(String[] args) throws Exception {
      new Test();
   }
}