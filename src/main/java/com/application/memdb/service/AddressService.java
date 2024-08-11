package com.application.memdb.service;

import com.application.memdb.controller.MainController;
import com.application.memdb.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
}
