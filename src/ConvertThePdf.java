import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import PageXml.CreatePageXml;
import tests.CreateMediaXml;
import zip.archiver.ZipDirectory;
/**
 * 
 * @author dcallif
 * This class is used to take the output from running ExtractHeaderAndPartsList.jar 
 * and turn it into a Media xml and individual page xmls/images in separated directories
 */
public class ConvertThePdf 
{

	public void convertPdf(String pdfLocation, String exportPdfDataLocation, String pageFileLocation, String pageSaveLocation, String mediaFileLocation, 
			String mediaSaveLocation, String directoryToZip, String saveToLocation) throws IOException
	{
		long startTime = System.nanoTime();

		// Creates all source data to use for Page/Media creation
		ExtractHeaderAndPartsList extractPdfData = new ExtractHeaderAndPartsList();
		extractPdfData.extractPdfText(pdfLocation, exportPdfDataLocation);

		File directoryToZip1 = new File( directoryToZip );

		// Creates Media XML and Page XML's
		// Pages are separated into individual directories for each Page XML
		ConvertThePdf convert = new ConvertThePdf();
		convert.createPages(pageFileLocation, pageSaveLocation);
		convert.createMedia(mediaFileLocation, mediaSaveLocation);

		// Zip contents of Page XML directories into PLZ's
		convert.zipPages(directoryToZip1, saveToLocation);

		long endTime = System.nanoTime();
		long duration = ( endTime - startTime ) / 1000000;
		System.out.println("Total time to convert PDF: " + duration + "ms");
		System.out.println("Or: " + ( duration / 1000 ) + "s");
	}

	public static void main(String[] args) throws IOException 
	{
		long startTime = System.nanoTime();

		String pdfLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/PDF/2009 316XL Parts Book.pdf";
		String exportPdfDataLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/";

		// Creates all source data to use for Page/Media creation
//		ExtractHeaderAndPartsList extractPdfData = new ExtractHeaderAndPartsList();
//		extractPdfData.extractPdfText(pdfLocation, exportPdfDataLocation);

		String pageFileLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/";
		String pageSaveLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/Pages/";

		String mediaFileLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/Media/2009 316XL Parts Book.csv";
		String mediaSaveLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/Media/";

		File directoryToZip = new File( "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/Pages/" );
		String saveToLocation = "/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/PLZs/";

		// Creates Media XML and Page XML's
		// Pages are separated into individual directories for each Page XML
		ConvertThePdf convert = new ConvertThePdf();
		convert.createPages(pageFileLocation, pageSaveLocation);
		convert.createMedia(mediaFileLocation, mediaSaveLocation);

		// Zip contents of Page XML directories into PLZ's
		convert.zipPages(directoryToZip, saveToLocation);

		long endTime = System.nanoTime();
		long duration = ( endTime - startTime ) / 1000000;
		System.out.println("Total time to convert PDF: " + duration + "ms");
		System.out.println("Or: " + ( duration / 1000 ) + "s");
	}

	// Create all Page Xml's
	public void createPages(String pageFileLocation, String pageSaveLocation)
	{
		CreatePageXml createPages = new CreatePageXml();
		try
		{
			createPages.createPageXmls( pageFileLocation, pageSaveLocation );
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}

	// Creates the Media Xml
	public void createMedia(String mediaFileLocation, String mediaSaveLocation)
	{
		CreateMediaXml createXml = new CreateMediaXml();
		try 
		{
			createXml.createXml( mediaFileLocation, mediaSaveLocation );
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

	// Zip contents of Page XML directories into PLZ's
	public void zipPages(File directoryToZip, String saveToLocation) throws IOException
	{
		ZipDirectory zipPages = new ZipDirectory();
		zipPages.zipPages( directoryToZip, saveToLocation );
	}
}