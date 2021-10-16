package Directeur;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class DirecteurGestionPistes extends JFrame {

	private JPanel contentPane;
	private JTable pistestable;
	JTextArea problemearea;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTextField pistefield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					DirecteurGestionPistes frame = new DirecteurGestionPistes();

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
	public DirecteurGestionPistes() {
		super("Directeur - Gestions des Pistes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1204, 560);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/Les Icones/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenDirecteur obj = new FenDirecteur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/Les Icones/back.png")));
		lblNewLabel.setBounds(10, 0, 46, 35);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(98, 57, 590, 453);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 570, 402);
		panel.add(scrollPane);

		pistestable = new JTable();
		pistestable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = pistestable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une Piste dans la table");
				} else {

					String id = pistestable.getModel().getValueAt(ligne, 0).toString();
					String sql = " select * from piste where num_piste = " + id + " ";

					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						if (resultat.next()) {
							pistefield.setText(resultat.getString("num_piste"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		pistestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		pistestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(pistestable);

		JButton btnVoirLesPistes = new JButton("Voir les Pistes de l'Aeroport");
		btnVoirLesPistes.setBackground(new Color(153, 255, 255));
		btnVoirLesPistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesPistes.setBackground(new Color(153, 255, 255));
				btnVoirLesPistes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesPistes.setForeground(new Color(153, 255, 255));
				btnVoirLesPistes.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesPistes.setBounds(158, 8, 281, 29);
		panel.add(btnVoirLesPistes);
		btnVoirLesPistes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = " select * from piste";

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					pistestable.setModel(DbUtils.resultSetToTableModel(resultat));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirLesPistes.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-eye-22.png")));
		btnVoirLesPistes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));

		JButton btnSignalerReparations = new JButton("Signaler Reparations \u00E0 faire");
		btnSignalerReparations.setBackground(new Color(153, 255, 255));
		btnSignalerReparations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignalerReparations.setBackground(new Color(153, 255, 255));
				btnSignalerReparations.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnSignalerReparations.setForeground(new Color(153, 255, 255));
				btnSignalerReparations.setBackground(new Color(105, 105, 105));
			}
		});
		btnSignalerReparations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = pistestable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une Piste dans la table");
				} else {
					String sql = "insert into reparationpiste (num_piste , repration ) values ( ? , ? )";
					if (!problemearea.getText().equals("")) {
						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, pistefield.getText().toString());
							prepared.setString(2, problemearea.getText().toString());

							prepared.execute();
							JOptionPane.showMessageDialog(null, "Une Reparation pour cette Piste a ete demandée");
							problemearea.setText("");
							pistefield.setText("");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Champ Probleme vide ! Veuillez le remplir SVP !");
					}
				}
			}
		});
		btnSignalerReparations.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-caisse-\u00E0-outils-22.png")));
		btnSignalerReparations.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnSignalerReparations.setBounds(875, 379, 263, 31);
		contentPane.add(btnSignalerReparations);

		pistefield = new JTextField();
		pistefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				JOptionPane.showMessageDialog(null, "Veuillez selectionner une piste dans la table ");
			}
		});
		pistefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		pistefield.setBounds(952, 173, 127, 22);
		contentPane.add(pistefield);
		pistefield.setColumns(10);

		JLabel lblNumeroDeLa = new JLabel("Numero de la Piste :");
		lblNumeroDeLa.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNumeroDeLa.setBounds(764, 175, 170, 14);
		contentPane.add(lblNumeroDeLa);

		problemearea = new JTextArea();
		problemearea.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		problemearea.setLineWrap(true);
		problemearea.setBounds(913, 205, 212, 163);
		contentPane.add(problemearea);

		JLabel lblProblemes = new JLabel("problemes :");
		lblProblemes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblProblemes.setBounds(806, 269, 170, 14);
		contentPane.add(lblProblemes);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/GTAFE.png")));
		label.setBounds(0, -56, 1200, 650);
		contentPane.add(label);
	}
}
