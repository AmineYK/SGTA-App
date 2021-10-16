package CompagnieAerienne;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;
import net.proteanit.sql.DbUtils;

public class CompagnieVoirFicheInfos extends JFrame {

	private JPanel contentPane;
	private JTable fichetable;
	JComboBox compagniebox;
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
					CompagnieVoirFicheInfos frame = new CompagnieVoirFicheInfos();

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
	public CompagnieVoirFicheInfos() {
		super("Compagnie Aerienne - Voir Reponse : Fiche d'informations");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1211, 560);
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
				CompagnieLesDeuxFrames obj = new CompagnieLesDeuxFrames();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/Les Icones/back.png")));
		label_1.setBounds(10, 11, 46, 29);
		contentPane.add(label_1);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(314, 51, 668, 459);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 648, 437);
		panel.add(scrollPane);

		fichetable = new JTable();
		fichetable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		fichetable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(fichetable);

		compagniebox = new JComboBox();
		compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		compagniebox.setBounds(47, 173, 209, 25);
		contentPane.add(compagniebox);
		remplirnom();

		JLabel lblNomDeLa = new JLabel("Nom de la compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		lblNomDeLa.setBounds(79, 148, 156, 14);
		contentPane.add(lblNomDeLa);

		JButton btnVoirFicheDinformations = new JButton("Voir fiche d'informations ");
		btnVoirFicheDinformations.setBackground(new Color(153, 255, 255));
		btnVoirFicheDinformations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirFicheDinformations.setBackground(new Color(153, 255, 255));
				btnVoirFicheDinformations.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnVoirFicheDinformations.setForeground(new Color(153, 255, 255));
				btnVoirFicheDinformations.setBackground(new Color(105, 105, 105));
			}
		});
		btnVoirFicheDinformations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upadatetable();
			}
		});
		btnVoirFicheDinformations.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-eye-22.png")));
		btnVoirFicheDinformations.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirFicheDinformations.setBounds(35, 264, 244, 29);
		contentPane.add(btnVoirFicheDinformations);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/Les Icones/GTAFE.png")));
		label.setBounds(0, -50, 1200, 650);
		contentPane.add(label);
	}

	public void upadatetable() {
		cnx = ConnexionMySql.ConnectionDB();
		String id = compagniebox.getSelectedItem().toString();
		String sql = " select * from ficheinfos where nom = '" + id + "'";

		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			fichetable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public void remplircompagnie(){ cnx=ConnexionMySql.ConnectionDB(); String
	 * sql= "select * from compagnie";
	 * 
	 * try { prepared=cnx.prepareStatement(sql); resultat=prepared.executeQuery();
	 * while(resultat.next()){ compagniebox.addItem(resultat.getString("nom")); } }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
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
}
