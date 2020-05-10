# Multi-Module Maven Project: Cache POC
* This project demonstrates two approaches for integration of Cache in Spring boot Application : 
	- * Spring Default cache (ConcurrentMapCacheManager).
	- * Ehcache
	
## Approach selection
- use * mvn package -Pspringcache * to use approach 1
- use * mvn package -Pehcache * to use approach 2

## Endpoints

### 1. Populate Cache
- Request Type: POST
- URL: http://localhost:9889/cacheapp/v1/cache/rates
- JSON Request: 
	{ "rates" : [
		{
		"sourceCurrency" : "INR",
		"destinationCurrency" : "ABB",
		"midRate" : "90",
		"buyRate" : "97", 
		"sellRate" : "88", 
		"dateTime" : "Fri May 06 2020"
		},
		
		{
		"sourceCurrency" : "INR",
		"destinationCurrency" : "USD",
		"midRate" : "56",
		"buyRate" : "57", 
		"sellRate" : "58", 
		"dateTime" : "Fri May 06 2020"
		}, 
		
		{
		"sourceCurrency" : "INR",
		"destinationCurrency" : "GBP",
		"midRate" : "101",
		"buyRate" : "102", 
		"sellRate" : "103", 
		"dateTime" : "Fri May 06 2020"
		}]

	}
	
### 2. Check Cache Population
- Request Type : GET
- URL: http://localhost:9889/cacheapp/v1/cache/rates

### 3. Cache Lookup
- Request Type: GET
- URL: http://localhost:9889/cacheapp/v1/cache/rate
- Query Params: sourceCur, desCur
- Sample : http://localhost:9889/cacheapp/v1/cache/rate?sourceCur=INR&desCur=USD

### 4. Clear Cache
- Request Type: POST
- URL: http://localhost:9889/cacheapp/v1/cache/clear
