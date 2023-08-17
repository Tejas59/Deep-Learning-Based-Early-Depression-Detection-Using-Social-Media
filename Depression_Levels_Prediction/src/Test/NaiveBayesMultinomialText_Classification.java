/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Master.Dbconn;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffLoader;

/**
 *
 * @author Jitu Patil
 */
public class NaiveBayesMultinomialText_Classification {

    public int Execute(String train, String test, int classindex, Connection con) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic hereInstances trainset=new Instances(new BufferedReader(new FileReader("C:/project/traindataset.arff")));
        int rval = 0;
        //double baseval = 1.8;
        boolean T = false;
        Statement st = con.createStatement();
        // load train dataset
        int correctlyPredicted[] = new int[5];
        ArffLoader al = new ArffLoader();
        al.setFile(new File(train));
        Instances inst = al.getDataSet();
        inst.setClassIndex(inst.numAttributes() - 1);

        // load test dataset
        ArffLoader alTest = new ArffLoader();
        alTest.setFile(new File(test));
        Instances instTest = alTest.getDataSet();
        instTest.setClassIndex(instTest.numAttributes() - 1);

        NaiveBayesMultinomialText nb = new NaiveBayesMultinomialText();
       
        nb.buildClassifier(instTest);

        double correctClass = 0;
        int records = 0;
//        int size=Integer.valueOf(Dbconn.recordsize);
       
                int i = 1;
                //attributes.remove();
                for (i = 0; i < instTest.numInstances(); i++) {
                    records++;
                    double pred = 0, pred1 = 0, pred2 = 0, pred3 = 0, pred4 = 0, pred5 = 0, pred6 = 0, pred7 = 0;
                    int classArray[] = new int[5];

                    pred = nb.classifyInstance(instTest.instance(i));
                    classArray[(int) pred]++;

                    int maxClass = 0;
                    for (int k = 1; k < classArray.length; k++) {
                        if (classArray[k] > classArray[maxClass]) {
                            maxClass = k;
                        }
                    }

                    String actualClass = instTest.classAttribute().value((int) instTest.instance(i).classValue());
                    String predictedClass = instTest.classAttribute().value((int) maxClass);
                    System.out.println("Actual Class is :" + actualClass);
                    System.out.println("Predicted Class is :" + predictedClass);
                   // Statement st = conn.createStatement();
//                    st.executeUpdate("update testdata set NBClass='" + predictedClass + "' where id ='" + (records) + "'");
                    if (actualClass.equalsIgnoreCase(predictedClass)) {
                        correctlyPredicted[maxClass]++;
                        correctClass++;
                    }
                }
//                printMeasures(evaluation, T);

                System.out.println("No of records : " + records);
                System.out.println("Correctly classified : " + correctClass);
                System.out.println("Incorrectly classified : " + (records - correctClass));
                System.out.println("Accuracy : " + (correctClass / (records) * 100));
        Evaluation evaluation = new Evaluation(instTest);
        System.out.println(evaluation.toSummaryString());
        
//        for (i = 0; i < instTest.numInstances(); i++) {
//            records++;
//            double pred = 0, pred1 = 0;
//            int classArray[] = new int[5];
//            pred = nb.classifyInstance(instTest.instance(i));
//
//            classArray[(int) pred]++;
//
//            System.out.println("Predicted Class for : \t" + instTest.instance(i) + " \t is \t" + pred);
//            int maxClass = 0;
//            for (int k = 1; k < classArray.length; k++) {
//                if (classArray[k] > classArray[maxClass]) {
//                    maxClass = k;
//                }
//            }
//            String predictedClass = "";
//            if (pred == 0.0) {
//                predictedClass = "0";
//            } else {
//                predictedClass = "1";
//            }
//
////            if (records <= size) {
////
////                st.executeUpdate("update tbldata set NB_label='" + predictedClass + "' where id='" + records + "'");
////            }
//        }
        
        return rval;
    }

    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
        Instances[][] split = new Instances[2][numberOfFolds];

        for (int i = 0; i < numberOfFolds; i++) {
            split[0][i] = data.trainCV(numberOfFolds, i);
            split[1][i] = data.testCV(numberOfFolds, i);
        }

        return split;
    }
}
