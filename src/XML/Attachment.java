package XML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Attachment")
public class Attachment 
{
	@XStreamAsAttribute
	public String fileName;
	
	@XStreamAsAttribute
	public boolean global;
	
	@XStreamAsAttribute
	public boolean publicBelowOrg;
	
	@XStreamAsAttribute
	public String userName;
	
	@XStreamAsAttribute
	public String comment;
	
	public Attachment() { }
	
	public Attachment(String fileName, boolean global, boolean publicBelowOrg, String userName, String comment)
	{
		this.fileName = fileName;
		this.global = global;
		this.publicBelowOrg = publicBelowOrg;
		this.userName = userName;
		this.comment = comment;
	}
}