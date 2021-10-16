package CompagnieAerienne;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompagnieLesDeuxFrames extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompagnieLesDeuxFrames frame = new CompagnieLesDeuxFrames();
					
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
	public CompagnieLesDeuxFrames() {
		super("Verification");
		setResizable(false);
		setBounds(100, 100, 519, 344);
		setLocationRelativeTo(null);;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/Les Icones/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		
		JButton btnFicheDinformations = new JButton("Fiche d'Informations");
		btnFicheDinformations.setBackground(new Color(153,255,255));
		btnFicheDinformations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnFicheDinformations.setBackground(new Color(153,255,255));
				btnFicheDinformations.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnFicheDinformations.setForeground(new Color(153,255,255));
				btnFicheDinformations.setBackground(new Color(105,105,105));
			}
		});
		btnFicheDinformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompagnieVoirFicheInfos obj = new CompagnieVoirFicheInfos();
				obj.setLocationRelativeTo(null);
				obj.setVisible(true);
				dispose();
			}
		});
		btnFicheDinformations.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-information-22.png")));
		btnFicheDinformations.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnFicheDinformations.setBounds(147, 73, 206, 34);
		contentPane.add(btnFicheDinformations);
		
		JButton btnSuggestionDeDates = new JButton("Suggestion de Dates");
		btnSuggestionDeDates.setBackground(new Color(153,255,255));
		btnSuggestionDeDates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSuggestionDeDates.setBackground(new Color(153,255,255));
				btnSuggestionDeDates.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnSuggestionDeDates.setForeground(new Color(153,255,255));
				btnSuggestionDeDates.setBackground(new Color(105,105,105));
			}
		});
		btnSuggestionDeDates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompagnieVoirSuggestion obj = new CompagnieVoirSuggestion();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSuggestionDeDates.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-jusqu'\u00E0-la-date-22.png")));
		btnSuggestionDeDates.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnSuggestionDeDates.setBounds(147, 209, 206, 34);
		contentPane.add(btnSuggestionDeDates);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			FenCompagnie obj = new FenCompagnie();
			obj.setVisible(true);
			obj.setLocationRelativeTo(null);
			dispose();
			}
		});
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/back.png")));
		label.setBounds(10, 11, 46, 24);
		contentPane.add(label);
	}
}
