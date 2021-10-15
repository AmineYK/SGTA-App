package Operateur;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;

public class OperateurSignalerProblemes extends JFrame {

	private JPanel contentPane;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurSignalerProblemes frame = new OperateurSignalerProblemes();

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
	public OperateurSignalerProblemes() {
		super("Operateur - Signaler des Problémes ");
		setBounds(100, 100, 439, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(34, 34, 346, 411);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblProblemes = new JLabel("Problemes :");
		lblProblemes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblProblemes.setBounds(10, 63, 69, 14);
		panel.add(lblProblemes);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		textArea.setLineWrap(true);
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(98, 34, 238, 310);
		panel.add(textArea);

		JButton btnAjouter = new JButton("Confirmer");
		btnAjouter.setBackground(new Color(153, 255, 255));
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnAjouter.setBackground(new Color(153, 255, 255));
				btnAjouter.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnAjouter.setForeground(new Color(153, 255, 255));
				btnAjouter.setBackground(new Color(105, 105, 105));
			}
		});
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " insert into problemesoperateur (probleme) values ( ? )";

				try {
					if (!textArea.getText().equals("")) {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textArea.getText().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Problemes Signalés");
						textArea.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Assurez-vous bien d'avoir signalé un probleme !");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.setBounds(60, 456, 135, 27);
		contentPane.add(btnAjouter);
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/icons8-bouton-de-radio-coch\u00E9-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBackground(new Color(153, 255, 255));
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnAnnuler.setBackground(new Color(153, 255, 255));
				btnAnnuler.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnAnnuler.setForeground(new Color(153, 255, 255));
				btnAnnuler.setBackground(new Color(105, 105, 105));
			}
		});
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuler.setIcon(new ImageIcon(getClass().getResource("/icons8-annuler-22.png")));
		btnAnnuler.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnAnnuler.setBounds(226, 456, 122, 27);
		contentPane.add(btnAnnuler);
	}
}
