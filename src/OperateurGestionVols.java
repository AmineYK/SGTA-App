import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperateurGestionVols extends JFrame {

	private JPanel contentPane;
	private JTable volstable;
    Connection cnx=null;
    PreparedStatement prepared=null;
    ResultSet resultat =null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurGestionVols frame = new OperateurGestionVols();
					
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
	public OperateurGestionVols() {
		super("Operateur - Gestion des Vols ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1203,560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx=ConnexionMySql.ConnectionDB();
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
		label_1.setBounds(10, 0, 46, 40);
		contentPane.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(66, 22, 1028, 499);
		contentPane.add(panel_1);
		
		JButton button_3 = new JButton("Voir les Vols");
		button_3.setBackground(new Color(153,255,255));
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_3.setBackground(new Color(153,255,255));
				button_3.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				button_3.setForeground(new Color(153,255,255));
				button_3.setBackground(new Color(105,105,105));
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from vol";
				
				try {
					prepared=cnx.prepareStatement(sql);
				    resultat=prepared.executeQuery();
				    volstable.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		button_3.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		button_3.setBounds(397, 7, 164, 34);
		panel_1.add(button_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 1008, 451);
		panel_1.add(scrollPane);
		
		volstable = new JTable();
		volstable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		volstable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(volstable);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		label.setBounds(0, -57, 1200, 650);
		contentPane.add(label);
	}
}
