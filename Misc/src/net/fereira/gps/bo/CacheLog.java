package net.fereira.gps.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("groundspeak:log")
public class CacheLog {
	
	@XStreamAlias("groundspeak:date")
	private String date;
	
	@XStreamAlias("groundspeak:type")
	private String type;
	
	@XStreamAlias("groundspeak:finder")
	private String finder;
	
	@XStreamAlias("groundspeak:text")
	private String text;

	public CacheLog() {
		// TODO Auto-generated constructor stub
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFinder() {
		return finder;
	}

	public void setFinder(String finder) {
		this.finder = finder;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this);
	}

}
