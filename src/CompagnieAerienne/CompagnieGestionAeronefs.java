package CompagnieAerienne;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class CompagnieGestionAeronefs extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField numerofield;
	private JTextField nomconsfield;
	private JTextField nbpassfield;
	private JTextField fretfield;
	private JTextField poidsfield;
	private JTable aeronefstable;
	private String id = null;
	private JComboBox<String> nomBox;
	private JComboBox<String> typebox;
	private JComboBox<String> categoriebox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	JLabel lblerror;
	JLabel lblerror1;
	JLabel lblerror2;
	JLabel lblerror4;
	Character ccc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					CompagnieGestionAeronefs frame = new CompagnieGestionAeronefs();
					ImageIcon obj = new ImageIcon("C:\\Users\\m2k\\Desktop\\Amine YKh\\amine\\PROJET GL\\logo.png");
					frame.setIconImage(obj.getImage());
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
	public CompagnieGestionAeronefs() {
		super("Compagnie Aerienne - Gestion des Aeronefs ");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenCompagnie oj = new FenCompagnie();
				oj.setVisible(true);
				oj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(10, 11, 46, 24);
		contentPane.add(label_1);

		JPanel panel = new JPanel();

		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20, 46, 453, 467);
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
				if (lblerror.getText().equals("") && lblerror1.getText().equals("") && lblerror2.getText().equals("")
						&& lblerror4.getText().equals("")) {
					if (!numerofield.getText().equals("") && !nomconsfield.getText().equals("")
							&& !poidsfield.getText().equals("") && !nbpassfield.getText().equals("")
							&& !fretfield.getText().equals("")) {
						String sql = " insert into aeronef (num_aerof , type , nom_constructeur , poids , nb_passagers , fret , categorie , nom )values(? , ? , ? , ? , ? , ? , ? , ? )";
						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, numerofield.getText().toString());
							prepared.setString(2, typebox.getSelectedItem().toString());
							prepared.setString(3, nomconsfield.getText().toString());
							prepared.setString(4, poidsfield.getText().toString());
							prepared.setString(5, nbpassfield.getText().toString());
							prepared.setString(6, fretfield.getText().toString());
							prepared.setString(7, categoriebox.getSelectedItem().toString());
							prepared.setString(8, nomBox.getSelectedItem().toString());

							prepared.execute();
							JOptionPane.showMessageDialog(null, "Aeronef ajouté");
							updatetable();
							numerofield.setText("");
							poidsfield.setText("");
							fretfield.setText("");
							nbpassfield.setText("");
							nomconsfield.setText("");

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Remplissez le(s) champ(s) !!");

					}
				} else {
					JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
				}
			}
		});
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnAjouter.setBounds(10, 425, 120, 31);
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
				int ligne = aeronefstable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = aeronefstable.getModel().getValueAt(ligne, 0).toString();

					String sql = " delete from aeronef where num_aerof =" + id + " ";

					try {
						prepared = cnx.prepareStatement(sql);
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Aeronef supprimé ");
						updatetable();
						fretfield.setText("");
						numerofield.setText("");
						nbpassfield.setText("");
						poidsfield.setText("");
						nomconsfield.setText("");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
					}
				}
			}
		});
		btnSupprimer.setIcon(new ImageIcon(getClass().getResource("/icons8-supprimer-22.png")));
		btnSupprimer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnSupprimer.setBounds(162, 425, 128, 31);
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
				int ligne = aeronefstable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = aeronefstable.getModel().getValueAt(ligne, 0).toString();
					if (lblerror.getText().equals("") && lblerror1.getText().equals("")
							&& lblerror2.getText().equals("") && lblerror4.getText().equals("")) {
						String sql = " update aeronef set num_aerof = ? , type = ? , nom_constructeur = ? , poids = ? , nb_passagers = ? , fret = ? , categorie = ? , nom = ? where num_aerof ="
								+ id + " ";

						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, numerofield.getText().toString());
							prepared.setString(2, typebox.getSelectedItem().toString());
							prepared.setString(3, nomconsfield.getText().toString());
							prepared.setString(4, poidsfield.getText().toString());
							prepared.setString(5, nbpassfield.getText().toString());
							prepared.setString(6, fretfield.getText().toString());
							prepared.setString(7, categoriebox.getSelectedItem().toString());
							prepared.setString(8, nomBox.getSelectedItem().toString());

							prepared.execute();
							JOptionPane.showMessageDialog(null, "Aeronef mis à jour");
							updatetable();
							fretfield.setText("");
							numerofield.setText("");
							nbpassfield.setText("");
							poidsfield.setText("");
							nomconsfield.setText("");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
					}
				}
			}
		});
		btnModifier.setIcon(new ImageIcon(getClass().getResource("/icons8-modifier-22.png")));
		btnModifier.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnModifier.setBounds(323, 425, 120, 31);
		panel.add(btnModifier);

		numerofield = new JTextField();
		numerofield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * try { int i = Integer.parseInt(numerofield.getText()); lblerror.setText("");
				 * } catch (NumberFormatException e2) { lblerror.setText("Champ Invalide"); }
				 */
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					numerofield.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				} else {
					numerofield.setEditable(true);
					lblerror.setText("");
				}

			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					nomconsfield.requestFocus();
				}
			}
		});
		numerofield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numerofield.setBounds(229, 27, 186, 22);
		panel.add(numerofield);
		numerofield.setColumns(10);

		nomconsfield = new JTextField();
		nomconsfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					nbpassfield.requestFocus();
				}
			}
		});
		nomconsfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nomconsfield.setColumns(10);
		nomconsfield.setBounds(229, 82, 186, 31);
		panel.add(nomconsfield);

		nbpassfield = new JTextField();
		nbpassfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * try { int i = Integer.parseInt(nbpassfield.getText()); lblerror1.setText("");
				 * } catch (NumberFormatException e2) { lblerror1.setText("Champ Invalide"); }
				 */
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbpassfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					nbpassfield.setEditable(true);
					lblerror1.setText("");
				}
			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					fretfield.requestFocus();
				}
			}
		});
		nbpassfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbpassfield.setColumns(10);
		nbpassfield.setBounds(229, 182, 186, 22);
		panel.add(nbpassfield);

		fretfield = new JTextField();
		fretfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * try { int i = Integer.parseInt(fretfield.getText()); lblerror2.setText(""); }
				 * catch (NumberFormatException e2) { lblerror2.setText("Champ Invalide"); }
				 */
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					fretfield.setEditable(false);
					lblerror2.setText("Que des chiffres SVP");
				} else {
					fretfield.setEditable(true);
					lblerror2.setText("");
				}
			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					poidsfield.requestFocus();
				}
			}
		});
		fretfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		fretfield.setColumns(10);
		fretfield.setBounds(196, 232, 186, 22);
		panel.add(fretfield);

		poidsfield = new JTextField();
		poidsfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * try { int i = Integer.parseInt(poidsfield.getText()); lblerror4.setText("");
				 * } catch (NumberFormatException e2) { lblerror4.setText("Champ Invalide"); }
				 */
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					poidsfield.setEditable(false);
					lblerror4.setText("Que des chiffres SVP");
				} else {
					poidsfield.setEditable(true);
					lblerror4.setText("");
				}
			}
			/*
			 * public void keyPressed(KeyEvent e) { int key=e.getKeyCode(); if(key==10){
			 * passworddirecteurfield.requestFocus(); } }
			 */
		});
		poidsfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		poidsfield.setColumns(10);
		poidsfield.setBounds(196, 387, 186, 22);
		panel.add(poidsfield);

		categoriebox = new JComboBox();
		categoriebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		categoriebox.setModel(new DefaultComboBoxModel(
				new String[] { "De Sport et de Loisirs ", "Commerciaux", "A Services divers", "A Usage militaire" }));
		categoriebox.setBounds(229, 337, 186, 25);
		panel.add(categoriebox);

		typebox = new JComboBox();
		typebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		typebox.setModel(
				new DefaultComboBoxModel(new String[] { "Aeronef civil", "Aeronef militaire", "H\u00E9licoptaire civil",
						"Helicptaire militaire", "Aeronef bombardier", "Aeronef cargo ravitailleur" }));
		typebox.setBounds(229, 129, 186, 25);
		panel.add(typebox);

		JLabel lblNumro = new JLabel("Num\u00E9ro :");
		lblNumro.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNumro.setBounds(123, 30, 92, 14);
		panel.add(lblNumro);

		JLabel lblNomDuConstructeur = new JLabel("Nom du Constructeur :");
		lblNomDuConstructeur.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNomDuConstructeur.setBounds(44, 85, 136, 14);
		panel.add(lblNomDuConstructeur);

		JLabel lblPoids = new JLabel("Poids :");
		lblPoids.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblPoids.setBounds(123, 390, 92, 14);
		panel.add(lblPoids);

		JLabel lblNombreDePassagers = new JLabel("Nombre de PAssagers Possible :");
		lblNombreDePassagers.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNombreDePassagers.setBounds(22, 185, 209, 14);
		panel.add(lblNombreDePassagers);

		JLabel lblFretTransportable = new JLabel("Fret Transportable :");
		lblFretTransportable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblFretTransportable.setBounds(66, 235, 153, 14);
		panel.add(lblFretTransportable);

		JLabel lblNomDeLa = new JLabel("Nom de la Compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNomDeLa.setBounds(44, 291, 153, 14);
		panel.add(lblNomDeLa);

		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblCatgorie.setBounds(88, 340, 92, 14);
		panel.add(lblCatgorie);

		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblType.setBounds(105, 132, 92, 14);
		panel.add(lblType);

		nomBox = new JComboBox();
		nomBox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nomBox.setBounds(229, 288, 186, 25);
		remplirnom();
		panel.add(nomBox);

		JLabel lblEnTonne = new JLabel("en tonne");
		lblEnTonne.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 10));
		lblEnTonne.setBounds(392, 235, 51, 14);
		panel.add(lblEnTonne);

		JLabel label_2 = new JLabel("en tonne");
		label_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 10));
		label_2.setBounds(392, 390, 51, 14);
		panel.add(label_2);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(239, 50, 176, 14);
		panel.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(239, 207, 160, 14);
		panel.add(lblerror1);

		lblerror2 = new JLabel("");
		lblerror2.setForeground(Color.RED);
		lblerror2.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror2.setBounds(206, 257, 165, 14);
		panel.add(lblerror2);

		lblerror4 = new JLabel("");
		lblerror4.setForeground(Color.RED);
		lblerror4.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror4.setBounds(206, 408, 165, 14);
		panel.add(lblerror4);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(516, 46, 657, 467);
		contentPane.add(panel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 637, 445);
		panel_1.add(scrollPane);

		aeronefstable = new JTable();
		aeronefstable.setSurrendersFocusOnKeystroke(true);
		aeronefstable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		aeronefstable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = aeronefstable.getSelectedRow();
				String id = aeronefstable.getModel().getValueAt(ligne, 0).toString();

				String sql = "select * from aeronef where num_aerof =" + id + " ";

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();

					if (resultat.next()) {
						numerofield.setText(resultat.getString("num_aerof"));
						poidsfield.setText(resultat.getString("poids"));
						fretfield.setText(resultat.getString("fret"));
						nbpassfield.setText(resultat.getString("nb_passagers"));
						nomconsfield.setText(resultat.getString("nom_constructeur"));
						nomBox.setSelectedItem(resultat.getString("nom"));
						categoriebox.setSelectedItem(resultat.getString("categorie"));
						typebox.setSelectedItem(resultat.getString("type"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		aeronefstable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		aeronefstable.setBackground(new Color(153, 255, 255));
		aeronefstable.setForeground(Color.black);
		scrollPane.setViewportView(aeronefstable);

		JButton btnVoirListeDes = new JButton("Voir Liste des Aeronefs");
		btnVoirListeDes.setBounds(734, 6, 256, 37);
		contentPane.add(btnVoirListeDes);
		btnVoirListeDes.setBackground(new Color(153, 255, 255));
		btnVoirListeDes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirListeDes.setBackground(new Color(153, 255, 255));
				btnVoirListeDes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirListeDes.setForeground(new Color(153, 255, 255));
				btnVoirListeDes.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirListeDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirListeDes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirListeDes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(20, -57, 1200, 650);
		contentPane.add(label);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label_3.setBounds(0, -57, 1200, 650);
		contentPane.add(label_3);
	}

	public void remplirnom() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from connecter";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				nomBox.addItem(resultat.getString("nom_compagnie"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();

		String pr = "Privé";

		String sql = "select * from aeronef where  categorie <> '" + pr + "' and nom = '"
				+ nomBox.getSelectedItem().toString() + "'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			aeronefstable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
