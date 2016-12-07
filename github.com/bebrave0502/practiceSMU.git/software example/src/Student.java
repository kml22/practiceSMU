import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{
	private String stuno;	//학번
	private String name;	//이름
	private String major;	//전공, 학과
	private int grade;		//학년
	// 생년월일
	private String year;
	private String month;
	private String date;
	private String phone;	//연락처
	private String address;	//주소
	private String photo;	//사진 (경로 저장)	
	private String profile;	//프로필(자기소개)
	private static int studentCount=0; 			// number of Students
	
	public Student(String name, String stuno, String major, int grade,String year,String month,String date,String phone, String address,
			String photo, String profile) {
		this.stuno = stuno;
		this.name = name;
		this.major = major;
		this.grade = grade;
		this.year = year;
		this.month = month;
		this.date = date;
		this.phone = phone;
		this.address = address;
		this.photo = photo;
		this.profile = profile;
		studentCount++;
	}
	public void edit(String major, int grade,String year,String month,String date, String phone, String address,
			String photo, String profile) {
		setStuno(stuno);
		setName(name);
		setMajor(major);
		setGrade(grade);
		setYear(year);
		setMonth(month);
		setDate(date);
		setPhone(phone);
		setAddress(address);
		setPhoto(photo);
		setProfile(profile);
	}	
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getYear(){
		return year;
	}

	public void setYear(String year){
		this.year = year;
	}
	public String getMonth(){
		return month;
	}
	public void setMonth(String month){
		this.month = month;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public static int getStudentCount() {
		return studentCount;
	}	
	//------------------------------------------------------------------------------
	//Student 정보를 반환한다.
	//------------------------------------------------------------------------------
	public String toString()
	{
		String description;		
		description="-이름: "+name+"\n";
		description+="-학번: "+stuno+"\n";
		description+="-학과: "+major+"\n";
		description+="-학년: "+grade+"\n";
		//if(birth != null)
		description+="-생년월일: "+getYear()+"년 "+getMonth()+"월 "+getDate()+"일\n";
		description+="-연락처: "+phone+"\n";
		description+="-주소: "+address+"\n";
		description+="-사진: "+photo+"\n";
		description+="-프로필: \n";
		description+=profile+"\n";		
		return description;
	}
}
