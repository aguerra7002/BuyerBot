package Main;

import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.Timer;

public class MouseCalibration extends JFrame implements ActionListener {
	
	JLabel xPosLabel;
	JLabel yPosLabel;
	JTextField xPos;
	JTextField yPos;
	double x, y;
	JCheckBox checkout;
	JCheckBox cc;
	JCheckBox terms;
	JCheckBox pay;
	JComboBox items;
	JButton update;
	JLabel mouseX;
	JLabel mouseY;
	
	public MouseCalibration() {
		super("Mouse Position");
		setSize(200, 300);
		setLayout(new GridLayout(6, 1));
		xPosLabel = new JLabel("X: ");
		yPosLabel = new JLabel("Y: ");
		xPos = new JTextField(5);
		yPos = new JTextField(5);
		mouseX = new JLabel("");
		mouseY = new JLabel("");
		JPanel x = new JPanel();
		JPanel y = new JPanel();
		x.add(xPosLabel);
		y.add(yPosLabel);
		x.add(xPos);
		y.add(yPos);
		add(x);
		add(y);
		add(mouseX);
		add(mouseY);
		checkout = new JCheckBox("Checkout");
		cc = new JCheckBox("CC");
		terms = new JCheckBox("terms");
		pay = new JCheckBox("Pay");
		String[] itemStrings = new String[]{
				"Item","Add To Cart", "Checkout",
				"CC","Terms", "Pay"
		};
		
		items = new JComboBox(itemStrings);
		update = new JButton("Update");
//		add(checkout);
//		add(cc);
//		add(terms);
//		add(pay);
		add(items);
		add(update);
		update.addActionListener(this);
		checkout.addActionListener(this);
		cc.addActionListener(this);
		terms.addActionListener(this);
		pay.addActionListener(this);
		setAlwaysOnTop(true);
		pack();
	//	addMouseMotionListener(this);
		setVisible(true);
		
		
		startCalibration();
	}
	
	private void startCalibration() {
		Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	x = MouseInfo.getPointerInfo().getLocation().x ;
            	y = MouseInfo.getPointerInfo().getLocation().y ;
            	mouseX.setText("x: " + x);
            	mouseY.setText("y: " + y);
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action");
		// TODO Auto-generated method stub
		
		
		if (e.getSource() == update) {
			try {
				Properties props = new Properties();
				
				File f = new File(System.getProperty("user.home") + "\\coords.properties");
				FileInputStream in = new FileInputStream(f);
			    props.load(in);
			    in.close();
			    String item = (String)items.getSelectedItem();
				if (item.equals("Checkout")) {
					props.setProperty("checkoutX", ""+xPos.getText());
					props.setProperty("checkoutY", ""+yPos.getText());
				} else if (item.equals("Item")) {
					props.setProperty("itemLocationX", ""+xPos.getText());
					props.setProperty("itemLocationY", ""+yPos.getText());
				} else if (item.equals("Add To Cart")) {
					props.setProperty("addToCartX", ""+xPos.getText());
					props.setProperty("addToCartY", ""+yPos.getText());
				} else if (item.equals("CC")) {
					props.setProperty("ccX", ""+xPos.getText());
					props.setProperty("ccY", ""+yPos.getText());
				} else if (item.equals("Terms")) {
					props.setProperty("termsX", ""+xPos.getText());
					props.setProperty("termsY", ""+yPos.getText());
				} else if (item.equals("Pay")) {
					props.setProperty("payX", ""+xPos.getText());
					props.setProperty("payY", ""+yPos.getText());
				}
				OutputStream out = new FileOutputStream( f );
				props.store(out, "Mouse Click Positions for Supreme Bot");
				out.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	        
		} 
	}

}
