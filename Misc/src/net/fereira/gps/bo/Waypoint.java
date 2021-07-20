package net.fereira.gps.bo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.opencsv.bean.CsvBindByPosition;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("wpt")
public class Waypoint {
	
	@XStreamAsAttribute
	@CsvBindByPosition(position = 0)
    private String lat;
	
	@XStreamAsAttribute
	@CsvBindByPosition(position = 1)
    private String lon;
	
	@CsvBindByPosition(position = 2)	
	private String time;
	@CsvBindByPosition(position = 3)
	private String name;
	@CsvBindByPosition(position = 4)
	private String desc;
	@CsvBindByPosition(position = 5)
	private String url;
	@CsvBindByPosition(position = 6)
	private String urlname;
	@CsvBindByPosition(position = 7)
	private String sym;
	@CsvBindByPosition(position = 8)
	private String type;
	
	
	
	@XStreamAlias("groundspeak:cache")
    private Groundspeak groundspeak;
    
	public Waypoint() {
		// TODO Auto-generated constructor stub
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String l) {
		this.lon = l;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlname() {
		return urlname;
	}

	public void setUrlname(String urlname) {
		this.urlname = urlname;
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Groundspeak getGroundspeak() {
		return groundspeak;
	}

	public void setGroundspeak(Groundspeak groundspeak) {
		this.groundspeak = groundspeak;
	}
	
	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this);
	}

}
