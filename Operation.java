package tp3;

import java.io.Serializable;

public class Operation implements Serializable {
	private final double operande1, operande2;
	private final char operateur ;
	private double resultat;
	private String erreur;
	private int compteurGlobal;
	public Operation(double operande1, char operateur, double operande2) {
		this.operande1 = operande1;
		this.operateur = operateur;
		this.operande2 = operande2;
	}
	public double getOperande1() { return operande1; }
    public double getOperande2() { return operande2; }
    public char getOperateur() { return operateur; }
    public double getResultat() { return resultat; }
    public void setResultat(double r) { this.resultat = r; }
    public String getErreur() { return erreur; }
    public void setErreur(String e) { this.erreur = e; }
    public int getCompteurGlobal() { return compteurGlobal; }
    public void setCompteurGlobal(int c) { this.compteurGlobal = c; }
    public String toString() { return operande1 + " " + operateur + " " + operande2; }
}
