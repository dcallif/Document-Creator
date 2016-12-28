package XML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Translation")
public class Translation 
{
	@XStreamAsAttribute
	public String description;

	@XStreamAsAttribute
	public String locale;

	@XStreamAsAttribute
	public String name;



	public Translation () { }

	public Translation (String locale, String name) 
	{
		this.locale = locale;
		this.name = name;
	}

	public Translation (String description, String locale, String name) 
	{
		this.locale = locale;
		this.name = name;
		this.description = description;
	}
}

