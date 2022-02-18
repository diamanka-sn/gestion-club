package sn.uasz.tp2.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sn.uasz.tp2.connexion.DbConnexion;
import sn.uasz.tp2.model.Membre;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ListeMembre extends JFrame {
	private String uprenom;
	private String unom;
	private String unumero = "";
	private String uemail;
	private String udate;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	JPanel p = new JPanel();
	JLabel label = new JLabel("Liste membre du club");
	Membre membre = new Membre();
	Object body[][] = new Object[100][8];
	String[] header = { "Identifiant", "Prenom", "Nom", "Email", "Sexe", "Numero", "Date adhesion", "Profession" };

	JTable table = null;
	private JTextField txtRecherche;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ListeMembre() {
		this.setLocationRelativeTo(null);
		this.setSize(700, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Liste membre");
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setForeground(Color.white);
		p.setBackground(new Color(0, 0, 128));

		JButton btn_retour = new JButton("Retour");
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn_retour) {
					dispose();
					Accueil frame = new Accueil();
					frame.setTitle("Accueil");
					Image ic = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
					frame.setIconImage(ic);
					frame.setResizable(false);
					frame.setVisible(true);
				}
			}
		});

		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnImprimer) {
					PrinterJob imp = PrinterJob.getPrinterJob();
					imp.setPrintable(table.getPrintable(PrintMode.NORMAL, null, null));
					if (imp.printDialog()) {
						if (imp.printDialog()) {
							try {
								imp.print();
							} catch (PrinterException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnImprimer.setForeground(new Color(0, 0, 128));
		btnImprimer.setBackground(new Color(128, 128, 128));
		btnImprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnImprimer.setFont(new Font("Times New Roman", Font.BOLD, 11));
		p.add(btnImprimer);
		btn_retour.setBackground(new Color(128, 128, 128));
		btn_retour.setForeground(new Color(0, 0, 128));
		btn_retour.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
		p.add(btn_retour);
		p.add(label);
		getContentPane().add(p, BorderLayout.NORTH);

		JButton btn_modifier = new JButton("Modifier");
		btn_modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifier();
			}
		});
		btn_modifier.setBackground(new Color(128, 128, 128));
		btn_modifier.setForeground(new Color(0, 0, 128));
		btn_modifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_modifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		p.add(btn_modifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSupprimer) {
					int i = table.getSelectedRow();
					if (i == -1) {
						JOptionPane.showMessageDialog(btnSupprimer, "Veuillez selectionner une ligne!!!");
					} else {
						int id = (int) body[i][0];
						int sup = JOptionPane.showConfirmDialog(btnSupprimer,
								"Voulez-vous vraiment supprimer ce membre ?", "Confirmation de la suppression",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if (sup == 0) {
							try {
								membre.supprimer(id);
								JOptionPane.showMessageDialog(btnSupprimer, "Membre supprimer avec succéss!!!");
								dispose();
								ListeMembre frame = new ListeMembre();
								frame.setTitle("Liste Membre");
								Image ic = Toolkit.getDefaultToolkit()
										.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
								frame.setIconImage(ic);
								// frame.setResizable(false);
								frame.setVisible(true);
								// membre.supprimerdutabeau(i);
							} catch (Exception s) {
								System.err.println(s);
							}
						}
					}
				}
			}
		});
		btnSupprimer.setBackground(new Color(128, 128, 128));
		btnSupprimer.setForeground(new Color(0, 0, 128));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSupprimer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		p.add(btnSupprimer);

		txtRecherche = new JTextField();
		txtRecherche.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		txtRecherche.setText("Rechercher");
		p.add(txtRecherche);
		txtRecherche.setColumns(10);
		txtRecherche.setBounds(10, 24, 173, 30);

		data();
	}

	public void data() {
		// Membre membre = new Membre();
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		int i = 0;
		for (Membre m : membre.afficher()) {
			body[i][0] = m.getId();
			body[i][1] = m.getPrenom();
			body[i][2] = m.getNom();
			body[i][3] = m.getEmail();
			body[i][4] = m.getSexe();
			body[i][5] = m.getNumero();
			body[i][6] = m.getDate_adhesion();
			body[i][7] = m.getProfession();
			i++;
		}
		getContentPane().add(new JScrollPane(table));
		table.setModel(new DefaultTableModel(body, header));
	}

	public void modifier() {

		// ArrayList<Membre> Membre = new ArrayList<Membre>();
		int i = table.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(this, "Veuillez selectionner une ligne!!!");
		} else {
			int id = (int) body[i][0];

			String uPrenom = (String) body[i][1];
			String uNom = (String) body[i][2];
			String uEmail = (String) body[i][3];
			String uSexe = (String) body[i][4];
			String uNumero = (String) body[i][5];
			String uDate = (String) body[i][6];
			String uProfession = (String) body[i][7];

			String[] num = uNumero.split("-");
			for (i = 1; i < num.length; i++) {
				unumero = unumero + num[i];
			}
			dispose();
			Modifier m = new Modifier(id, uPrenom, uNom, uEmail, uSexe, unumero, uDate);
			m.setResizable(false);
			Image ic = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
			m.setIconImage(ic);
			m.setTitle("Modification d'un membre");
			m.setVisible(true);
		}

	}
}
