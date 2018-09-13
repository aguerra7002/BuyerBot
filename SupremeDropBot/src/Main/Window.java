package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Window extends JFrame implements ActionListener {
	JButton startBot;
	JButton checkoutOnly;
	JLabel msg;
	JLabel ccTypeLabel;
	String[] ccTypes = new String[]{"Visa", "AmEx", "MC"};
	JComboBox ccType;
	JLabel ccNumLabel;
	JTextField ccNum;
	JLabel ccExpLabel;
	JTextField ccExp;
	JLabel ccCVCLabel;
	JTextField ccCVC;
	JCheckBox buy;
	SupremeBot sb;
	TimePanel t;
	public Window() {
		super("Supreme Drop Bot");
		setSize(260, 330);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sb = new SupremeBot();
		sb.refreshClickVals();
		t = new TimePanel();
		add(t);
		
		ccTypeLabel = new JLabel("CC Type: ");
		ccNumLabel = new JLabel("CC #: ");
		ccExpLabel = new JLabel("CC Exp: ");
		ccCVCLabel = new JLabel("CC CVC: ");
		
		ccType = new JComboBox(ccTypes);
		ccType.setSelectedIndex(2);
		
		ccNum = new JTextField(16);
		ccNum.setText("1234654356780987");
		ccExp = new JTextField(5);
		ccExp.setText("062021");
		ccCVC = new JTextField(4);
		ccCVC.setText("752");
		buy = new JCheckBox("Buy? ");
		
		JPanel ccTypePane = new JPanel();
		ccTypePane.add(ccTypeLabel);
		ccTypePane.add(ccType);
		JPanel ccNumPane = new JPanel();
		ccNumPane.add(ccNumLabel);
		ccNumPane.add(ccNum);
		JPanel ccExpPane = new JPanel();
		ccExpPane.add(ccExpLabel);
		ccExpPane.add(ccExp);
		JPanel ccCVCPane = new JPanel();
		ccCVCPane.add(ccCVCLabel);
		ccCVCPane.add(ccCVC);
		JPanel buyPanel = new JPanel();
		buyPanel.add(buy);
		
		JPanel msgPane = new JPanel();
		msg = new JLabel("Press A to Checkout");
		msgPane.setLayout(new GridLayout(7,1));
		startBot = new JButton("Auto Buy");
		startBot.addActionListener(this);
		checkoutOnly = new JButton("Auto Checkout");
		checkoutOnly.addActionListener(this);
		JPanel fill = new JPanel();
		fill.add(msg);
		msgPane.add(ccTypePane);
		msgPane.add(ccNumPane);
		msgPane.add(ccExpPane);
		msgPane.add(ccCVCPane);
		msgPane.add(buyPanel);
		msgPane.add(startBot);
		msgPane.add(checkoutOnly);
		add(msgPane, BorderLayout.SOUTH);
		
		t.getInputMap().put(KeyStroke.getKeyStroke("E"),
				"E pressed");
		t.getActionMap().put("E pressed",
				mouseCalibration);
		
	    t.getInputMap().put(KeyStroke.getKeyStroke("A"),
	            "A pressed");
	    t.getActionMap().put("A pressed",
	            checkout);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Window();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == startBot) {
			sb.checkout(ccType.getSelectedIndex(), ccNum.getText(), ccExp.getText(), ccCVC.getText(), buy.isSelected(), false);
			
		} else if (e.getSource() == checkoutOnly) {
			sb.checkout(ccType.getSelectedIndex(), ccNum.getText(), ccExp.getText(), ccCVC.getText(), buy.isSelected(), true);
		}
		
		
	}
	
	Action mouseCalibration = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			//setFocusable(false);
			//JFrame f = new JFrame();
			new MouseCalibration();
			sb.refreshClickVals();
		}
	};
	
	Action checkout = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    	sb.checkout(ccType.getSelectedIndex(), ccNum.getText(), ccExp.getText(), ccCVC.getText(), buy.isSelected(), false);
	    }
	};
	
}
