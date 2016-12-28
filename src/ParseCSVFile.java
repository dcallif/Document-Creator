import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

import com.thoughtworks.xstream.XStream;

import au.com.bytecode.opencsv.CSVReader;


import XML.Media;
import XML.Tag;
import XML.Translation;

/**
 * This class parses a CSV file and creates a Media of type 'Document, Image, or Video' based on the row data. 
 * Each row is a separate media XML and each column can only appear once except for tags. 
 * Tags can appear more than once if there is a valid tag name/value pair.
 */
public class ParseCSVFile 
{

	public static void main(String[] args) 
	{

		Scanner user_input = new Scanner(System.in);

		//		String fileLocation;
		String fileLocation = "/Users/dcallif/Downloads/Oxbo - Lynden Doc Creator - 072016.csv";
		//		System.out.print("Path to CSV file?");
		//		fileLocation = user_input.next();
		//		new File(fileLocation).getAbsolutePath();

		//		String saveLocation;
		String saveLocation = "/Users/dcallif/Downloads/Lynden Static Media/";
		//		System.out.print("Enter save Location");
		//		saveLocation = user_input.next();
		//		new File(saveLocation).getAbsolutePath();

		user_input.close();

		ParseCSVFile parseCSVFile = new ParseCSVFile();

		System.out.println("Starting to parse CSV file using opencsv");
		parseCSVFile.parseUsingOpenCSV(fileLocation, saveLocation);
	}

	//Opens CSV file and parses data
	private void parseUsingOpenCSV(String filename, String saveLocation) 
	{

		//Try's to load the CSV file
		try 
		{
			CSVReader reader = new CSVReader(new FileReader(filename));

			String[] header = reader.readNext();
			String[] row;

			String identifier = "";
			String fileName = "";
			String mediaType = "";
			String locale = "";
			String translationName = "";
			String translationDescription = "";
			String tenantKey = "";
			List<Tag> tags = new ArrayList<Tag>();

			while ((row = reader.readNext()) != null) 
			{

				tags = new ArrayList<Tag>();

				//Checks that each case below exists in CSV file header
				for(int i = 0; i < row.length; i++) 
				{
					switch (header[i].toLowerCase()) 
					{
					case "filename":
						fileName = row[i];
						break;
					case "identifier":
						identifier = row[i];
						break;
					case "media type":
						mediaType = row[i];
						break;
					case "tenant key":
						tenantKey = row[i];
						break;
					case "locale":
						locale = row[i];
						break;
					case "name":
						translationName = row[i];
						break;
					case "description":
						translationDescription = row[i];
						break;
					case "tag name":
					{
						String name = row[i].trim();
						String value = row[i+1].trim();
						String lowerBound = row[i+2].trim();
						String upperBound = row[i+3].trim();

						if(!name.equals("") && !value.equals(""))
						{
							tags.add(new Tag(name, value, null, null, null, null));
						}
						//handles tag ranges
						if( !(name.equals("")) && value.equals("") && (!(lowerBound.equals("")) | !(upperBound.equals(""))))
						{
							tags.add(new Tag(row[i], row[i+1], row[i+2], row[i+3], row[i+4], row[i+5]));
						}
						else
						{
							continue;
						}
						break;
					}
					}
				}

				//Creates new media object to serialize CSV data to
				Media m = new Media(identifier, tenantKey, mediaType);
				m.fileName = fileName;
				m.translations.add(new Translation(translationDescription, locale, translationName));
				m.tags = tags;

				XStream xstream = new XStream();
				xstream.autodetectAnnotations(true);

				//Creates media XML
				try 
				{
					//Create a random number
					Random rand = new Random();
					int randomNum = rand.nextInt((50 - 1) + 1) + 1;
					//Creates media XML
					OutputStream os = new FileOutputStream(saveLocation + identifier + randomNum + ".xml");
					OutputStreamWriter writer = new OutputStreamWriter(os, Charset.forName("UTF-8"));
					writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
					writer.write(xstream.toXML(m));
					writer.close();
					os.flush();
					System.out.println("XML File Complete: " + identifier);
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}	System.out.println("");
			}

			reader.close();
			System.out.println("Done.");

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("");
		}
	}
}