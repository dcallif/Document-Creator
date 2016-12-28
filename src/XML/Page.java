package XML;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Page")
public class Page 
{
	@XStreamAsAttribute
	public String tenantKey;
	
	@XStreamAsAttribute
	public String pageFile;
	
	@XStreamAsAttribute
	public String xmlns = "http://digabit.com/documoto/partslist/1.4";
//	
//	@XStreamAlias("ProcessingInstructions")
//	public ProcessingInstructions processingInstructions = new ProcessingInstructions(false, false, false, false, false);
	
	@XStreamImplicit(itemFieldName="Translation")
	public List<Translation> translations = new ArrayList<Translation>();
	
	@XStreamImplicit(itemFieldName="Tag")
	public List<Tag> tags = new ArrayList<Tag>();
	
	@XStreamImplicit(itemFieldName="Part")
	public List<Part> part = new ArrayList<Part>();
	
	@XStreamImplicit(itemFieldName="PartTranslation")
	public List<Translation> partTranslations = new ArrayList<Translation>();
	
	public Page () { }
	
	public Page (String tenantKey, String pageFile) 
	{
		this.pageFile = pageFile;
		this.tenantKey = tenantKey;
	}
	
	@Override 
	public String toString()
	{
		String pageString = "";
		
		pageString += pageFile + "\n";
		pageString += tenantKey + "\n";
		
		pageString += "Translations: \n";
		for(int i = 0; i < translations.size(); i++)
		{
			pageString += "  " + translations.get(i).locale + " " + translations.get(i).name + " " + translations.get(i).description + "\n";
		}
		
		pageString += "\n";
		
		pageString += "Tags \n";
		for(int j = 0; j < tags.size(); j++)
		{
			pageString += "  " + tags.get(j).name + " " + tags.get(j).value + "\n";
		}
		
		pageString += "Part \n";
		for(int x = 0; x < part.size(); x++)
		{
			pageString += "  " + part.get(x).item + " " + part.get(x).partNumber + " " + part.get(x).name + " " + part.get(x).description + " " + part.get(x).quantity + "\n";
		}
		
		return pageString;
	}
}
