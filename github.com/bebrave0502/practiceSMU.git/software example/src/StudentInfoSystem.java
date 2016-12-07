import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;

//import �ϴ¹�  : ctrl  + shift + o

/*1411854 ��ǻ�Ͱ��к� �̰��*/

public class StudentInfoSystem  extends JFrame implements ActionListener, ListSelectionListener
{
	//------------------------------------------------------------------------------
	// ����� UI�� ���� �ʿ��� ������ ���� 
	//------------------------------------------------------------------------------
	private Vector<Student> collection; // a collection of Student objects	
	private JFileChooser fc;
	
	// 	GUI ������Ʈ�� ���� ��� ������ ����
	
	//panel
	private JPanel topPanel;
	private JPanel addPanel,allPanel,lblPanel,textPanel,radioP,selectP,photoP,profilePanel,btnPanel;
	private JPanel stdPanel,searchPanel,listPanel;
	private JPanel infoPanel,phPanel,viewPanel;
		
	//menubar
	private JMenuBar menuBar;
	private JMenu menu1,menu2;
	private JMenuItem menuItem1,menuItem2,menuItem3;
	
	//������ư �׷�
	private ButtonGroup group;
	private ButtonModel b1;
	
	//label
	private JLabel title,lblName,lblId,lblMa,lblGr,lblBi,lblPhn,lblAdd,lblPhoto,lblPro;
	private JLabel iconLbl;
		
	private JTextField txtName,txtId,txtMa,txtPhn,txtAdd,txtPhoto,searchCon;
	private JButton btnPhoto,btnAdd,btnDel,searchBut,editBut,delBut;
	private JComboBox comboSearch,year,month,date; 
	
	//�г������ư
	private JRadioButton radio1,radio2,radio3,radio4; 
	
	private DefaultListModel listModel;
	private JList stdList;
	private JTextArea prof,taView;
	private JScrollPane profScroll,spList,spView;
	private ImageIcon st;
	
	int set=0; //"�߰�"��ư�� "�����Ϸ�"��ư �̺�Ʈ�� ������ ����
	
	//------------------------------------------------------------------------------
	//������ - GUI�� �����Ѵ�.
	//------------------------------------------------------------------------------
	public StudentInfoSystem(){
		
		// collection �� �����ϴ�.			
		collection = new Vector<Student>(); //student Ÿ���� vector
		fc = new JFileChooser();
		
		//------------------------------------------------------------------------------
		// ����� ȭ�鿡 ������Ʈ�� �����Ͽ� ��ġ
		// �� ������Ʈ ������ �ʿ��� ������ ���
		//------------------------------------------------------------------------------		

		//�޴��� �����
		menuBar = new JMenuBar();
		menu1 = new JMenu("����");
		menu2 = new JMenu("����");
		menuItem1 = new JMenuItem("�ҷ�����");
		menuItem2 = new JMenuItem("����");
		menuItem3 = new JMenuItem("�ý��� ����");
		menuBar.add(menu1);
		menuBar.add(menu2);
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		menu2.add(menuItem3);
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
	
		// ��� Ÿ��Ʋ
		topPanel = new JPanel();
		title = new JLabel("�л� ���� �ý���");
		title.setFont(new Font("����", Font.BOLD, 36));
		topPanel.add(title);

		/////////////////////////////////////////////////////////////////////////////////
		// �л����� ��� panel  �ۼ� <��>
		/////////////////////////////////////////////////////////////////////////////////
		
		//���� ��ü �г�
		addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(300,550));
		addPanel.setBorder(BorderFactory.createTitledBorder("�л� ���� �Է�"));
		
		//�� �г�(����)�� �ؽ�Ʈ �г�(������)�� �����ϴ� �г�
		allPanel = new JPanel();
		allPanel.setLayout(new BorderLayout());
		
		//�� panel 
		lblPanel = new JPanel();
		lblPanel.setPreferredSize(new Dimension(48,260));
		lblPanel.setLayout(new GridLayout(0,1));
		
		lblName = new JLabel("�̸�");
		lblId = new JLabel("�й�");
		lblMa = new JLabel("�а�");
		lblGr = new JLabel("�г�");
		lblBi = new JLabel("�������");
		lblBi.setFont(new Font("����", Font.BOLD, 11));
		lblPhn = new JLabel("�޴���");
		lblAdd = new JLabel("�ּ�");
		lblPhoto = new JLabel("����");
		
