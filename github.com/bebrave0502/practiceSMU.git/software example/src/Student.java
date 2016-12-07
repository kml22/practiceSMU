import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{
	private String stuno;	//�й�
	private String name;	//�̸�
	private String major;	//����, �а�
	private int grade;		//�г�
	// �������
	private String year;
	private String month;
	private String date;
	private String phone;	//����ó
	private String address;	//�ּ�
	private String photo;	//���� (��� ����)	
	private String profile;	//������(�ڱ�Ұ�)
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
	//Student ������ ��ȯ�Ѵ�.
	//------------------------------------------------------------------------------
	public String toString()
	{
		String description;		
		description="-�̸�: "+name+"\n";
		description+="-�й�: "+stuno+"\n";
		description+="-�а�: "+major+"\n";
		description+="-�г�: "+grade+"\n";
		//if(birth != null)
		description+="-�������: "+getYear()+"�� "+getMonth()+"�� "+getDate()+"��\n";
		description+="-����ó: "+phone+"\n";
		description+="-�ּ�: "+address+"\n";
		description+="-����: "+photo+"\n";
		description+="-������: \n";
		description+=profile+"\n";		
		return description;
	}
}
