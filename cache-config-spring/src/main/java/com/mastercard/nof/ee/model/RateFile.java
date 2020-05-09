package com.mastercard.nof.ee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(content = Include.NON_NULL)
@JsonPropertyOrder({
	"sourceCurrency", 
	"destinationCurrency",
	"midRate",
	"buyRate",
	"sellRate",
	"dateTime"
})
public class RateFile {
	
	@JsonProperty("sourceCurrency")
	private String sourceCurrency;
	
	@JsonProperty("destinationCurrency")
	private String destinationCurrency;
	
	@JsonProperty("midRate")
	private String midRate;
	
	@JsonProperty("buyRate")
	private String buyRate;
	
	@JsonProperty("sellRate")
	private String sellRate;
	
	@JsonProperty("dateTime")
	private String dateTime;
	
	
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public String getDestinationCurrency() {
		return destinationCurrency;
	}
	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}
	public String getMidRate() {
		return midRate;
	}
	public void setMidRate(String midRate) {
		this.midRate = midRate;
	}
	public String getBuyRate() {
		return buyRate;
	}
	public void setBuyRate(String buyRate) {
		this.buyRate = buyRate;
	}
	public String getSellRate() {
		return sellRate;
	}
	public void setSellRate(String sellRate) {
		this.sellRate = sellRate;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
