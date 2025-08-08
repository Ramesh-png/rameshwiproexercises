	package com.example.paymentservice;



	public class Payment {
	    private int orderId;
	    private boolean paymentStatus;

	    public int getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }

	    public boolean isPaymentStatus() {
	        return paymentStatus;
	    }

	    public void setPaymentStatus(boolean paymentStatus) {
	        this.paymentStatus = paymentStatus;
	    }
	}
