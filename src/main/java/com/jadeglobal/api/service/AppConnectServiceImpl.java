package com.jadeglobal.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.jadeglobal.api.config.RestTemplateConfig;
import com.jadeglobal.api.exception.BadRequestException;
import com.jadeglobal.api.model.AppConnectRequest;
import com.jadeglobal.api.model.AppConnectResponse;

import lombok.extern.slf4j.Slf4j;
/**
 * @author nikhil.lokhande
 * @description: Service for call Salesforce Connect App
 * */
@Slf4j(topic = "APP_CONNECT_SERVICE")
@Service
public class AppConnectServiceImpl implements AppConnectService{
	
	// Inject the Rest template configuration
	@Autowired
	private RestTemplateConfig restConfig;
	@Value("${salesforece.oauthUrl}")
	private String oauthUrl;
	 
	@SuppressWarnings("unchecked")
	@Override
	public AppConnectResponse callSalesforceAPIConnection(AppConnectRequest appConnectRequest) {

		  // Create a multi value map to put necessary attributes for the authentication request
		  MultiValueMap<String, String> mvMap = new LinkedMultiValueMap<>();
		  mvMap.add("grant_type", appConnectRequest.getGrantType());
		  mvMap.add("client_id", appConnectRequest.getClientId());
		  mvMap.add("client_secret", appConnectRequest.getClientSecret());
		  mvMap.add("username", appConnectRequest.getUsername());
		  mvMap.add("password", appConnectRequest.getPassword());//Added Password and Security token (case-sensitive): kL1rAcQCrd5Minju68FcQ5nDF
		  // Create an instance of the RestTemplate
		  RestTemplate restTemplate = new RestTemplate(restConfig.clientHttpRequestFactory());
		  // Send the REST request to get authenticated and receive an OAuth token
		  Map<String, String> tokenMap=new HashMap<>();
		  log.info("Salforce Connection Started");
		  try {
			  tokenMap = (Map<String, String>) restTemplate.postForObject(oauthUrl, mvMap, Map.class);
		  }catch (Exception e) {
			log.info("Salforce Connection failure");
			throw new BadRequestException("Invalid credentials for Salesforce Connection hence we get authentication failure");
		  }
		  log.info("Salforce Connected successfully");
		  AppConnectResponse appConnectResponse = new AppConnectResponse();
		  appConnectResponse.setAccessToken(tokenMap.get("access_token"));
		  appConnectResponse.setInstanceURL(tokenMap.get("instance_url"));	
		  appConnectResponse.setId(tokenMap.get("id"));
		  appConnectResponse.setTokenType(tokenMap.get("token_type"));
		  appConnectResponse.setIssuedAt(tokenMap.get("issued_at"));
		  appConnectResponse.setSignature(tokenMap.get("signature"));

		  return appConnectResponse;
	}

}
