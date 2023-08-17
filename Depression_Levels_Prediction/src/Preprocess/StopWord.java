package Preprocess;

import java.io.IOException;
public class StopWord {
	public static void main(String[] args) {
		
 {
		 {
			
		    try {
				String content = "B/C";
				System.out.println("Content:-"+content);
				RemoveStopwords rm = new RemoveStopwords();
				String result = rm.RemoveWords(content);
				System.out.println("Data is " + result);

				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    /* do somthing with content */
		  } 
		}
	}

}
