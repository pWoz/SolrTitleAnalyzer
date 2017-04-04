package pl.psnc.synat.SolrAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Scanner;

/**
 * Analyzes title from file and pushes results to another file
 *
 * Created by pwozniak on 3/17/17.
 */
public class TitlesAnalyzer {

    private static Logger LOG = LogManager.getLogger(TitlesAnalyzer.class);


    private FileReader fileReader = null;
    private FileWriter fileWriter = null;

    public TitlesAnalyzer() throws IOException {
        fileReader = new FileReader("titles.csv");
        fileWriter = new FileWriter("parsed_titles.csv");
    }

    public void analyze() throws IOException {

        Scanner scanner = new Scanner(fileReader);

        int counter = 0;

        while (scanner.hasNext()) {
            counter++;
            LOG.info("Counter: " + counter);
            String title = findTitle(scanner);
            if (title == null) {
                scanner.nextLine();
                continue;
            }
            NewspaperTitle nTitle = new NewspaperTitle(title);
            writeToFile(nTitle);
            scanner.nextLine();
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private String findTitle(Scanner scanner) {
        return scanner.findInLine("\\[.*\\]");
    }

    private void writeToFile(NewspaperTitle newspaperTitle) throws IOException {
        fileWriter.write("--------------");
        fileWriter.write(System.lineSeparator());
        fileWriter.write(newspaperTitle.getOriginalTitle());
        fileWriter.write(System.lineSeparator());
//        fileWriter.write(newspaperTitle.algoryth1());
//        fileWriter.write(System.lineSeparator());
        fileWriter.write(newspaperTitle.findTitle());
        fileWriter.write(System.lineSeparator());
        fileWriter.write("--------------");
        fileWriter.write(System.lineSeparator());
    }

    public static void main(String[] args) throws IOException {

        TitlesAnalyzer titlesAnalyzer = new TitlesAnalyzer();
        titlesAnalyzer.analyze();

    }
}
