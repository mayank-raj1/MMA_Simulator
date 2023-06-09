import java.util.HashMap;
import java.util.List;

public class Fighter {
	private static int baseFighterId = 1000;
	public enum WeightClass{LightWeight, MiddleWeight, HeavyWeight}
	public enum Status{Available, InMatch}
	private String name;
	private String id;
	private WeightClass weightClass;
	private double strength;
	private double speed;
	private double skill;
	private int age;
	private double weight;
	private Status status;
	{status = Status.Available;}
	private HashMap<String, Record> records;
	public Fighter(String name, double strength, double speed, double skill, int age, double weight, int id){
		this.name = name;
		this.setStrength(strength);
		this.setSpeed(speed);
		this.setSkill(skill);
		this.setAge(age);
		this.setWeight(weight);
		this.id = Integer.toString(id);
	}

	public Fighter(String name, double strength, double speed, double skill, int age, double weight){
		this.name = name;
		this.setStrength(strength);
		this.setSpeed(speed);
		this.setSkill(skill);
		this.setAge(age);
		this.setWeight(weight);
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public WeightClass getWeightClass() {
		return this.weightClass;
	}

	public double getStrength() {
		return this.strength;
	}

	public void setStrength(double strength) throws IllegalArgumentException{
		if (strength>10.0||strength<0){
			throw new IllegalArgumentException("Give the strength in a 0-10.0 range!");
		}
		this.strength = strength;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) throws IllegalArgumentException{
		if (speed>10.0||speed<0){
			throw new IllegalArgumentException("Give the speed in a 0-10.0 range!");
		}
		this.speed = speed;
	}

	public double getSkill() {
		return this.skill;
	}
	public void setSkill(double skill) throws IllegalArgumentException {
		if (skill>10.0||skill<0){
			throw new IllegalArgumentException("Give the skill in a 0-10.0 range!");
		}
		this.skill = skill;
	}

	public int getAge() {
		return this.age;
	}
	public void setAge(int age) throws IllegalArgumentException {
		if (age<18||age>100){
			throw new IllegalArgumentException("Enter the age in valid range 18-100");
		}
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) throws IllegalArgumentException{
		if (weight<40.0||weight>300){
			throw new IllegalArgumentException("Enter an reasonable weight for the fighter");
		}
		this.weight = weight;
		setWeightClass();
	}
	private void setWeightClass(){
		if (this.weight<70){
			this.weightClass = WeightClass.LightWeight;
		} else if (this.weight<84) {
			this.weightClass = WeightClass.MiddleWeight;
		}
		else {
			this.weightClass = WeightClass.HeavyWeight;
		}
	}

	public String getId() {
		return id;
	}

	public void inMatch(){this.status = Status.InMatch;}

	public Status getStatus() {
		return status;
	}

	public void lost(){this.status = Status.Available;}
	public void addRecord(Record record){
		this.records.put(record.match().getMatchId(), record);
	}

}