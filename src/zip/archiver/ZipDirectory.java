package zip.archiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory {

	public static void main(String[] args) throws IOException 
	{
		File directoryToZip = new File("/Users/dcallif/Downloads/Test/Pages/");
		String saveToLocation = "/Users/dcallif/Downloads/Test/PLZs/";
		for( File file : directoryToZip.listFiles() )
		{
			if( file.isDirectory() )
			{
				List<File> fileList = new ArrayList<File>();
				System.out.println("---Getting references to all files in: " + file.getCanonicalPath());
				getAllFiles(file, fileList);
				System.out.println("---Creating zip file");
				writeZipFile(file, saveToLocation, fileList);
				System.out.println("---Done");
			}
		}
		// Zip a single Directory
//		File directoryToZip = new File("/Users/dcallif/Downloads/Test/Pages/211_OTR WEAR PAD ASSY");
//
//		List<File> fileList = new ArrayList<File>();
//		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
//		getAllFiles(directoryToZip, fileList);
//		System.out.println("---Creating zip file");
//		writeZipFile(directoryToZip, fileList);
//		System.out.println("---Done");
	}
	
	public void zipPages(File directoryToZip, String saveToLocation) throws IOException
	{
		for( File file : directoryToZip.listFiles() )
		{
			if( file.isDirectory() )
			{
				List<File> fileList = new ArrayList<File>();
				System.out.println("---Getting references to all files in: " + file.getCanonicalPath());
				getAllFiles(file, fileList);
				System.out.println("---Creating zip file");
				writeZipFile(file, saveToLocation, fileList);
				System.out.println("---Done");
			}
		}
	}

	public static void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeZipFile(File directoryToZip, String saveToLocation, List<File> fileList) {

		try {
			FileOutputStream fos = new FileOutputStream(saveToLocation + directoryToZip.getName() + ".plz");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
	IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

}
