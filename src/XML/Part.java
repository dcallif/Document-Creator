package XML;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Part")
public class Part 
{
	@XStreamAsAttribute
	public String partNumber;
	
	@XStreamAsAttribute
	public String supplierKey;
	
	@XStreamAsAttribute
	public String unitOfMeasure;
	
	@XStreamAsAttribute
	public String item;

	@XStreamAsAttribute
	public String quantity;
	
	@XStreamAsAttribute
	public int instanceId;

	@XStreamAsAttribute
	public String name;
	
	@XStreamAsAttribute
	public String description;
	
	@XStreamImplicit(itemFieldName="Translation")
	public List<Translation> translations = new ArrayList<Translation>();
	
	public Part () { }
	
	public Part (String itemNumber, String partNumber, String quantity, String supplierKey, int instanceId, String unitOfMeasure, List<Translation> translations)
	{
		this.item = itemNumber;
		this.partNumber = partNumber;
		this.quantity = quantity;
		this.supplierKey = supplierKey;
		this.instanceId = instanceId;
		this.unitOfMeasure = unitOfMeasure;
		this.translations = translations;
	}
	
	@Override 
	public String toString()
	{
		String partString = "";
		
		partString += item + "\n";
		partString += partNumber + "\n";
		partString += quantity + "\n";
		partString += unitOfMeasure + "\n";
		
		partString += "Translations: \n";
		for(int i = 0; i < translations.size(); i++)
		{
			partString += "  " + translations.get(i).description + " " + translations.get(i).locale + " " + translations.get(i).name + "\n";
		}
		
		partString += "\n";
		
		return partString;
	}
}