package sn.uasz.tp2.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValiderEmail {
	private Pattern modele;
	private Matcher matcher;

	private static final String EMAIL_MODELE = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String NOM_MODELE = "[A-Z][a-z]+(?:[ \t]*[A-Z]?[a-z]+)?[ \t]*[A-Z][a-z]";

	public boolean validerMail(final String mail) {
		modele = Pattern.compile(EMAIL_MODELE);
		matcher = modele.matcher(mail);

		return matcher.matches();
	}

	public boolean verifPrenom(final String prenom) {
		modele = Pattern.compile(NOM_MODELE);
		matcher = modele.matcher(prenom);

		return matcher.matches();
	}
}
