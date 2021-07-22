package com.jadeglobal.api.service;

import com.jadeglobal.api.model.AppConnectRequest;
import com.jadeglobal.api.model.AppConnectResponse;

public interface AppConnectService {

	AppConnectResponse callSalesforceAPIConnection(AppConnectRequest appConnectRequest );
}
