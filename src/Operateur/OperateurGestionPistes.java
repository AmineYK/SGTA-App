package Operateur;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class OperateurGestionPistes extends JFrame {

	private JPanel contentPane;
	private JTextField numerofield;
	private JTextField largeurfield;
	private JTable pistestable;
	JComboBox etatbox;
	Connection cnx = null;
	Connection cnx1 = null;
	PreparedStatement prepared = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat = null;
	JLabel lblerror;
	JLabel lblerror1;
	Character ccc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurGestionPistes frame = new OperateurGestionPistes();

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
	public OperateurGestionPistes() {
		super("Operateur - Gestion des Pistes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1203, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		cnx1 = ConnexionMySql.ConnectionDB();

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenOperateur obj = new FenOperateur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(10, 0, 46, 33);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(31, 44, 441, 466);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAjouter = new JButton("Ajouter");
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
				String sd = null;
				if (lblerror.getText().equals("") && lblerror1.getText().equals("")) {
					String sql = " insert into piste (num_piste , largeur , etat ) values ( ? , ? , ? ) ";

					try {
						if (!numerofield.getText().equals("") && !largeurfield.getText().equals("")) {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, numerofield.getText().toString());
							prepared.setString(2, largeurfield.getText().toString());
							prepared.setString(3, etatbox.getSelectedItem().toString());

							prepared.execute();
							JOptionPane.showMessageDialog(null, "Piste ajouté");
							updatetable();

							String sql1 = "insert into nbpiste (num_piste,nombre) values(? , ? )";
							sd = String.valueOf(0);
							prepared1 = cnx1.prepareStatement(sql1);
							prepared1.setString(1, numerofield.getText().toString());
							prepared1.setString(2, sd);
							prepared1.execute();

							numerofield.setText("");
							largeurfield.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Remplissez les champs");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
				}
			}
		});
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnAjouter.setBounds(10, 428, 109, 30);
		panel.add(btnAjouter);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(153, 255, 255));
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimer.setBackground(new Color(153, 255, 255));
				btnSupprimer.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnSupprimer.setForeground(new Color(153, 255, 255));
				btnSupprimer.setBackground(new Color(105, 105, 105));
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ligne = pistestable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = pistestable.getModel().getValueAt(ligne, 0).toString();

					String sql = "delete from piste where num_piste =" + id + " ";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Piste supprimé");
						updatetable();
						largeurfield.setText("");
						numerofield.setText("");

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
					}
				}

			}
		});
		btnSupprimer.setIcon(new ImageIcon(getClass().getResource("/icons8-supprimer-22.png")));
		btnSupprimer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnSupprimer.setBounds(142, 428, 139, 30);
		panel.add(btnSupprimer);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(153, 255, 255));
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnModifier.setBackground(new Color(153, 255, 255));
				btnModifier.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnModifier.setForeground(new Color(153, 255, 255));
				btnModifier.setBackground(new Color(105, 105, 105));
			}
		});
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("")) {
					int ligne = pistestable.getSelectedRow();
					if (ligne == -1) {
						JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
					} else {
						String id = pistestable.getModel().getValueAt(ligne, 0).toString();

						String sql = "update piste set num_piste = ? , largeur = ? , etat = ?  where num_piste = " + id
								+ " ";

						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, numerofield.getText().toString());
							prepared.setString(2, largeurfield.getText().toString());
							prepared.setString(3, etatbox.getSelectedItem().toString());
							prepared.execute();
							JOptionPane.showMessageDialog(null, "Piste mise à jour");
							updatetable();
							numerofield.setText("");
							largeurfield.setText("");

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
				}
			}
		});
		btnModifier.setIcon(new ImageIcon(getClass().getResource("/icons8-modifier-22.png")));
		btnModifier.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnModifier.setBounds(312, 428, 119, 30);
		panel.add(btnModifier);

		numerofield = new JTextField();
		numerofield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					numerofield.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				} else {
					numerofield.setEditable(true);
					lblerror.setText("");
				}

				int key = e.getKeyCode();
				if (key == 10) {
					largeurfield.requestFocus();
				}
			}
		});
		numerofield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numerofield.setBounds(218, 63, 177, 22);
		panel.add(numerofield);
		numerofield.setColumns(10);

		largeurfield = new JTextField();
		largeurfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					largeurfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					largeurfield.setEditable(true);
					lblerror1.setText("");
				}
			}
		});
		largeurfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		largeurfield.setColumns(10);
		largeurfield.setBounds(199, 163, 177, 22);
		panel.add(largeurfield);

		etatbox = new JComboBox();
		etatbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		etatbox.setModel(
				new DefaultComboBoxModel(new String[] { "Degag\u00E9", "Non-utilis\u00E9", "Res\u00E9rv\u00E9" }));
		etatbox.setBounds(218, 264, 177, 22);
		panel.add(etatbox);

		JLabel lblEtat = new JLabel("Etat :");
		lblEtat.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblEtat.setBounds(117, 267, 102, 14);
		panel.add(lblEtat);

		JLabel lblLargeurOuSeuil = new JLabel("Largeur ou seuil :");
		lblLargeurOuSeuil.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblLargeurOuSeuil.setBounds(64, 166, 155, 14);
		panel.add(lblLargeurOuSeuil);

		JLabel lblNumero = new JLabel("Numero :");
		lblNumero.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNumero.setBounds(117, 66, 102, 14);
		panel.add(lblNumero);

		JLabel lblEnKm = new JLabel("en Km");
		lblEnKm.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 10));
		lblEnKm.setBounds(385, 166, 46, 14);
		panel.add(lblEnKm);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(184, 82, 165, 14);
		panel.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(184, 182, 165, 14);
		panel.add(lblerror1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(721, 44, 441, 466);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnVoirLesPistes = new JButton("Voir les Pistes Exsitantes ");
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
		btnVoirLesPistes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirLesPistes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLesPistes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirLesPistes.setBounds(106, 9, 236, 32);
		panel_1.add(btnVoirLesPistes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 421, 412);
		panel_1.add(scrollPane);

		pistestable = new JTable();
		pistestable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = pistestable.getSelectedRow();
				String id = pistestable.getModel().getValueAt(ligne, 0).toString();

				String sql = " select * from piste where num_piste =" + id + " ";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if (resultat.next()) {
						numerofield.setText(resultat.getString("num_piste"));
						largeurfield.setText(resultat.getString("largeur"));
						etatbox.setSelectedItem(resultat.getString("etat"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pistestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		pistestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(pistestable);

		JButton btnSignalerProblemes = new JButton("Signaler Problemes");
		btnSignalerProblemes.setBackground(new Color(153, 255, 255));
		btnSignalerProblemes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperateurSignalerProblemes obj = new OperateurSignalerProblemes();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);

			}
		});
		btnSignalerProblemes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignalerProblemes.setBackground(new Color(153, 255, 255));
				btnSignalerProblemes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnSignalerProblemes.setForeground(new Color(153, 255, 255));
				btnSignalerProblemes.setBackground(new Color(105, 105, 105));
			}
		});
		btnSignalerProblemes.setIcon(new ImageIcon(getClass().getResource("/icons8-caisse-\u00E0-outils-22.png")));
		btnSignalerProblemes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnSignalerProblemes.setBounds(507, 264, 182, 33);
		contentPane.add(btnSignalerProblemes);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -29, 1200, 560);
		contentPane.add(label);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();

		String sql = " select * from piste";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			pistestable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
