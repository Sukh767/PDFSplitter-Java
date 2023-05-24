
                    /* The PDF file will Split into a given specific range  */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.multipdf.Splitter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the PDF file path: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the output directory path: ");
        String outputDirectoryPath = scanner.nextLine();

        System.out.print("Enter the start page number: ");
        int startPageNumber = scanner.nextInt();

        System.out.print("Enter the end page number: ");
        int endPageNumber = scanner.nextInt();

        try {

            PDDocument document = PDDocument.load(new File(inputFilePath));
            int numberOfPages = document.getNumberOfPages();
            if (startPageNumber < 1 || endPageNumber > numberOfPages || startPageNumber > endPageNumber) {
                System.out.println("Invalid page range.");
                return;
            }
            Splitter splitter = new Splitter();

            splitter.setStartPage(startPageNumber);
            splitter.setEndPage(endPageNumber);

            List<PDDocument> pages = splitter.split(document);
            for (int i = 0; i < pages.size(); i++) {
                String outputFilePath = outputDirectoryPath + File.separator + "split_page_" + (i + 1) + ".pdf";
                pages.get(i).save(outputFilePath);
            }

            for (PDDocument page : pages) {
                page.close();
            }
            document.close();

            System.out.println("PDF split successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
