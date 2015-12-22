package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.receiptbl.getDate;
import presentation.commonui.DateChooser;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import type.ReceiptType;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import vo.UserVO;
/**
 * 暂时先把根据营业厅筛选的去掉了，以后有时间再说吧
 * */
public class CollectionReceiptPanel extends OperationPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel dateChooseLabel;
	private MyLabel hallChooseLabel;
	private MyLabel collectionOKButton;
	private MyLabel totalButton;
	private MyLabel cancelButton;

	private JLabel function;
	private JLabel date;
	private JLabel businessHall;
	private JLabel infoLine;

	private MyTextField date_Input;
	private MyTextField businessHall_ID_Input;
	
	private MyTable collectionTable;
	

	public CollectionReceiptBLController controller;
	public FinanceFrame financeFrame;
	public UserVO user;

	private ArrayList<GatheringReceiptVO> gatheringReceiptVOs;


	String hallID_str;
	String date_str;
	public CollectionReceiptPanel(CollectionReceiptBLController controller,FinanceFrame parent,UserVO user) {
		this.controller=controller;
		this.financeFrame=parent;
		this.user = user;
		dateChooseLabel =new JLabel("日期");
		hallChooseLabel = new MyLabel("营业厅");
		collectionOKButton = new MyLabel("确认");
		totalButton = new MyLabel("合计");
		cancelButton = new MyLabel("返回");

		function = new JLabel("新建入款单");
		date = new JLabel("日期");
		businessHall = new JLabel("营业厅");
		infoLine = new JLabel("时间："+getDate.getdate().substring(0,4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate()
				.substring(6)+ " 合计金额：0");

		date_Input = new MyTextField("");
		businessHall_ID_Input = new MyTextField("");

		setLayout(null);

		add(dateChooseLabel);
		add(hallChooseLabel);
		add(collectionOKButton);
		add(totalButton);
		add(cancelButton);
	
		
		add(function);
		add(date);
		add(businessHall);
		add(infoLine);

		add(date_Input);
		add(businessHall_ID_Input);
		dateChooseLabel.setLayout(new BorderLayout());
		dateChooseLabel.add(new DateChooser(date_Input),BorderLayout.CENTER);

		addListener();
		setBaseInfo();

		
		
	}
	
	public void setBaseInfo(){
		String[] head = new String[]{"收款单编号","营业厅编号","收款金额"};
		int[] widths = new int[]{200,190,190};
		collectionTable = new MyTable(head, getInfos(gatheringReceiptVOs), widths, false);
		add(collectionTable);
	}
	
	public ArrayList<String[]> getInfos(ArrayList<GatheringReceiptVO> gvos){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if(gvos!=null){
			for(GatheringReceiptVO v : gvos){
				lineInfo.add(new String[]{v.receiptID,v.businesshall.organizationID,v.totalmoney+""});
			}
			return lineInfo;
		}
		else{
			return new ArrayList<String[]>();
		}
	}


	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

	dateChooseLabel.setBounds((int)(width * 7.174744897959184/25),(int)(height *  2.522504892367906/20),(int)(width *  1.0204081632653061 /25),(int)(height *  0.9001956947162426/20));
		hallChooseLabel.setBounds((int)(width * 14.09438775510204/25),(int)(height * 2.522504892367906/20),(int)(width *  0.9885204081632653 /25),(int)(height *  0.9784735812133072/20));
		collectionOKButton.setBounds((int)(width * 21.739795918367346/25),(int)(height * 2.522504892367906/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.9784735812133072/20));
		totalButton.setBounds((int)(width * 21.739795918367346/25),(int)(height * 18.434442270058707/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.9784735812133072/20));
		cancelButton.setBounds((int)(width * 20.239795918367346/28), (int)(height * 18.434442270058707/20),(int)(width *  2.072704081632653 /25),(int)(height *  0.9784735812133072/20));
		function.setBounds((int)(width * 0.6696428571428571/25),(int)(height * 0.821917808219178/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.643835616438356/20));
		date.setBounds((int)(width * 2.391581632653061/25),(int)(height * 2.522504892367906/20),(int)(width *  1.594387755102041 /25),(int)(height *  0.9001956947162426/20));
		businessHall.setBounds((int)(width * 9.056122448979592/25),(int)(height * 2.522504892367906/20),(int)(width *  1.6581632653061225 /25),(int)(height *  0.8610567514677103/20));
		infoLine.setBounds((int)(width * 2.5191326530612246/25),(int)(height * 18.12133072407045/20),(int)(width *  13.424744897959183 /25),(int)(height *  1.2915851272015655/20));
		date_Input.setBounds((int)(width * 3.877295918367347/25),(int)(height * 2.522504892367906/20),(int)(width *  2.874234693877551 /25),(int)(height *  1.0/20));
		businessHall_ID_Input.setBounds((int)(width * 11.033163265306122/25),(int)(height * 2.522504892367906/20),(int)(width *  2.874234693877551 /25),(int)(height *  1.0/20));
		collectionTable.setLocationAndSize((int)(width * 1.1510204081632653/25),(int)(height * 4.04481409001957/20),(int)(width *  22.92091836734694 /25),(int)(height *  13.95890410958904/20));
	}
	

		
		private void addListener(){
			/**
			 * 监听
			 * */
	                  /**
	                    * 选择日期
	                   * */
			/**
			 * 确认日期输入
			 * */
			hallChooseLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					infookui();
				}
			});

			collectionOKButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					okui();
				}
			});

			totalButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					totalui();
				}
			});
			
			cancelButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					cancelui();
				}
			});

		}
		
		/**
		 * 监听方法
		 * */
	public void dateChooseui() {

	}

	/**
	 * 输入（选择）日期的方法,这个怎么写
	 * */
	public void infookui() {
	}

	/**
	 * 确定
	 * */
	public void okui() {
		date_str=date_Input.getText();

			if(date_str.equals("")){
			JOptionPane.showMessageDialog(null, "请输入完整信息！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
			else{
				String time=date_str.substring(0, 4)+date_str.substring(5, 7)+date_str.substring(8);

		 if(time.length()!=8){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else if(time.substring(0,4).compareTo("2015")>0||time.substring(4,6).compareTo("12")>0||time.substring(6,8).compareTo("31")>0){
			JOptionPane.showMessageDialog(null, "请输入正确的日期！", "提示",
					JOptionPane.CLOSED_OPTION);
			date_Input.setText("");
		}
		else{
			gatheringReceiptVOs = controller.getGathering(date_str);
			collectionTable.setInfos(getInfos(gatheringReceiptVOs));
		}

			}
	}
	
	/**
	 * 取消
	 * */
	public void cancelui(){
		financeFrame.toMainPanel();
	}
	

	public void totalui() {
		double money=controller.getTotalMoney(controller.getGathering(date_str));
		infoLine.setText("日期："+getDate.getdate().substring(0,4)+"-"+getDate.getdate().substring(4, 6)+"-"+getDate.getdate()
				.substring(6)+"    金额总和："+money);
		CollectionReceiptVO vo=new CollectionReceiptVO(controller.getCollectionListID(), user.userID, ReceiptType.COLLECTIONRECEIPT, ReceiptState.SUBMIT, money, getDate.getdate(), "boss");
		int temp=controller.creatCollection(vo);
		if(temp==0){
			controller.excute(vo);
			JOptionPane.showMessageDialog(null, "创建入款单成功！", "提示",
					JOptionPane.DEFAULT_OPTION);
		}
		else{
			JOptionPane.showMessageDialog(null, "该入款单已经被创建了！", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void nextui() {

	}

	public void previousui() {

	}
	
	
}



