package sn.uasz.tp2.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sn.uasz.tp2.main.Main;
import sn.uasz.tp2.model.Membre;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Recuperation extends JFrame {

	private JPanel contentPane;
	private JPasswordField codepasse;

	Membre membre = new Membre();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Recuperation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		codepasse = new JPasswordField();
		codepasse.setBounds(137, 25, 118, 29);
		contentPane.add(codepasse);

		JButton btn_annuler = new JButton("Annuler");
		btn_annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main frame = new Main();
				frame.setTitle("Accueil");
				Image ic = Toolkit.getDefaultToolkit()
						.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
				frame.setIconImage(ic);
				frame.setResizable(false);
				frame.setVisible(true);
			}
		});
		btn_annuler.setBackground(new Color(128, 128, 128));
		btn_annuler.setForeground(new Color(0, 0, 128));
		btn_annuler.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_annuler.setBounds(30, 89, 102, 29);
		contentPane.add(btn_annuler);

		JButton btnVerifier = new JButton("Verifier");
		btnVerifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerifier) {
					String code = codepasse.getText();
					if (code.equals("")) {
						JOptionPane.showMessageDialog(btnVerifier, "Veuillez saisir ce code.");
					} else {
						try {
							int id = membre.verifCode(code);
							if (id != -1) {
								dispose();
								Changer frame = new Changer(id);
								frame.setTitle("Modifier son mot de passe");
								Image ic = Toolkit.getDefaultToolkit()
										.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
								frame.setIconImage(ic);
								frame.setResizable(false);
								frame.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(btnVerifier,
										"Le code saisi est erroné. Veuillez-vous rapprocher de votre administrateur");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnVerifier.setBackground(new Color(128, 128, 128));
		btnVerifier.setForeground(new Color(0, 0, 128));
		btnVerifier.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnVerifier.setBounds(157, 89, 102, 29);
		contentPane.add(btnVerifier);

		JLabel lblNewLabel = new JLabel("Code de verification");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 24, 132, 29);
		contentPane.add(lblNewLabel);
	}
}
