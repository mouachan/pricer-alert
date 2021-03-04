package org.crypto.model;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Threshold {
    @ProtoField(number = 1, defaultValue =  "0")
    String id;

    @ProtoField(number = 2, defaultValue = "0")
     double value;

    @ProtoField(number = 3, defaultValue = "paire")
    String paire;


    public Threshold(){}
    
    @ProtoFactory
    public Threshold(String id, double value, String paire){
        this.id = id;
        this.value = value;
        this.paire = paire;
    }


    /**
     * @return double return the value
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getPaire() {
		return paire;
	}

	public void setPaire(String paire) {
		this.paire = paire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paire == null) ? 0 : paire.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Threshold other = (Threshold) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paire == null) {
			if (other.paire != null)
				return false;
		} else if (!paire.equals(other.paire))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Threshold [id=" + id + ", paire=" + paire + ", value=" + value + "]";
	}
}