package mma_simulator;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a fighter in a match.
 */
public class Fighter {
	private static int baseFighterId = 1000;
	public enum WeightClass {LightWeight, MiddleWeight, HeavyWeight}
	public enum Status {Available, InMatch}

	private final String name;
	private final String id;
	private WeightClass weightClass;
	private double strength;
	private double speed;
	private double skill;
	private int age;
	private double weight;
	private Status status;
	private Map<String, Record> records;

	private double balance;

	{
		status = Status.Available;
		balance = 0;
		records = new HashMap<String, Record>();
	}

	/**
	 * Creates a fighter with the specified attributes and ID.
	 *
	 * @param name     the name of the fighter
	 * @param strength the strength of the fighter (0.0 - 10.0 range)
	 * @param speed    the speed of the fighter (0.0 - 10.0 range)
	 * @param skill    the skill of the fighter (0.0 - 10.0 range)
	 * @param age      the age of the fighter (18 - 100 range)
	 * @param weight   the weight of the fighter (40.0 - 300.0 range)
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
	}

	/**
	 * Creates a fighter with the specified attributes and automatically generated ID.
	 *
	 * @param name     the name of the fighter
	 * @param strength the strength of the fighter (0.0 - 10.0 range)
	 * @param speed    the speed of the fighter (0.0 - 10.0 range)
	 * @param skill    the skill of the fighter (0.0 - 10.0 range)
	 * @param age      the age of the fighter (18 - 100 range)
	 * @param weight   the weight of the fighter (40.0 - 300.0 range)
	 */
	public Fighter(String name, double strength, double speed, double skill, int age, double weight) {
		this(name, strength, speed, skill, age, weight, baseFighterId++);
	}

	// Getter and setter methods

	/**
	 * Retrieves the name of the fighter.
	 *
	 * @return the name of the fighter
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retrieves the weight class of the fighter.
	 *
	 * @return the weight class of the fighter
	 */
	public WeightClass getWeightClass() {
		return this.weightClass;
	}

	/**
	 * Retrieves the strength of the fighter.
	 *
	 * @return the strength of the fighter
	 */
	public double getStrength() {
		return this.strength;
	}

	/**
	 * Sets the strength of the fighter.
	 *
	 * @param strength the strength of the fighter (0.0 - 10.0 range)
	 * @throws IllegalArgumentException if the strength value is outside the valid range
	 */
	public void setStrength(double strength) throws IllegalArgumentException {
		validateAttributeRange(strength, "strength", 0.0, 10.0);
		this.strength = strength;
	}

	/**
	 * Retrieves the speed of the fighter.
	 *
	 * @return the speed of the fighter
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Sets the speed of the fighter.
	 *
	 * @param speed the speed of the fighter (0.0 - 10.0 range)
	 * @throws IllegalArgumentException if the speed value is outside the valid range
	 */
	public void setSpeed(double speed) throws IllegalArgumentException {
		validateAttributeRange(speed, "speed", 0.0, 10.0);
		this.speed = speed;
	}

	/**
	 * Retrieves the skill of the fighter.
	 *
	 * @return the skill of the fighter
	 */
	public double getSkill() {
		return this.skill;
	}

	/**
	 * Sets the skill of the fighter.
	 *
	 * @param skill the skill of the fighter (0.0 - 10.0 range)
	 * @throws IllegalArgumentException if the skill value is outside the valid range
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
	 * Adds to the  age of the fighter.
	 */

	public void addAge(){
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
	 * @param weight the weight of the fighter (40.0 - 300.0 range)
	 * @throws IllegalArgumentException if the weight value is outside the valid range
	 */
	public void setWeight(double weight) throws IllegalArgumentException {
		validateAttributeRange(weight, "weight", 40.0, 300.0);
		this.weight = weight;
		setWeightClass();
	}

	/**
	 * Retrieves the ID of the fighter.
	 *
	 * @return the ID of the fighter
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the status of the fighter to "InMatch".
	 */
	public void inMatch() {
		this.status = Status.InMatch;
	}

	public void available(){this.status = Status.Available;}
	/**
	 * Retrieves the status of the fighter.
	 *
	 * @return the status of the fighter
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status of the fighter to "Available".
	 */
	public void lost() {
		this.status = Status.Available;
	}

	/**
	 * Adds a record to the fighter's list of records.
	 *
	 * @param record the record to be added
	 */
	public void addRecord(Record record) {
		this.records.put(record.match().getMatchId(), record);
	}

	/**
	 * Retrieves a record of the fighter.
	 *
	 * @param index of the record in the map
	 */
	public Record getRecord(int index) {
		return (Record) records.values().toArray()[index];
	}
	public Record getRecord(String matchId) {
		return records.get(matchId);
	}

	@Override
	public String toString() {
		return "Fighter{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				", weightClass=" + weightClass +
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

	// Private utility methods
	private void setAge(int age) throws IllegalArgumentException {
		validateAttributeRange(age);
		this.age = age;
	}
	private void setWeightClass() {
		if (this.weight < 70) {
			this.weightClass = WeightClass.LightWeight;
		} else if (this.weight < 84) {
			this.weightClass = WeightClass.MiddleWeight;
		} else {
			this.weightClass = WeightClass.HeavyWeight;
		}
	}
	private void validateAttributeRange(double value, String attributeName, double minValue, double maxValue) {
		if (value < minValue || value > maxValue) {
			throw new IllegalArgumentException("Invalid value for " + attributeName + ". Value should be in range: " + minValue + " - " + maxValue);
		}
	}

	private void validateAttributeRange(int value) {
		if (value < 18 || value > 100) {
			throw new IllegalArgumentException("Invalid value for " + "age" + ". Value should be in range: " + 18 + " - " + 100);
		}
	}
}
