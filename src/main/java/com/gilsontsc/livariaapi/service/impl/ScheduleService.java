package com.gilsontsc.livariaapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gilsontsc.livariaapi.model.entity.Loan;
import com.gilsontsc.livariaapi.service.LoanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private static final String CRON_LATE_LOANS = "0 0 0 1/1 * ?";
	
	@Value("${application.mail.lateloans.message}")
	private String mensagem;
	
	private final LoanService loanService;
	
	private final EmailServiceImpl emailService;
	
	@Scheduled(cron = CRON_LATE_LOANS)
	public void sendMailToLateLoans() {
		List<Loan> allLateLoans = this.loanService.getAllateLoan();
		List<String> emailList = allLateLoans
				.stream()
				.map(loan -> loan.getCustomerEmail())
				.collect(Collectors.toList());
		
		emailService.sendMails(mensagem, emailList);
	}
	
}