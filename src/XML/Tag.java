package XML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Tag")
public class Tag 
{
	
	@XStreamAsAttribute
	public String name;
	
	@XStreamAsAttribute
	public String value;
	
	@XStreamAsAttribute
	public String lowerBoundValue;
	
	@XStreamAsAttribute
	public String upperBoundValue;
	
	@XStreamAsAttribute
	public String prefixValue;
	
	@XStreamAsAttribute
	public String suffixValue;
	
	@XStreamAsAttribute
	public String isLocalToPage;
	
	public Tag () { }
	
	public Tag (String name, String value, String lowerBoundValue, String upperBoundValue, String prefixValue, String suffixValue) 
	{
			this.name = name;
			this.value = value;
			this.lowerBoundValue = lowerBoundValue;
			this.upperBoundValue = upperBoundValue;
			this.prefixValue = prefixValue;
			this.suffixValue = suffixValue;
	}
}
