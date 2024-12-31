package com.ParsingCV.services;

import com.ParsingCV.dto.SignupRequest;
import com.ParsingCV.entities.Customer;

public interface AuthService {

	Customer createCustomer(SignupRequest signupRequest);

}
