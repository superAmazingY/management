package supreme.model;

public class StudentClass 
{
	private String id;
	private String grade;
	private String name;
	private String secondary;
	private String major;
	private String info;
	
	public StudentClass()
	{
		
	}
	
	public StudentClass(String id, String grade, String name, String secondary, String major, String info) 
	{
		super();
		this.id = id;
		this.grade = grade;
		this.name = name;
		this.secondary = secondary;
		this.major = major;
		this.info = info;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String toString()
	{
		return name;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof StudentClass)
		{
			StudentClass sc = (StudentClass)obj;
			if(sc.getName().equals(name)&&sc.getId().equals(id))
				return true;
		}
		return false;
	}
}
