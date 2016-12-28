package PageXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;

import XML.Page;
import XML.Part;
import XML.Tag;
import XML.Translation;
import au.com.bytecode.opencsv.CSVReader;

public class CreatePageXml 
{
	static ArrayList<String> sourceFileNames = new ArrayList<String>();
	List<String> results = new ArrayList<String>();

	public void createPageXmls(String fileLocation, String saveLocation)
	{
		File folder = new File(fileLocation);

		CreatePageXml createPageXml = new CreatePageXml();

		createPageXml.listFilesForFolder(folder);

		for ( int i = 0; i < sourceFileNames.size(); i++)
		{
			createPageXml.parseUsingOpenCSV(fileLocation + sourceFileNames.get(i), saveLocation + sourceFileNames.get(i));
		}
	}

	private void listFilesForFolder(final File folder) 
	{
		for (final File fileEntry : folder.listFiles()) 
		{
			if (!(fileEntry.isDirectory()) && getFileExtension(fileEntry).equalsIgnoreCase("csv"))
			{
				System.out.println("File found: " + fileEntry.getName());
				sourceFileNames.add(fileEntry.getName());
			} 
		}
	}

	private String getFileExtension(File file) {
		String name = file.getName();
		try 
		{
			return name.substring(name.lastIndexOf(".") + 1);
		} 
		catch (Exception e) 
		{
			return "Could not get file extension...";
		}
	}

	private void moveImage(String imageLocation, String saveLocation)
	{
		File image = new File(imageLocation);

		File saveToLocation = new File(saveLocation);
		
		String imageFileName = saveLocation.substring( saveLocation.lastIndexOf( "/" ), saveLocation.length() );

		if( image.renameTo( new File( saveToLocation + "/" + imageFileName + ".svg" ) ) )
		{
			System.out.println("File is moved successfully!");
		}
		else
		{
			System.out.println("File is failed to move!");
		}
	}

	// Opens CSV file and parses data
	private void parseUsingOpenCSV(String filename, String saveLocation)
	{
		// Try to load the CSV file
		try 
		{
			CSVReader reader = new CSVReader(new FileReader(filename));

			String[] row;


			String fileName = "";
			String locale = "";
			String translationName = "";
			String translationDescription = "";
			String tenantKey = "";
			String supplierKey = "";
			String pageTranslation = saveLocation.substring(0, saveLocation.lastIndexOf('.'));
			File directory;

			tenantKey = "PLOEGEROXBO";
			locale = "en_US";
			translationName = pageTranslation.substring(pageTranslation.lastIndexOf("/") + 1, pageTranslation.length());
			supplierKey = "OXBO";
			fileName = translationName.substring(0, translationName.lastIndexOf("_"));

			// Creates new page object to serialize CSV data to
			Page page = new Page(tenantKey, "");
			page.pageFile = translationName + ".svg";
			page.tenantKey = tenantKey;
			page.translations.add(new Translation(translationDescription, locale, translationName));
			XStream xstream = new XStream();
			int x = 1;

			while ((row = reader.readNext()) != null && row.length >= 3)
			{
				// Get translation(s) for parts
				List<Translation> translation = new ArrayList<Translation>();
				
				// Checks to ensure there are 3 params for each part
				if( row.length > 3 )
				{
					translation.add(new Translation("", locale, row[3]));
				}

				// Check to see if part number and name are present...can't write part without both pieces of data.
				if(!(row[1].isEmpty() || row[2].isEmpty()))
				{
					page.part.add(new Part(row[0], row[1], row[2], supplierKey, x, "EA", translation));
				}

				xstream.autodetectAnnotations(true);

				x++;
			}

			// Create XML
			try
			{
				directory = new File(saveLocation.substring(0, saveLocation.lastIndexOf('.')));

				// Separates each xml file in it's own dir
				if( !(directory.exists()) )
				{
					directory.mkdir();
				}
					String saveToLocation = saveLocation.substring(0, saveLocation.lastIndexOf('.'));
					
					File file = new File(saveToLocation + "/" + directory.getName() + ".xml");
					if( !(file.exists() ) )
					{
						OutputStream os = new FileOutputStream(file, true);
						OutputStreamWriter writer = new OutputStreamWriter(os, Charset.forName("UTF-8"));
						writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
						writer.write(xstream.toXML(page));
						writer.close();
						os.flush();
						
						String image = fileName = translationName.substring(0, translationName.lastIndexOf("_"));
						
						Integer image2 = Integer.parseInt(image);
						image2 = image2 - 1;
						
						String imagesLocation =  saveToLocation.substring( 0, saveToLocation.lastIndexOf( "/" ) );
						String finalImagesLocation = imagesLocation.substring( 0, imagesLocation.lastIndexOf( "/" ) );
						
						moveImage( finalImagesLocation + "/" + image2.toString() + ".svg", saveToLocation);
					}
					
			} 
			catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}

			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		CreatePageXml createXmls = new CreatePageXml();
		createXmls.createPageXmls("/Users/dcallif/Downloads/Test/PDFs/2009 316XL PARTS BOOK/", "/Users/dcallif/Downloads/Test/PDFs/2009 316XL PARTS BOOK/Pages/");
	}
}
