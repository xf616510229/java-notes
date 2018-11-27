package jaxb;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 2048464929567943555L;

	private String province;
	
	private String city;
	
	public Address() {
	}

	public Address(String province, String city) {
		super();
		this.province = province;
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Adress [province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}
