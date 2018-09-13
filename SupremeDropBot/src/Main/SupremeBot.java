package Main;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SupremeBot {
	
	
	private static SupremeBot instance = null;
	Robot r;
	int checkoutX = 0;
	int checkoutY = 0;
	int ccX = 0;
	int ccY = 0;
	int termsX = 0;
	int termsY = 0;
	int payX = 0;
	int payY = 0;
	
	int addToCartX = 0;
	int addToCartY = 0;
	int itemLocationX = 0;
	int itemLocationY = 0;
	
	public SupremeBot() {
		
			try {
				r = new Robot();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}
	
	public void refreshClickVals() {
		try {
			Properties p;
			File file = new File(System.getProperty("user.home") + "\\coords.properties");
			FileInputStream fileInput = new FileInputStream(file);
			p = new Properties();
			p.load(fileInput);
			checkoutX = Integer.parseInt(p.getProperty("checkoutX"));
			checkoutY = Integer.parseInt(p.getProperty("checkoutY"));
			ccX = Integer.parseInt(p.getProperty("ccX"));
			ccY = Integer.parseInt(p.getProperty("ccY"));
			termsX = Integer.parseInt(p.getProperty("termsX"));
			termsY = Integer.parseInt(p.getProperty("termsY"));
			payX = Integer.parseInt(p.getProperty("payX"));
			payY = Integer.parseInt(p.getProperty("payY"));
			
			addToCartX = Integer.parseInt(p.getProperty("addToCartX"));
			addToCartY = Integer.parseInt(p.getProperty("addToCartY"));
			itemLocationX = Integer.parseInt(p.getProperty("itemLocationX"));
			itemLocationY = Integer.parseInt(p.getProperty("itemLocationY"));
			
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*Make Sure Chrome is open and the window is maximized*/
	
	//Execute after you have added item to cart
	public void checkout(int type, String num, String exp, String cvc, boolean buy, boolean checkoutOnly) {
		Thread t = new Thread(() -> {
			r.setAutoWaitForIdle(true);		
			
			if (!checkoutOnly) {
				r.mouseMove(itemLocationX, itemLocationY);
				r.mouseMove(itemLocationX, itemLocationY);
				mousePress();
				r.delay(750);
				r.mouseMove(addToCartX, addToCartY - 35);
				r.mouseMove(addToCartX, addToCartY - 35);
				mousePress();
				r.delay(100);
				arrowDown();
				tab();
				r.mouseMove(addToCartX, addToCartY);
				r.mouseMove(addToCartX, addToCartY);
				mousePress();
				r.delay(100);
			}
			///////
			
			
				r.mouseMove(checkoutX,
					checkoutY);
			r.mouseMove(checkoutX,
					checkoutY);
			r.delay(100);
//			x = MouseInfo.getPointerInfo().getLocation().x ;
//        	y = MouseInfo.getPointerInfo().getLocation().y ;
//        	System.out.println(x + ", " + y);
//        	System.out.println(checkoutX+ ", "+ checkoutY);
//        	r.delay(1000);
			mousePress();
			r.delay(1500);
			r.mouseMove(ccX, ccY);
			r.mouseMove(ccX, ccY);
			r.mouseMove(ccX, ccY);
			r.delay(100); //Adjust this accordingly for webpage load time
			///////////////////////////
//			r.delay(1000);
//			x = MouseInfo.getPointerInfo().getLocation().x ;
//        	y = MouseInfo.getPointerInfo().getLocation().y ;
//        	System.out.println(x + ", " + y);
//        	System.out.println(ccX+ ", " + ccY);
//        	r.delay(1000);
			mousePress();

			r.delay(550);//* Necessary for proper cc num typing, idk why tho.
			//type(num);
			copy(num);
			paste();
			
			
			tab();
			type(exp.substring(0,2));
			
			tab();
			type(exp.substring(2, 6));
			
			tab();
			type(cvc);
			r.mouseMove(termsX,
					termsY);
			r.mouseMove(termsX,
					termsY);
			r.mouseMove(termsX,
					termsY); //Terms and Conditions
//			r.delay(1000);
//			x = MouseInfo.getPointerInfo().getLocation().x ;
//        	y = MouseInfo.getPointerInfo().getLocation().y ;
//			System.out.println(x + ", " + y);
//			r.delay(1000);
			mousePress();
			r.mouseMove(payX,
					payY);
			r.mouseMove(payX,
					payY);
			r.mouseMove(payX,
					payY);
//			r.delay(1000);
//			x = MouseInfo.getPointerInfo().getLocation().x ;
//        	y = MouseInfo.getPointerInfo().getLocation().y ;
//        	System.out.println(x + ", " + y);
//        	System.out.println(payX+ ", "+ payY);
//        	r.delay(1000);
			// The following line should actually buy the item, comment out if just testing
			if (buy) {
				mousePress();
			}
			//System.out.println("Checkout Time: " + (double)((System.currentTimeMillis() - startTime) / 1000d) + " seconds");
			try {
				Thread.currentThread().join();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t.start();
	}
	
	
	public void refresh() {
		r.delay(490);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.delay(10);
		r.keyPress(KeyEvent.VK_R);
		r.delay(10);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_R);
		r.delay(10);
	}
	
	public void type(String stringy) {
		byte[] bytes = stringy.getBytes();
		for (byte b : bytes) {
			int code = b;
		      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
		      if (code > 96 && code < 123) code = code - 32;
			r.keyPress(code);
			r.keyRelease(code);
			r.delay(15);
		}
	}
	
	private void mousePress() {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.delay(10);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		r.delay(10);
	}
	
	private void tab() {
		r.keyPress(KeyEvent.VK_TAB);
		r.delay(10);
		r.keyRelease(KeyEvent.VK_TAB);
		r.delay(10);
	}
	private void arrowDown() {
		r.keyPress(KeyEvent.VK_DOWN);
		r.delay(10);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.delay(150);
	}
	private void copy(String str) {
		Toolkit.getDefaultToolkit().getSystemClipboard()
        .setContents(new StringSelection(str), null);
	}
	private void paste() {
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.delay(10);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.delay(10);
	}
	
	public static SupremeBot getInstance() {
	      if(instance == null) {
	         instance = new SupremeBot();
	      }
	      return instance;
	}
}
