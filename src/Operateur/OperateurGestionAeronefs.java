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

public class OperateurGestionAeronefs extends JFrame {

	private JPanel contentPane;
	private JTextField nomconsfield;
	private JTextField nbpassfield;
	private JTextField fretfield;
	private JTextField poidsfield;
	private JTable aeronefstable;
	private JTextField numerofield;
	JComboBox nomBox;
	JComboBox typebox;
	JComboBox categoriebox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	JLabel lblerror;
	JLabel lblerror1;
	JLabel lblerror3;
	JLabel lblerror2;
	Character ccc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurGestionAeronefs frame = new OperateurGestionAeronefs();

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
	public OperateurGestionAeronefs() {
		super("Operateur - Gestion des Aeronefs");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1201, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/Les Icones/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenOperateur obj = new FenOperateur();
				obj.setVisible(true);
				dispose();
				obj.setLocationRelativeTo(null);
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/Les Icones/back.png")));
		label_1.setBounds(10, 4, 46, 33);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(20, 43, 453, 467);
		contentPane.add(panel);

		JButton button = new JButton("Ajouter");
		button.setBackground(new Color(153, 255, 255));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(153, 255, 255));
				button.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(153, 255, 255));
				button.setBackground(new Color(105, 105, 105));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("") && lblerror2.getText().equals("")
						&& lblerror3.getText().equals("")) {
					String sql = " insert into aeronef (num_aerof , type , nom_constructeur , poids , nb_passagers , fret , categorie , nom )values(? , ? , ? , ? , ? , ? , ? , ? )";
					try {
						if (!numerofield.getText().equals("") && !nomconsfield.getText().equals("")
								&& !poidsfield.getText().equals("") && !nbpassfield.getText().equals("")
								&& !fretfield.getText().equals("")) {
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
						} else {
							JOptionPane.showMessageDialog(null, "Remplissez Les champs");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
				}
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-ajouter-22.png")));
		button.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		button.setBounds(10, 433, 112, 26);
		panel.add(button);

		JButton button_1 = new JButton("Supprimer");
		button_1.setBackground(new Color(153, 255, 255));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setBackground(new Color(153, 255, 255));
				button_1.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(new Color(153, 255, 255));
				button_1.setBackground(new Color(105, 105, 105));
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = aeronefstable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "selectionnez une ligne");
				} else {
					String id = numerofield.getText().toString();
					String id1 = "Privé";

					String sql = " delete from aeronef where  num_aerof =" + id + " and categorie = '" + id1 + "'";

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
		button_1.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-supprimer-22.png")));
		button_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		button_1.setBounds(162, 433, 128, 26);
		panel.add(button_1);

		JButton button_2 = new JButton("Modifier");
		button_2.setBackground(new Color(153, 255, 255));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_2.setBackground(new Color(153, 255, 255));
				button_2.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				button_2.setForeground(new Color(153, 255, 255));
				button_2.setBackground(new Color(105, 105, 105));
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("") && lblerror2.getText().equals("")
						&& lblerror3.getText().equals("")) {
					int ligne = aeronefstable.getSelectedRow();
					if (ligne == -1) {
						JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
					} else {
						String id = aeronefstable.getModel().getValueAt(ligne, 0).toString();

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
					}
				} else {
					JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
				}
			}
		});
		button_2.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-modifier-22.png")));
		button_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		button_2.setBounds(323, 433, 120, 26);
		panel.add(button_2);

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
		nomconsfield.setBounds(229, 82, 186, 22);
		panel.add(nomconsfield);

		nbpassfield = new JTextField();
		nbpassfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbpassfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					nbpassfield.setEditable(true);
					lblerror1.setText("");
				}

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
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					fretfield.setEditable(false);
					lblerror2.setText("Que des chiffres SVP");
				} else {
					fretfield.setEditable(true);
					lblerror2.setText("");
				}

				int key = e.getKeyCode();
				if (key == 10) {
					poidsfield.requestFocus();
				}
			}
		});
		fretfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		fretfield.setColumns(10);
		fretfield.setBounds(229, 232, 186, 22);
		panel.add(fretfield);

		poidsfield = new JTextField();
		poidsfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					poidsfield.setEditable(false);
					lblerror3.setText("Que des chiffres SVP");
				} else {
					poidsfield.setEditable(true);
					lblerror3.setText("");
				}
			}
		});
		poidsfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		poidsfield.setColumns(10);
		poidsfield.setBounds(229, 387, 186, 22);
		panel.add(poidsfield);

		categoriebox = new JComboBox();
		categoriebox.setModel(new DefaultComboBoxModel(new String[] { "Priv\u00E9 " }));
		categoriebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		categoriebox.setBounds(229, 337, 186, 22);
		panel.add(categoriebox);

		typebox = new JComboBox();
		typebox.setModel(
				new DefaultComboBoxModel(new String[] { "Aeronef civil", "Aeronef militaire", "H\u00E9licoptaire civil",
						"Helicptaire militaire", "Aeronef bombardier", "Aeronef cargo ravitailleur" }));
		typebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		typebox.setBounds(229, 129, 186, 22);
		panel.add(typebox);

		JLabel label_2 = new JLabel("Num\u00E9ro :");
		label_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_2.setBounds(123, 30, 92, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Nom du Constructeur :");
		label_3.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_3.setBounds(44, 85, 136, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Poids :");
		label_4.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_4.setBounds(123, 390, 92, 14);
		panel.add(label_4);

		JLabel label_5 = new JLabel("Nombre de PAssagers Possible :");
		label_5.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_5.setBounds(22, 185, 209, 14);
		panel.add(label_5);

		JLabel label_6 = new JLabel("Fret Transportable :");
		label_6.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_6.setBounds(66, 235, 153, 14);
		panel.add(label_6);

		JLabel label_7 = new JLabel("Nom de la Compagnie :");
		label_7.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_7.setBounds(44, 291, 153, 14);
		panel.add(label_7);

		JLabel label_8 = new JLabel("Cat\u00E9gorie :");
		label_8.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_8.setBounds(88, 340, 92, 14);
		panel.add(label_8);

		JLabel label_9 = new JLabel("Type :");
		label_9.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		label_9.setBounds(105, 132, 92, 14);
		panel.add(label_9);

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
					nomconsfield.requestFocus();
				}
			}
		});
		numerofield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numerofield.setColumns(10);
		numerofield.setBounds(229, 27, 186, 22);
		panel.add(numerofield);

		nomBox = new JComboBox();
		nomBox.setModel(new DefaultComboBoxModel(new String[] { "Sans Compagnie" }));
		nomBox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nomBox.setBounds(229, 288, 186, 22);
		panel.add(nomBox);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(198, 47, 176, 14);
		panel.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(198, 203, 176, 14);
		panel.add(lblerror1);

		lblerror2 = new JLabel("");
		lblerror2.setForeground(Color.RED);
		lblerror2.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror2.setBounds(198, 253, 176, 14);
		panel.add(lblerror2);

		lblerror3 = new JLabel("");
		lblerror3.setForeground(Color.RED);
		lblerror3.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror3.setBounds(198, 408, 176, 14);
		panel.add(lblerror3);
		// remplirnom();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(511, 43, 674, 467);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 654, 417);
		panel_1.add(scrollPane);

		aeronefstable = new JTable();
		aeronefstable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = aeronefstable.getSelectedRow();
				String id = aeronefstable.getModel().getValueAt(ligne, 0).toString();
				String id1 = "Privé";
				String sql = "select * from aeronef where num_aerof =" + id + " and categorie = '" + id1 + "'";

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
		scrollPane.setViewportView(aeronefstable);

		JButton btnVoirLesAeronefs = new JButton("Voir les Aeronefs en relation avec l'aeroport");
		btnVoirLesAeronefs.setBackground(new Color(153, 255, 255));
		btnVoirLesAeronefs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesAeronefs.setBackground(new Color(153, 255, 255));
				btnVoirLesAeronefs.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesAeronefs.setForeground(new Color(153, 255, 255));
				btnVoirLesAeronefs.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesAeronefs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirLesAeronefs.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-eye-22.png")));
		btnVoirLesAeronefs.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirLesAeronefs.setBounds(141, 6, 367, 29);
		panel_1.add(btnVoirLesAeronefs);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/GTAFE.png")));
		label.setBounds(0, -52, 1200, 650);
		contentPane.add(label);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from aeronef";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			aeronefstable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remplirnom() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from compagnie";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				nomBox.addItem(resultat.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
