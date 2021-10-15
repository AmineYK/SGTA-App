package Welcome;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class apropos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					apropos frame = new apropos();
					
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
	public apropos() {
		super("à Propos");
		setResizable(false);
		setBounds(100, 100, 418, 446);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("   Present\u00E9 par YOUCEF KHODJA Amine ");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(22, 338, 353, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblProjetGenieLogiciel = new JLabel("      Projet Genie Logiciel L2 ISIL A ");
		lblProjetGenieLogiciel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProjetGenieLogiciel.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 13));
		lblProjetGenieLogiciel.setBounds(0, 11, 382, 27);
		contentPane.add(lblProjetGenieLogiciel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/SGTA_logo1.jpg")));
		label.setBounds(70, 49, 258, 278);
		contentPane.add(label);
		
		JLabel lblAnneUniversitaire = new JLabel("         Ann\u00E9e Universitaire 2019/2020");
		lblAnneUniversitaire.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAnneUniversitaire.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblAnneUniversitaire.setBounds(0, 30, 382, 27);
		contentPane.add(lblAnneUniversitaire);
		
		JLabel lblEtKhadirSalim = new JLabel("Et KHADIR Salim");
		lblEtKhadirSalim.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEtKhadirSalim.setForeground(Color.BLACK);
		lblEtKhadirSalim.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 17));
		lblEtKhadirSalim.setBounds(112, 364, 157, 30);
		contentPane.add(lblEtKhadirSalim);
	}
}
