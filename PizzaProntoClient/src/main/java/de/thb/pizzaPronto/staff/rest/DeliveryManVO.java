package de.thb.pizzaPronto.staff.rest;



public class DeliveryManVO extends EmployeeVO  {
	
	private String driverLicence;
	
	public DeliveryManVO(String personnelNo, String lastName, String firstName) {
		super(personnelNo, lastName,firstName);
		vacationDays = 25;
		salary = 2100;
		driverLicence = "XYZ123";
	}
	
	
	public DeliveryManVO() {
		this(null, null, null);
		
	}

		
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\nDelivery Man:\n" );
		
		sb.append(super.toString());
		sb.append("\n Driver licence: " + driverLicence);
		
		return sb.toString();
		
	}


	/**
	 * @return the driverLicence
	 */
	public String getDriverLicence() {
		return driverLicence;
	}


	/**
	 * @param driverLicence the driverLicence to set
	 */
	public void setDriverLicence(String driverLicence) {
		this.driverLicence = driverLicence;
	}
	

} 
