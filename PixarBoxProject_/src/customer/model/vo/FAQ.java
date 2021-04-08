package customer.model.vo;

import java.io.Serializable;

public class FAQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String faqCode;
	private String faqType;
	private String faqTitle;
	private String faqContent;
	
	public FAQ() {
		super();
	}
	public FAQ(String faqCode, String faqType, String faqTitle, String faqContent) {
		super();
		this.faqCode = faqCode;
		this.faqType = faqType;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}
	
	public String getFaqCode() {
		return faqCode;
	}
	public void setFaqCode(String faqCode) {
		this.faqCode = faqCode;
	}
	public String getFaqType() {
		return faqType;
	}
	public void setFaqType(String faqType) {
		this.faqType = faqType;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "faq [faqCode=" + faqCode + ", faqType=" + faqType + ", faqTitle=" + faqTitle + ", faqContent="
				+ faqContent + "]";
	}
	
	
}
