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

import com.toedter.calendar.JDateChooser;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class DirecteurGestionsVols extends JFrame {

	private JPanel contentPane;
	private JTable volseffectuestable;
	private JTextField heureDfield;
	private JTextField aeroportAfield;
	private JTextField aeroportDfiled;
	private JTextField nbescalefield;
	private JTextField nbpassagersfield;
	private JTextField fretfield;
	private JTextField heureAfield;
	JComboBox actionbox;
	JComboBox priobox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	JComboBox numerobox;
	ResultSet resultat = null;
	JLabel lblerror1;
	JLabel lblerror2;
	JLabel lblerror;
	JLabel lblerror3;
	Character ccc;
	private JTextField numerofield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					DirecteurGestionsVols frame = new DirecteurGestionsVols();

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
	public DirecteurGestionsVols() {
		super("Directeur - Gestion de Vols");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1204, 560);
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
				FenDirecteur obj = new FenDirecteur();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/back.png")));
		label_1.setBounds(12, 5, 46, 25);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 36, 820, 474);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnVoirVolsDeja = new JButton("Voir Vols deja effectu\u00E9s");
		btnVoirVolsDeja.setBackground(new Color(153, 255, 255));
		btnVoirVolsDeja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirVolsDeja.setBackground(new Color(153, 255, 255));
				btnVoirVolsDeja.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirVolsDeja.setForeground(new Color(153, 255, 255));
				btnVoirVolsDeja.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirVolsDeja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select * from vol";

				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					volseffectuestable.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnVoirVolsDeja.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirVolsDeja.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirVolsDeja.setBounds(276, 7, 223, 30);
		panel.add(btnVoirVolsDeja);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 800, 423);
		panel.add(scrollPane);

		volseffectuestable = new JTable();
		volseffectuestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		volseffectuestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(volseffectuestable);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(840, 11, 355, 499);
		contentPane.add(panel_2);

		JButton btnOrganiserUnVol = new JButton("Organiser un vol URGENT");
		btnOrganiserUnVol.setBackground(new Color(153, 255, 255));
		btnOrganiserUnVol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnOrganiserUnVol.setBackground(new Color(153, 255, 255));
				btnOrganiserUnVol.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnOrganiserUnVol.setForeground(new Color(153, 255, 255));
				btnOrganiserUnVol.setBackground(new Color(105, 105, 105));
			}
		});
		btnOrganiserUnVol.setIcon(new ImageIcon(getClass().getResource("/icons8-temps-22.png")));
		btnOrganiserUnVol.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnOrganiserUnVol.setBounds(70, 11, 223, 23);
		panel_2.add(btnOrganiserUnVol);

		JDateChooser datedepart = new JDateChooser();
		datedepart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					heureDfield.requestFocus();
				}
			}
		});
		datedepart.setDateFormatString("yyyy/MM/dd");
		datedepart.setBounds(200, 45, 128, 27);
		panel_2.add(datedepart);

		heureDfield = new JTextField();
		heureDfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					aeroportDfiled.requestFocus();
				}
			}
		});
		heureDfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heureDfield.setBounds(200, 76, 128, 22);
		panel_2.add(heureDfield);
		heureDfield.setColumns(10);

		aeroportAfield = new JTextField();
		aeroportAfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					nbescalefield.requestFocus();
				}
			}
		});
		aeroportAfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		aeroportAfield.setColumns(10);
		aeroportAfield.setBounds(200, 179, 128, 27);
		panel_2.add(aeroportAfield);

		aeroportDfiled = new JTextField();
		aeroportDfiled.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					heureAfield.requestFocus();
				}
			}
		});
		aeroportDfiled.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		aeroportDfiled.setBounds(200, 107, 126, 27);
		panel_2.add(aeroportDfiled);
		aeroportDfiled.setColumns(10);

		nbescalefield = new JTextField();
		nbescalefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbescalefield.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				} else {
					nbescalefield.setEditable(true);
					lblerror.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					nbpassagersfield.requestFocus();
				}

			}
		});
		nbescalefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbescalefield.setBounds(200, 260, 128, 22);
		panel_2.add(nbescalefield);
		nbescalefield.setColumns(10);

		nbpassagersfield = new JTextField();
		nbpassagersfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					nbpassagersfield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					nbpassagersfield.setEditable(true);
					lblerror1.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					fretfield.requestFocus();
				}

			}
		});
		nbpassagersfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nbpassagersfield.setColumns(10);
		nbpassagersfield.setBounds(200, 302, 128, 22);
		panel_2.add(nbpassagersfield);

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
					numerofield.requestFocus();
				}

			}
		});
		fretfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		fretfield.setColumns(10);
		fretfield.setBounds(200, 344, 128, 22);
		panel_2.add(fretfield);

		heureAfield = new JTextField();
		heureAfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					aeroportAfield.requestFocus();
				}
			}
		});
		heureAfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heureAfield.setColumns(10);
		heureAfield.setBounds(200, 148, 127, 22);
		panel_2.add(heureAfield);

		JComboBox typebox = new JComboBox();
		typebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		typebox.setModel(new DefaultComboBoxModel(new String[] { "Militaire ou Gouvernemental" }));
		typebox.setBounds(119, 377, 209, 22);
		panel_2.add(typebox);
		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setBackground(new Color(153, 255, 255));
		btnConfirmer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirmer.setBackground(new Color(153, 255, 255));
				btnConfirmer.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnConfirmer.setForeground(new Color(153, 255, 255));
				btnConfirmer.setBackground(new Color(105, 105, 105));
			}
		});
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblerror.getText().equals("") && lblerror1.getText().equals("") && lblerror2.getText().equals("")
						&& lblerror3.getText().equals("")) {
					String sql = "insert into vol (num_vol,date_vol , aero_d , aero_a , heure_d , heure_a ,priorite , nb_escales , nb_passagers ,fret , type_vol  , num_aerof , nom , action ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					String dt = ((JTextField) datedepart.getDateEditor().getUiComponent()).getText();
					if (!aeroportAfield.getText().equals("") && !aeroportDfiled.getText().equals("")
							&& !heureDfield.getText().equals("") && !heureAfield.getText().equals("")
							&& !nbescalefield.getText().equals("") && !nbpassagersfield.getText().equals("")
							&& !fretfield.getText().equals("")) {
						try {

							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, numerofield.getText().toString());
							prepared.setString(2, dt);
							prepared.setString(3, aeroportDfiled.getText().toString());
							prepared.setString(4, aeroportAfield.getText().toString());
							prepared.setString(5, heureDfield.getText().toString());
							prepared.setString(6, heureAfield.getText().toString());
							prepared.setString(7, priobox.getSelectedItem().toString());
							prepared.setString(8, nbescalefield.getText().toString());
							prepared.setString(9, nbpassagersfield.getText().toString());
							prepared.setString(10, fretfield.getText().toString());
							prepared.setString(11, typebox.getSelectedItem().toString());
							prepared.setString(12, numerobox.getSelectedItem().toString());
							String cString = "Sans compagnie";
							prepared.setString(13, cString);
							prepared.setString(14, actionbox.getSelectedItem().toString());

							prepared.execute();
							updatetable();
							JOptionPane.showMessageDialog(null, "Vol en Urgence ajouté");

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
		btnConfirmer.setIcon(new ImageIcon(getClass().getResource("/icons8-bouton-de-radio-coch\u00E9-22.png")));
		btnConfirmer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnConfirmer.setBounds(31, 465, 125, 27);
		panel_2.add(btnConfirmer);
		JButton btnAnuuler = new JButton("Anuuler");
		btnAnuuler.setBackground(new Color(153, 255, 255));
		btnAnuuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnAnuuler.setBackground(new Color(153, 255, 255));
				btnAnuuler.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnAnuuler.setForeground(new Color(153, 255, 255));
				btnAnuuler.setBackground(new Color(105, 105, 105));
			}
		});
		btnAnuuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aeroportAfield.setText("");
				aeroportDfiled.setText("");
				nbescalefield.setText("");
				nbpassagersfield.setText("");
				heureAfield.setText("");
				heureDfield.setText("");
				fretfield.setText("");
			}
		});
		btnAnuuler.setIcon(new ImageIcon(getClass().getResource("/icons8-annuler-22.png")));
		btnAnuuler.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnAnuuler.setBounds(193, 465, 110, 27);
		panel_2.add(btnAnuuler);
		JLabel lblDateDeDepart = new JLabel("Date de Depart :");
		lblDateDeDepart.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblDateDeDepart.setBounds(40, 51, 150, 14);
		panel_2.add(lblDateDeDepart);

		JLabel lblHeureDeDepart = new JLabel("Heure de Depart :");
		lblHeureDeDepart.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblHeureDeDepart.setBounds(31, 79, 159, 14);
		panel_2.add(lblHeureDeDepart);

		JLabel lblAeroportDeDepart = new JLabel("Aeroport de Depart :");
		lblAeroportDeDepart.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblAeroportDeDepart.setBounds(10, 112, 180, 14);
		panel_2.add(lblAeroportDeDepart);

		JLabel lblHeureDarriv = new JLabel("Heure d'Arriv\u00E9 :");
		lblHeureDarriv.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblHeureDarriv.setBounds(31, 151, 159, 14);
		panel_2.add(lblHeureDarriv);

		JLabel lblAeroportDarriv = new JLabel("Aeroport d'Arriv\u00E9 :");
		lblAeroportDarriv.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblAeroportDarriv.setBounds(10, 182, 180, 14);
		panel_2.add(lblAeroportDarriv);

		JLabel lblPriorit = new JLabel("Priorit\u00E9 :");
		lblPriorit.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblPriorit.setBounds(58, 210, 125, 14);
		panel_2.add(lblPriorit);

		JLabel lblNombreDescales = new JLabel("Nombre d'escales :");
		lblNombreDescales.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNombreDescales.setBounds(10, 265, 159, 14);
		panel_2.add(lblNombreDescales);

		JLabel lblNombreDePassagers = new JLabel("Nombre de Passagers :");
		lblNombreDePassagers.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNombreDePassagers.setBounds(10, 307, 180, 14);
		panel_2.add(lblNombreDePassagers);

		JLabel lblFretTransportable = new JLabel("Fret Transportable :");
		lblFretTransportable.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblFretTransportable.setBounds(10, 349, 180, 14);
		panel_2.add(lblFretTransportable);

		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblType.setBounds(31, 383, 125, 14);
		panel_2.add(lblType);

		JLabel lblNumero = new JLabel("Numero de l'aeronef :");
		lblNumero.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNumero.setBounds(10, 410, 185, 14);
		panel_2.add(lblNumero);

		priobox = new JComboBox();
		priobox.setModel(new DefaultComboBoxModel(new String[] { "Urgence" }));
		priobox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		priobox.setBounds(200, 210, 128, 22);
		panel_2.add(priobox);

		JLabel lblEnTonne = new JLabel("en tonne");
		lblEnTonne.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 10));
		lblEnTonne.setBounds(262, 330, 66, 14);
		panel_2.add(lblEnTonne);

		JLabel lblTypeDeLaction = new JLabel("Type de l'action :");
		lblTypeDeLaction.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblTypeDeLaction.setBounds(10, 237, 173, 14);
		panel_2.add(lblTypeDeLaction);

		actionbox = new JComboBox();
		actionbox.setModel(new DefaultComboBoxModel(new String[] { "Decolage", "Atterissage" }));
		actionbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		actionbox.setBounds(200, 235, 128, 22);
		panel_2.add(actionbox);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(162, 281, 165, 14);
		panel_2.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(162, 323, 165, 14);
		panel_2.add(lblerror1);

		lblerror2 = new JLabel("");
		lblerror2.setForeground(Color.RED);
		lblerror2.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror2.setBounds(162, 368, 165, 14);
		panel_2.add(lblerror2);

		lblerror3 = new JLabel("");
		lblerror3.setForeground(Color.RED);
		lblerror3.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror3.setBounds(163, 454, 165, 14);
		panel_2.add(lblerror3);

		numerobox = new JComboBox();
		numerobox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numerobox.setBounds(200, 405, 128, 22);
		panel_2.add(numerobox);

		JLabel lblNumeroDuVol = new JLabel("Numero du vol :");
		lblNumeroDuVol.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNumeroDuVol.setBounds(10, 440, 185, 14);
		panel_2.add(lblNumeroDuVol);

		numerofield = new JTextField();
		numerofield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		numerofield.setColumns(10);
		numerofield.setBounds(200, 432, 128, 22);
		panel_2.add(numerofield);
		rempliraeronef();

		JLabel label = new JLabel("");
		label.setBounds(0, -52, 1200, 650);
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		contentPane.add(label);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = "select * from vol";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			volseffectuestable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void rempliraeronef() {
		cnx = ConnexionMySql.ConnectionDB();
		String id = "Sans Compagnie";
		String sql = "select * from aeronef where nom = '" + id + "'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				numerobox.addItem(resultat.getString("num_aerof"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
