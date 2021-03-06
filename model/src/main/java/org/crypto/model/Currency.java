package org.crypto.model;


public class Currency {

    private String paire;
    private double value;

    public Currency(){}

    public Currency(String paire, double value){
        this.paire = paire;
        this.value = value;
    }

    /**
     * @return String return the paire
     */
    public String getPaire() {
        return paire;
    }

    /**
     * @param paire the paire to set
     */
    public void setPaire(String paire) {
        this.paire = paire;
    }

    /**
     * @return String return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    @Override
	public String toString() {
		return "Currency [paire=" + paire + ", value=" + value + "]";
	}

}