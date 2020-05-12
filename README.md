# Multi-Module Maven Project: Cache POC
* This project demonstrates two approaches for integration of Cache in Spring boot Application : 
	- * Spring Default cache (ConcurrentMapCacheManager).
	- * Ehcache
	
## Approach selection
- use * mvn clean install -Pspringcache * to use approach 1
- use * mvn clean install -Pehcache * to use approach 2

## Endpoints

### 1. Populate Cache
- Request Type: POST
- URL: http://localhost:9889/cacheapp/v1/cache/rates
- JSON Request: 
	[
		{
			"INR-AUS": {
				"sourceCurrency": "INR",
				"destinationCurrency": "AUS",
				"midRate": "89",
				"buyRate": "90",
				"sellRate": "91",
				"dateTime": "Fri May 06 2020"
			},
			"INR-AMC": {
				"sourceCurrency": "INR",
				"destinationCurrency": "AMC",
				"midRate": "72",
				"buyRate": "73",
				"sellRate": "74",
				"dateTime": "Fri May 06 2020"
			},
			"INR-USD": {
				"sourceCurrency": "INR",
				"destinationCurrency": "USD",
				"midRate": "86",
				"buyRate": "87",
				"sellRate": "88",
				"dateTime": "Fri May 06 2020"
			},
			"INR-ACC": {
				"sourceCurrency": "INR",
				"destinationCurrency": "ACC",
				"midRate": "61",
				"buyRate": "62",
				"sellRate": "63",
				"dateTime": "Fri May 06 2020"
			},
			"INR-FRT": {
				"sourceCurrency": "INR",
				"destinationCurrency": "FRT",
				"midRate": "44",
				"buyRate": "45",
				"sellRate": "46",
				"dateTime": "Fri May 06 2020"
			},
			"INR-CUK": {
				"sourceCurrency": "INR",
				"destinationCurrency": "CUK",
				"midRate": "55",
				"buyRate": "56",
				"sellRate": "57",
				"dateTime": "Fri May 06 2020"
			},
			"INR-HUN": {
				"sourceCurrency": "INR",
				"destinationCurrency": "HUN",
				"midRate": "33",
				"buyRate": "39",
				"sellRate": "35",
				"dateTime": "Fri May 06 2020"
			}
		}
	]
	
### 2. Check Cache Population
- Request Type : GET
- URL: http://localhost:9889/cacheapp/v1/cache/rates

### 3. Update Cache
- Request Type : POST
- URL: http://localhost:9889/cacheapp/v1/cache/rate
- JSON Request: 
	[
		{
			"sourceCurrency": "INR",
			"destinationCurrency": "AUB",
			"midRate": "99",
			"buyRate": "94",
			"sellRate": "91",
			"dateTime": "Fri May 06 2020"
		}
		
	]

### 3. Cache Lookup
- Request Type: GET
- URL: http://localhost:9889/cacheapp/v1/cache/rate
- Query Params: sourceCur, desCur
- Sample : http://localhost:9889/cacheapp/v1/cache/rate?sourceCur=INR&desCur=USD

### 4. Clear Cache
- Request Type: POST
- URL: http://localhost:9889/cacheapp/v1/cache/clear
