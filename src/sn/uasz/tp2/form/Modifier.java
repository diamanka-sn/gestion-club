package sn.uasz.tp2.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import sn.uasz.tp2.connexion.DbConnexion;

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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

import sn.uasz.tp2.form.ListeMembre;
import sn.uasz.tp2.model.Membre;
import sn.uasz.tp2.validation.ValiderEmail;

public class Modifier extends JFrame {
	// ListeMembre parent;
	private JPanel contentPane;
	private JTextField txt_prenom;
	private JFormattedTextField txt_numero;
	private JTextField txt_nom;
	private JTextField txt_email;

	ArrayList<String> listeProfession = new ArrayList<String>();

	ValiderEmail valider = new ValiderEmail();

	private JDateChooser dateChooser;

	ListeMembre m = new ListeMembre();

	SimpleDateFormat dchoix = new SimpleDateFormat("dd/MM/yyyy");
	Date dactuelle = new Date();
	String dA = dchoix.format(dactuelle);
	String date = null;
	Date dateJour = null, dateChoix = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Modifier frame = new Modifier(int id);

					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param id
	 * @param udate
	 */
	public int verifDate() {
		date = dchoix.format(dateChooser.getDate());
		int retour = 0;
		try {
			dateJour = dchoix.parse(dA);
			dateChoix = dchoix.parse(date);
			if (dateChoix.after(dateJour)) {
				// JOptionPane.showMessageDialog(null, "Veuillez choisir une Date correcte");
				retour = -1;
			}
		} catch (ParseException e2) {
		}

		return retour;
	}

	public Modifier(int id, String prenom, String nom, String email, String usexe, String numero, String udate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 362);
		System.out.println(id);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txt_prenom = new JTextField();
		txt_prenom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_prenom.setText(prenom);
		txt_prenom.setBounds(10, 24, 173, 30);
		contentPane.add(txt_prenom);
		txt_prenom.setColumns(10);

		txt_prenom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
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
		// txt_numero = new JFormattedTextField();
		txt_numero.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_numero.setText(numero);
		txt_numero.setColumns(10);
		txt_numero.setBounds(68, 86, 115, 30);
		contentPane.add(txt_numero);

		txt_nom = new JTextField();
		txt_nom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_nom.setText(nom);
		txt_nom.setColumns(10);
		txt_nom.setBounds(209, 24, 173, 30);
		contentPane.add(txt_nom);

		txt_nom.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

		txt_email = new JTextField();
		txt_email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt_email.setText(email);
		txt_email.setColumns(10);
		txt_email.setBounds(209, 86, 173, 30);
		contentPane.add(txt_email);

		dateChooser = new JDateChooser();
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
		contentPane.add(rd_masculin);

		JRadioButton rd_feminin = new JRadioButton("Feminin");
		rd_feminin.setBounds(502, 90, 78, 23);
		rd_feminin.setActionCommand("Feminin");
		contentPane.add(rd_feminin);
		if (usexe.equals("Masculin")) {
			rd_masculin.setSelected(true);
		} else {
			rd_feminin.setSelected(true);
		}
		ButtonGroup rd = new ButtonGroup();
		rd.add(rd_feminin);
		rd.add(rd_masculin);

		JComboBox operateurCombo = new JComboBox();
		operateurCombo.setBackground(Color.GRAY);
		operateurCombo.setModel(new DefaultComboBoxModel(new String[] { "77", "78", "76", "70", "75" }));
		operateurCombo.setBounds(10, 86, 48, 30);
		contentPane.add(operateurCombo);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnModifier) {
					int sup = JOptionPane.showConfirmDialog(btnModifier, "Voulez-vous vraiment modifier ce membre ?",
							"Confirmation de la modification", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (sup == 0) {
						String nom = txt_nom.getText();
						String prenom = txt_prenom.getText();
						String num = txt_numero.getText();
						String numero = operateurCombo.getSelectedItem().toString() + "-" + txt_numero.getText();
						String email = txt_email.getText();
						String profession = txt_profession.getSelectedItem().toString();
						String sexe = rd.getSelection().getActionCommand();
						// String date = txt_date.getText();
						date = dchoix.format(dateChooser.getDate());
						if (nom.equals("") || prenom.equals("") || num.contains("*") || profession.equals("")) {
							JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
						} else if (dateChooser.getDate() == null) {
							JOptionPane.showMessageDialog(null, "Veuillez choisir une Date");
						} else if (verifDate() == -1) {
							JOptionPane.showMessageDialog(null, "Veuillez choisir une Date correcte");
						} else if (!valider.validerMail(email.trim())) {
							JOptionPane.showMessageDialog(null, "Email invalide!!!");
						} else {
							try {
								new Membre().modifier(id, prenom, nom, email, sexe, date, numero, profession);
								JOptionPane.showMessageDialog(btnModifier,
										"Membre avec l'identiant: " + id + " est modifié avec success!");
								dispose();
								ListeMembre frame = new ListeMembre();
								frame.setTitle("Liste Membre");
								Image ic = Toolkit.getDefaultToolkit()
										.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
								frame.setIconImage(ic);
								// frame.setResizable(false);
								frame.setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}

				}
			}
		});
		btnModifier.setBackground(new Color(128, 128, 128));
		btnModifier.setForeground(new Color(0, 0, 128));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnModifier.setBounds(10, 206, 118, 30);
		btnModifier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnModifier);

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

		dateChooser.setBounds(10, 145, 173, 30);
		contentPane.add(dateChooser);
	}
}
