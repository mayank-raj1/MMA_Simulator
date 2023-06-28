package mma_simulator;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a fighter in an MMA simulator.
 */
public class Fighter {
	private static int baseFighterId = 1000;

	/**
	 * Enum representing weight classes.
	 */
	public enum WeightClass {LightWeight, MiddleWeight, HeavyWeight}

	/**
	 * Enum representing fighter status.
	 */
	public enum Status {Available, InMatch}

	private final String name;
	private final String id;
	private double strength;
	private double speed;
	private double skill;
	private int age;
	private double weight;
	private Status status;
	private final Map<String, Record> records;
	private double balance;

	{
		status = Status.Available;
		balance = 0;
		records = new HashMap<String, Record>();
	}

	/**
	 * Creates a new Fighter object.
	 *
	 * @param name     the name of the fighter
	 * @param strength the strength attribute of the fighter
	 * @param speed    the speed attribute of the fighter
	 * @param skill    the skill attribute of the fighter
	 * @param age      the age of the fighter
	 * @param weight   the weight of the fighter
	 * @param id       the ID of the fighter
	 */
	public Fighter(String name, double strength, double speed, double skill, int age, double weight, int id) {
		this.name = name;
		this.setStrength(strength);
		this.setSpeed(speed);
		this.setSkill(skill);
		this.setAge(age);
		this.setWeight(weight);
		this.id = Integer.toString(id);
		baseFighterId = id + 1;
	}

	/**
	 * Creates a new Fighter object with an automatically generated ID.
	 *
	 * @param name     the name of the fighter
	 * @param strength the strength attribute of the fighter
	 * @param speed    the speed attribute of the fighter
	 * @param skill    the skill attribute of the fighter
	 * @param age      the age of the fighter
	 * @param weight   the weight of the fighter
	 */
	public Fighter(String name, double strength, double speed, double skill, int age, double weight) {
		this(name, strength, speed, skill, age, weight, baseFighterId);
	}

	/**
	 * Retrieves the name of the fighter.
	 *
	 * @return the name of the fighter
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * Retrieves the strength attribute of the fighter.
	 *
	 * @return the strength attribute of the fighter
	 */
	public double getStrength() {
		return this.strength;
	}

	/**
	 * Sets the strength attribute of the fighter.
	 *
	 * @param strength the strength attribute of the fighter
	 * @throws IllegalArgumentException if the strength value is not within the valid range
	 */
	public void setStrength(double strength) throws IllegalArgumentException {
		validateAttributeRange(strength, "strength", 0.0, 10.0);
		this.strength = strength;
	}

	/**
	 * Retrieves the speed attribute of the fighter.
	 *
	 * @return the speed attribute of the fighter
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed attribute of the fighter.
	 *
	 * @param speed the speed attribute of the fighter
	 * @throws IllegalArgumentException if the speed value is not within the valid range
	 */
	public void setSpeed(double speed) throws IllegalArgumentException {
		validateAttributeRange(speed, "speed", 0.0, 10.0);
		this.speed = speed;
	}

	/**
	 * Retrieves the skill attribute of the fighter.
	 *
	 * @return the skill attribute of the fighter
	 */
	public double getSkill() {
		return this.skill;
	}

	/**
	 * Sets the skill attribute of the fighter.
	 *
	 * @param skill the skill attribute of the fighter
	 * @throws IllegalArgumentException if the skill value is not within the valid range
	 */
	public void setSkill(double skill) throws IllegalArgumentException {
		validateAttributeRange(skill, "skill", 0.0, 10.0);
		this.skill = skill;
	}

	/**
	 * Retrieves the age of the fighter.
	 *
	 * @return the age of the fighter
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Increases the age of the fighter by 1.
	 */
	public void addAge() {
		this.age++;
	}

	/**
	 * Retrieves the weight of the fighter.
	 *
	 * @return the weight of the fighter
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight of the fighter.
	 *
	 * @param weight the weight of the fighter
	 * @throws IllegalArgumentException if the weight value is not within the valid range
	 */
	public void setWeight(double weight) throws IllegalArgumentException {
		validateAttributeRange(weight, "weight", 40.0, 300.0);
		this.weight = weight;
	}

	/**
	 * Retrieves the ID of the fighter.
	 *
	 * @return the ID of the fighter
	 */
	public String getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public void addMoney(double money) {
		this.balance += money;
	}

	/**
	 * Sets the status of the fighter to "InMatch".
	 */
	public void inMatch() {
		this.status = Status.InMatch;
	}

	/**
	 * Sets the status of the fighter to "Available".
	 */
	public void available() {
		this.status = Status.Available;
	}

	/**
	 * Retrieves the status of the fighter.
	 *
	 * @return the status of the fighter
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status of the fighter to "Available" after losing a match.
	 */
	public void lost() {
		this.status = Status.Available;
	}

	/**
	 * Adds a record to the fighter's records.
	 *
	 * @param record the record to be added
	 */
	public void addRecord(Record record) {
		this.records.put(record.match().getMatchId(), record);
	}

	/**
	 * Retrieves a record from the fighter's records by index.
	 *
	 * @param index the index of the record
	 * @return the record at the specified index
	 */
	public Record getRecord(int index) {
		return (Record) records.values().toArray()[index];
	}

	/**
	 * Retrieves a record from the fighter's records by match ID.
	 *
	 * @param matchId the ID of the match
	 * @return the record with the specified match ID
	 */
	public Record getRecord(String matchId) {
		return records.get(matchId);
	}

	@Override
	public String toString() {
		return "Fighter{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				", strength=" + strength +
				", speed=" + speed +
				", skill=" + skill +
				", age=" + age +
				", weight=" + weight +
				", status=" + status +
				", records=" + records +
				", balance=" + balance +
				'}';
	}

	private void setAge(int age) throws IllegalArgumentException {
		validateAttributeRange(age);
		this.age = age;
	}


	private void validateAttributeRange(double value, String attributeName, double minValue, double maxValue) {
		if (value < minValue || value > maxValue) {
			throw new IllegalArgumentException("Invalid value for " + attributeName + ". Value should be in range: " + minValue + " - " + maxValue);
		}
	}

	private void validateAttributeRange(int value) {
		if (value < 18 || value > 100) {
			throw new IllegalArgumentException("Invalid value for age. Value should be in range: 18 - 100");
		}
	}
}
