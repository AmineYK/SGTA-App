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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;

public class OperateurAjouterPisteLibre extends JFrame {

	private JPanel contentPane;
	JLabel lblerror1;
	JLabel lblerror;
	Character ccc;
	Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	JComboBox numeroBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurAjouterPisteLibre frame = new OperateurAjouterPisteLibre();

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
	public OperateurAjouterPisteLibre() {
		super("Operateur - Ajouter Piste Libre");
		setResizable(false);
		setBounds(100, 100, 439, 387);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/Les Icones/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 26, 403, 313);
		contentPane.add(panel);
		panel.setLayout(null);

		numeroBox = new JComboBox();
		numeroBox.setFont(new Font("Lucida Handwriting", Font.BOLD | Font.ITALIC, 12));
		numeroBox.setBounds(184, 53, 190, 22);
		panel.add(numeroBox);
		remplirpiste();

		JComboBox etatbox = new JComboBox();
		etatbox.setModel(new DefaultComboBoxModel(new String[] { "Degag\u00E9" }));
		etatbox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		etatbox.setBounds(184, 143, 190, 22);
		panel.add(etatbox);

		JLabel lblNumeroDeLa = new JLabel("Numero de la Piste :");
		lblNumeroDeLa.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblNumeroDeLa.setBounds(15, 57, 164, 14);
		panel.add(lblNumeroDeLa);

		JLabel lblEtat_1 = new JLabel("Etat :");
		lblEtat_1.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblEtat_1.setBounds(71, 146, 164, 14);
		panel.add(lblEtat_1);

		lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(159, 221, 176, 14);
		panel.add(lblerror1);

		lblerror = new JLabel("");
		lblerror.setForeground(Color.RED);
		lblerror.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror.setBounds(159, 134, 176, 14);
		panel.add(lblerror);

		JButton btnConfirmer = new JButton("Confirmer ");
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
				String sql = "update piste set etat=?  where num_piste = " + numeroBox.getSelectedItem().toString()
						+ " ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, etatbox.getSelectedItem().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Piste libre ajoutée !");
					numeroBox.removeAllItems();
					remplirpiste();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConfirmer.setBounds(15, 246, 159, 33);
		panel.add(btnConfirmer);
		btnConfirmer.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-bouton-de-radio-coch\u00E9-22.png")));
		btnConfirmer.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));

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
		btnAnnuler.setBounds(227, 245, 147, 34);
		panel.add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuler.setIcon(new ImageIcon(getClass().getResource("/Les Icones/icons8-annuler-22.png")));
		btnAnnuler.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
	}

	public void remplirpiste() {
		cnx = ConnexionMySql.ConnectionDB();
		String s = "Degagé";
		String sql = "select * from piste where etat <>'" + s + "'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while (resultat.next()) {
				numeroBox.addItem(resultat.getString("num_piste"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
