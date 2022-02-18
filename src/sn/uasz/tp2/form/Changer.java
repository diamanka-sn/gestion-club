package sn.uasz.tp2.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sn.uasz.tp2.main.Main;
import sn.uasz.tp2.model.Membre;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Changer extends JFrame {

	private JPanel contentPane;
	private JPasswordField txt_new;
	private JPasswordField txt_confirme;

	Membre membre = new Membre();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param id
	 */
	public Changer(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 233);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txt_new = new JPasswordField();
		txt_new.setText("mot de passe");
		txt_new.setForeground(Color.GRAY);
		txt_new.setBackground(new Color(0, 0, 128));
		txt_new.setBounds(206, 32, 187, 31);
		contentPane.add(txt_new);

		txt_confirme = new JPasswordField();
		txt_confirme.setText("mot de passe");
		txt_confirme.setForeground(Color.GRAY);
		txt_confirme.setBackground(new Color(0, 0, 128));
		txt_confirme.setBounds(206, 86, 187, 31);
		contentPane.add(txt_confirme);

		JButton btn_modifier = new JButton("Modifier");
		btn_modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btn_modifier) {
					String newPasse = txt_new.getText();
					String confirmePasse = txt_confirme.getText();
					if (newPasse.equals("mot de passe")) {
						JOptionPane.showMessageDialog(btn_modifier, "Veuillez saisir le nouveau mot de passe");
					} else if (newPasse.equals("") || confirmePasse.equals("")) {
						JOptionPane.showMessageDialog(btn_modifier, "Veuillez saisir le nouveau mot de passe");
					} else if (newPasse.equals(confirmePasse)) {
						try {
							membre.modifierPasse(id, newPasse);
							JOptionPane.showMessageDialog(btn_modifier, "Mot de passe modifier avec succes");
							dispose();
							Main frame = new Main();
							frame.setTitle("Connexion");
							Image ic = Toolkit.getDefaultToolkit()
									.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
							frame.setIconImage(ic);
							frame.setResizable(false);
							frame.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(btn_modifier, "Mots de passe non identique");
					}

				}
			}
		});
		btn_modifier.setForeground(new Color(0, 0, 128));
		btn_modifier.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_modifier.setBackground(Color.LIGHT_GRAY);
		btn_modifier.setBounds(289, 143, 104, 31);
		contentPane.add(btn_modifier);

		JLabel lblNewLabel = new JLabel("Nouveau mot de passe");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setBounds(10, 32, 139, 31);
		contentPane.add(lblNewLabel);

		JLabel lblConfirmerMotDe = new JLabel("Confirmer mot de passe");
		lblConfirmerMotDe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblConfirmerMotDe.setForeground(new Color(128, 128, 128));
		lblConfirmerMotDe.setBounds(10, 86, 168, 31);
		contentPane.add(lblConfirmerMotDe);
	}
}
