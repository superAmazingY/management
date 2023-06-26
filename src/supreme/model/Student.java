package supreme.model;

public class Student
{
	private String id;
	private String grade;
	private String classId;
	private String className;
	private String name;
	private String sex;
	private String nation;
	private String major;
	private String secondary;
	
	public Student()
	{
		
	}
	
	public Student(String id, String grade, String classId, String className, String name, String sex, String nation,
			String major, String secondary) {
		super();
		this.id = id;
		this.grade = grade;
		this.classId = classId;
		this.className = className;
		this.name = name;
		this.sex = sex;
		this.nation = nation;
		this.major = major;
		this.secondary = secondary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

}
