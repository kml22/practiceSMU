import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

//import 하는법  : ctrl  + shift + o

/*1411854 컴퓨터과학부 이경민*/

public class StudentInfoSystem  extends JFrame implements ActionListener, ListSelectionListener
{
	//------------------------------------------------------------------------------
	// 사용자 UI를 위해 필요한 변수들 선언 
	//------------------------------------------------------------------------------
	private Vector<Student> collection; // a collection of Student objects	
	private JFileChooser fc;
	
	// 	GUI 컴포넌트를 위한 멤버 변수들 선언
	
	//panel
	private JPanel topPanel;
	private JPanel addPanel,allPanel,lblPanel,textPanel,radioP,selectP,photoP,profilePanel,btnPanel;
	private JPanel stdPanel,searchPanel,listPanel;
	private JPanel infoPanel,phPanel,viewPanel;
		
	//menubar
	private JMenuBar menuBar;
	private JMenu menu1,menu2;
	private JMenuItem menuItem1,menuItem2,menuItem3;
	
	//라디오버튼 그룹
	private ButtonGroup group;
	private ButtonModel b1;
	
	//label
	private JLabel title,lblName,lblId,lblMa,lblGr,lblBi,lblPhn,lblAdd,lblPhoto,lblPro;
	private JLabel iconLbl;
		
	private JTextField txtName,txtId,txtMa,txtPhn,txtAdd,txtPhoto,searchCon;
	private JButton btnPhoto,btnAdd,btnDel,searchBut,editBut,delBut;
	private JComboBox comboSearch,year,month,date; 
	
	//학년라디오버튼
	private JRadioButton radio1,radio2,radio3,radio4; 
	
	private DefaultListModel listModel;
	private JList stdList;
	private JTextArea prof,taView;
	private JScrollPane profScroll,spList,spView;
	private ImageIcon st;
	
	int set=0; //"추가"버튼과 "수정완료"버튼 이벤트를 나누기 위함
	
	//------------------------------------------------------------------------------
	//생성자 - GUI를 구성한다.
	//------------------------------------------------------------------------------
	public StudentInfoSystem(){
		
		// collection 을 생성하다.			
		collection = new Vector<Student>(); //student 타입의 vector
		fc = new JFileChooser();
		
		//------------------------------------------------------------------------------
		// 사용자 화면에 컴포넌트들 구성하여 배치
		// 각 컴포넌트 구성시 필요한 리스너 등록
		//------------------------------------------------------------------------------		

		//메뉴바 만들기
		menuBar = new JMenuBar();
		menu1 = new JMenu("파일");
		menu2 = new JMenu("도움말");
		menuItem1 = new JMenuItem("불러오기");
		menuItem2 = new JMenuItem("저장");
		menuItem3 = new JMenuItem("시스템 정보");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		menu2.add(menuItem3);
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
	
		// 상단 타이틀
		topPanel = new JPanel();
		title = new JLabel("학생 정보 시스템");
		title.setFont(new Font("돋움", Font.BOLD, 36));
		topPanel.add(title);

		/////////////////////////////////////////////////////////////////////////////////
		// 학생정보 등록 panel  작성 <블럭>
		/////////////////////////////////////////////////////////////////////////////////
		
		//왼쪽 전체 패널
		addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(300,550));
		addPanel.setBorder(BorderFactory.createTitledBorder("학생 정보 입력"));
		
		//라벨 패널(왼쪽)과 텍스트 패널(오른쪽)을 포함하는 패널
		allPanel = new JPanel();
		allPanel.setLayout(new BorderLayout());
		
		//라벨 panel 
		lblPanel = new JPanel();
		lblPanel.setPreferredSize(new Dimension(48,260));
		lblPanel.setLayout(new GridLayout(0,1));
		
		lblName = new JLabel("이름");
		lblId = new JLabel("학번");
		lblMa = new JLabel("학과");
		lblGr = new JLabel("학년");
		lblBi = new JLabel("생년월일");
		lblBi.setFont(new Font("돋움", Font.BOLD, 11));
		lblPhn = new JLabel("휴대폰");
		lblAdd = new JLabel("주소");
		lblPhoto = new JLabel("사진");
		
		lblPanel.add(lblName);
		lblPanel.add(lblId);
		lblPanel.add(lblMa);
		lblPanel.add(lblGr);
		lblPanel.add(lblBi);
		lblPanel.add(lblPhn);
		lblPanel.add(lblAdd);
		lblPanel.add(lblPhoto);
		
