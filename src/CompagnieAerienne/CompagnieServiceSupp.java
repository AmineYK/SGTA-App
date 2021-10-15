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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import Welcome.ConnexionMySql;

public class CompagnieServiceSupp extends JFrame {
	Connection cnx2 = null;
	PreparedStatement prepared2 = null;
	ResultSet resultat2 = null;
	JComboBox nombox;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					CompagnieServiceSupp frame = new CompagnieServiceSupp();

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
	public CompagnieServiceSupp() {
		super("Emmetre des services Supplementaires");
		setResizable(false);
		setBounds(100, 100, 439, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx2 = ConnexionMySql.ConnectionDB();

		JPanel panel = new JPanel();
		panel.setBounds(10, 40, 413, 443);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("Nom :");
		label.setBounds(46, 54, 40, 12);
		label.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		panel.add(label);

		nombox = new JComboBox();
		nombox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		nombox.setBounds(121, 49, 194, 25);
		remplirnom();
		panel.add(nombox);

		JLabel lblTypeDeService = new JLabel("Type de Service :");
		lblTypeDeService.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblTypeDeService.setBounds(29, 114, 168, 32);
		panel.add(lblTypeDeService);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "Sant\u00E9", "Economie et Gestion", "Environement et m\u00E9t\u00E9o" }));
		comboBox_1.setBounds(184, 119, 194, 25);
		panel.add(comboBox_1);

		JLabel lblLeService = new JLabel("Le Service :");
		lblLeService.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblLeService.setBounds(46, 157, 168, 32);
		panel.add(lblLeService);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		editorPane.setForeground(Color.WHITE);
		editorPane.setBackground(Color.GRAY);
		editorPane.setBounds(27, 186, 351, 202);
		panel.add(editorPane);
		editorPane.requestFocus();
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBackground(new Color(153, 255, 255));
		btnEnvoyer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnEnvoyer.setBackground(new Color(153, 255, 255));
				btnEnvoyer.setForeground(Color.black);//
			}

			public void mouseEntered(MouseEvent e) {
				btnEnvoyer.setForeground(new Color(153, 255, 255));
				btnEnvoyer.setBackground(new Color(105, 105, 105));
			}
		});
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into service(nom_compagnie , type , service ) values ( ? , ? , ? )";

				try {
					if (!editorPane.getText().equals("")) {
						prepared2 = cnx2.prepareStatement(sql);
						prepared2.setString(1, nombox.getSelectedItem().toString());
						prepared2.setString(2, comboBox_1.getSelectedItem().toString());
						prepared2.setString(3, editorPane.getText().toString());
						prepared2.execute();
						JOptionPane.showMessageDialog(null, "Service envoyé à la direction de l'aeroport !");
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Service !");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnvoyer.setIcon(new ImageIcon(getClass().getResource("/icons8-envoyer-22.png")));
		btnEnvoyer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 14));
		btnEnvoyer.setBounds(132, 399, 141, 33);
		panel.add(btnEnvoyer);
	}

	public void remplirnom() {
		cnx2 = ConnexionMySql.ConnectionDB();
		String sqll = "select * from connecter";

		try {
			prepared2 = cnx2.prepareStatement(sqll);
			resultat2 = prepared2.executeQuery();
			while (resultat2.next()) {
				nombox.addItem(resultat2.getString("nom_compagnie"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
