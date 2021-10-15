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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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

public class FenCompagnie extends JFrame {

	private JPanel contentPane;
	private JTextField dureefield;
	private JTable facturetable;
	private JTextField heurefield;
	JComboBox nombox;
	JComboBox typebox;
	JDateChooser date;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	private JTable listetable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					FenCompagnie frame = new FenCompagnie();

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
	public FenCompagnie() {
		super("Compagnie Aerienne");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		JMenuBar menuBar = new JMenuBar();
		// menuBar.setBackground(new Color(153,255,255));
		menuBar.setBounds(30, 0, 1129, 27);
		contentPane.add(menuBar);

		JMenu mnGestionDesMembres = new JMenu("Gestion des Membres d'\u00E9quipage ");
		mnGestionDesMembres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompagnieGestionEquipage obj = new CompagnieGestionEquipage();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		mnGestionDesMembres.setMnemonic('M');
		// mnGestionDesMembres.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_DOWN_MASK));
		mnGestionDesMembres.setBackground(Color.WHITE);
		mnGestionDesMembres
				.setIcon(new ImageIcon(getClass().getResource("/icons8-casquette-de-pilote-de-l'air-25.png")));
		mnGestionDesMembres.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionDesMembres);

		JMenu mnGestionDesAronefs = new JMenu(" Gestion des Aeronefs ");
		mnGestionDesAronefs.setMnemonic('A');
		mnGestionDesAronefs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompagnieGestionAeronefs obj = new CompagnieGestionAeronefs();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				mnGestionDesAronefs.setToolTipText("Gestion des Aeronefs.....");
			}
		});
		mnGestionDesAronefs.setIcon(new ImageIcon(getClass().getResource("/icons8-mode-avion-on-25.png")));
		mnGestionDesAronefs.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionDesAronefs);

		JMenu mnGestionsDesVols = new JMenu(" Gestion des Vols ");
		mnGestionsDesVols.setMnemonic('V');
		mnGestionsDesVols.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompagnieGestionvols obj = new CompagnieGestionvols();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}

			public void mouseEntered(MouseEvent e) {
				mnGestionsDesVols.setToolTipText("Gestion des Vols.....");
			}
		});
		mnGestionsDesVols.setIcon(new ImageIcon(
				"C:\\Users\\m2k\\Desktop\\Amine YKh\\amine\\PROJET GL\\Les Icones\\icons8-british-airways-25 (1).png"));
		mnGestionsDesVols.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionsDesVols);

		JMenu mnNotifications = new JMenu(" Notifications ");
		mnNotifications.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Aucune notifications pour vous !");
			}
		});
		mnNotifications.setMnemonic('N');
		mnNotifications.setIcon(new ImageIcon(getClass().getResource("/icons8-cloche-22.png")));
		mnNotifications.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNotifications);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 63, 370, 447);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnDemanderPiste = new JButton("Demander Piste");
		btnDemanderPiste.setBackground(new Color(153, 255, 255));
		btnDemanderPiste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnDemanderPiste.setBackground(new Color(153, 255, 255));
				btnDemanderPiste.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnDemanderPiste.setForeground(new Color(153, 255, 255));
				btnDemanderPiste.setBackground(new Color(105, 105, 105));
			}
		});
		btnDemanderPiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "insert into demande (date_demande , heure_demande , duree_demande , type_action , nom ) values ( ? , ? , ? , ? , ? )";
				String dt = ((JTextField) date.getDateEditor().getUiComponent()).getText();
				try {
					if (!heurefield.getText().equals("") && !dureefield.getText().equals("")) {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, dt);
						prepared.setString(2, heurefield.getText().toString());
						prepared.setString(3, dureefield.getText().toString());
						prepared.setString(4, typebox.getSelectedItem().toString());
						prepared.setString(5, nombox.getSelectedItem().toString());
						prepared.execute();

						JOptionPane.showMessageDialog(null, "Demande bien envoyé ");
						dureefield.setText("");
						heurefield.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnDemanderPiste.setIcon(new ImageIcon(getClass().getResource("/icons8-editer-le-fichier-22.png")));
		btnDemanderPiste.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnDemanderPiste.setBounds(87, 11, 191, 31);
		panel.add(btnDemanderPiste);

		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblDate.setBounds(42, 137, 68, 14);
		panel.add(lblDate);

		date = new JDateChooser();
		date.setDateFormatString("yyyy/MM/dd");
		date.setBounds(164, 137, 131, 27);
		panel.add(date);

		JLabel lblHeure = new JLabel("Heure :");
		lblHeure.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblHeure.setBounds(42, 200, 78, 14);
		panel.add(lblHeure);

		JLabel lblDure = new JLabel("Dur\u00E9e :");
		lblDure.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblDure.setBounds(51, 326, 69, 14);
		panel.add(lblDure);

		dureefield = new JTextField();
		dureefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		dureefield.setBounds(164, 323, 131, 22);
		panel.add(dureefield);
		dureefield.setColumns(10);

		heurefield = new JTextField();
		heurefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == 10) {
					dureefield.requestFocus();
				}
			}
		});
		heurefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heurefield.setBounds(164, 197, 131, 22);
		panel.add(heurefield);
		heurefield.setColumns(10);

		JLabel lblTypeDeLaction = new JLabel("Type de l'action :");
		lblTypeDeLaction.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblTypeDeLaction.setBounds(32, 259, 160, 14);
		panel.add(lblTypeDeLaction);

		typebox = new JComboBox();
		typebox.setModel(new DefaultComboBoxModel(new String[] { "Decolage", "Atterissage" }));
		typebox.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		typebox.setBounds(202, 257, 158, 22);
		panel.add(typebox);

		nombox = new JComboBox();
		nombox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nombox.setBounds(176, 79, 184, 22);
		// nombox.addItem("Selectionnez");
		remplirnom();
		panel.add(nombox);

		JLabel lblNomDeLa = new JLabel("Nom de la compagie :");
		lblNomDeLa.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		lblNomDeLa.setBounds(10, 81, 167, 14);
		panel.add(lblNomDeLa);

		JLabel lblEnMinute = new JLabel("en Minute");
		lblEnMinute.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 10));
		lblEnMinute.setBounds(296, 327, 75, 14);
		panel.add(lblEnMinute);

		JLabel lblEnHhmm = new JLabel("en HH:MM");
		lblEnHhmm.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 10));
		lblEnHhmm.setBounds(305, 200, 75, 14);
		panel.add(lblEnHhmm);

		JButton btnVoirReponse = new JButton("Voir Reponse ");
		btnVoirReponse.setBackground(new Color(153, 255, 255));
		btnVoirReponse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirReponse.setBackground(new Color(153, 255, 255));
				btnVoirReponse.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirReponse.setForeground(new Color(153, 255, 255));
				btnVoirReponse.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirReponse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Votre Demande de Piste a ete bien traité ; "
						+ "Pour cela vous avez qu'a verifié l'exsitance de la date de votre "
						+ "demande dans la FICHE D'INFORMATION " + "sinon Consultez les Suggestions de Dates !!");
				CompagnieLesDeuxFrames obj = new CompagnieLesDeuxFrames();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);

			}
		});
		btnVoirReponse.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirReponse.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnVoirReponse.setBounds(87, 363, 191, 31);
		panel.add(btnVoirReponse);

		JButton btnVoirNombreDe = new JButton("Voir nombre de Demandes");
		btnVoirNombreDe.setBackground(new Color(153, 255, 255));
		btnVoirNombreDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirNombreDe.setBackground(new Color(153, 255, 255));
				btnVoirNombreDe.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirNombreDe.setForeground(new Color(153, 255, 255));
				btnVoirNombreDe.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirNombreDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select count(*) from demande where nom = '" + nombox.getSelectedItem().toString() + "'";
				int cpt = 0;
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					while (resultat.next()) {
						cpt = Integer.valueOf(resultat.getString("count(*)"));
					}

					JOptionPane.showMessageDialog(null, "Nombre de Demandes est :" + " " + cpt);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirNombreDe.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirNombreDe.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnVoirNombreDe.setBounds(55, 405, 263, 31);
		panel.add(btnVoirNombreDe);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(815, 63, 370, 447);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnVoirFacture = new JButton("Voir Facture");
		btnVoirFacture.setBackground(new Color(153, 255, 255));
		btnVoirFacture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirFacture.setBackground(new Color(153, 255, 255));
				btnVoirFacture.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirFacture.setForeground(new Color(153, 255, 255));
				btnVoirFacture.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirFacture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable1();
			}
		});
		btnVoirFacture.setIcon(new ImageIcon(getClass().getResource("/icons8-facture-22.png")));
		btnVoirFacture.setBounds(97, 11, 175, 31);
		btnVoirFacture.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		panel_1.add(btnVoirFacture);

		JButton btnPayer = new JButton("Payer");
		btnPayer.setBackground(new Color(153, 255, 255));
		btnPayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnPayer.setBackground(new Color(153, 255, 255));
				btnPayer.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnPayer.setForeground(new Color(153, 255, 255));
				btnPayer.setBackground(new Color(105, 105, 105));
			}
		});
		btnPayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = facturetable.getSelectedRow();

				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = facturetable.getModel().getValueAt(ligne, 0).toString();
					String ss = "payée";
					String sql = "update facture set payer = ? where id_facture = " + id + " ";

					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, ss);
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Facture payée !");
						updatetable1();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnPayer.setIcon(new ImageIcon(getClass().getResource("/icons8-payer-par-carte-22.png")));
		btnPayer.setBounds(151, 405, 104, 31);
		panel_1.add(btnPayer);
		btnPayer.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 350, 345);
		panel_1.add(scrollPane);

		facturetable = new JTable();
		facturetable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		facturetable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(facturetable);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE, 2));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(389, 63, 416, 447);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnVoirListeDes = new JButton("Voir Liste des membres d'\u00E9quipage par Vol");
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
		btnVoirListeDes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		btnVoirListeDes.setBounds(10, 11, 396, 31);
		panel_2.add(btnVoirListeDes);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 53, 396, 383);
		panel_2.add(scrollPane_1);

		listetable = new JTable();
		listetable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		listetable.setBackground(new Color(153, 255, 255));
		scrollPane_1.setViewportView(listetable);

		JButton btnEmmetreDesDemandes = new JButton("Emmetre des demandes de service");
		btnEmmetreDesDemandes.setBackground(new Color(153, 255, 255));
		btnEmmetreDesDemandes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnEmmetreDesDemandes.setBackground(new Color(153, 255, 255));
				btnEmmetreDesDemandes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnEmmetreDesDemandes.setForeground(new Color(153, 255, 255));
				btnEmmetreDesDemandes.setBackground(new Color(105, 105, 105));
			}
		});
		btnEmmetreDesDemandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompagnieServiceSupp obj = new CompagnieServiceSupp();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);

			}
		});
		btnEmmetreDesDemandes.setIcon(new ImageIcon(getClass().getResource("/icons8-document-22.png")));
		btnEmmetreDesDemandes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnEmmetreDesDemandes.setBounds(434, 30, 334, 31);
		contentPane.add(btnEmmetreDesDemandes);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment eteindre le logiciel ??",
						"la Mise hors tension du logiciel ", JOptionPane.YES_NO_CANCEL_OPTION);
				if (a == 0) {
					dispose();
				}
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/button_turn_on_15006.png")));
		label_1.setBounds(5, 21, 41, 46);
		contentPane.add(label_1);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -53, 1200, 650);
		contentPane.add(label);
	}

	public void remplirnom() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select * from connecter";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				nombox.addItem(resultat.getString("nom_compagnie"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		/*
		 * String sql= "select * from connecter"; String nom=null; try {
		 * prepared=cnx.prepareStatement(sql); resultat=prepared.executeQuery();
		 * if(resultat.next()){ nom= resultat.getString("nom_compagnie"); }
		 * cnx1=ConnexionMySql.ConnectionDB(); String
		 * sql1="select * from listevols where nom='"+nom+"'";
		 * prepared1=cnx1.prepareStatement(sql1); resultat1=prepared1.executeQuery();
		 * listetable.setModel(DbUtils.resultSetToTableModel(resultat1)); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		String sql = "select num_vol,membre_comp,fonction,nom  from listevols,connecter where connecter.nom_compagnie = listevols.nom ";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			listetable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatetable1() {
		cnx = ConnexionMySql.ConnectionDB();
		/*
		 * String sql= "select * from connecter"; String nom=null; try {
		 * prepared=cnx.prepareStatement(sql); resultat=prepared.executeQuery();
		 * if(resultat.next()){ nom= resultat.getString("nom_compagnie"); }
		 * cnx1=ConnexionMySql.ConnectionDB(); String
		 * sql1="select * from facture where nom_compagnie='"+nom+"'";
		 * prepared1=cnx1.prepareStatement(sql1); resultat1=prepared1.executeQuery();
		 * facturetable.setModel(DbUtils.resultSetToTableModel(resultat1)); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * }
		 */
		String sql = "select id_facture ,facture.nom_compagnie,montant,payer from facture,connecter where facture.nom_compagnie = connecter.nom_compagnie";
		try {
			prepared1 = cnx.prepareStatement(sql);
			resultat1 = prepared1.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		facturetable.setModel(DbUtils.resultSetToTableModel(resultat1));

	}
}