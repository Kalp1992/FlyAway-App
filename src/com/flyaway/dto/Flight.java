package com.flyaway.dto;


public class Flight {
	 private int flightNo;
	    private String source;
	    private String destination;
	    private String startdate22;
	    private String endDate;
	    private String departureTime;
	    private String arrivalTime;
	    private int noPerson;
	    private double ticketPrice;
		public Flight() {
			
		}
		
		public Flight(String source, String destination, String startdate22) {
			
			this.source = source;
			this.destination = destination;
			this.startdate22 = startdate22;
		}

		

		public Flight(int flightNo, String source, String destination, String startdate22, String endDate,
				String departureTime, String arrivalTime, int noPerson, double ticketPrice) {
			super();
			this.flightNo = flightNo;
			this.source = source;
			this.destination = destination;
			this.startdate22 = startdate22;
			this.endDate = endDate;
			this.departureTime = departureTime;
			this.arrivalTime = arrivalTime;
			this.noPerson = noPerson;
			this.ticketPrice = ticketPrice;
		}
		public int getFlightNo() {
			return flightNo;
		}
		public void setFlightNo(int flightNo) {
			this.flightNo = flightNo;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		public String getStartdate22() {
			return startdate22;
		}
		public void setStartdate22(String startdate22) {
			this.startdate22 = startdate22;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getDepartureTime() {
			return departureTime;
		}
		public void setDepartureTime(String departureTime) {
			this.departureTime = departureTime;
		}
		public String getArrivalTime() {
			return arrivalTime;
		}
		public void setArrivalTime(String arrivalTime) {
			this.arrivalTime = arrivalTime;
		}
		public int getNoPerson() {
			return noPerson;
		}
		public void setNoPerson(int noPerson) {
			this.noPerson = noPerson;
		}
		public double getTicketPrice() {
			return ticketPrice;
		}
		public void setTicketPrice(double ticketPrice) {
			this.ticketPrice = ticketPrice;
		}
		@Override
		public String toString() {
			return "Flight [flightNo=" + flightNo + ", source=" + source + ", destination=" + destination + ", startdate22="
					+ startdate22 + ", endDate=" + endDate + ", departureTime=" + departureTime + ", arrivalTime="
					+ arrivalTime + ", noPerson=" + noPerson + ", ticketPrice=" + ticketPrice + "]";
		}
		
}
