
                    /* The PDF file will Split into a separate single pdf page */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.multipdf.Splitter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) 
    {
        System.out.println("Welcome To My Pdf Splitter.");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the PDF file path: "); //Input File path
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the output directory path: "); //Output Directory path
        String outputDirectoryPath = scanner.nextLine();

        try {
            
            PDDocument document = PDDocument.load(new File(inputFilePath));            
            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(document);
            
            for (int i = 0; i < pages.size(); i++) {
                String outputFilePath = outputDirectoryPath + File.separator + "split_page_" + (i + 1) + ".pdf";
                pages.get(i).save(outputFilePath);
            }

            // Close the PDF documents.
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
