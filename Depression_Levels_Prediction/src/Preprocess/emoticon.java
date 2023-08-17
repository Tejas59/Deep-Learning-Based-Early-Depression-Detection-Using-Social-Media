/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocess;

import static Master.Dbconn.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author JP
 */
public class emoticon {

    public static int Pos = 0, Neg = 0;

    public static int positivetopics(String m) {
        int Pos1 = 0;
        try {

            FileReader fr;
            fr = new FileReader(file70path);
            BufferedReader br1 = new BufferedReader(fr);
            String datas;

            while ((datas = br1.readLine()) != null) {

                String[] parts = datas.split("####0");
                //System.out.println("Size=>" + parts.length);
                if (parts.length <= 5) {

                } else {
                    String msg = parts[5];

                    if (msg.contains("💜💞💙") || msg.contains("😍😍😍") || msg.contains("❤") || msg.contains("👌") || msg.contains("😂") || msg.contains("😍😌") || msg.contains("👍") || msg.contains("🏆")) {
                        Pos1++;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MasterPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return Pos1;
    }

    public static int negativetopics(String m) {
       int Neg1 = 0;
        try {

            FileReader fr;
            fr = new FileReader(file70path);
            BufferedReader br1 = new BufferedReader(fr);
            String datas;

            while ((datas = br1.readLine()) != null) {

                String[] parts = datas.split("####0");
              //  System.out.println("Size=>" + parts.length);
                if (parts.length <= 5) {

                } else {
                    String msg = parts[5];

                    if (msg.contains("😘") || msg.contains("😭😭") || msg.contains("😣") || msg.contains("😖") || msg.contains("😖") || msg.contains("😫") || msg.contains("😿") || msg.contains("😩") || msg.contains("😤") || msg.contains("👹") || msg.contains("👿") || msg.contains("😔")) {
                        Neg1++;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(MasterPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return Neg1;
    }
public static int Emojipos(String msg)
{ int pos=0;
//String[] tokens = m.split("\\P{Alpha}+");// \\p is use for space and
       // for (String msg : tokens)
        {
    int i=msg.indexOf("😐");
            
            if (msg.contains("🌊")||msg.contains("👩‍🎓")||msg.contains("😏") ||msg.contains("💙")||msg.contains("😋")||msg.contains("🌼🌹")||msg.contains("♥")||msg.contains("😎👌")||msg.contains("😐")||msg.contains("💜💞💙") || msg.contains("😍😍😍") || msg.contains("❤") || msg.contains("👌") || msg.contains("😂") || msg.contains("😍😌") || msg.contains("👍") || msg.contains("🏆")) {
                        pos++;
                    }
        }

return pos;
}
public static int Emojineg(String msg)
{ int neg1=0;

        {
            if (msg.contains("📚")||msg.contains("😕")||msg.contains("😎")||msg.contains("😘") || msg.contains("😭😭") || msg.contains("😣") || msg.contains("😖") || msg.contains("😖") || msg.contains("😫") || msg.contains("😿") || msg.contains("😩") || msg.contains("😤") || msg.contains("👹") || msg.contains("👿") || msg.contains("😔")) {
                        neg1++;
                    }
        }

return neg1;
}

public static void main(String args[]){
 
 
 
// /neg
         
}

}
