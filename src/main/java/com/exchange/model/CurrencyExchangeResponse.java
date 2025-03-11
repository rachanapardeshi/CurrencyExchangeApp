package com.exchange.model;

import java.util.Map;

public class CurrencyExchangeResponse {

	private String result;
	private String base_code;
	private Map<String, Double> rates;
	public String provider;
	public String documentation;
	public String terms_of_use;
	public int time_last_update_unix;
	public String time_last_update_utc;
	public int time_next_update_unix;
	public String time_next_update_utc;
	public int time_eol_unix;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBase_code() {
		return base_code;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getTerms_of_use() {
		return terms_of_use;
	}

	public void setTerms_of_use(String terms_of_use) {
		this.terms_of_use = terms_of_use;
	}

	public int getTime_last_update_unix() {
		return time_last_update_unix;
	}

	public void setTime_last_update_unix(int time_last_update_unix) {
		this.time_last_update_unix = time_last_update_unix;
	}

	public String getTime_last_update_utc() {
		return time_last_update_utc;
	}

	public void setTime_last_update_utc(String time_last_update_utc) {
		this.time_last_update_utc = time_last_update_utc;
	}

	public int getTime_next_update_unix() {
		return time_next_update_unix;
	}

	public void setTime_next_update_unix(int time_next_update_unix) {
		this.time_next_update_unix = time_next_update_unix;
	}

	public String getTime_next_update_utc() {
		return time_next_update_utc;
	}

	public void setTime_next_update_utc(String time_next_update_utc) {
		this.time_next_update_utc = time_next_update_utc;
	}

	public int getTime_eol_unix() {
		return time_eol_unix;
	}

	public void setTime_eol_unix(int time_eol_unix) {
		this.time_eol_unix = time_eol_unix;
	}

	public void setBase_code(String base_code) {
		this.base_code = base_code;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

}
