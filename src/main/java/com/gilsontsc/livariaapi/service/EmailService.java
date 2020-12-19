package com.gilsontsc.livariaapi.service;

import java.util.List;

public interface EmailService {

	void sendMails(String mensagem, List<String> emailList);

}