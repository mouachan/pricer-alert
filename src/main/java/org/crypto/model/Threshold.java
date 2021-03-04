package org.crypto.model;

import java.util.ArrayList;
import java.util.List;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Threshold {
    @ProtoField(number = 1, defaultValue =  "0")
    String id;

    @ProtoField(number = 2, collectionImplementation = ArrayList.class)
	List<Double> values;

    @ProtoField(number = 3, defaultValue = "paire")
    String paire;


    public Threshold(){}
    
    @ProtoFactory
    public Threshold(String id, List<Double> values, String paire){
        this.id = id;
        this.values = values;
        this.paire = paire;
    }


    /**
     * @return double return the value
     */
    public List<Double> getValues() {
        return values;
    }

    /**
     * @param value the value to set
     */
    public void setValues(List<Double> values) {
        this.values = values;
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
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Threshold [id=" + id + ", paire=" + paire + ", values=" + values + "]";
	}

	
}