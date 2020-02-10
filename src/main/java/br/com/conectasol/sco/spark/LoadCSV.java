package br.com.conectasol.sco.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Map;

public class LoadCSV {

    public static void main(String[] args) {

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        String folderpath = "/home/sergio/Downloads/files/v1/";
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .config("spark.master", "local")
                .getOrCreate();
        spark.sparkContext().setLogLevel("ERROR");
        Dataset<Row> dataset = spark
                .read()
                .format("csv")
                .option("sep", ";")
                .option("inferSchema", "true")
                .option("header", "true")
//                .load(folderpath + "FolhaPagamento_201201.csv");
                .load(folderpath + "*.csv");

        dataset.printSchema();
        dataset.show();

        System.out.println("Quantidade:: -->>> " + dataset.count());
        int line = 1;
        for (Row row : dataset.collectAsList()) {
            System.out.printf("#" + line + "\t");
            for (int i = 0; i < row.length(); i++) {
                System.out.print(row.get(i));
                System.out.print("\t");
            }
            System.out.println();
            line++;
        }

        spark.stop();
    }
}
