package XML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Pricing")
public class Pricing 
{
	@XStreamAsAttribute
	public String organizationName;
	
	@XStreamAsAttribute
	public double retailPrice;
	
	@XStreamAsAttribute
	public double discountedPrice;
	
	@XStreamAsAttribute
	public double wholesalePrice;
	
	public Pricing() { }
	
	public Pricing(String organizationName, double retailPrice, double discountedPrice, double wholesalePrice)
	{
		this.organizationName = organizationName;
		this.retailPrice = retailPrice;
		this.discountedPrice = discountedPrice;
		this.wholesalePrice = wholesalePrice;
	}
}