		allPanel.add(lblPanel,BorderLayout.WEST);
		
				
		// 텍스트 panel
		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(232,260));
		textPanel.setLayout(new GridLayout(0,1));
		
		txtName = new JTextField(20);
		txtId = new JTextField(20);
		txtMa = new JTextField(20);
		txtPhn = new JTextField(20);
		txtAdd = new JTextField(20);
		
		// 텍스트 패널 안, 라디오버튼패널과 생년월일패널
		radioP = new JPanel();
		selectP = new JPanel();
			
		
		//라디오버튼 그룹
		group = new ButtonGroup();
		radio1 = new JRadioButton("1학년",true);
		radio2 = new JRadioButton("2학년");
		radio3 = new JRadioButton("3학년");
		radio4 = new JRadioButton("4학년");
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
				
		//한줄에 넣기위해 폰트조절
		radio1.setFont(new Font("바탕", Font.PLAIN, 11));
		radio2.setFont(new Font("바탕", Font.PLAIN, 11));
		radio3.setFont(new Font("바탕", Font.PLAIN, 11));
		radio4.setFont(new Font("바탕", Font.PLAIN, 11));
		
		//라디오버튼패널에 라디오 버튼 넣어줌
		radioP.add(radio1);
		radioP.add(radio2);
		radioP.add(radio3);
		radioP.add(radio4);
		
		//생년월일 콤보박스
		year = new JComboBox();
		for(int i=1980;i<2016;i++){
			year.addItem(String.valueOf(i));
			
		}
				
		month = new JComboBox();
		for(int i=1;i<13;i++){
			month.addItem(String.valueOf(i));
			
		}
		
		date = new JComboBox();
		for(int i=1;i<32;i++){
			date.addItem(String.valueOf(i));
			
		}
		
		//생년월일패널에 콤보박스추가
		selectP.add(year);
		selectP.add(month);
		selectP.add(date);
				
		//사진경로텍스트필드와 불러오기버튼이 있는 사진패널
		photoP = new JPanel();
		photoP.setLayout(new BoxLayout(photoP,BoxLayout.X_AXIS));
		txtPhoto = new JTextField(10);
		btnPhoto = new JButton("불러오기");	
		btnPhoto.addActionListener(this);
		
		//사진패널에 붙여주기
		photoP.add(txtPhoto);
		photoP.add(btnPhoto);
				
		//textPanel에 모두 붙여주기
		textPanel.add(txtName);
		textPanel.add(txtId);
		textPanel.add(txtMa);
		textPanel.add(radioP);
		textPanel.add(selectP);
		textPanel.add(txtPhn);
		textPanel.add(txtAdd);
		textPanel.add(photoP);
		
		//텍스트패널을 allPanel에 붙여주기
		allPanel.add(textPanel,BorderLayout.CENTER);
				
		// 프로필 panel
		profilePanel = new JPanel();
		profilePanel.setPreferredSize(new Dimension(280,180));
		profilePanel.setLayout(new BorderLayout());
		
		lblPro = new JLabel("프로필/자기소개");
		profilePanel.add(lblPro,BorderLayout.NORTH);
		
		prof = new JTextArea();
		profScroll = new JScrollPane(prof);
		profilePanel.add(profScroll, BorderLayout.CENTER);
				
		// 버튼 panel - 추가, 취소
		btnPanel = new JPanel();
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btnDel = new JButton("취소");
		btnDel.addActionListener(this);
		
		btnPanel.add(btnAdd);
		btnPanel.add(btnDel);
		
		// addPanel에 컴포넌트들 등록
		addPanel.add(allPanel);
		addPanel.add(profilePanel);
		addPanel.add(btnPanel);
				
		
		/////////////////////////////////////////////////////////////////////////////////
		// 가운데 student panel <블럭>
		/////////////////////////////////////////////////////////////////////////////////
		stdPanel = new JPanel();
		stdPanel.setPreferredSize(new Dimension(280,550));
		
		// 수정 삭제 버튼
		editBut = new JButton("수정");
		editBut.addActionListener(this);
		delBut = new JButton("삭제");
		delBut.addActionListener(this);
		
		// 검색 panel
		searchPanel = new JPanel();
		searchPanel.setPreferredSize(new Dimension(280,70));
		searchPanel.setBorder(BorderFactory.createTitledBorder("학생 정보 검색"));
		
		String[] search = {"이름","학번"};
		comboSearch = new JComboBox(search);
		searchPanel.add(comboSearch);
		
		searchCon = new JTextField(12);
		searchPanel.add(searchCon);
		
		searchBut = new JButton("검색");
		searchPanel.add(searchBut);
		searchBut.addActionListener(this);
				
		// 목록 panel
		listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(280,400));
		listPanel.setBorder(BorderFactory.createTitledBorder("학생 목록"));

		// 리스트
		listModel = new DefaultListModel();
		stdList = new JList(listModel);
		stdList.setPreferredSize(new Dimension(300,400));
		stdList.addListSelectionListener(this);
		spList = new JScrollPane(stdList);
		spList.setPreferredSize(new Dimension(260,370));
		listPanel.add(spList); //스크롤팬 부착
	
		//stdPanel에 컴포넌트들 등록
		stdPanel.add(searchPanel);
		stdPanel.add(listPanel);
		stdPanel.add(editBut);
		stdPanel.add(delBut);
		
		/////////////////////////////////////////////////////////////////////////////////
		// 상세 정보 panel <블럭>
		/////////////////////////////////////////////////////////////////////////////////
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(300,550));
		infoPanel.setBorder(BorderFactory.createTitledBorder("학생 상세 정보"));
		
		// photo Panel 사진보여주는 패널
		phPanel = new JPanel();
		phPanel.setPreferredSize(new Dimension(280,150));
		iconLbl = new JLabel();
		phPanel.add(iconLbl);

		// 상세정보 textarea 담는 panel
		viewPanel = new JPanel();
		taView = new JTextArea(17,25);
		
		spView = new JScrollPane(taView);//스크롤팬 부착
		
		viewPanel.add(spView);
		
		//infoPanel에 붙여주기
		infoPanel.add(phPanel);
		infoPanel.add(viewPanel);
		
		//	프레임에 panel 추가
		add(topPanel, BorderLayout.PAGE_START);
		add(addPanel, BorderLayout.LINE_START);
		add(stdPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.LINE_END);
		
		//프레임 속성
		setTitle("학생 정보 시스템");
		setSize(900, 630);
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}
	
	
	//------------------------------------------------------------------------------
	// 리스너 인터페이스를 위한 각 메소드 구현
	// 어떤 이벤트가 발생했는지에 따라 각각의 기능을 수행한다.
	//------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		//선택된 리스트의 인덱스 가져오기
		int n = stdList.getSelectedIndex();
		
		//Student 객체 선언
		Student s;
				
		
		// 메뉴아이템 3개, 버튼 5개
		
				
		//사진불러오기
		if(source == btnPhoto){
			int rv = fc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION){
				File f = fc.getSelectedFile();
				txtPhoto.setText(f.getAbsolutePath());
				txtPhoto.setEditable(false);//사용자 직접 입력 불가
			}
		}
		
		//"추가"버튼 누를때와 "수정완료"버튼 누를때
		else if(source == btnAdd){
			//사용자로부터 입력받은 정보를 대상으로 student 객체를 생성해서 collection에 추가
			//리스트에 이름을 추가
			
			if(set==0){ //"추가"버튼 누를때
				
				int gn = grade();
				s = new Student(txtName.getText(),txtId.getText(),txtMa.getText(),gn,year.getSelectedItem().toString(),month.getSelectedItem().toString(),date.getSelectedItem().toString(),txtPhn.getText(),txtAdd.getText(),txtPhoto.getText(),prof.getText());
				collection.addElement(s);
				listModel.addElement(txtName.getText());
				reset();
			}
			else if(set==1){ //"수정완료"버튼 누를때
				
				int gn=grade();// 학년 읽어오기
				
				s = collection.get(n);
				s.edit(txtMa.getText(),gn,year.getSelectedItem().toString(), month.getSelectedItem().toString(), date.getSelectedItem().toString(), txtPhn.getText(), txtAdd.getText(), txtPhoto.getText(), prof.getText());
				reset();
				taView.setText("");
				set = 0; // 다시 초기화
				btnAdd.setText("추가");
									
			}
			
			
		}
		else if(source == btnDel){// 취소 버튼 누를때
			reset();
		}
		else if(source == editBut){//수정 버튼 누를때
			
			s = collection.get(n);
			
			// <<<<<왼쪽에 원래학생정보 뜨게하기>>>>>
			//이름변경불가
			txtName.setText(s.getName());
			txtName.setEditable(false);
			//학번변경불가
			txtId.setText(s.getStuno());
			txtId.setEditable(false);
			//전공
			txtMa.setText(s.getMajor());
			//학년
			group.setSelected(b1, true);
			//연락처
			txtPhn.setText(s.getPhone());
			//주소
			txtAdd.setText(s.getAddress());
			//사진경로
			txtPhoto.setText(s.getPhoto());
			//프로필
			prof.setText(s.getProfile());
			//생년월일
			year.setSelectedItem(s.getYear());
			month.setSelectedItem(s.getMonth());
			date.setSelectedItem(s.getDate());
			
			btnAdd.setText("수정완료");
			set = 1; // "수정완료" 버튼으로 동작하기
			
					
		}
		else if(source == searchBut){//검색버튼 누를때
			
			if(comboSearch.getSelectedItem().toString().compareTo("이름")==0){//콤보박스 "이름"선택시
				
				String find = searchCon.getText();
				
				for(int i=0;i<collection.size();i++){
					s = collection.get(i);
					if(find.compareTo(s.getName())==0){//이름 비교
						stdList.setSelectedIndex(i);
					}
				}
				
			}
			else if(comboSearch.getSelectedItem().toString().compareTo("학번")==0){//콤보박스 "학번"선택시
				
				String find = searchCon.getText();
				
				for(int i=0;i<collection.size();i++){
					s = collection.get(i);
					if(find.compareTo(s.getStuno())==0){//학번 비교
						stdList.setSelectedIndex(i);
					}
				}
				
			}
			searchCon.setText(""); //서치 콘텐츠 초기화
			
		}
		else if(source == delBut){ //리스트 삭제시
			s = collection.elementAt(n);
			collection.remove(n);
			listModel.removeElementAt(n);
			//오른쪽 학생정보 패널 초기화
			taView.setText("");
			iconLbl.setIcon(null);
				
		}
		else if(source == menuItem1){//불러오기 메뉴
			
			int op = fc.showOpenDialog(this);
			if(op == JFileChooser.APPROVE_OPTION){
				try{
					reset(); //왼쪽패널 초기화
					taView.setText("");	//오른쪽패널 초기화
					iconLbl.setIcon(null);//오른쪽사진 초기화
					listModel.removeAllElements(); //기존 리스트를 모두 제거
					
					File f = fc.getSelectedFile();
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f.getAbsolutePath()));
					
					collection = (Vector)ois.readObject();
					
					Student k = collection.get(0);
					listModel.addElement(k.getName());
					
					for(int i=1;i<collection.size();i++){
						k = collection.get(i);
						listModel.addElement(k.getName());
					}
					ois.close();
				
				}
				catch(IOException ie){}
				catch(ClassNotFoundException ce){}
			}
		}
		else if(source == menuItem2){ //저장 메뉴
			int sv = fc.showSaveDialog(this);
			File file = fc.getSelectedFile();
			if(sv == JFileChooser.APPROVE_OPTION){
				try{
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(collection);
					oos.close();
					
				}
				catch(IOException ie){}
			}
		}
		else if(source == menuItem3){ //시스템 정보 메뉴
			JOptionPane.showMessageDialog(this, "학생정보시스템 v 1.0입니다.");
		}
	}
	
	//입력 폼을 초기화
	void reset(){
		txtName.setText("");
		txtId.setText("");
		txtMa.setText("");
		txtPhn.setText("");
		group.clearSelection(); //라디오버튼 초기화
		txtAdd.setText("");
		prof.setText("");
		txtPhoto.setText("");
		//생년월일 초기화
		year.setSelectedItem("1980");
		month.setSelectedItem("1");
		date.setSelectedItem("1");
		
		txtName.setEditable(true);
		txtId.setEditable(true);
		txtPhoto.setEditable(true);
		btnAdd.setText("추가");
		
	}
	
	int grade(){ //학년 읽어오는 메소드
		int g=1; //디폴트 1학년
		if(radio1.isSelected()){			
			g = 1;
			b1 = group.getSelection();
		}
		else if(radio2.isSelected()){
			g = 2;
			b1 = group.getSelection();
		}
		else if(radio3.isSelected()){
			g = 3;
			b1 = group.getSelection();
		}
		else if(radio4.isSelected()){
			g = 4;
			b1 = group.getSelection();
		}
		return g;
		
	}

	//------------------------------------------------------------------------------
	//선택된 인덱스에 해당되는 영화 정보를 textArea 에서 출력한다.
	//------------------------------------------------------------------------------
	public void valueChanged(ListSelectionEvent event){
		int n = stdList.getSelectedIndex();
				
		if (n != -1){
			
			Student x = collection.get(n);
			//사진, 텍스트영역 내용 보여줘야됨
			view(x);
			
		}
	}	
	void view(Student s){
		//s의 정보 중에 사진값을 얻어와서 이미지 아이콘생성
		st = new ImageIcon(s.getPhoto());
		//라벨에 설정 
		iconLbl.setIcon(st);
		//iconLbl.setText(null);
		//텍스트 영역에 s의 정보 출력
		taView.setText(s.toString());
	}
	/*
	void nonono(Vector<Student> collection){
		collection.elementAt(2);
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentInfoSystem();
	}
}
