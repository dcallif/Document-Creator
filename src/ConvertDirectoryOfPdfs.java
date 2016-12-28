import java.io.File;
import java.io.IOException;

public class ConvertDirectoryOfPdfs 
{

	public static void main(String[] args) throws IOException 
	{
		File startingDir = new File( "/Users/dcallif/Downloads/LyndenAdditional Category 2 PDFs/PDFs/" );
		for( File file : startingDir.listFiles() )
		{
			if( file.isDirectory() )
			{
				ConvertThePdf convertPdfs = new ConvertThePdf();
				String pdfLocation = file.getAbsolutePath() + "/PDF/" + file.getName() + ".pdf";
				String exportPdfDataLocation = file.getAbsolutePath() + File.separator;
				String pageFileLocation = file.getAbsolutePath() + File.separator;
				String pageSaveLocation = file.getAbsolutePath() + "/Pages/";
				String mediaFileLocation = file.getAbsolutePath() + "/Media/" + file.getName() + ".csv";
				String mediaSaveLocation = file.getAbsolutePath() + "/Media/";
				String directoryToZip = file.getAbsolutePath() + File.separator + "Pages/";
				String saveToLocation = file.getAbsolutePath() + File.separator + "PLZs/";
				convertPdfs.convertPdf(pdfLocation, exportPdfDataLocation, pageFileLocation, pageSaveLocation, mediaFileLocation, mediaSaveLocation, directoryToZip, saveToLocation);
			}
		}
	}
}