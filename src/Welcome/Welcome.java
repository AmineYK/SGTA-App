package Welcome;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Welcome extends JFrame {
	JCheckBox  chckbxJaccepteLesConditions;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Welcome frame = new Welcome();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Welcome() {
		super("Bienvenue au SGTA Systeme de Gestion de Trafic Aerien");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,905,396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/Les Icones/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnContinuer = new JButton("Continuer...");
		btnContinuer.setForeground(new Color(0, 0, 0));
		btnContinuer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnContinuer.setBackground(new Color(153,255,255));
				btnContinuer.setForeground(Color.black);
			}
			public void mouseEntered(MouseEvent e) {
				btnContinuer.setForeground(new Color(153,255,255));
				btnContinuer.setBackground(new Color(105,105,105));
			}

		});
		btnContinuer.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-trier-\u00E0-droite-25.png")));
		btnContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxJaccepteLesConditions.isSelected()){
				Authentification obj = new Authentification();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();}else{
					JOptionPane.showMessageDialog(null,"Vous Devez accepter les conditions du contrat du logiciel !");
				}
			}
		});
		btnContinuer.setBackground(new Color(153, 255, 255));
		btnContinuer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnContinuer.setBounds(370, 316, 161, 31);
		contentPane.add(btnContinuer);
		
		 chckbxJaccepteLesConditions = new JCheckBox("J'accepte les conditions");
		chckbxJaccepteLesConditions.setBackground(new Color(135, 255, 255));
		chckbxJaccepteLesConditions.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		chckbxJaccepteLesConditions.setBounds(93, 331, 161, 23);
		contentPane.add(chckbxJaccepteLesConditions);
		
		JButton button = new JButton("\u00E0 Propos ");
		button.setBackground(new Color(135, 255, 255));
		button.setForeground(new Color(0, 0, 0));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(153,255,255));
				button.setForeground(Color.black);
			}
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(153,255,255));
				button.setBackground(new Color(105,105,105));
			}

		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apropos obj = new apropos();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-question-23.png")));
		button.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		button.setBounds(588, 316, 161, 30);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(240, 240, 240));
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/134.png")));
		label.setBounds(0, -101, 1380, 557);
		contentPane.add(label);
	}
}
