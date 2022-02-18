package sn.uasz.tp2.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sn.uasz.tp2.main.Main;
import sn.uasz.tp2.model.Membre;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Accueil extends JFrame {

	private JPanel contentPane;
	Membre membre = new Membre();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Accueil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(Accueil.class.getResource("/sn/uasz/tp2/icone/icons8_inscription_100px.png")));
		lblNewLabel.setBounds(34, 43, 122, 124);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(Accueil.class.getResource("/sn/uasz/tp2/icone/icons8_list_view_100px.png")));
		lblNewLabel_1.setBounds(220, 43, 105, 117);
		contentPane.add(lblNewLabel_1);

		JButton btnInscrire = new JButton("Inscrire");
		btnInscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnInscrire) {
					dispose();
					Inscrire frame = new Inscrire();
					frame.setTitle("Inscription d'un membre");
					Image ic = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
					frame.setIconImage(ic);
					frame.setResizable(false);
					frame.setVisible(true);
				}
			}
		});
		btnInscrire.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInscrire.setForeground(new Color(0, 0, 128));
		btnInscrire.setBackground(new Color(128, 128, 128));
		btnInscrire.setBounds(23, 178, 111, 35);
		btnInscrire.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnInscrire);

		JButton btnLister = new JButton("Lister");
		btnLister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLister) {
					dispose();
					ListeMembre frame = new ListeMembre();
					frame.setTitle("Liste Membre");
					Image ic = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
					frame.setIconImage(ic);
					// frame.setResizable(false);
					frame.setVisible(true);
				}
			}
		});
		btnLister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLister.setForeground(new Color(0, 0, 128));
		btnLister.setBackground(new Color(128, 128, 128));
		btnLister.setBounds(230, 178, 105, 35);
		btnLister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnLister);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1
				.setIcon(new ImageIcon(Accueil.class.getResource("/sn/uasz/tp2/icone/icons8_new_job_100px.png")));
		lblNewLabel_1_1.setBounds(374, 43, 105, 117);
		contentPane.add(lblNewLabel_1_1);

		JButton btn_profession = new JButton("Profession");
		btn_profession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn_profession) {
					String profession = JOptionPane.showInputDialog(btn_profession, "Ajouter profession",
							"Nouvelle Profession", JOptionPane.PLAIN_MESSAGE);
					if (profession.equals("")) {
						JOptionPane.showMessageDialog(btn_profession, "Veuillez saisir une nouvelle profession!!");
					} else {
						try {
							int i = membre.profession(profession);
							if (i > 0) {
								JOptionPane.showMessageDialog(btn_profession, "Profession ajouté avec success!!");
							} else {
								JOptionPane.showMessageDialog(btn_profession, "Profession existe déja!!");
							}
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(btn_profession, "Profession existe déja!!");
						}
					}
				}
			}
		});
		btn_profession.setForeground(new Color(0, 0, 128));
		btn_profession.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_profession.setBackground(new Color(128, 128, 128));
		btn_profession.setBounds(374, 178, 122, 35);
		btn_profession.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btn_profession);

		JButton btn_deconnexion = new JButton("");
		btn_deconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sup = JOptionPane.showConfirmDialog(btn_deconnexion, "Voulez-vous vraiment vous deconnectez?",
						"Confirmation de la deconnexion", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (sup == 0) {
					dispose();
					Main frame = new Main();
					frame.setTitle("Connexion");
					Image ic = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
					frame.setIconImage(ic);
					frame.setResizable(false);
					frame.setVisible(true);

				}
			}
		});
		btn_deconnexion
				.setIcon(new ImageIcon(Accueil.class.getResource("/sn/uasz/tp2/icone/icons8_connected_50px.png")));
		btn_deconnexion.setForeground(new Color(0, 0, 128));
		btn_deconnexion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_deconnexion.setBackground(Color.GRAY);
		btn_deconnexion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_deconnexion.setBounds(10, 303, 56, 35);
		contentPane.add(btn_deconnexion);
	}
}
