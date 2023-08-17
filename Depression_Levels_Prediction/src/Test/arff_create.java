package Test;

import Master.Dbconn;
import java.io.*;
import java.sql.*;
import java.util.*;

public class arff_create {

    public static void main(String[] args) {
        arff_create.Trainarff();
        arff_create.Testarff();
        System.out.println("Done");
    }
    public static void Trainarff() {
        try {
            System.out.println("\t Data After Filteration");
            ArrayList<String> patterns = new ArrayList<>();
            File file = new File(Dbconn.arff + "\\nTrain.arff");
            FileWriter writer = null;
            writer = new FileWriter(file);

            writer.write("@relation 'dataset-information'");
            writer.write("\n");
            writer.write("@attribute 'id' numeric");
            writer.write("\n");
            writer.write("@attribute 'headline' string");
            writer.write("\n");
            writer.write("@attribute 'class' {Not_Depressed,Depressed}");
            writer.write("\n");
            writer.write("@data");
            writer.write("\n");

            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            String tablename = "nlptrain";
            patterns = Getdata(tablename);
            for (int i = 0; i < patterns.size(); i++) {
                System.out.println("Result \t" + patterns.get(i));
                String str = patterns.get(i).toString();
                if (!str.isEmpty()) {
                    writer.write(str);
                    writer.write("\n");
                }

            }
            writer.close();

        } catch (Exception ex) {
        }

    }

    public static ArrayList<String> Getdata(String tablename) throws Exception {

        ArrayList<String> objlist = new ArrayList<>();
        Connection con = (Connection) Dbconn.conn();
        Statement st = (Statement) con.createStatement();
        ResultSet rs = (ResultSet) st.executeQuery("select * from " + tablename + "");
        while (rs.next()) {
            String label = rs.getString(5);
           
            String id = rs.getString(1);
            String data = rs.getString(3).replace("?", "").replace(".", "").replace("-", "");
            String ss = id + ",'" + data + "'" + "," + label;
            objlist.add(ss);
        }
        return objlist;
    }

    public static void Testarff() {
        try {
            System.out.println("\t Data After Filteration");
            ArrayList<String> patterns = new ArrayList<>();
            File file = new File(Dbconn.arff + "\\nTest.arff");
            FileWriter writer = null;
            writer = new FileWriter(file);

            writer.write("@relation 'dataset-information'");
            writer.write("\n");
            writer.write("@attribute 'id' numeric");
            writer.write("\n");
            writer.write("@attribute 'headline' string");
            writer.write("\n");
            writer.write("@attribute 'class' {Not_Depressed,Depressed}");
            writer.write("\n");
            writer.write("@data");
            writer.write("\n");

            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            String tablename = "nlptest";
            patterns = Getdata(tablename);
            for (int i = 0; i < patterns.size(); i++) {
                System.out.println("Result \t" + patterns.get(i));
                String str = patterns.get(i).toString();
                if (!str.isEmpty()) {
                    writer.write(str);
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (Exception ex) {
        }

    }
}
