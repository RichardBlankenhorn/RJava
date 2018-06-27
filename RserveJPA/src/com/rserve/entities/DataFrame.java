package com.rserve.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFrame {

	private String title;

	private List<String> names;

	private List<List<Object>> objValues;

	public DataFrame() {

	}

	public DataFrame(String title, List<String> names, List<List<Object>> objValues) {
		super();
		this.title = title;
		this.names = names;
		this.objValues = objValues;
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

	public List<List<Object>> getObjValues() {
		return objValues;
	}

	public void setObjValues(List<List<Object>> objValues) {
		this.objValues = objValues;
	}

	@Override
	public String toString() {
		return "DataFrame [title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((names == null) ? 0 : names.hashCode());
		result = prime * result + ((objValues == null) ? 0 : objValues.hashCode());
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
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		if (objValues == null) {
			if (other.objValues != null)
				return false;
		} else if (!objValues.equals(other.objValues))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
