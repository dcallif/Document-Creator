package zip.archiver;

import java.io.File;

public class CreatePlz 
{
	public static void main(String[] args) 
	{
		ZipFiles zipFiles = new ZipFiles();
		zipFiles.setSaveToLocation("/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/PLZs/");
		zipFiles.setSourceDirectory("/Users/dcallif/Downloads/Test/PDFs/2009 316XL Parts Book/Pages/");
		zipFiles.setZipExtension(".plz");
		
		zipFiles.ZipAllDirectories(zipFiles.getSourceDirectory(), zipFiles.getSaveToLocation(), zipFiles.getZipExtension());
	}
}