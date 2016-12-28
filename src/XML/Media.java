package XML;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/*
 * This class defines attributes allowed in a media xml. Currently, only media of type 'Document' are allowed
 */

@XStreamAlias("Media")
@XmlRootElement(name="Media")
public class Media 
{
	static final String BOOK = "Book";
	static final String VIDEO = "Video";
	static final String DOCUMENT = "Document";
	static final String IMAGE = "Image";
	
	@XStreamAsAttribute
	public String xmlns = "http://digabit.com/documoto/media/1.2";
	
	@XStreamAsAttribute
	public String tenantKey;
	
	@XStreamAsAttribute
	public String identifier;
	
	@XStreamAsAttribute
	public String mediaType;
	
	@XStreamAsAttribute
	public String fileName;
	
	@XStreamAsAttribute
	public String externalUri;
	
	@XStreamAsAttribute
	public String openInNewWindow;
	
	@XStreamImplicit(itemFieldName="Translation")
	public List<Translation> translations = new ArrayList<Translation>();
	
	@XStreamImplicit(itemFieldName="Tag")
	public List<Tag> tags = new ArrayList<Tag>();
	
	@XStreamImplicit(itemFieldName="Page")
	public List<Page> pages = new ArrayList<Page>();
	
	@XStreamImplicit(itemFieldName="Chapter")
	public List<Chapter> chapters = new ArrayList<Chapter>();
	
	public Media () { }
	
	public Media (String identifier, String tenantKey, String mediaType) 
	{
		this.identifier = identifier;
		this.tenantKey = tenantKey;
		this.mediaType = mediaType;		
	}
	
	@Override 
	public String toString()
	{
		String mediaString = "";
		
		mediaString += identifier + "\n";
		mediaString += tenantKey + "\n";
		mediaString += mediaType + "\n";
		mediaString += fileName + "\n\n";
		
		mediaString += "Translations: \n";
		for(int i = 0; i < translations.size(); i++)
		{
			mediaString += "  " + translations.get(i).locale + " " + translations.get(i).name + " " + translations.get(i).description + "\n";
		}
		
		mediaString += "\n";
		
		mediaString += "Tags \n";
		for(int j = 0; j < tags.size(); j++)
		{
			mediaString += "  " + tags.get(j).name + " " + tags.get(j).value + "\n";
		}
		
		return mediaString;
	}

}
