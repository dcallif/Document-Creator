package zip.archiver;

import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

/*
 * Used to zip a single directory or every directory within a specified parent directory
 */
public class ZipFiles 
{
	private File sourceDirectory;
	private File updatedDir;
	private String zipExtension;

	// Zip all files in sourceDirectory
	public void zipDirectory(File file, File saveToLocation, String zipExtension)
	{
		if( sourceDirectory != null && updatedDir != null)
		{
			try
			{
				File newFile = new File(saveToLocation.getAbsolutePath() + File.separator + file.getName() + zipExtension);

				ZipFile zipFile = new ZipFile(newFile);

				for (File f : sourceDirectory.listFiles())
				{
					// Look for all directories in sourceDirectory folder
					if(f.isDirectory())
					{
						for(File file1 : f.listFiles())
						{
							zipFile.addFile(file1, new ZipParameters());
						}
					}
				}
			}

			catch (Exception e)
			{
				e.printStackTrace();
			} 
		}
		else
		{
			System.out.println("Please set sourceDirectory and updatedDir...");
		}
	}

	// Zip all child directories into individual zip files
	public void ZipAllDirectories(File sourceDir, File saveToLocation, String zipExtension)
	{
		for (File file : sourceDir.listFiles())
		{
			if (file.isDirectory())
			{
				zipDirectory(file, saveToLocation, zipExtension);
			}
		}
	}

	/*
	 * Getter and Setter methods
	 */
	public void setSourceDirectory(String sourceDirectory)
	{
		this.sourceDirectory = new File(sourceDirectory);
	}

	public File getSourceDirectory()
	{
		return sourceDirectory;
	}

	public void setSaveToLocation(String saveToLocation)
	{
		this.updatedDir = new File(saveToLocation);
	}

	public File getSaveToLocation()
	{
		return updatedDir;
	}

	public void setZipExtension(String extension)
	{
		this.zipExtension = extension;
	}

	public String getZipExtension()
	{
		return zipExtension;
	}
}
