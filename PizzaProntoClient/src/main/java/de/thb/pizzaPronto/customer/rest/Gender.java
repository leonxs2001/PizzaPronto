package de.thb.pizzaPronto.customer.rest;

public enum Gender {
	M("male"), F("female"), I("intersex"), U ("unknown");

	private final String textRepresentation;

	private Gender(String textRepresentation) {
		this.textRepresentation = textRepresentation;
	}

	public String toString() {
		return textRepresentation;
	}

}