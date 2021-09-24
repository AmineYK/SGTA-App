import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenDirecteur extends JFrame {

	private JPanel contentPane;
	private JTextField fretfield;
	private JTextField operateurfield;
	private JTextField comfield;
	private JTable aeroportstable;
	private JTable listenoirtable;
	private JTable pistedensetable;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					FenDirecteur frame = new FenDirecteur();
					
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
	public FenDirecteur() {
		super("Directeur Generale");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1202,560);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setLayout(null);
		cnx=ConnexionMySql.ConnectionDB();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(20, 0, 1165, 29);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Gestion des Aeronefs");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DirecteurGestionAeronefs obj = new DirecteurGestionAeronefs();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu.setToolTipText("Gestion des Aeronefs....");
				mnNewMenu.setBackground(Color.LIGHT_GRAY);
			}
			public void mouseExited(MouseEvent e){
				mnNewMenu.setBackground(Color.white);
			}
		});
		mnNewMenu.setIcon(new ImageIcon(getClass().getResource("/icons8-mode-avion-on-25.png")));
		mnNewMenu.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnNewMenu);
		
		JMenu mnGestionDesVols = new JMenu("  Gestion des Vols");
		mnGestionDesVols.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DirecteurGestionsVols obj = new DirecteurGestionsVols();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				mnGestionDesVols.setToolTipText("Gestion des Vols....");
			}
		});
		mnGestionDesVols.setIcon(new ImageIcon(getClass().getResource("/icons8-british-airways-25 (1).png")));
		mnGestionDesVols.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionDesVols);
		
		JMenu mnGestionDesPistes = new JMenu("  Gestion des Pistes");
		mnGestionDesPistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DirecteurGestionPistes obj = new DirecteurGestionPistes();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				mnGestionDesPistes.setToolTipText("Gestion des Pistes....");
			}
		});
		mnGestionDesPistes.setIcon(new ImageIcon(getClass().getResource("/icons8-piste-22 (1).png")));
		menuBar.add(mnGestionDesPistes);
		mnGestionDesPistes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		
		JMenu mnGestionDeLoperateur = new JMenu(" Gestion de l'Operateur");
		mnGestionDeLoperateur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DirecteurGestionOperateur obj = new DirecteurGestionOperateur();
				obj.setVisible(true);
				dispose();
				obj.setLocationRelativeTo(null);
			}
			public void mouseEntered(MouseEvent e) {
				mnGestionDeLoperateur.setToolTipText("Gestion de l'Operateur....");
			}
		});
		mnGestionDeLoperateur.setIcon(new ImageIcon(getClass().getResource("/icons8-utilisateur-22.png")));
		mnGestionDeLoperateur.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionDeLoperateur);
		
		JMenu mnGestion = new JMenu(" Gestion des Compgnies ");
		mnGestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DirecteurGestionCompagnies obj = new DirecteurGestionCompagnies();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
                dispose();  
 			}
			public void mouseEntered(MouseEvent e) {
				mnGestion.setToolTipText("Gestion des Compagnies....");
			}
		});
		mnGestion.setIcon(new ImageIcon(getClass().getResource("/icons8-avion-hublot-ouvert-22.png")));
		menuBar.add(mnGestion);
		mnGestion.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		
		JButton btnVoirLeFret = new JButton("Voir le Fret Realis\u00E9");
		btnVoirLeFret.setBackground(new Color(153,255,255));
		btnVoirLeFret.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLeFret.setBackground(new Color(153,255,255));
				btnVoirLeFret.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirLeFret.setForeground(new Color(153,255,255));
				btnVoirLeFret.setBackground(new Color(105,105,105));
			}
		});
		btnVoirLeFret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select sum(montant) from facture";
				try {
					prepared=cnx.prepareStatement(sql);
					resultat=prepared.executeQuery();
                    if(resultat.next()){				
					fretfield.setText(resultat.getString("sum(montant)"));
				} }catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirLeFret.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLeFret.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnVoirLeFret.setBounds(70, 56, 214, 29);
		contentPane.add(btnVoirLeFret);
		
		fretfield = new JTextField();
		fretfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		fretfield.setBounds(83, 96, 176, 27);
		contentPane.add(fretfield);
		fretfield.setColumns(10);
		
		JButton btnVoirLaCompagnie_1 = new JButton("Voir l'Operateur le plus consciencieux");
		btnVoirLaCompagnie_1.setBackground(new Color(153,255,255));
		btnVoirLaCompagnie_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLaCompagnie_1.setBackground(new Color(153,255,255));
				btnVoirLaCompagnie_1.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirLaCompagnie_1.setForeground(new Color(153,255,255));
				btnVoirLaCompagnie_1.setBackground(new Color(105,105,105));
			}
		});
		btnVoirLaCompagnie_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*String sql = "select * from nboperateur";
			int max = 0 ;int a ;String s;
			String num = null;
			try {
				prepared=cnx.prepareStatement(sql);
			    resultat=prepared.executeQuery();
			    while(resultat.next()){
			    	s = resultat.getString("compteur");
			    	a=Integer.valueOf(s);
			    	if(a>max){
			    		max = a ;
			    	}
			    }
			    String Max = String.valueOf(max);
			    String sql1="select * from nboperateur where compteur = "+Max+"";
			    cnx1=ConnexionMySql.ConnectionDB();
			    cnx2=ConnexionMySql.ConnectionDB();
			    prepared1=cnx1.prepareStatement(sql1);
			    resultat1=prepared1.executeQuery();
			    if(resultat1.next()){
			    	num =resultat1.getString("num_operateur");
			    }
			    
			    String sql2="select * from operateurusers where matricule = "+num+"";
			    prepared2=cnx2.prepareStatement(sql2);
			    resultat2=prepared2.executeQuery();
			    if(resultat2.next()){
			    	operateurfield.setText(resultat2.getString("nom") +" "+ resultat2.getString("prenom"));
			    }
			    
			    
			    
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
				String sql = "select nom,prenom from operateurusers, nboperateur where operateurusers.matricule = nboperateur.num_operateur"
						+ " AND nboperateur.compteur in ( select max(compteur) from nboperateur ) " ;
						
				try {
					prepared=cnx.prepareStatement(sql);
				    resultat=prepared.executeQuery();
				    while(resultat.next()){
				    	operateurfield.setText(resultat.getString("nom")+ " "+ resultat.getString("prenom"));
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnVoirLaCompagnie_1.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLaCompagnie_1.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 14));
		btnVoirLaCompagnie_1.setBounds(368, 56, 392, 29);
		contentPane.add(btnVoirLaCompagnie_1);
		
		JButton btnVoirLaCompagnie = new JButton("Voir la compagnie la plus impos\u00E9e");
		btnVoirLaCompagnie.setBackground(new Color(153,255,255));
		btnVoirLaCompagnie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLaCompagnie.setBackground(new Color(153,255,255));
				btnVoirLaCompagnie.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirLaCompagnie.setForeground(new Color(153,255,255));
				btnVoirLaCompagnie.setBackground(new Color(105,105,105));
			}
		});
		btnVoirLaCompagnie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*String sql ="select * from nbcompagnie";
			String aa=null;int  a;int max=0;
			try {
				prepared=cnx.prepareStatement(sql);
			    resultat=prepared.executeQuery();
			    while( resultat.next()){
			    	aa=resultat.getString("compteur");
			    	a=Integer.valueOf(aa);
			    	if(a>max){
			    		max=a;
			    	}
			    }
			    String cpt=String.valueOf(max);
			    String sql1="select * from nbcompagnie where compteur ="+cpt+"";
			    cnx1=ConnexionMySql.ConnectionDB();
			    prepared1=cnx1.prepareStatement(sql1);
			    resultat1=prepared1.executeQuery();
			    if(resultat1.next()){
			    	comfield.setText(resultat1.getString("nom"));
			    }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
				String sql ="select * from nbcompagnie where compteur in ( select max(compteur) from nbcompagnie )";
				try {
					prepared=cnx.prepareStatement(sql);
				    resultat=prepared.executeQuery();
				    if (resultat.next()){
				    	comfield.setText(resultat.getString("nom"));
				    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnVoirLaCompagnie.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLaCompagnie.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 14));
		btnVoirLaCompagnie.setBounds(827, 56, 358, 29);
		contentPane.add(btnVoirLaCompagnie);
		
		operateurfield = new JTextField();
		operateurfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		operateurfield.setColumns(10);
		operateurfield.setBounds(468, 96, 225, 27);
		contentPane.add(operateurfield);
		
		comfield = new JTextField();
		comfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		comfield.setColumns(10);
		comfield.setBounds(898, 96, 246, 27);
		contentPane.add(comfield);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 134, 340, 374);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnVoirListeDes = new JButton("Voir Liste des Aeroports Desservis");
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
		btnVoirListeDes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql =" select distinct  aero_a from vol ";
				
				try {
					prepared=cnx.prepareStatement(sql);
				     resultat=prepared.executeQuery();
				     aeroportstable.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnVoirListeDes.setBounds(10, 11, 320, 29);
		btnVoirListeDes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirListeDes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		panel.add(btnVoirListeDes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 320, 312);
		panel.add(scrollPane);
		
		aeroportstable = new JTable();
		aeroportstable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		aeroportstable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(aeroportstable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBounds(845, 134, 340, 374);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnVoirLes = new JButton("Voir les 5 Pistes les plus Denses");
		btnVoirLes.setBackground(new Color(153,255,255));
		btnVoirLes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirLes.setBackground(new Color(153,255,255));
				btnVoirLes.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirLes.setForeground(new Color(153,255,255));
				btnVoirLes.setBackground(new Color(105,105,105));
			}
		});
		btnVoirLes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String sql = "select num_piste , largeur from piste where piste.num_piste in ( select nbpiste.num_piste from nbpiste) ";
			try {
				prepared=cnx.prepareStatement(sql);
				resultat=prepared.executeQuery();
				
					pistedensetable.setModel(DbUtils.resultSetToTableModel(resultat));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnVoirLes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirLes.setBounds(23, 11, 292, 28);
		btnVoirLes.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		panel_1.add(btnVoirLes);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 47, 320, 316);
		panel_1.add(scrollPane_2);
		
		pistedensetable = new JTable();
		pistedensetable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		pistedensetable.setBackground(new Color(153, 255, 255));
		scrollPane_2.setViewportView(pistedensetable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.WHITE, 2));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(360, 134, 475, 374);
		contentPane.add(panel_2);
		
		JButton btnVoirListeNoir = new JButton("Voir Liste Noir");
		btnVoirListeNoir.setBackground(new Color(153,255,255));
		btnVoirListeNoir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirListeNoir.setBackground(new Color(153,255,255));
				btnVoirListeNoir.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirListeNoir.setForeground(new Color(153,255,255));
				btnVoirListeNoir.setBackground(new Color(105,105,105));
			}
		});
		btnVoirListeNoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirListeNoir.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirListeNoir.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 14));
		btnVoirListeNoir.setBounds(148, 11, 182, 31);
		panel_2.add(btnVoirListeNoir);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(153,255,255));
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnAjouter.setBackground(new Color(153,255,255));
				btnAjouter.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnAjouter.setForeground(new Color(153,255,255));
				btnAjouter.setBackground(new Color(105,105,105));
			}
		});
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirecteurAjouterListeNoir obj = new DirecteurAjouterListeNoir();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);
				
			}
		});
		btnAjouter.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		btnAjouter.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnAjouter.setBounds(60, 340, 131, 26);
		panel_2.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(153,255,255));
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimer.setBackground(new Color(153,255,255));
				btnSupprimer.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnSupprimer.setForeground(new Color(153,255,255));
				btnSupprimer.setBackground(new Color(105,105,105));
			}
		});
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = listenoirtable.getSelectedRow();
				if(ligne == -1){
					JOptionPane.showMessageDialog(null,"Selectionnez une ligne");
				}else{
				String id = listenoirtable.getModel().getValueAt(ligne,0).toString();
				int a =JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment la supprimer de la Liste Noire ??","Suppression de la Liste Noire",JOptionPane.YES_NO_OPTION);
				String sql = " delete from listenoir where id_compagnie = ' "+id+" ' ";
				
				try {
					prepared=cnx.prepareStatement(sql);
					if(a==0){prepared.execute();
					JOptionPane.showMessageDialog(null,"Compagnie Aerienne supprimée de la liste noir");
					updatetable();
				}} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}}
		});
		btnSupprimer.setIcon(new ImageIcon(getClass().getResource("/icons8-supprimer-22.png")));
		btnSupprimer.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnSupprimer.setBounds(270, 340, 147, 26);
		panel_2.add(btnSupprimer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 51, 455, 285);
		panel_2.add(scrollPane_1);
		
		listenoirtable = new JTable();
		listenoirtable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		listenoirtable.setBackground(new Color(153, 255, 255));
		scrollPane_1.setViewportView(listenoirtable);
		
		JLabel lblEnDa = new JLabel("en DA");
		lblEnDa.setForeground(new Color(0, 0, 0));
		lblEnDa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 10));
		lblEnDa.setBounds(261, 103, 46, 14);
		contentPane.add(lblEnDa);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment eteindre le logiciel ??","la Mise hors tension du logiciel ", JOptionPane.YES_NO_CANCEL_OPTION);
				if(a==0){
					dispose();
				}
			}
		});
		label_1.setIcon(new ImageIcon(getClass().getResource("/button_turn_on_15006.png")));
		label_1.setBounds(5, 27, 41, 46);
		contentPane.add(label_1);
		
		JLabel fondecran = new JLabel("");
		fondecran.setBackground(Color.LIGHT_GRAY);
		fondecran.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		fondecran.setBounds(0, -51, 1200, 650);
		contentPane.add(fondecran);
	}
	public void updatetable(){
		String sql = "Select * from listenoir";
		
		try {
			prepared=cnx.prepareStatement(sql);
            resultat=prepared.executeQuery();
            listenoirtable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			}
}
