package br.com.conectasol.sco.controller;

import br.com.conectasol.sco.domain.Domain;
import br.com.conectasol.sco.domain.DomainRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainWeb {

    @Autowired
    public DomainRepository rep;

    @GetMapping
    public List<Domain> teste(){
        return rep.findAll();
    }

    @GetMapping("/salvar")
    public String salvar(){
//        Domain d = new Domain();
//        d.setId(System.currentTimeMillis());
//        d.setDomain("COnectaSol" + d.getId());
//        d.setDisplayAds(true);
//        rep.save(d);

        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        String folderpath = "/bck/v1/";
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


        return "Done!!";
    }

    @GetMapping("/log")
    public ResponseEntity log(){
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

        return ResponseEntity.ok("OK");
    }
}
