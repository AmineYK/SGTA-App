import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class DirecteurGestionAeronefs extends JFrame {

	private JPanel contentPane;
	java.sql.Connection cnx = null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTable aeronefstable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					DirecteurGestionAeronefs frame = new DirecteurGestionAeronefs();
					
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
	public DirecteurGestionAeronefs() {
		super("Directeur - Gestion des Aeronefs");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1204,560);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMySql.ConnectionDB();
		ImageIcon obj = new ImageIcon(getClass().getResource("logo.png"));
		super.setIconImage(obj.getImage());
		
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
		label_1.setIcon(new ImageIcon(getClass().getResource("back.png")));
		label_1.setBounds(10, 0, 45, 34);
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(33, 32, 963, 488);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton btnVoirListeDes = new JButton("Voir Liste des Aeronefs");
		btnVoirListeDes.setBackground(new Color(153,255,255));
		btnVoirListeDes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirListeDes.setBackground(new Color(153,255,255));
				btnVoirListeDes.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirListeDes.setForeground(new Color(153,255,255));
				btnVoirListeDes.setBackground(new Color(105,105,105));
			}
		});
		btnVoirListeDes.setBounds(299, 3, 302, 34);
		panel.add(btnVoirListeDes);
		btnVoirListeDes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql = "  select * from aeronef";
				
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					aeronefstable.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//btnVoirListeDes.setIcon(new ImageIcon("C:\\Users\\m2k\\Desktop\\Amine YKh\\amine\\PROJET GL\\Les Icones\\GTAFE.png"));
		btnVoirListeDes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 943, 437);
		panel.add(scrollPane);
		
		aeronefstable = new JTable();
		aeronefstable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		aeronefstable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(aeronefstable);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("GTAFE.png")));
		label.setBounds(0, -51, 1200, 650);
		contentPane.add(label);
	}
}
