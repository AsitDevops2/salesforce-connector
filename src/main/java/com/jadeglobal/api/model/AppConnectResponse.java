package com.jadeglobal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppConnectResponse {
	
	private String id;
	private String instanceURL;
	private String accessToken;
	private String tokenType;
	private String signature;
	private String issuedAt;
}