		lblPanel.add(lblName);
		lblPanel.add(lblId);
		lblPanel.add(lblMa);
		lblPanel.add(lblGr);
		lblPanel.add(lblBi);
		lblPanel.add(lblPhn);
		lblPanel.add(lblAdd);
		lblPanel.add(lblPhoto);
		
		allPanel.add(lblPanel,BorderLayout.WEST);
		
				
		// �ؽ�Ʈ panel
		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(232,260));
		textPanel.setLayout(new GridLayout(0,1));
		
		txtName = new JTextField(20);
		txtId = new JTextField(20);
		txtMa = new JTextField(20);
		txtPhn = new JTextField(20);
		txtAdd = new JTextField(20);
		
		// �ؽ�Ʈ �г� ��, ������ư�гΰ� ��������г�
		radioP = new JPanel();
		selectP = new JPanel();
			
		
		//������ư �׷�
		group = new ButtonGroup();
		radio1 = new JRadioButton("1�г�",true);
		radio2 = new JRadioButton("2�г�");
		radio3 = new JRadioButton("3�г�");
		radio4 = new JRadioButton("4�г�");
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
				
		//���ٿ� �ֱ����� ��Ʈ����
		radio1.setFont(new Font("����", Font.PLAIN, 11));
		radio2.setFont(new Font("����", Font.PLAIN, 11));
		radio3.setFont(new Font("����", Font.PLAIN, 11));
		radio4.setFont(new Font("����", Font.PLAIN, 11));
		
		//������ư�гο� ���� ��ư �־���
		radioP.add(radio1);
		radioP.add(radio2);
		radioP.add(radio3);
		radioP.add(radio4);
		
		//������� �޺��ڽ�
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
		
		//��������гο� �޺��ڽ��߰�
		selectP.add(year);
		selectP.add(month);
		selectP.add(date);
				
		//��������ؽ�Ʈ�ʵ�� �ҷ������ư�� �ִ� �����г�
		photoP = new JPanel();
		photoP.setLayout(new BoxLayout(photoP,BoxLayout.X_AXIS));
		txtPhoto = new JTextField(10);
		btnPhoto = new JButton("�ҷ�����");	
		btnPhoto.addActionListener(this);
		
		//�����гο� �ٿ��ֱ�
		photoP.add(txtPhoto);
		photoP.add(btnPhoto);
				
		//textPanel�� ��� �ٿ��ֱ�
		textPanel.add(txtName);
		textPanel.add(txtId);
		textPanel.add(txtMa);
		textPanel.add(radioP);
		textPanel.add(selectP);
		textPanel.add(txtPhn);
		textPanel.add(txtAdd);
		textPanel.add(photoP);
		
		//�ؽ�Ʈ�г��� allPanel�� �ٿ��ֱ�
		allPanel.add(textPanel,BorderLayout.CENTER);
				
		// ������ panel
		profilePanel = new JPanel();
		profilePanel.setPreferredSize(new Dimension(280,180));
		profilePanel.setLayout(new BorderLayout());
		
		lblPro = new JLabel("������/�ڱ�Ұ�");
		profilePanel.add(lblPro,BorderLayout.NORTH);
		
		prof = new JTextArea();
		profScroll = new JScrollPane(prof);
		profilePanel.add(profScroll, BorderLayout.CENTER);
				
		// ��ư panel - �߰�, ���
		btnPanel = new JPanel();
		
		btnAdd = new JButton("�߰�");
		btnAdd.addActionListener(this);
		btnDel = new JButton("���");
		btnDel.addActionListener(this);
		
		btnPanel.add(btnAdd);
		btnPanel.add(btnDel);
		
		// addPanel�� ������Ʈ�� ���
		addPanel.add(allPanel);
		addPanel.add(profilePanel);
		addPanel.add(btnPanel);
				
		
		/////////////////////////////////////////////////////////////////////////////////
		// ��� student panel <��>
		/////////////////////////////////////////////////////////////////////////////////
		stdPanel = new JPanel();
		stdPanel.setPreferredSize(new Dimension(280,550));
		
		// ���� ���� ��ư
		editBut = new JButton("����");
		editBut.addActionListener(this);
		delBut = new JButton("����");
		delBut.addActionListener(this);
		
		// �˻� panel
		searchPanel = new JPanel();
		searchPanel.setPreferredSize(new Dimension(280,70));
		searchPanel.setBorder(BorderFactory.createTitledBorder("�л� ���� �˻�"));
		
		String[] search = {"�̸�","�й�"};
		comboSearch = new JComboBox(search);
		searchPanel.add(comboSearch);
		
		searchCon = new JTextField(12);
		searchPanel.add(searchCon);
		
		searchBut = new JButton("�˻�");
		searchPanel.add(searchBut);
		searchBut.addActionListener(this);
				
		// ��� panel
		listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(280,400));
		listPanel.setBorder(BorderFactory.createTitledBorder("�л� ���"));

		// ����Ʈ
		listModel = new DefaultListModel();
		stdList = new JList(listModel);
		stdList.setPreferredSize(new Dimension(300,400));
		stdList.addListSelectionListener(this);
		spList = new JScrollPane(stdList);
		spList.setPreferredSize(new Dimension(260,370));
		listPanel.add(spList); //��ũ���� ����
	
		//stdPanel�� ������Ʈ�� ���
		stdPanel.add(searchPanel);
		stdPanel.add(listPanel);
		stdPanel.add(editBut);
		stdPanel.add(delBut);
		
		/////////////////////////////////////////////////////////////////////////////////
		// �� ���� panel <��>
		/////////////////////////////////////////////////////////////////////////////////
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(300,550));
		infoPanel.setBorder(BorderFactory.createTitledBorder("�л� �� ����"));
		
		// photo Panel ���������ִ� �г�
		phPanel = new JPanel();
		phPanel.setPreferredSize(new Dimension(280,150));
		iconLbl = new JLabel();
		phPanel.add(iconLbl);

		// ������ textarea ��� panel
		viewPanel = new JPanel();
		taView = new JTextArea(17,25);
		
		spView = new JScrollPane(taView);//��ũ���� ����
		
		viewPanel.add(spView);
		
		//infoPanel�� �ٿ��ֱ�
		infoPanel.add(phPanel);
		infoPanel.add(viewPanel);
		
		//	�����ӿ� panel �߰�
		add(topPanel, BorderLayout.PAGE_START);
		add(addPanel, BorderLayout.LINE_START);
		add(stdPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.LINE_END);
		
		//������ �Ӽ�
		setTitle("�л� ���� �ý���");
		setSize(900, 630);
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setVisible(true);
		setResizable(false);
	}
	
	
	//------------------------------------------------------------------------------
	// ������ �������̽��� ���� �� �޼ҵ� ����
	// � �̺�Ʈ�� �߻��ߴ����� ���� ������ ����� �����Ѵ�.
	//------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		//���õ� ����Ʈ�� �ε��� ��������
		int n = stdList.getSelectedIndex();
		
		//Student ��ü ����
		Student s;
				
		
		// �޴������� 3��, ��ư 5��
		
				
		//�����ҷ�����
		if(source == btnPhoto){
			int rv = fc.showOpenDialog(this);
			if(rv == JFileChooser.APPROVE_OPTION){
				File f = fc.getSelectedFile();
				txtPhoto.setText(f.getAbsolutePath());
				txtPhoto.setEditable(false);//����� ���� �Է� �Ұ�
			}
		}
		
		//"�߰�"��ư �������� "�����Ϸ�"��ư ������
		else if(source == btnAdd){
			//����ڷκ��� �Է¹��� ������ ������� student ��ü�� �����ؼ� collection�� �߰�
			//����Ʈ�� �̸��� �߰�
			
			if(set==0){ //"�߰�"��ư ������
				
				int gn = grade();
				s = new Student(txtName.getText(),txtId.getText(),txtMa.getText(),gn,year.getSelectedItem().toString(),month.getSelectedItem().toString(),date.getSelectedItem().toString(),txtPhn.getText(),txtAdd.getText(),txtPhoto.getText(),prof.getText());
				collection.addElement(s);
				listModel.addElement(txtName.getText());
				reset();
			}
			else if(set==1){ //"�����Ϸ�"��ư ������
				
				int gn=grade();// �г� �о����
				
				s = collection.get(n);
				s.edit(txtMa.getText(),gn,year.getSelectedItem().toString(), month.getSelectedItem().toString(), date.getSelectedItem().toString(), txtPhn.getText(), txtAdd.getText(), txtPhoto.getText(), prof.getText());
				reset();
				taView.setText("");
				set = 0; // �ٽ� �ʱ�ȭ
				btnAdd.setText("�߰�");
									
			}
			
			
		}
		else if(source == btnDel){// ��� ��ư ������
			reset();
		}
		else if(source == editBut){//���� ��ư ������
			
			s = collection.get(n);
			
			// <<<<<���ʿ� �����л����� �߰��ϱ�>>>>>
			//�̸�����Ұ�
			txtName.setText(s.getName());
			txtName.setEditable(false);
			//�й�����Ұ�
			txtId.setText(s.getStuno());
			txtId.setEditable(false);
			//����
			txtMa.setText(s.getMajor());
			//�г�
			group.setSelected(b1, true);
			//����ó
			txtPhn.setText(s.getPhone());
			//�ּ�
			txtAdd.setText(s.getAddress());
			//�������
			txtPhoto.setText(s.getPhoto());
			//������
			prof.setText(s.getProfile());
			//�������
			year.setSelectedItem(s.getYear());
			month.setSelectedItem(s.getMonth());
			date.setSelectedItem(s.getDate());
			
			btnAdd.setText("�����Ϸ�");
			set = 1; // "�����Ϸ�" ��ư���� �����ϱ�
			
					
		}
		else if(source == searchBut){//�˻���ư ������
			
			if(comboSearch.getSelectedItem().toString().compareTo("�̸�")==0){//�޺��ڽ� "�̸�"���ý�
				
				String find = searchCon.getText();
				
				for(int i=0;i<collection.size();i++){
					s = collection.get(i);
					if(find.compareTo(s.getName())==0){//�̸� ��
						stdList.setSelectedIndex(i);
					}
				}
				
			}
			else if(comboSearch.getSelectedItem().toString().compareTo("�й�")==0){//�޺��ڽ� "�й�"���ý�
				
				String find = searchCon.getText();
				
				for(int i=0;i<collection.size();i++){
					s = collection.get(i);
					if(find.compareTo(s.getStuno())==0){//�й� ��
						stdList.setSelectedIndex(i);
					}
				}
				
			}
			searchCon.setText(""); //��ġ ������ �ʱ�ȭ
			
		}
		else if(source == delBut){ //����Ʈ ������
			s = collection.elementAt(n);
			collection.remove(n);
			listModel.removeElementAt(n);
			//������ �л����� �г� �ʱ�ȭ
			taView.setText("");
			iconLbl.setIcon(null);
				
		}
		else if(source == menuItem1){//�ҷ����� �޴�
			
			int op = fc.showOpenDialog(this);
			if(op == JFileChooser.APPROVE_OPTION){
				try{
					reset(); //�����г� �ʱ�ȭ
					taView.setText("");	//�������г� �ʱ�ȭ
					iconLbl.setIcon(null);//�����ʻ��� �ʱ�ȭ
					listModel.removeAllElements(); //���� ����Ʈ�� ��� ����
					
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
		else if(source == menuItem2){ //���� �޴�
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
		else if(source == menuItem3){ //�ý��� ���� �޴�
			JOptionPane.showMessageDialog(this, "�л������ý��� v 1.0�Դϴ�.");
		}
	}
	
	//�Է� ���� �ʱ�ȭ
	void reset(){
		txtName.setText("");
		txtId.setText("");
		txtMa.setText("");
		txtPhn.setText("");
		group.clearSelection(); //������ư �ʱ�ȭ
		txtAdd.setText("");
		prof.setText("");
		txtPhoto.setText("");
		//������� �ʱ�ȭ
		year.setSelectedItem("1980");
		month.setSelectedItem("1");
		date.setSelectedItem("1");
		
		txtName.setEditable(true);
		txtId.setEditable(true);
		txtPhoto.setEditable(true);
		btnAdd.setText("�߰�");
		
	}
	
	int grade(){ //�г� �о���� �޼ҵ�
		int g=1; //����Ʈ 1�г�
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
	//���õ� �ε����� �ش�Ǵ� ��ȭ ������ textArea ���� ����Ѵ�.
	//------------------------------------------------------------------------------
	public void valueChanged(ListSelectionEvent event){
		int n = stdList.getSelectedIndex();
				
		if (n != -1){
			
			Student x = collection.get(n);
			//����, �ؽ�Ʈ���� ���� ������ߵ�
			view(x);
			
		}
	}	
	void view(Student s){
		//s�� ���� �߿� �������� ���ͼ� �̹��� �����ܻ���
		st = new ImageIcon(s.getPhoto());
		//�󺧿� ���� 
		iconLbl.setIcon(st);
		//iconLbl.setText(null);
		//�ؽ�Ʈ ������ s�� ���� ���
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
