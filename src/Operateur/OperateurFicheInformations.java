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

import com.toedter.calendar.JDateChooser;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class OperateurFicheInformations extends JFrame {

	private JPanel contentPane;
	private JTextField dureefield;
	private JTextField heurefield;
	private JTable demandestable;
	JComboBox compagniebox;
	JComboBox pistebox;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Connection cnx1 = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat1 = null;
	Connection cnx2 = null;
	PreparedStatement prepared2 = null;
	ResultSet resultat2 = null;
	Connection cnx3 = null;
	PreparedStatement prepared3 = null;
	ResultSet resultat3 = null;
	Connection cnx4 = null;
	PreparedStatement prepared4 = null;
	ResultSet resultat4 = null;
	Connection cnx5 = null;
	PreparedStatement prepared5 = null;
	ResultSet resultat5 = null;
	Connection cnx6 = null;
	PreparedStatement prepared7 = null;
	ResultSet resultat6 = null;
	Connection cnx7 = null;
	PreparedStatement prepared6 = null;
	ResultSet resultat7 = null;
	Character ccc;
	JLabel lblerror;
	JLabel lblerror1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurFicheInformations frame = new OperateurFicheInformations();

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
	public OperateurFicheInformations() {
		super("Operateur - Fiche d'Informations");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1185, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		cnx7 = ConnexionMySql.ConnectionDB();
		cnx6 = ConnexionMySql.ConnectionDB();

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
		label_1.setBounds(10, 11, 36, 24);
		contentPane.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(20, 45, 370, 465);
		contentPane.add(panel_1);

		JLabel label_2 = new JLabel("Date :");
		label_2.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_2.setBounds(32, 96, 68, 14);
		panel_1.add(label_2);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(127, 96, 151, 27);
		panel_1.add(dateChooser);

		JLabel label_3 = new JLabel("Heure :");
		label_3.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_3.setBounds(32, 145, 78, 14);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Dur\u00E9e :");
		label_4.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_4.setBounds(31, 203, 69, 14);
		panel_1.add(label_4);

		dureefield = new JTextField();
		dureefield.addKeyListener(new KeyAdapter() {
			@Override

			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					dureefield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				} else {
					dureefield.setEditable(true);
					lblerror1.setText("");
				}

			}
		});
		dureefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		dureefield.setColumns(10);
		dureefield.setBounds(127, 201, 131, 22);
		panel_1.add(dureefield);

		heurefield = new JTextField();
		heurefield.addKeyListener(new KeyAdapter() {
			@Override

			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if (Character.isLetter(ccc)) {
					heurefield.setEditable(false);
					lblerror.setText("Que des chiffres SVP");
				} else {
					heurefield.setEditable(true);
					lblerror.setText("");
				}
				int key = e.getKeyCode();
				if (key == 10) {
					dureefield.requestFocus();
				}
			}
		});
		heurefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heurefield.setColumns(10);
		heurefield.setBounds(127, 143, 131, 22);
		panel_1.add(heurefield);

		JLabel label_5 = new JLabel("Type de l'action :");
		label_5.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		label_5.setBounds(32, 310, 160, 14);
		panel_1.add(label_5);

		JComboBox actionbox = new JComboBox();
		actionbox.setModel(new DefaultComboBoxModel(new String[] { "Atterissage", "Decolage" }));
		actionbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		actionbox.setBounds(202, 307, 158, 25);
		panel_1.add(actionbox);

		JLabel lblRemplirFicheDinformations = new JLabel("Remplir Fiche d'informations du vol ");
		lblRemplirFicheDinformations.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		lblRemplirFicheDinformations.setBounds(46, 25, 291, 26);
		panel_1.add(lblRemplirFicheDinformations);

		pistebox = new JComboBox();
		pistebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		pistebox.setBounds(202, 370, 158, 22);
		panel_1.add(pistebox);
		remplirpistes();

		JLabel lblNumeroDeLa = new JLabel("Numero de la piste :");
		lblNumeroDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		lblNumeroDeLa.setBounds(32, 372, 160, 14);
		panel_1.add(lblNumeroDeLa);

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
				String sql = "insert into ficheinfos (date_fiche , heure_fiche , num_piste , type_action , duree_fiche , nom ) values(  ? , ? ,? , ? , ?, ? )";
				String dt = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				if (pistebox.getItemCount() == 0) {
					JOptionPane.showMessageDialog(null, "Aucune piste libre pour le moment ");
				} else {

					String a = pistebox.getSelectedItem().toString();
					int ligne = demandestable.getSelectedRow();
					if (ligne == -1) {
						JOptionPane.showMessageDialog(null, "selectionnez une ligne ");
					} else {
						String fr = null;
						String frr = null;

						String cpt = null;
						String cptt = null;
						String id = demandestable.getModel().getValueAt(ligne, 0).toString();
						try {

							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, dt);
							prepared.setString(2, heurefield.getText().toString());
							prepared.setString(3, a);
							prepared.setString(4, actionbox.getSelectedItem().toString());
							prepared.setString(5, dureefield.getText().toString());
							prepared.setString(6, compagniebox.getSelectedItem().toString());

							prepared.execute();
							JOptionPane.showMessageDialog(null, "Fiche d'informations enregistrée et envoyée");
							heurefield.setText("");
							dureefield.setText("");

							String sql1 = "update piste set etat = ? where num_piste =" + a + " ";
							cnx1 = ConnexionMySql.ConnectionDB();
							prepared1 = cnx1.prepareStatement(sql1);
							prepared1.setString(1, "Resérvé");
							prepared1.execute();
							JOptionPane.showMessageDialog(null, "cette Piste n'est plus libre");
							updatetable();
							viderpistes();
							remplirpistes();

							String sql2 = "update demande set reguler = ? where id_demande =" + id + " ";
							cnx2 = ConnexionMySql.ConnectionDB();
							prepared2 = cnx2.prepareStatement(sql2);
							prepared2.setString(1, "Deja Traité");
							prepared2.execute();
							updatetable();

							String sql3 = "select * from connecterr";
							cnx3 = ConnexionMySql.ConnectionDB();
							prepared3 = cnx3.prepareStatement(sql3);
							resultat3 = prepared3.executeQuery();
							if (resultat3.next()) {
								fr = resultat3.getString("num_operateur");
							}

							String sql4 = "select * from nboperateur where num_operateur = " + fr + "";
							cnx4 = ConnexionMySql.ConnectionDB();
							prepared4 = cnx4.prepareStatement(sql4);
							resultat4 = prepared4.executeQuery();
							if (resultat4.next()) {
								cpt = resultat4.getString("compteur");
							}
							Integer aInteger = null;
							aInteger = Integer.valueOf(cpt);
							aInteger = aInteger + 1;
							cpt = String.valueOf(aInteger);

							String sql5 = "update nboperateur set compteur = ? where num_operateur=" + fr + "";
							cnx5 = ConnexionMySql.ConnectionDB();
							prepared5 = cnx5.prepareStatement(sql5);
							prepared5.setString(1, cpt);
							prepared5.execute();

							frr = pistebox.getSelectedItem().toString();
							String sql6 = "select * from nbpiste where num_piste = " + a + "";

							prepared7 = cnx7.prepareStatement(sql6);
							resultat7 = prepared7.executeQuery();
							if (resultat7.next()) {
								cptt = resultat7.getString("nombre");
							}
							;
							Integer aaa = null;
							aaa = Integer.valueOf(cptt);
							aaa = aaa + 1;
							cptt = String.valueOf(aaa);

							String sql7 = "update nbpiste set nombre = ? where num_piste=" + a + "";

							prepared6 = cnx6.prepareStatement(sql7);
							prepared6.setString(1, cptt);
							prepared6.execute();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnConfirmer.setIcon(new ImageIcon(getClass().getResource("/icons8-bouton-de-radio-coch\u00E9-22.png")));
		btnConfirmer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnConfirmer.setBounds(32, 429, 131, 28);
		panel_1.add(btnConfirmer);

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
				heurefield.setText("");
				dureefield.setText("");

			}
		});
		btnAnnuler.setIcon(new ImageIcon(getClass().getResource("/icons8-annuler-22.png")));
		btnAnnuler.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnAnnuler.setBounds(206, 429, 131, 28);
		panel_1.add(btnAnnuler);

		compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		// compagniebox.setBackground(new Color(153,255,255));
		compagniebox.setBounds(202, 255, 158, 25);
		panel_1.add(compagniebox);
		remplircompagnie();

		JLabel lblNomDeLa = new JLabel("Nom de la compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		lblNomDeLa.setBounds(32, 256, 160, 14);
		panel_1.add(lblNomDeLa);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(87, 164, 176, 14);
		panel_1.add(lblerror);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(87, 223, 176, 14);
		panel_1.add(lblerror1);

		JLabel lblOui = new JLabel("OUI");
		lblOui.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 22));
		lblOui.setBounds(174, 0, 142, 35);
		contentPane.add(lblOui);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE, 2, true));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(446, 45, 706, 465);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnVoirLesDemandes = new JButton("Voir les demandes de Pistes");
		btnVoirLesDemandes.setBackground(new Color(153, 255, 255));
		btnVoirLesDemandes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLesDemandes.setBackground(new Color(153, 255, 255));
				btnVoirLesDemandes.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirLesDemandes.setForeground(new Color(153, 255, 255));
				btnVoirLesDemandes.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirLesDemandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirLesDemandes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLesDemandes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnVoirLesDemandes.setBounds(236, 3, 247, 34);
		panel_2.add(btnVoirLesDemandes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 686, 415);
		panel_2.add(scrollPane);

		demandestable = new JTable();
		demandestable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = demandestable.getSelectedRow();
				if (ligne == -1) {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne");
				} else {
					String id = demandestable.getModel().getValueAt(ligne, 0).toString();
					String dd = "Deja Traité";
					String sql = "select * from demande where id_demande =" + id + " and reguler <> '" + dd + "'";

					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						if (resultat.next()) {
							dateChooser.setDate(resultat.getDate("date_demande"));
							heurefield.setText(resultat.getString("heure_demande"));
							dureefield.setText(resultat.getString("duree_demande"));
							actionbox.setSelectedItem(resultat.getString("type_action"));
							compagniebox.setSelectedItem(resultat.getString("nom"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		demandestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		demandestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(demandestable);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(-15, -54, 1200, 650);
		contentPane.add(label);
	}

	public void updatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = " select id_demande ,date_demande , heure_demande , duree_demande , type_action , nom , reguler from demande";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			demandestable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remplirpistes() {
		cnx = ConnexionMySql.ConnectionDB();
		String id = "degagé";
		String sql = " select * from piste where etat = '" + id + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				pistebox.addItem(resultat.getString("num_piste"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void viderpistes() {
		pistebox.removeAllItems();
	}

	public void remplircompagnie() {
		cnx = ConnexionMySql.ConnectionDB();
		String sql = "select * from compagnieusers";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				compagniebox.addItem(resultat.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
