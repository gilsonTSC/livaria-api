package com.gilsontsc.livariaapi.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

	@Scheduled
	public void sendMailToLateLoans() {
		
	}
	
}