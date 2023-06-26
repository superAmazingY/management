package supreme.model;

public enum CollegeStructure {

	JISUANJI("计算机科学与工程学院",0),
	TUMU("土木工程学院",1),
	CHUANMEI("传媒学院",2);
	
	
	private String name;
	private int index;
	
	private CollegeStructure(String name,int index)
	{
		this.name=name;
		this.index=index;
	}
	
	public static final String[][] major= {
			{"物联网工程","软件工程","计算机科学与技术"},
			{"道路工程","桥梁工程","地下空间工程"},
			{"广告学","传播学","网络与新媒体"}
	};
	
	public static final String[][]  majorNum={
		{"5001","5002","5003"},
		{"6001","6002","6003"},
		{"7001","7002","7003"}
	};
		
	public static final String[] gradeStr= {"2021","2020","2019"};
	
	public static final String[] secondaryStr= {
			JISUANJI.getName(),
			TUMU.getName(),
			CHUANMEI.getName()
			
	};
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name)
	{

		this.name=name;
	}

}
