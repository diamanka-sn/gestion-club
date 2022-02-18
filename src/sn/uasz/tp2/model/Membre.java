package sn.uasz.tp2.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sn.uasz.tp2.connexion.DbConnexion;

public class Membre {

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	public int id;
	public String prenom;
	public String nom;
	public String email;
	public String sexe;
	public String numero;
	public String date_adhesion;
	public String profession;
	public int p = 0;

	ArrayList<Membre> listeMembres = new ArrayList<Membre>();

	public Membre(int id, String prenom, String nom, String email, String sexe, String numero, String date_adhesion,
			String profession) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.sexe = sexe;
		this.numero = numero;
		this.date_adhesion = date_adhesion;
		this.profession = profession;
	}

	public Membre() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDate_adhesion() {
		return date_adhesion;
	}

	public void setDate_adhesion(String date_adhesion) {
		this.date_adhesion = date_adhesion;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public ArrayList<Membre> afficher() {
		String select = "SELECT * FROM membre";
		con = DbConnexion.conDb();

		try {
			ps = con.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {
				Membre membre = new Membre();
				membre.setId(rs.getInt("id"));
				membre.setNom(rs.getString("nom"));
				membre.setPrenom(rs.getString("prenom"));
				membre.setSexe(rs.getString("sexe"));
				membre.setEmail(rs.getString("email"));
				membre.setDate_adhesion(rs.getString("date_adhesion"));
				membre.setNumero(rs.getString("numero"));
				membre.setProfession(rs.getString("profession"));
				listeMembres.add(membre);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listeMembres;
	}

	public Membre supprimerdutabeau(int id) {
		Membre membre = listeMembres.get(id);
		listeMembres.remove(id);
		return membre;
	}

	public void supprimer(int id) throws SQLException {
		String supprimer = "DELETE from membre where id=? limit 1";
		con = DbConnexion.conDb();
		ps = con.prepareStatement(supprimer);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}

	public void modifier(int id, String prenom, String nom, String email, String sexe, String date_adhesion,
			String numero, String profession) throws SQLException {
		String modifier = "UPDATE membre set prenom=?,nom=?,email=?, sexe=?, date_adhesion=?, numero=?, profession=? where id=? limit 1";
		con = DbConnexion.conDb();
		ps = con.prepareStatement(modifier);
		ps.setString(1, prenom);
		ps.setString(2, nom);
		ps.setString(3, email);
		ps.setString(4, sexe);
		ps.setString(5, date_adhesion);
		ps.setString(6, numero);
		ps.setString(7, profession);

		ps.setInt(8, id);

		ps.execute();
		ps.close();
	}

	public int profession(String profession) throws SQLException {
		// String select = "SELECT * from profession where nom_profession=?";
		String insertion = "INSERT INTO profession(id,nom_profession) values(?,?)";
		con = DbConnexion.conDb();
		ps = con.prepareStatement(insertion);
		ps = con.prepareStatement(insertion);
		ps.setInt(1, 0);
		ps.setString(2, profession);

		int status = ps.executeUpdate();

		return status;
	}

	public int insertion(String prenom, String nom, String email, String sexe, String date_adhesion, String numero,
			String profession) throws SQLException {

		String insertion = "Insert into membre(id,prenom,nom,email,sexe,numero,date_adhesion,profession) values(?,?,?,?,?,?,?,?)";
		con = DbConnexion.conDb();
		ps = con.prepareStatement(insertion);

		ps = con.prepareStatement(insertion);
		ps.setInt(1, 0);
		ps.setString(2, prenom);
		ps.setString(3, nom);
		ps.setString(4, email);
		ps.setString(5, sexe);
		ps.setString(6, numero);
		ps.setString(7, date_adhesion);
		ps.setString(8, profession);

		int status = ps.executeUpdate();

		return status;

	}

	public int verifierMembre(String email) throws SQLException {
		String verif = "SELECT * from membre where email=?";

		con = DbConnexion.conDb();
		ps = con.prepareStatement(verif);
		ps.setString(1, profession);
		rs = ps.executeQuery();
		int status = -1;
		while (!rs.next()) {
			status = 1;
		}

		return status;
	}

	public int verifDate(java.util.Date dateJour, java.util.Date dateChoix) {
		int retour = 0;

		if (dateChoix.after(dateJour)) {
			retour = -1;
		}
		return retour;
	}

	public int verifCode(String code) throws SQLException {
		String verif = "SELECT * from admin where code=?";

		con = DbConnexion.conDb();
		ps = con.prepareStatement(verif);
		ps.setString(1, code);
		rs = ps.executeQuery();
		int status = -1;
		int id = 0;
		if (rs.next()) {
			status =  rs.getInt("id");;
		}
		return status;
	}
	
	public void modifierPasse(int id, String newPasse) throws SQLException {
		String modifier = "UPDATE admin set passe=? where id=? limit 1";
		con = DbConnexion.conDb();
		ps = con.prepareStatement(modifier);	
		ps.setString(1, newPasse);
		
		ps.setInt(2, id);
		
		ps.execute();
		ps.close();
	}
}
