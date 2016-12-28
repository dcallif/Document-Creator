package tests;

import zip.archiver.ZipFiles;

public class testZip 
{

	public static void main(String[] args) 
	{
		ZipFiles zipFiles = new ZipFiles();
		
		zipFiles.setSourceDirectory("/Users/dcallif/Downloads/Test/TestArchive/");
		zipFiles.setSaveToLocation("/Users/dcallif/Downloads/Test/");
		zipFiles.setZipExtension(".plz");
		
		zipFiles.zipDirectory(zipFiles.getSourceDirectory(), zipFiles.getSaveToLocation(), zipFiles.getZipExtension());
	}
}