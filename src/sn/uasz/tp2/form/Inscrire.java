package sn.uasz.tp2.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import sn.uasz.tp2.connexion.DbConnexion;
import sn.uasz.tp2.model.Membre;
import sn.uasz.tp2.validation.ValiderEmail;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;

public class Inscrire extends JFrame {

	private JPanel contentPane;
	private JTextField txt_prenom;
	private JFormattedTextField txt_numero;
	private JTextField txt_nom;
	private JFormattedTextField txt_email;

	ArrayList<String> annee = new ArrayList<String>();

	ArrayList<String> listeProfession = new ArrayList<String>();
	Membre membre = new Membre();
	ValiderEmail valider = new ValiderEmail();

	JDateChooser dateChooser;
	SimpleDateFormat dchoix = new SimpleDateFormat("dd/MM/yyyy");
	Date dactuelle = new Date();
	String dA = dchoix.format(dactuelle);
	String date = null;
	Date dateJour = null, dateChoix = null;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public void effacerChamp() {
		txt_email.setText("Email");
		txt_nom.setText("Nom");
		txt_prenom.setText("Prenom");
	}

	public int verifDate() {
		date = dchoix.format(dateChooser.getDate());
		int retour = 0;
		try {
			dateJour = dchoix.parse(dA);
			dateChoix = dchoix.parse(date);
			if (dateChoix.after(dateJour)) {
				retour = -1;
			}
		} catch (ParseException e2) {
		}

		return retour;
	}

	public Inscrire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txt_prenom = new JTextField();
		txt_prenom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_prenom.setText("Prenom");
		txt_prenom.setBounds(10, 24, 173, 30);
		contentPane.add(txt_prenom);
		txt_prenom.setColumns(10);

		txt_prenom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		try {
			MaskFormatter numb = new MaskFormatter("***-**-**");
			numb.setPlaceholderCharacter('*');
			txt_numero = new JFormattedTextField(numb);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		txt_numero.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});

		txt_numero.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		// t_numero.setText("Numero");
		txt_numero.setColumns(10);
		txt_numero.setBounds(68, 86, 115, 30);
		contentPane.add(txt_numero);

		txt_nom = new JTextField();
		txt_nom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_nom.setText("Nom");
		txt_nom.setColumns(10);
		txt_nom.setBounds(209, 24, 173, 30);
		contentPane.add(txt_nom);

		txt_nom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		txt_email = new JFormattedTextField();
		txt_email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_email.setText("Email");
		txt_email.setColumns(10);
		txt_email.setBounds(209, 86, 173, 30);
		contentPane.add(txt_email);

		JComboBox txt_profession = new JComboBox();

		String sql = "Select Distinct nom_profession from profession";
		Connection con = DbConnexion.conDb();
		try {

			PreparedStatement p = con.prepareStatement(sql);
			ResultSet r = p.executeQuery();

			while (r.next()) {
				listeProfession.add(r.getString("nom_profession"));
			}

		} catch (Exception e) {

		}
		// comboBox.setModel(new DefaultComboBoxModel(new String[] {"mecanicien"}));
		txt_profession.setModel(new DefaultComboBoxModel(listeProfession.toArray()));

		txt_profession.setBackground(new Color(128, 128, 128));
		txt_profession.setBounds(404, 24, 169, 26);
		contentPane.add(txt_profession);

		JRadioButton rd_masculin = new JRadioButton("Masculin");
		rd_masculin.setBounds(401, 90, 78, 23);
		rd_masculin.setActionCommand("Masculin");
		rd_masculin.setSelected(true);
		contentPane.add(rd_masculin);

		JRadioButton rd_feminin = new JRadioButton("Feminin");
		rd_feminin.setBounds(502, 90, 78, 23);
		rd_feminin.setActionCommand("Feminin");
		contentPane.add(rd_feminin);

		ButtonGroup rd = new ButtonGroup();
		rd.add(rd_feminin);
		rd.add(rd_masculin);
		dateChooser = new JDateChooser();

		JComboBox operateurCombo = new JComboBox();
		operateurCombo.setBackground(Color.GRAY);
		operateurCombo.setModel(new DefaultComboBoxModel(new String[] { "77", "78", "76", "70", "75" }));
		operateurCombo.setBounds(10, 86, 48, 30);
		contentPane.add(operateurCombo);
		JButton btnInscription = new JButton("Inscrire");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnInscription) {

					String nom = txt_nom.getText();
					String prenom = txt_prenom.getText();
					String num = txt_numero.getText();
					String numero = operateurCombo.getSelectedItem().toString() + "-" + num;
					String email = txt_email.getText();
					String profession = txt_profession.getSelectedItem().toString();
					String sexe = rd.getSelection().getActionCommand();
					// String date = txt_date.getText();

					if (nom.equals("") || prenom.equals("") || num.contains("*") || profession.equals("")) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
					} else if (nom.equals("Nom") || prenom.equals("Prenom")) {
						JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
					} else if ((nom.length() < 2 || nom.length() > 20)
							|| (prenom.length() < 2 || prenom.length() > 20)) {
						JOptionPane.showMessageDialog(null,
								"Veuillez donner un nom ou un prenom superieur à 2 et inferieur à 20 caracteres");
					} else if (dateChooser.getDate() == null) {
						JOptionPane.showMessageDialog(null, "Veuillez choisir une Date");
					} else if (verifDate() == -1) {
						JOptionPane.showMessageDialog(null, "Veuillez choisir une Date correcte");
					} else if (!valider.validerMail(email.trim())) {
						JOptionPane.showMessageDialog(null, "Email invalide!!!");
					} else {
						date = dchoix.format(dateChooser.getDate());
						int status;
						try {
							// int verif = membre.verifierMembre(email);
							// if (verif == 1) {
							// System.out.println("Membre existe deja");
							// } else {
							status = membre.insertion(prenom, nom, email, sexe, date, numero, profession);
							if (status > 0) {
								JOptionPane.showMessageDialog(null, "Enregistrer avec success!!!!!!");
								effacerChamp();
							} else {
								JOptionPane.showMessageDialog(null, "Membre existe déjà!!!!!!");
							}
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Membre existe déjà!!!!!!");
						}
					}
				}
			}
		});
		btnInscription.setBackground(new Color(128, 128, 128));
		btnInscription.setForeground(new Color(0, 0, 128));
		btnInscription.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInscription.setBounds(10, 206, 118, 30);
		btnInscription.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnInscription);

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
		btn_retour.setBackground(new Color(128, 128, 128));
		btn_retour.setForeground(new Color(0, 0, 128));
		btn_retour.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_retour.setBounds(10, 273, 118, 30);
		btn_retour.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btn_retour);

		JComboBox anneeBox = new JComboBox();
		for (int i = 1980; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
			annee.add(i + "");
		}
		anneeBox.setModel(new DefaultComboBoxModel(annee.toArray()));
		anneeBox.setBounds(480, 169, 65, 22);
		contentPane.add(anneeBox);

		dateChooser.setBounds(10, 145, 173, 30);
		contentPane.add(dateChooser);
	}
}
