package XML;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Chapter")
public class Chapter 
{
	@XStreamAsAttribute
	public String pageFile;
	
	@XStreamImplicit(itemFieldName="Translation")
	public List<Translation> translations = new ArrayList<Translation>();
	
	@XStreamImplicit(itemFieldName="Page")
	public List<Page> pages = new ArrayList<Page>();
	
	public Chapter() { }
	
	public Chapter(List<Translation> translations, List<Page> pages)
	{
		this.translations = translations;
		this.pages = pages;
	}
}