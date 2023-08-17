package Preprocess;


import Master.Dbconn;
import static Master.Dbconn.negative;
import static Master.Dbconn.positive;
import static Master.Dbconn.stopwordspath;
import com.mysql.jdbc.Statement;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class RemoveStopwords {

    private static Statement st;
    static int maxno;
    public static String slang = null;
   public static String filepath = "D:\\stopwords.txt";
    //String filepath2 = "F:\\stopwords.txt";

    int count = 0;

    @SuppressWarnings("resource")
    public static String slang(String data) throws FileNotFoundException {

        String[] tokens = data.split("\\s+");// \\p is use for space and

        try {
            for (String s : tokens) {
                File file = new File(filepath);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    line = br.readLine();
                    System.out.println(line);
                }

            }
        } catch (IOException e) {
        }
        return slang.toString();
    }

    public String RemoveWords(String data) throws FileNotFoundException {
        boolean flag = false;
        Scanner sc;

        StringBuilder sb = new StringBuilder();

        String[] tokens = data.split("\\P{Alpha}+");// \\p is use for space and
        // {Alpha for (')eg it's}

        for (String s : tokens) {
            sc = new Scanner(new File(stopwordspath));

            while (sc.hasNext()) {
                if (sc.next().toLowerCase().equals(s.toLowerCase())) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }

            }
            if (flag == false) {

                sb.append(s + ",");
            }
        }
        return sb.toString();
    }

    public static int negativetopics(String data) throws FileNotFoundException {
        boolean flag = false;
        Scanner sc;
        int a = 1;
        StringBuilder sb = new StringBuilder();

        String[] tokens = data.split(",");// \\p is use for space and
        // {Alpha for (')eg it's}

        for (String s : tokens) {
            sc = new Scanner(new File(negative));

            while (sc.hasNext()) {
                String word = sc.next();
                if (word.toLowerCase().equals(s.toLowerCase())) {
                    flag = true;
                    a++;
                    //System.out.println(s+"="+word+"count"+a);
                    // sb.append(s=word+",");
                    break;
                } else {
                    flag = false;
                }

            }
            if (flag == false) {

                //sb.append(a);
            }
        }
        return a;
    }

    public static int positivetopics(String data) throws FileNotFoundException {
        boolean flag = false;
        Scanner sc;
        int a = 1;
        StringBuilder sb = new StringBuilder();

        String[] tokens = data.split(",");// \\p is use for space and
        // {Alpha for (')eg it's}

        for (String s : tokens) {
            sc = new Scanner(new File(positive));

            while (sc.hasNext()) {
                String word = sc.next();
                if (word.toLowerCase().equals(s.toLowerCase())) {
                    flag = true;
                    a++;
                    break;
                } else {
                    flag = false;
                }

            }
            if (flag == false) {

                //sb.append(a);
            }
        }
        return a;
    }

   
}
