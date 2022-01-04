package GestAlim.control;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.File; 
import java.io.InputStream;
public class PDFgenerator {
	
	//private static String FILE = "c:/temp/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.COURIER, 8,Font.BOLD);
    private static final String FILE__NAME = "/tmp/itext.pdf";
    //private static Font redFont = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL, BaseColor.RED);
    //private static Font subFont = new Font(Font.FontFamily.COURIER, 16,Font.BOLD);
    //private static Font smallBold = new Font(Font.FontFamily.COURIER, 12,Font.BOLD);
	public static void main(String[] args) throws DocumentException, IOException{
		
		String chemin="E:\\DOCUMENTS\\Nouveau dossier\\GestAlim\\src\\pdf\\facture_"+args[0]+".pdf";
		Document document = new Document();
		try {
			if(!document.isOpen()) {
				PdfWriter.getInstance(document, new FileOutputStream(chemin));
	            document.open();
	                            
	            
	            // Creating an Image object        
	            Image image = Image.getInstance("E:\\DOCUMENTS\\Nouveau dossier\\GestAlim\\src\\GestAlim\\view\\icones\\logo.png");            
	            // Adding image to the document
	            //image.setAbsolutePosition(105, 50);
	            document.add(image);           
			      
				document.add(new Paragraph(args[1], catFont));
				
				
			}
			
		}catch(DocumentException de){
			de.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		document.close();
		//document.
		
        Desktop.getDesktop().open((new File(chemin)));
	}
}
