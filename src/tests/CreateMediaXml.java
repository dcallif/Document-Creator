package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;

import XML.Media;
import XML.Page;
import XML.Tag;
import XML.Translation;
import au.com.bytecode.opencsv.CSVReader;

public class CreateMediaXml 
{

	public static void main(String[] args) 
	{
		CreateMediaXml createXml = new CreateMediaXml();
		try 
		{
			createXml.createXml("/Users/dcallif/Downloads/Test/PDFs/2010 3016XL Parts Book/Media/2010 3016XL Parts Book.csv", "/Users/dcallif/Downloads/Test/PDFs/2010 3016XL Parts Book/Media/");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void createXml(String fileName, String saveLocation) throws FileNotFoundException
	{
		//Added to remove non part pages from Media XML
		String pattern1 = ".*START OF SECTION.*";
		String pattern2 = ".*NOTE TO PRINTER.*";
		String pattern3 = ".*Table of Contents.*";
		String pattern4 = ".*Recommended Parts.*";
		String pattern5 = ".*Parts Index.*";
		Pattern regex1 = Pattern.compile(pattern1);
		Pattern regex2 = Pattern.compile(pattern2);
		Pattern regex3 = Pattern.compile(pattern3);
		Pattern regex4 = Pattern.compile(pattern4);
		Pattern regex5 = Pattern.compile(pattern5);
		
		CSVReader reader = new CSVReader( new FileReader( fileName ) );
		
		String[] row;
		
		String mediaTranslation = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf('.'));
		
		Media media = new Media();
		media.tenantKey = "PLOEGEROXBO";
		media.identifier = mediaTranslation.replace(" ", "-");
		media.mediaType = "Book";
		media.translations.add( new Translation("en_US", mediaTranslation) );
		
		XStream xstream = new XStream();
		try 
		{
			while( (row = reader.readNext()) != null )
			{
				if( !(StringUtils.isBlank(row[0]) ) )
				{
					if( !(regex1.matcher(row[0]).matches() ) && !(regex2.matcher(row[0]).matches() ) && !( regex3.matcher(row[0]).matches() ) && !( regex4.matcher(row[0]).matches() ) && !( regex5.matcher(row[0]).matches() ) )
					{
						Page page = new Page();
						page.xmlns = null;
						page.pageFile = row[0].substring(0, row[0].lastIndexOf(".") ) + ".svg";
						page.translations.add( new Translation( "en_US", row[0].substring(0, row[0].lastIndexOf(".") ) ) );
						if( !(StringUtils.isBlank( row[2] ) ) )
						{
							page.tags.add( new Tag("AssemblyNumber", row[2], null, null, null, null) );
						}
						
						media.pages.add( page );
					}
				}

				xstream.autodetectAnnotations(true);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		File file = new File( fileName + ".xml" );
		if( !( file.exists() ) )
		{
			OutputStream os = new FileOutputStream( file, true);
			OutputStreamWriter writer = new OutputStreamWriter(os, Charset.forName("UTF-8"));
			try 
			{
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				writer.write(xstream.toXML(media));
				writer.close();
				os.flush();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
