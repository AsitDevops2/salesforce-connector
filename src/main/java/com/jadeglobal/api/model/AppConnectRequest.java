package com.jadeglobal.api.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppConnectRequest {
	
	@NotEmpty(message = " clientId must not be empty")
	private String clientId;
	@NotEmpty(message = " clientSecret must not be empty")
	private String clientSecret;
	@NotEmpty(message = " username must not be empty")
	private String username;
	@ApiModelProperty(notes = "Password and Security token (case-sensitive)", required = true)
	@NotEmpty(message = " password must not be empty")
	private String password;
	@NotEmpty(message = " grantType must not be empty")
	private String grantType;
	
}
