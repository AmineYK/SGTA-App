import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FenOperateur extends JFrame {

	private JPanel contentPane;
	private JTextField volfield;
	private JTextField poidsfield;
	private JTextField totalfield;
	private JTable listegenraletable;
	private JTable pistelsibrestable;
	JComboBox compagniebox;
	Connection cnx=null;
	PreparedStatement prepared=null;
	ResultSet resultat = null;
	Connection cnx1=null;
	PreparedStatement prepared1=null;
	ResultSet resultat1 = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					FenOperateur frame = new FenOperateur();
					
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
	public FenOperateur() {
		super("Operateur de Controle Aerien");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100,1202,560);
		cnx=ConnexionMySql.ConnectionDB();
		contentPane = new JPanel();
		ImageIcon obj = new ImageIcon(getClass().getResource("/logo.png"));
		super.setIconImage(obj.getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 66, 391, 454);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnVoirListeDes = new JButton("Voir Liste des Pistes Libres");
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
				String id="degagé";
				String sql = " select * from piste where etat = '"+id+"'";
				
				try {
					prepared=cnx.prepareStatement(sql);
					resultat=prepared.executeQuery();
					pistelsibrestable.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVoirListeDes.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		btnVoirListeDes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnVoirListeDes.setBounds(37, 11, 301, 31);
		panel.add(btnVoirListeDes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 371, 365);
		panel.add(scrollPane);
		
		pistelsibrestable = new JTable();
		pistelsibrestable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		pistelsibrestable.setBackground(new Color(153, 255, 255));
		scrollPane.setViewportView(pistelsibrestable);
		
		JButton button_2 = new JButton("Ajouter");
		button_2.setBackground(new Color(153,255,255));
		button_2.addMouseListener(new MouseAdapter() {
			//button_2
			public void mouseExited(MouseEvent e) {
				button_2.setBackground(new Color(153,255,255));
				button_2.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				button_2.setForeground(new Color(153,255,255));
				button_2.setBackground(new Color(105,105,105));
			}
		});
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurAjouterPisteLibre obj = new  OperateurAjouterPisteLibre();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);
				
			}
		});
		button_2.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		button_2.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		button_2.setBounds(126, 418, 135, 31);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 2));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(756, 66, 439, 454);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnVoirListeGenerale = new JButton("Voir Liste Generale");
		btnVoirListeGenerale.setBackground(new Color(153,255,255));
		btnVoirListeGenerale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnVoirListeGenerale.setBackground(new Color(153,255,255));
				btnVoirListeGenerale.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnVoirListeGenerale.setForeground(new Color(153,255,255));
				btnVoirListeGenerale.setBackground(new Color(105,105,105));
			}
		});
		btnVoirListeGenerale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetable();
			}
		});
		btnVoirListeGenerale.setBounds(112, 11, 222, 31);
		btnVoirListeGenerale.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		btnVoirListeGenerale.setIcon(new ImageIcon(getClass().getResource("/icons8-eye-22.png")));
		panel_1.add(btnVoirListeGenerale);
		
		JButton button = new JButton("Ajouter");
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
				OperateurAjouterListeGenerale obj = new OperateurAjouterListeGenerale();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);
				
			}
		});
		button.setIcon(new ImageIcon(getClass().getResource("/icons8-ajouter-22.png")));
		button.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		button.setBounds(60, 414, 135, 31);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Supprimer");
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
			int ligne = listegenraletable.getSelectedRow();
			if(ligne == -1){
				JOptionPane.showMessageDialog(null,"Selectionnez une ligne au Niveau de la table");
			}else{
			String id=listegenraletable.getModel().getValueAt(ligne,0).toString();
			
				String sql = "delete from listegle where id_listegle ="+id+" " ;
				
				try {
					prepared=cnx.prepareStatement(sql);
				    prepared.execute();
				    JOptionPane.showMessageDialog(null,"Action supprimée de la Liste generale");
				    updatetable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}}
		});
		button_1.setIcon(new ImageIcon(getClass().getResource("/icons8-supprimer-22.png")));
		button_1.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 11));
		button_1.setBounds(266, 414, 135, 31);
		panel_1.add(button_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 46, 419, 363);
		panel_1.add(scrollPane_1);
		
		listegenraletable = new JTable();
		listegenraletable.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		listegenraletable.setBackground(new Color(153, 255, 255));
		scrollPane_1.setViewportView(listegenraletable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE, 2, true));
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(409, 66, 337, 454);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		volfield = new JTextField();
		volfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		volfield.setBounds(241, 122, 86, 22);
		panel_2.add(volfield);
		volfield.setColumns(10);
		
		poidsfield = new JTextField();
		poidsfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		poidsfield.setBounds(219, 193, 86, 22);
		panel_2.add(poidsfield);
		poidsfield.setColumns(10);
		
		JButton btnPoids = new JButton("Poids < 2t ou vol humanitaire");
		btnPoids.setBackground(new Color(153,255,255));
		btnPoids.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnPoids.setBackground(new Color(153,255,255));
				btnPoids.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnPoids.setForeground(new Color(153,255,255));
				btnPoids.setBackground(new Color(105,105,105));
			}
		});
		btnPoids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int cpt = 0 ; int cpt1=0;
			    String tString=null,tString1=null;
				String sql ="select count(*) from aeronef  where nom= '"+compagniebox.getSelectedItem().toString()+ "' and poids < 2 ";
				
				try {
					prepared= cnx.prepareStatement(sql);
				    resultat=prepared.executeQuery();
				    while(resultat.next()){
				    	cpt =Integer.valueOf(resultat.getString("count(*)"));
				    }
				    String id = "Humanitaire";
				    String sql1="select count(*) from vol where nom = '"+compagniebox.getSelectedItem().toString()+"' and  type_vol = '"+id+"'";
				    cnx1=ConnexionMySql.ConnectionDB();
				   prepared1=cnx1.prepareStatement(sql1);
				    resultat1=prepared1.executeQuery();
				    while(resultat1.next()){
				    	cpt1  = Integer.valueOf(resultat1.getString("count(*)"));				    }
				    String t = String.valueOf(cpt+cpt1);
				    volfield.setText(t);
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				 }
				   
				}
			
		});
		btnPoids.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnPoids.setBounds(10, 120, 221, 28);
		panel_2.add(btnPoids);
		
		JButton btnPoidsEntrett = new JButton("Poids entre 2t et 6t ");
		btnPoidsEntrett.setBackground(new Color(153,255,255));
		btnPoidsEntrett.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnPoidsEntrett.setBackground(new Color(153,255,255));
				btnPoidsEntrett.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnPoidsEntrett.setForeground(new Color(153,255,255));
				btnPoidsEntrett.setBackground(new Color(105,105,105));
			}
		});
		btnPoidsEntrett.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql= "select count(*) from vol where nom ='"+compagniebox.getSelectedItem().toString()+"' and fret < 6 and fret > 2";
				int cpt = 0;
				try {
					prepared=cnx.prepareStatement(sql);
				    resultat=prepared.executeQuery();
				    while(resultat.next()){
				    	cpt =Integer.valueOf(resultat.getString("count(*)"));
				    }
				    String t =String.valueOf(cpt);
				    poidsfield.setText(t);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPoidsEntrett.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		btnPoidsEntrett.setBounds(38, 191, 173, 28);
		panel_2.add(btnPoidsEntrett);
		
		JButton btnTotal = new JButton("TOTAL ");
		btnTotal.setBackground(new Color(153,255,255));
		btnTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnTotal.setBackground(new Color(153,255,255));
				btnTotal.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnTotal.setForeground(new Color(153,255,255));
				btnTotal.setBackground(new Color(105,105,105));
			}
		});
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select count(*) from vol where nom ='"+compagniebox.getSelectedItem().toString()+"'";
				int cpt=0;
				try {
					prepared=cnx.prepareStatement(sql);
				    resultat=prepared.executeQuery();
				    while(resultat.next()){
				    	cpt =Integer.valueOf(resultat.getString("count(*)"));
				    }
				    String tString = String.valueOf(cpt);
				    totalfield.setText(tString);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTotal.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		btnTotal.setBounds(105, 274, 89, 28);
		panel_2.add(btnTotal);
		
		totalfield = new JTextField();
		totalfield.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		totalfield.setBounds(219, 277, 86, 22);
		panel_2.add(totalfield);
		totalfield.setColumns(10);
		
		JButton btnEtablirFactureEt = new JButton("Etablir Facture et Envoyer");
		btnEtablirFactureEt.setBackground(new Color(153,255,255));
		btnEtablirFactureEt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnEtablirFactureEt.setBackground(new Color(153,255,255));
				btnEtablirFactureEt.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnEtablirFactureEt.setForeground(new Color(153,255,255));
				btnEtablirFactureEt.setBackground(new Color(105,105,105));
			}
		});
		btnEtablirFactureEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  String sql="insert into facture ( nom_compagnie , montant ) values ( ? , ? )";
			  String SS = null;Double X1 = 0.0;Double X2  =0.0;Double X3 = 0.0;
			  try {
				  if(volfield.getText().equals("") || totalfield.getText().equals("") || poidsfield.getText().equals("")){
					  JOptionPane.showMessageDialog(null,"Vous devez voir d'abord les statistiques relatants le nombre de Vols !");
				  }else{
					  if(volfield.getText().equals("0") && totalfield.getText().equals("0") && poidsfield.getText().equals("0")){
						  JOptionPane.showMessageDialog(null,"Aucun vol effectué !!");
					  }else{
				prepared=cnx.prepareStatement(sql);
				prepared.setString(1,compagniebox.getSelectedItem().toString());
				int total = Integer.valueOf(totalfield.getText().toString());
				int poids = Integer.valueOf(poidsfield.getText().toString());
				int vol = Integer.valueOf(volfield.getText().toString());
				X1 = (1000 * 3 * 258 ) * 0.4 * poids;
				X2 =  (double) ((1000 * 3 * 258 )*(total - poids - vol));
				X3 = (X1 + X2 ) + (X1 + X2)*0.17 ;
				SS = String.valueOf(X3);
				prepared.setString(2,SS);
				
				prepared.execute();
				JOptionPane.showMessageDialog(null,"Montant calculé et facture envoyée");
			} }}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnEtablirFactureEt.setIcon(new ImageIcon(getClass().getResource("/icons8-envoyer-22.png")));
		btnEtablirFactureEt.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 13));
		btnEtablirFactureEt.setBounds(26, 393, 283, 31);
		panel_2.add(btnEtablirFactureEt);
		
		JLabel lblVoirNombreDaeronefs = new JLabel("Voir nombre d'aeronefs avec :");
		lblVoirNombreDaeronefs.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblVoirNombreDaeronefs.setBounds(77, 81, 184, 14);
		panel_2.add(lblVoirNombreDaeronefs);
		
		 compagniebox = new JComboBox();
		 compagniebox.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 12));
		compagniebox.setBounds(154, 24, 173, 22);
		panel_2.add(compagniebox);
		
		JLabel lblNomDeLa = new JLabel("Nom de la compagnie :");
		lblNomDeLa.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 11));
		lblNomDeLa.setBounds(10, 27, 184, 14);
		panel_2.add(lblNomDeLa);
		remplircompagnie();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(24, 0, 1155, 27);
		contentPane.add(menuBar);
		
		JMenu gestionaeronefmenu = new JMenu("Gestion des Aeronefs");
		gestionaeronefmenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurGestionAeronefs obj = new OperateurGestionAeronefs();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				gestionaeronefmenu.setToolTipText("Gestion des Aeronefs....");
			}
		});
		gestionaeronefmenu.setIcon(new ImageIcon(getClass().getResource("/icons8-mode-avion-on-25.png")));
		gestionaeronefmenu.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(gestionaeronefmenu);
		
		JMenu mnGestionDesVols = new JMenu("Gestion des Vols ");
		mnGestionDesVols.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurGestionVols obj = new OperateurGestionVols();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				mnGestionDesVols.setToolTipText("Gestion des Vols....");
			}
		});
		mnGestionDesVols.setIcon(new ImageIcon(getClass().getResource("/icons8-british-airways-25 (1).png")));
		mnGestionDesVols.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionDesVols);
		
		JMenu mnGestionDesPistes = new JMenu("Gestion des Pistes ");
		mnGestionDesPistes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurGestionPistes obj = new OperateurGestionPistes();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				mnGestionDesPistes.setToolTipText("Gestion des Pistes....");
			}
		});
		mnGestionDesPistes.setIcon(new ImageIcon(getClass().getResource("/icons8-piste-22 (1).png")));
		mnGestionDesPistes.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnGestionDesPistes);
		
		JMenu mnVisualiserStatistiques = new JMenu("Visualiser Statistiques ");
		mnVisualiserStatistiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurVisualiserStatistiques obj = new OperateurVisualiserStatistiques();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
			mnVisualiserStatistiques.setToolTipText("Voir les Statistiques....");
			}
		});
		mnVisualiserStatistiques.setIcon(new ImageIcon(getClass().getResource("/icons8-statistiques-22.png")));
		mnVisualiserStatistiques.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnVisualiserStatistiques);
		
		JMenu mnFicheDinformations = new JMenu("Fiche d'Informations");
		mnFicheDinformations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurFicheInformations obj = new OperateurFicheInformations();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				mnFicheDinformations.setToolTipText("voir la fiche d'informations....");
			}
		});
		mnFicheDinformations.setIcon(new ImageIcon(getClass().getResource("/icons8-information-22.png")));
		mnFicheDinformations.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnFicheDinformations);
		
		JMenu mnSuggestionDeDates = new JMenu("Suggestion de Dates");
		mnSuggestionDeDates.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OperateurSuggestionDates obj = new OperateurSuggestionDates();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				mnSuggestionDeDates.setToolTipText("voir les suggestions de dates....");
			}
		});
		mnSuggestionDeDates.setIcon(new ImageIcon(getClass().getResource("/icons8-jusqu'\u00E0-la-date-22.png")));
		mnSuggestionDeDates.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		menuBar.add(mnSuggestionDeDates);
		
		JButton btnConsulterLesServices = new JButton("Consulter les Services Supplementaires des Compagnies");
		btnConsulterLesServices.setBackground(new Color(153,255,255));
		btnConsulterLesServices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnConsulterLesServices.setBackground(new Color(153,255,255));
				btnConsulterLesServices.setForeground(Color.black);//
			}
			public void mouseEntered(MouseEvent e) {
				btnConsulterLesServices.setForeground(new Color(153,255,255));
				btnConsulterLesServices.setBackground(new Color(105,105,105));
			}
		});
		btnConsulterLesServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperateurConsulterService obj = new OperateurConsulterService();
				obj.setVisible(true);
				obj.setLocationRelativeTo(contentPane);
				
			}
		});
		btnConsulterLesServices.setIcon(new ImageIcon(getClass().getResource("/consult.png")));
		btnConsulterLesServices.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 12));
		btnConsulterLesServices.setBounds(391, 30, 508, 33);
		contentPane.add(btnConsulterLesServices);
		
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
		label_1.setBounds(5, 24, 41, 46);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1200, 550);
		contentPane.add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/GTAFE.png")));
		
	}public void updatetable(){
		cnx=ConnexionMySql.ConnectionDB();
		String sql = "select * from listegle";
		
		try {
			prepared=cnx.prepareStatement(sql);
		    resultat=prepared.executeQuery();
		    listegenraletable.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   	public void remplircompagnie(){
		cnx=ConnexionMySql.ConnectionDB();
		String sql= "select * from compagnieusers";
		
		try {
			prepared=cnx.prepareStatement(sql);
			resultat=prepared.executeQuery();
			while(resultat.next()){
				compagniebox.addItem(resultat.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
