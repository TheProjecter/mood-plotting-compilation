package basic;

import java.util.Date;

public class MoodBean {
	
	Date date;
	int happiness, energy, anger, trust, balance;
	String description;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHappiness() {
		return happiness;
	}
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getAnger() {
		return anger;
	}
	public void setAnger(int anger) {
		this.anger = anger;
	}
	public int getTrust() {
		return trust;
	}
	public void setTrust(int trust) {
		this.trust = trust;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descr) {
		this.description = descr;
	}

}
