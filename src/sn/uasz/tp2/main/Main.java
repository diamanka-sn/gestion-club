package sn.uasz.tp2.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sn.uasz.tp2.connexion.DbConnexion;
import sn.uasz.tp2.form.Accueil;
import sn.uasz.tp2.form.Recuperation;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nomuser;
	private JPasswordField txt_passeuser;

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setTitle("Connexion");
					Image ic = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
					frame.setIconImage(ic);
					frame.setResizable(false);
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
	private void SeConnecter() {
		String Login = txt_nomuser.getText();
		String Passe = txt_passeuser.getText();
		if (Login.equals("")) {
			String msg = "Veuillez saisir le nom d'utilisateur!!";
			JOptionPane.showMessageDialog(this, msg, "Désoler !!", JOptionPane.WARNING_MESSAGE);
		} else if (Passe.equals("")) {
			String msg = "Veuillez saisir le mot de passe!!";
			JOptionPane.showMessageDialog(this, msg, "Désoler !!", JOptionPane.WARNING_MESSAGE);
		} else {
			String sql = "SELECT * FROM admin Where login = ? and passe = ?";
			try {
				con = DbConnexion.conDb();
				ps = con.prepareStatement(sql);
				ps.setString(1, Login);
				ps.setString(2, Passe);

				rs = ps.executeQuery();

				if (!rs.next()) {
					String msg = "Nom d'utilisateur ou mot de passe incorrecte !!!!";
					JOptionPane.showMessageDialog(this, msg, "Désoler !!", JOptionPane.WARNING_MESSAGE);
				} else {
					dispose();
					Accueil frame = new Accueil();
					frame.setTitle("Accueil");
					Image ic = Toolkit.getDefaultToolkit()
							.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
					frame.setIconImage(ic);
					frame.setResizable(false);
					frame.setVisible(true);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 262, 385);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel parkingLabel = new JLabel("");
		parkingLabel.setIcon(new ImageIcon(Main.class.getResource("/sn/uasz/tp2/icone/icons8_gacha_club_100px_1.png")));
		parkingLabel.setBounds(77, 25, 100, 112);
		panel.add(parkingLabel);

		JLabel lblNewLabel = new JLabel("BIENVENUE");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(54, 148, 165, 37);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(260, 0, 305, 385);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txt_nomuser = new JTextField();
		txt_nomuser.setText("Nom d'utilisateur");
		txt_nomuser.setForeground(new Color(128, 128, 128));
		txt_nomuser.setBackground(new Color(0, 0, 128));
		txt_nomuser.setBounds(70, 143, 187, 31);
		panel_1.add(txt_nomuser);
		txt_nomuser.setColumns(10);

		txt_passeuser = new JPasswordField();
		txt_passeuser.setText("Mot de passe");
		txt_passeuser.setForeground(new Color(128, 128, 128));
		txt_passeuser.setBackground(new Color(0, 0, 128));
		txt_passeuser.setBounds(70, 185, 187, 31);
		panel_1.add(txt_passeuser);

		JButton btn_connexion = new JButton("Se connecter");
		btn_connexion.setForeground(new Color(0, 0, 128));
		btn_connexion.setBackground(new Color(192, 192, 192));
		btn_connexion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_connexion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeConnecter();
			}
		});
		btn_connexion.setBounds(100, 244, 125, 31);
		panel_1.add(btn_connexion);

		JLabel lb_passeOublier = new JLabel("Mot de passe oublié ?");
		lb_passeOublier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// String profession = JOptionPane.showInputDialog(null, "Saississez votre
				// code",
				// "Recuperation mot de passe", JOptionPane.PLAIN_MESSAGE);
				dispose();
				Recuperation frame = new Recuperation();
				frame.setTitle("Recuperation code de modification");
				Image ic = Toolkit.getDefaultToolkit()
						.getImage(getClass().getResource("/sn/uasz/tp2/icone/favicon.png"));
				frame.setIconImage(ic);
				frame.setResizable(false);
				frame.setVisible(true);
			}
		});
		lb_passeOublier.setForeground(new Color(128, 128, 128));
		lb_passeOublier.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lb_passeOublier.setBounds(90, 334, 141, 14);
		lb_passeOublier.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel_1.add(lb_passeOublier);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(
				"C:\\Users\\Mdiamanka\\eclipse-workspace\\parkingClub\\src\\sn\\uasz\\tp2\\icone\\icons8_access_100px.png"));
		lblNewLabel_2.setBounds(100, 11, 111, 121);
		panel_1.add(lblNewLabel_2);
	}
}
