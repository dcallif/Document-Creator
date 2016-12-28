package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateSvg 
{
	public void createSvg( String saveToLocation, String imageFileName, int width, int height )
	{
		//create svg for page
		File svgImage = new File( saveToLocation + imageFileName.substring(0, imageFileName.lastIndexOf( "." ) ) + ".svg" );

		try 
		{
			FileWriter fileWriter = new FileWriter( svgImage );
			PrintWriter printWriter = new PrintWriter( fileWriter );

			printWriter.print( "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:html=\"http://www.w3.org/TR/REC-html40\">" );
			printWriter.print( "\n<image id=\"snapshot\" x=\"0\" y=\"0\" width=\"" + width + "\" height=\"" + height + "\" xlink:href=\"" + imageFileName + "\"/>" );
			printWriter.print( "\n</svg>" );

			fileWriter.flush();
			fileWriter.close();
		} 
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
	}
}
