import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationPage extends JFrame
{
    Toolkit tk;
    Dimension dm;
    int wid,hgh;
    JMenu jm;
    JMenuBar jmb;
    JMenuItem jm1,jm2,jm3;
    ImageIcon img,resizeimg;
    Image img1;
    JSeparator js1,js2;
    JLabel jl1;
    JTextArea jt1,jt2;
    ApplicationPage(String user,String email,JFrame Login)
    {
        setLayout(null);
        tk=Toolkit.getDefaultToolkit();
        dm = tk.getScreenSize();
        wid = (int)dm.getWidth();
        hgh = (int)dm.getHeight();
        setSize(wid,hgh);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        img = new ImageIcon("C:/Users/aravi/IdeaProjects/FramesAndJDBC/src/AgRam_Logo.png");
        img1 = img.getImage().getScaledInstance(1000,200,Image.SCALE_SMOOTH);
        resizeimg = new ImageIcon(img1);

        jl1 = new JLabel(resizeimg,JLabel.CENTER);
        jl1.setBounds(250,10,1000,200);

        js1 = new JSeparator();
        js1.setBounds(250,220,1000,1);

        jmb = new JMenuBar();
//        jmb.setBorderPainted(true);
        jmb.setBounds(1270,230,110,40);
        jmb.setBackground(Color.WHITE);
        jmb.setForeground(Color.green);

        jm = new JMenu("Settings");
        jm.setBorderPainted(true);
        jm.setFont(new Font("SERIF",Font.ITALIC,30));
        jm.setBackground(Color.WHITE);
        jm.setForeground(Color.green);
        jm.addSeparator();
//        jm.addSeparator();
//        jmb.add(jm);

        jm1 = new JMenuItem("Profile");
        jm1.setBorderPainted(true);
        jm1.setFont(new Font("SERIF",Font.ITALIC,20));
        jm1.setBackground(Color.WHITE);
        jm1.setForeground(Color.green);
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Profile Page(Application Page - Argument);
                setVisible(false);
                new ProfileFrame(user,email,ApplicationPage.this);
            }
        });

        jm2 = new JMenuItem("Logout");
        jm2.setBorderPainted(true);
        jm2.setFont(new Font("SERIF",Font.ITALIC,20));
        jm2.setBackground(Color.WHITE);
        jm2.setForeground(Color.green);
        jm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LoginPage;
                setVisible(false);
                Login.setVisible(true);
            }
        });

        jm3= new JMenuItem("Help Me");
        jm3.setBorderPainted(true);
        jm3.setFont(new Font("SERIF",Font.ITALIC,20));
        jm3.setBackground(Color.WHITE);
        jm3.setForeground(Color.green);
        jm3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Contact Admin(+91-9489943898) To Clear Your Doubts.\nAdmin will Guide you Virtually to Clear your Doubts.");
            }
        });

        jm.add(jm1);
        jm.add(jm2);
        jm.add(jm3);

        jmb.add(jm);
        jt1 = new JTextArea("What AgRam Company is Going to Do?\n"+
                "1. AgRam is a company focused on transforming the agricultural sector by supporting farmers through the development of smart, attractive, and portable devices designed to automate and improve crop production.\n" +
                "2. The company's vision is to enhance farming efficiency by introducing technology-based solutions that are affordable, easy to use, and suitable for all types of farmers.\n" +
                "3. Initially, AgRam has come up with two innovative ideas that aim to increase crop yield and reduce maintenance costs, addressing major challenges faced by modern-day farmers.\n" +
                "4. These two inventions will be implemented and tested in real-time farming conditions, ensuring they provide effective results before scaling up further.\n" +
                "5. Upon successful implementation, AgRam plans to fully automate these ideas, making the entire farming process more intelligent, data-driven, and less dependent on manual labor.\n" +
                "6. In the long term, AgRam envisions building a futuristic model of agriculture—often referred to as “Automated Agri”—that blends innovation with tradition to create a sustainable and productive farming future.");
        jt1.setBounds(250,270,1000,250);
        jt1.setFont(new Font("SERIF",Font.ROMAN_BASELINE,15));
        jt1.setEditable(false);
        jt1.setLineWrap(true);
        jt1.setBackground(getBackground());
        jt1.setForeground(Color.BLACK);
        jt1.setWrapStyleWord(true);
        jt1.setBorder(null);

        js2= new JSeparator();
        js2.setBounds(250,530,1000,1);

        jt2 = new JTextArea("About the Application – AR-1.0.0\n" +
                "1. The current application is developed for testing purposes, to evaluate the initial performance and real-world impact of our product on farming activities.\n" +
                "2. This version, AR-1.0.0, is a prototype model designed and developed by Aravindhan Ramasamy as a step toward smart and sustainable agriculture.\n" +
                "3. The main goal of this product is to support farmers by making their work more stable and helping them achieve financial stability through increased productivity and reduced manual effort.\n" +
                "4. This application serves as the foundation for future upgrades and automation that will be introduced after successful field testing.");
        jt2.setBounds(250,540,1000,125);
        jt2.setFont(new Font("SERIF",Font.ROMAN_BASELINE,15));
        jt2.setEditable(false);
        jt2.setLineWrap(true);
        jt2.setForeground(Color.BLACK);
        jt2.setBackground(getBackground());
        jt2.setWrapStyleWord(true);
        jt2.setBorder(null);

        add(jmb);
        add(jl1);
        add(js1);
        add(jt1);
        add(jt2);
        add(js2);

        setVisible(true);
    }
//    public static void main(String[] ar)
//    {
//        new ApplicationPage("AravindhanRam");
//    }
}
