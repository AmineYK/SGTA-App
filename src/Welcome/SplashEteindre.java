package Welcome;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class SplashEteindre extends JFrame {

	private JPanel contentPane;
	private static JLabel label_1;
	private static JProgressBar progressBar;
	private JLabel lblChargement;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		      
			

			
				
					
					SplashEteindre frame = new SplashEteindre();
					frame.setVisible(true);
					try {
						UIManager.setLookAndFeel(new NimbusLookAndFeel());
						int x;
						try {
							
							for(x=0;x<=100;x++){
								SplashEteindre.progressBar.setValue(x);
								Thread.sleep(50);
								SplashEteindre.label_1.setText(Integer.toString(x)+"%");
							    if(x==100){
							          frame.dispose();
							    }
							    
						} }catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
						
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
					
			
	
	}

	/**
	 * Create the frame.
	 */
	public SplashEteindre() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
			
			
		
		
		JLabel lblSgta = new JLabel("Systeme de Gestion du Trafic Aerien");
		lblSgta.setForeground(new Color(153,255,255));
		lblSgta.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 26));
		lblSgta.setBounds(10, 139, 480, 41);
		contentPane.add(lblSgta);
		
		JLabel lblSgta_1 = new JLabel("SGTA");
		lblSgta_1.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 73));
		lblSgta_1.setForeground(new Color(153,255,255));
		lblSgta_1.setBounds(50, 49, 196, 80);
		contentPane.add(lblSgta_1);
		
		 progressBar = new JProgressBar();
		 progressBar.setBackground(Color.DARK_GRAY);
		 progressBar.setForeground(new Color(153,255,255));
		// progressBar.setForeround(Color.green)
		progressBar.setBounds(0, 238, 500, 13);
		contentPane.add(progressBar);
		
		label_1 = new JLabel("");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(373, 207, 53, 30);
		contentPane.add(label_1);
		
		lblChargement = new JLabel("Mise hors tension du programme ...");
		lblChargement.setFont(new Font("Sitka Small", Font.BOLD | Font.ITALIC, 13));
		lblChargement.setForeground(Color.WHITE);
		lblChargement.setBounds(90, 216, 293, 14);
		contentPane.add(lblChargement);
		
		JLabel lblVersion = new JLabel("version 1.0");
		lblVersion.setForeground(new Color(153,255,255));
		lblVersion.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 37));
		lblVersion.setBounds(256, 85, 201, 41);
		contentPane.add(lblVersion);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/1.gif")));
		label.setBounds(0, 0, 500, 281);
		contentPane.add(label);
	
	 //afficher();
		
}
	
}