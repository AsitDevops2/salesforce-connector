package com.jadeglobal.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jadeglobal.api.model.AppConnectRequest;
import com.jadeglobal.api.model.AppConnectResponse;
import com.jadeglobal.api.service.AppConnectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nikhil.lokhande
 * @description Connect Salesforce connect app
 * */
@Api(value = "APP_CONNECT_CONTROLLER- REST APIs used to SalesforceAPI Connection !!!!")
@Slf4j(topic = "APP_CONNECT_CONTROLLER")
@RestController
@RequestMapping("/SalesforceAPI")
public class AppConnectController {
	
	@Autowired
	private AppConnectService appConnectService;
	/**
	  * @param:AppConnectRequest and parameter as below
	  *  client_id
	  *  client_secret
	  *  user name
	  *  password with Security token 
	  * @return AppConnectResponse token details
	  */
	 @ApiOperation(value = "call SalesforceAPI Connection API ", response = AppConnectResponse.class, tags = "connect")
	 @ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 @PostMapping("/connect")
	 public AppConnectResponse callSalesforceAPIConnection(@Valid @RequestBody AppConnectRequest appConnectRequest) {
		 log.info("Calling callSalesforceAPIConnection");
	  return appConnectService.callSalesforceAPIConnection(appConnectRequest);
	 }
	
	@GetMapping("/test")
	public String getMessage() {
		return "success";
	}

}
