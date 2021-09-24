import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import com.mysql.jdbc.CharsetMapping;
import com.toedter.calendar.JDateChooser;

public class OperateurAjouterListeGenerale extends JFrame {

	private JPanel contentPane;
	private JTextField heurefield;
	JDateChooser date;
	Connection cnx=null;
	PreparedStatement prepared=null;
	ResultSet resultat = null;
	JComboBox typebox;
JTextArea	problemesarea;
Character ccc;
JLabel lblerror1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					OperateurAjouterListeGenerale frame = new OperateurAjouterListeGenerale();
					
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
	public OperateurAjouterListeGenerale() {
		super("Operateur - Ajouter une action à la Liste Generale");
		setResizable(false);
		setBounds(100, 100, 439, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx=ConnexionMySql.ConnectionDB();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 23, 403, 430);
		contentPane.add(panel);
		panel.setLayout(null);
		
		 typebox = new JComboBox();
		typebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		typebox.setModel(new DefaultComboBoxModel(new String[] {"Decolage", "Atterissage"}));
		typebox.setBounds(186, 51, 182, 22);
		panel.add(typebox);
		
		heurefield = new JTextField();
		heurefield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		heurefield.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyPressed(KeyEvent e) {
				ccc = e.getKeyChar();
				if(Character.isLetter(ccc)){
					heurefield.setEditable(false);
					lblerror1.setText("Que des chiffres SVP");
				}else{
					heurefield.setEditable(true);
					lblerror1.setText("");
				}
				int key=e.getKeyCode();
				if(key==10){
					problemesarea.requestFocus();
				}
			}
		});
		
		heurefield.setBounds(186, 192, 182, 22);
		panel.add(heurefield);
		heurefield.setColumns(10);
		
		JLabel lblTypeDeLaction = new JLabel("Type de l'action :");
		lblTypeDeLaction.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblTypeDeLaction.setBounds(21, 53, 144, 14);
		panel.add(lblTypeDeLaction);
		
		JLabel lblDate = new JLabel("Date  :");
		lblDate.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblDate.setBounds(84, 130, 144, 14);
		panel.add(lblDate);
		
		JLabel lblHeure = new JLabel("Heure  :");
		lblHeure.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblHeure.setBounds(65, 196, 144, 14);
		panel.add(lblHeure);
		
		JLabel lblProblemesRecontrs = new JLabel("Problemes recontr\u00E9s :");
		lblProblemesRecontrs.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		lblProblemesRecontrs.setBounds(10, 337, 182, 14);
		panel.add(lblProblemesRecontrs);
		
		 problemesarea = new JTextArea();
		problemesarea.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		problemesarea.setForeground(Color.WHITE);
		problemesarea.setBackground(Color.GRAY);
		problemesarea.setLineWrap(true);
		problemesarea.setBounds(186, 250, 182, 169);
		panel.add(problemesarea);
		
		JDateChooser date = new JDateChooser();
		date.getCalendarButton().setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		date.setDateFormatString("yyyy/MM/dd");
		date.setBounds(186, 124, 182, 27);
		panel.add(date);
		
		 lblerror1 = new JLabel("");
		lblerror1.setForeground(Color.RED);
		lblerror1.setFont(new Font("Lucida Console", Font.ITALIC, 10));
		lblerror1.setBounds(151, 215, 176, 14);
		panel.add(lblerror1);
		
		JButton button = new JButton("Confirmer ");
		button.setBackground(new Color(153,255,255));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(new Color(153,255,255));
				button.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(153,255,255));
				button.setBackground(new Color(105,105,105));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!heurefield.getText().equals("") && !problemesarea.getText().equals("")){
			String sql ="insert into listegle (type_action , date_listegle , heure_listegle , problemes ) values ( ? , ? , ? , ? )";
			String dt = ((JTextField) date.getDateEditor().getUiComponent()).getText() ;
			try {
				prepared=cnx.prepareStatement(sql);
			    prepared.setString(1,typebox.getSelectedItem().toString());
			    prepared.setString(2,dt);
			    prepared.setString(3,heurefield.getText().toString());
			    prepared.setString(4,problemesarea.getText().toString());
			    
			    prepared.execute();
			    JOptionPane.showMessageDialog(null,"Action ajoutée a la Liste Generale");
			    heurefield.setText("");
			    problemesarea.setText("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else{
				JOptionPane.showMessageDialog(null,"Remplissez le(s) champ(s) vide(s)");
			}
				}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/icons8-bouton-de-radio-coch\u00E9-22.png")));
		button.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		button.setBounds(32, 460, 147, 33);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Annuler");
		button_1.setBackground(new Color(153,255,255));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setBackground(new Color(153,255,255));
				button_1.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(new Color(153,255,255));
				button_1.setBackground(new Color(105,105,105));
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(getClass().getResource("/icons8-annuler-22.png")));
		button_1.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		button_1.setBounds(243, 459, 147, 34);
		contentPane.add(button_1);
	}
}
