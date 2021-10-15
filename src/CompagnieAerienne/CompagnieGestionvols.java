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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.toedter.calendar.JDateChooser;

import Welcome.ConnexionMySql;

public class CompagnieGestionvols extends JFrame {

	private JPanel contentPane;
	private JTextField heuredfield;
	private JTextField aeroportdfield;
	private JTextField heureafield;
	private JTextField aeroportafield;
	private JTextField nbescalesfield;
	private JTextField nbpassagersfield;
	private JTextField fretfield;
	JDateChooser dateChooser;
	JComboBox compagniebox;
	JComboBox priobox;
	JComboBox pilotebox;
	JComboBox copilote1box;
	JComboBox copilote2box;
	JComboBox numaerobox;
	JComboBox typebox;
	JComboBox steewardbox;
	JComboBox actionbox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	Connection cnx2 = null;
	PreparedStatement prepared2 = null;
	ResultSet resultat2 = null;
	private JTextField numerofield;
	JLabel lblerror;
	JLabel lblerror2;
	JLabel lblerror1;
	JLabel lblerror3;
	Character ccc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					CompagnieGestionvols frame = new CompagnieGestionvols();

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
	public CompagnieGestionvols() {
		super("Compagnie Aerienne - Gestions des Vols");
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

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FenCompagnie obj = new FenCompagnie();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(10, 11, 46, 30);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(42, 11, 650, 499);
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
						&& lblerror3.getText().equals("")) {
					if (!numerofield.getText().equals("") && !aeroportafield.getText().equals("")
							&& !aeroportdfield.getText().equals("") && !heuredfield.getText().equals("")
							&& !heureafield.getText().equals("") && !nbescalesfield.getText().equals("")
							&& !nbpassagersfield.getText().equals("") && !fretfield.getText().equals("")) {

						String dt = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
						String sql = "insert into vol (num_vol ,date_vol ,aero_d , aero_a , heure_d , heure_a ,priorite , nb_escales ,nb_passagers , fret , type_vol , num_aerof , nom , action ) values ( ? ,? ,? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
						try {
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, numerofield.getText().toString());
							prepared.setString(2, dt);
							prepared.setString(3, aeroportdfield.getText().toString());
							prepared.setString(4, aeroportafield.getText().toString());
							prepared.setString(5, heuredfield.getText().toString());
							prepared.setString(6, heureafield.getText().toString());
							prepared.setString(7, priobox.getSelectedItem().toString());
							prepared.setString(8, nbescalesfield.getText().toString());
							prepared.setString(9, nbpassagersfield.getText().toString());
							prepared.setString(10, fretfield.getText().toString());
							prepared.setString(11, typebox.getSelectedItem().toString());
							prepared.setString(12, numaerobox.getSelectedItem().toString());
							prepared.setString(13, compagniebox.getSelectedItem().toString());
							prepared.setString(14, actionbox.getSelectedItem().toString());

							prepared.execute();
							JOptionPane.showMessageDialog(null, "Vol ajouté");

							cnx1 = ConnexionMySql.ConnectionDB();
							String sql1 = "insert into listevols (num_vol , membre_comp ,fonction , nom ) values ( ? , ? , ? , ? )";
							String hoc = null;
							prepared1 = cnx1.prepareStatement(sql1);
							prepared1.setString(1, numerofield.getText().toString());
							prepared1.setString(2, pilotebox.getSelectedItem().toString());
							hoc = "Pilote Comandant de bord";
							prepared1.setString(3, hoc);
							prepared1.setString(4, compagniebox.getSelectedItem().toString());
							prepared1.execute();
							prepared1.setString(1, numerofield.getText().toString());
							prepared1.setString(2, copilote1box.getSelectedItem().toString());
							hoc = "Premier Co-Pilote";
							prepared1.setString(3, hoc);
							prepared1.setString(4, compagniebox.getSelectedItem().toString());
							prepared1.execute();
							prepared1.setString(1, numerofield.getText().toString());
							prepared1.setString(2, copilote2box.getSelectedItem().toString());
							hoc = "Deuxieme Co-Pilote";
							prepared1.setString(3, hoc);
							prepared1.setString(4, compagniebox.getSelectedItem().toString());
							prepared1.execute();
							prepared1.setString(1, numerofield.getText().toString());
							prepared1.setString(2, steewardbox.getSelectedItem().toString());
							hoc = "Steeward";
							prepared1.setString(3, hoc);
							prepared1.setString(4, compagniebox.getSelectedItem().toString());
							prepared1.execute();

							aeroportafield.setText("");
							aeroportdfield.setText("");
							heuredfield.setText("");
							heureafield.setText("");
							nbescalesfield.setText("");
							nbpassagersfield.setText("");
							fretfield.setText("");
							numerofield.setText("");

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Numero deja existant ! veuillez le changer");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Existance de champ(s) invalide(s)");
				}
			}
		});
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnAjouter.setBounds(256, 457, 153, 31);
		panel.add(btnAjouter);

		heuredfield = new JTextField();
		heuredfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					aeroportdfield.requestFocus();
				}
			}
		});
		heuredfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heuredfield.setColumns(10);
		heuredfield.setBounds(167, 94, 126, 22);
		panel.add(heuredfield);

		aeroportdfield = new JTextField();
		aeroportdfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					heureafield.requestFocus();
				}
			}
		});
		aeroportdfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		aeroportdfield.setColumns(10);
		aeroportdfield.setBounds(412, 83, 216, 31);
		panel.add(aeroportdfield);

		heureafield = new JTextField();
		heureafield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					aeroportafield.requestFocus();
				}
			}
		});
		heureafield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heureafield.setColumns(10);
		heureafield.setBounds(167, 125, 126, 22);
		panel.add(heureafield);

		aeroportafield = new JTextField();
		aeroportafield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					nbpassagersfield.requestFocus();
				}
			}
		});
		aeroportafield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		aeroportafield.setColumns(10);
		aeroportafield.setBounds(412, 125, 216, 31);
		panel.add(aeroportafield);

		nbescalesfield = new JTextField();
		nbescalesfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbescalesfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					nbescalesfield.setEditable(true);
					lblerror1.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					dateChooser.requestFocus();
				}
			}
		});
		nbescalesfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbescalesfield.setColumns(10);
		nbescalesfield.setBounds(167, 52, 126, 22);
		panel.add(nbescalesfield);

		nbpassagersfield = new JTextField();
		nbpassagersfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbpassagersfield.setEditable(false);
					lblerror2.setText("Que des chiffres SVP");
				} else {
					nbpassagersfield.setEditable(true);
					lblerror2.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					fretfield.requestFocus();
				}
			}
		});
		nbpassagersfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbpassagersfield.setColumns(10);
		nbpassagersfield.setBounds(167, 166, 126, 22);
		panel.add(nbpassagersfield);

		fretfield = new JTextField();
		fretfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					fretfield.setEditable(false);
					lblerror3.setText("Que des chiffres SVP");
				} else {
					fretfield.setEditable(true);
					lblerror3.setText("");
				}
			}
		});
		fretfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		fretfield.setColumns(10);
		fretfield.setBounds(150, 210, 126, 22);
		panel.add(fretfield);

		typebox = new JComboBox();
		typebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		typebox.setModel(new DefaultComboBoxModel(new String[] { "Commercial Regulier", "Commercial Occasionnel",
				"Humanitaire", "Gouvernemental ou Militaire", "Non Commercial" }));
		typebox.setBounds(412, 166, 216, 25);
		panel.add(typebox);

		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblDate.setBounds(338, 55, 99, 14);
		panel.add(lblDate);

		JLabel lblDateDepart = new JLabel("Heure Depart :");
		lblDateDepart.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblDateDepart.setBounds(45, 97, 99, 14);
		panel.add(lblDateDepart);

		JLabel lblAeroportDepart = new JLabel(" Aeroport Depart");
		lblAeroportDepart.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblAeroportDepart.setBounds(303, 90, 99, 14);
		panel.add(lblAeroportDepart);

		JLabel lblDateDarriv = new JLabel("Heure d'Arriv\u00E9 :");
		lblDateDarriv.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblDateDarriv.setBounds(23, 129, 99, 14);
		panel.add(lblDateDarriv);

		JLabel lblAeroportDarriv = new JLabel(" Aeroport d'Arriv\u00E9 :");
		lblAeroportDarriv.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblAeroportDarriv.setBounds(290, 131, 133, 14);
		panel.add(lblAeroportDarriv);

		JLabel lblPriorit = new JLabel("Priorit\u00E9 :");
		lblPriorit.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblPriorit.setBounds(338, 17, 99, 14);
		panel.add(lblPriorit);

		JLabel lblNombreDescales = new JLabel("Nombre d'escales :");
		lblNombreDescales.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNombreDescales.setBounds(23, 55, 133, 14);
		panel.add(lblNombreDescales);

		JLabel lblNombreDePassagers = new JLabel("Nombre de Passagers :");
		lblNombreDePassagers.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNombreDePassagers.setBounds(7, 169, 149, 14);
		panel.add(lblNombreDePassagers);

		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblType.setBounds(325, 169, 99, 14);
		panel.add(lblType);

		JLabel lblFretTransportable = new JLabel("Fret Transportable :");
		lblFretTransportable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblFretTransportable.setBounds(7, 215, 133, 14);
		panel.add(lblFretTransportable);

		JLabel lblNumroDeLaeronef = new JLabel("Num\u00E9ro de l'Aeronef :");
		lblNumroDeLaeronef.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNumroDeLaeronef.setBounds(290, 213, 149, 14);
		panel.add(lblNumroDeLaeronef);

		dateChooser = new JDateChooser();
		dateChooser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					heuredfield.requestFocus();
				}
			}
		});
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(412, 52, 216, 22);
		panel.add(dateChooser);

		JLabel lblPiloteCommandant = new JLabel("Pilote Commandant :");
		lblPiloteCommandant.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblPiloteCommandant.setBounds(23, 262, 133, 14);
		panel.add(lblPiloteCommandant);

		JLabel lblPremierCopilote = new JLabel("Premier Co-Pilote :");
		lblPremierCopilote.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblPremierCopilote.setBounds(23, 311, 133, 14);
		panel.add(lblPremierCopilote);

		JLabel lblDeuxiemeCopilote = new JLabel("Deuxieme Co-Pilote :");
		lblDeuxiemeCopilote.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblDeuxiemeCopilote.setBounds(23, 359, 133, 14);
		panel.add(lblDeuxiemeCopilote);

		JLabel lblNombreDeSteewards = new JLabel(" Steewards :");
		lblNombreDeSteewards.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNombreDeSteewards.setBounds(58, 417, 153, 14);
		panel.add(lblNombreDeSteewards);

		priobox = new JComboBox();
		priobox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		priobox.setModel(new DefaultComboBoxModel(new String[] { "Avanc\u00E9e", "Normale" }));
		priobox.setBounds(412, 11, 216, 25);
		panel.add(priobox);

		JLabel lblCompagnie = new JLabel("Compagnie :");
		lblCompagnie.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblCompagnie.setBounds(378, 262, 99, 14);
		panel.add(lblCompagnie);

		compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		compagniebox.setBounds(458, 259, 169, 25);
		panel.add(compagniebox);
		remplirnom();

		steewardbox = new JComboBox();
		steewardbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		steewardbox.setBounds(159, 414, 216, 25);
		panel.add(steewardbox);
		remplirstee();

		pilotebox = new JComboBox();
		pilotebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		pilotebox.setBounds(159, 259, 216, 25);
		panel.add(pilotebox);
		remplirpilote();

		copilote1box = new JComboBox();
		copilote1box.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		copilote1box.setBounds(159, 308, 216, 25);
		panel.add(copilote1box);
		remplircopilote1();

		copilote2box = new JComboBox();
		copilote2box.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		copilote2box.setBounds(159, 356, 216, 25);
		panel.add(copilote2box);
		remplircopilote2();

		numaerobox = new JComboBox();
		numaerobox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numaerobox.setBounds(422, 207, 206, 22);
		rempliraeronef();
		panel.add(numaerobox);

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
					nbescalesfield.requestFocus();
				}
			}
		});
		numerofield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numerofield.setColumns(10);
		numerofield.setBounds(167, 14, 126, 22);
		panel.add(numerofield);

		JLabel lblNumero = new JLabel("Numero :");
		lblNumero.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNumero.setBounds(60, 17, 133, 14);
		panel.add(lblNumero);

		JLabel lblType_1 = new JLabel("Action :");
		lblType_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblType_1.setBounds(403, 311, 99, 14);
		panel.add(lblType_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Atterissage", "Decolage" }));
		comboBox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		comboBox.setBounds(459, 308, 169, 25);
		panel.add(comboBox);

		actionbox = new JComboBox();
		actionbox.setModel(new DefaultComboBoxModel(new String[] { "Decolage", "Atterissage" }));
		actionbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		actionbox.setBounds(459, 308, 169, 20);
		panel.add(actionbox);

		JLabel lblEnTonne = new JLabel("en tonne");
		lblEnTonne.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 10));
		lblEnTonne.setBounds(223, 197, 66, 14);
		panel.add(lblEnTonne);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(159, 35, 165, 14);
		panel.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(159, 69, 165, 14);
		panel.add(lblerror1);

		lblerror2 = new JLabel("");
		lblerror2.setForeground(Color.RED);
		lblerror2.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror2.setBounds(150, 185, 165, 14);
		panel.add(lblerror2);

		lblerror3 = new JLabel("");
		lblerror3.setForeground(Color.RED);
		lblerror3.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror3.setBounds(134, 229, 165, 14);
		panel.add(lblerror3);

		JButton btnVoirLesVols = new JButton("Voir les Vols");
		btnVoirLesVols.setBackground(new Color(153, 255, 255));
		btnVoirLesVols.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesVols.setBackground(new Color(153, 255, 255));
				btnVoirLesVols.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesVols.setForeground(new Color(153, 255, 255));
				btnVoirLesVols.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesVols.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompagnieVoirVols obj = new CompagnieVoirVols();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				disable();
			}
		});
		btnVoirLesVols.setBounds(861, 239, 209, 31);
		contentPane.add(btnVoirLesVols);
		btnVoirLesVols.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLesVols.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -57, 1200, 650);
		contentPane.add(label);
	}

	public void rempliraeronef() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from aeronef where nom = '" + compagniebox.getSelectedItem().toString() + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				numaerobox.addItem(resultat.getString("num_aerof"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remplirnom() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from connecter";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				compagniebox.addItem(resultat.getString("nom_compagnie"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remplirpilote() {
		cnx = ConnexionMySql.ConnectionDB();
		String cString = "Pilote Comandant de bord";
		String sql = " select * from membre where nom = '" + compagniebox.getSelectedItem().toString()
				+ "'and fonction = '" + cString + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				pilotebox.addItem(resultat.getString("nom_membre") + " " + resultat.getString("prenom_membre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void remplircopilote1() {
		cnx = ConnexionMySql.ConnectionDB();
		String cString = "Premier Co-Pilote";
		String sql = " select * from membre where nom = '" + compagniebox.getSelectedItem().toString()
				+ "'and fonction = '" + cString + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				copilote1box.addItem(resultat.getString("nom_membre") + " " + resultat.getString("prenom_membre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void remplircopilote2() {
		cnx = ConnexionMySql.ConnectionDB();
		String cString = "Deuxieme Co-Pilote";
		String sql = " select * from membre where nom = '" + compagniebox.getSelectedItem().toString()
				+ "'and fonction = '" + cString + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				copilote2box.addItem(resultat.getString("nom_membre") + " " + resultat.getString("prenom_membre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void remplirstee() {
		cnx = ConnexionMySql.ConnectionDB();
		String cString = "Steeward";
		String sql = " select * from membre where nom = '" + compagniebox.getSelectedItem().toString()
				+ "'and fonction = '" + cString + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				steewardbox.addItem(resultat.getString("nom_membre") + " " + resultat.getString("prenom_membre"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
