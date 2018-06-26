package com.rserve.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFrame {

	private String title;

	private List<String> names;

	private List<Map<String, List<Double>>> numericValues;

	private List<Map<String, List<String>>> factorValues;

	public DataFrame() {

	}

	public DataFrame(String title, List<String> names, List<Map<String, List<Double>>> numericValues,
			List<Map<String, List<String>>> factorValues) {
		super();
		this.title = title;
		this.names = names;
		this.numericValues = numericValues;
		this.factorValues = factorValues;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<Map<String, List<Double>>> getNumericValues() {
		return numericValues;
	}

	public void setNumericValues(List<Map<String, List<Double>>> numericValues) {
		this.numericValues = numericValues;
	}

	public List<Map<String, List<String>>> getFactorValues() {
		return factorValues;
	}

	public void setFactorValues(List<Map<String, List<String>>> factorValues) {
		this.factorValues = factorValues;
	}

	@Override
	public String toString() {
		return "DataFrame [title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factorValues == null) ? 0 : factorValues.hashCode());
		result = prime * result + ((names == null) ? 0 : names.hashCode());
		result = prime * result + ((numericValues == null) ? 0 : numericValues.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		DataFrame other = (DataFrame) obj;
		if (factorValues == null) {
			if (other.factorValues != null)
				return false;
		} else if (!factorValues.equals(other.factorValues))
			return false;
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		if (numericValues == null) {
			if (other.numericValues != null)
				return false;
		} else if (!numericValues.equals(other.numericValues))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
