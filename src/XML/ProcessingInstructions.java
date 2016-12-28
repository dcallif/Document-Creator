package XML;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class ProcessingInstructions 
{
	
	static final String ADD = "ADD";
	static final String UPDATE = "UPDATE";
    static final String REPLACE = "REPLACE";

	@XStreamAsAttribute
	public String updateOrReplacePartTags;
	
	@XStreamAsAttribute
	public String updateOrReplacePageTags;
	
	@XStreamAsAttribute
	public String updateOrReplaceMediaTags;
	
	@XStreamAsAttribute
	public String addOrReplaceAttachments;
	
	@XStreamAsAttribute
	public String updateOrReplaceAssemblyParts;
	
	public ProcessingInstructions (boolean replacePartTags, boolean replacePageTags, boolean replaceMediaTags, boolean replaceAttachments, boolean replaceAssemblyParts) 
	{
		this.updateOrReplacePartTags = replacePartTags ? ProcessingInstructions.REPLACE : ProcessingInstructions.UPDATE;
        this.updateOrReplacePageTags = replacePageTags ? ProcessingInstructions.REPLACE : ProcessingInstructions.UPDATE;
        this.updateOrReplaceMediaTags = replaceMediaTags ? ProcessingInstructions.REPLACE : ProcessingInstructions.UPDATE;
        this.addOrReplaceAttachments = replaceAttachments ? ProcessingInstructions.ADD : ProcessingInstructions.UPDATE;
        this.updateOrReplaceAssemblyParts = replaceAssemblyParts ? ProcessingInstructions.REPLACE : ProcessingInstructions.UPDATE;
	}

}
