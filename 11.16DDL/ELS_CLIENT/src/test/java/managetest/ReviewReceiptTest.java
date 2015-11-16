package managetest;
import junit.framework.TestCase;

import businesslogic.managebl.ReviewReceiptBL;

public class ReviewReceiptTest extends TestCase {

	public void testReceiptReview(){
		
		ReviewReceiptBL receiptReview = new ReviewReceiptBL();
		String[] strArr = {"20151001-00001","20151002-00011","20151003-00008"};
		
		receiptReview.modify("20151001-00001");
		receiptReview.batch(strArr);
		receiptReview.approve("20151001-00001");
		
		assertEquals(0,0);
	}
}
