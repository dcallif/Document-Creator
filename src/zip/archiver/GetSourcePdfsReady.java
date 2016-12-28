package zip.archiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetSourcePdfsReady 
{

	String testPdf = "/Users/dcallif/Downloads/Test/2006 920 Parts Book.pdf";

	public void moveFile( String sourceFile, String newFile )
	{
		InputStream inStream = null;
		OutputStream outStream = null;

		try
		{

			File afile =new File( sourceFile );
			File bfile =new File( newFile );

			inStream = new FileInputStream( afile );
			outStream = new FileOutputStream( bfile );

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes 
			while( ( length = inStream.read( buffer ) ) > 0 )
			{
				outStream.write( buffer, 0, length );
			}

			inStream.close();
			outStream.close();

			//delete the original file
			afile.delete();

			System.out.println( "File is copied successful!" );

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		File sourceDir = new File("/Users/dcallif/Downloads/LyndenAdditional Category 2 PDFs/");
		try
		{
			for( File file : sourceDir.listFiles() )
			{
				String fileName = file.getName().substring( file.getName().lastIndexOf("/") + 1, file.getName().lastIndexOf(".") );

				System.out.println(fileName);

				File newDir = new File( "/Users/dcallif/Downloads/LyndenAdditional Category 2 PDFs/PDFs/" + fileName );
				
				File mediaDir = new File(newDir.getAbsolutePath() + "/Media");
				File pageDir = new File(newDir.getAbsolutePath() + "/Pages");
				File plzDir = new File(newDir.getAbsolutePath() + "/PLZs");
				File pdfDir = new File(newDir.getAbsolutePath() + "/PDF");

				newDir.mkdir();
				mediaDir.mkdir();
				pageDir.mkdir();
				plzDir.mkdir();
				pdfDir.mkdir();

				if( file.renameTo( new File( newDir + "/PDF/" + file.getName() ) ) )
				{
					System.out.println("File moved successfully!");
				}

				else
				{
					System.out.println("File failed to move!");
				}
			}
			
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}