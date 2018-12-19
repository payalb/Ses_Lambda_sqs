package com.java.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class SendMailService implements RequestHandler<SQSEvent, Context>{

	public Context handleRequest(SQSEvent input, Context context) {
		String message=input.getRecords().get(0).getBody();
	//	String to=input.getRecords().get(0).getMessageAttributes().get("to").getStringValue();
		String to= "jahanvi.bansal@gmail.com";
		String from= "payal@rjtcompuquest.com";
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
		          // Replace US_WEST_2 with the AWS Region you're using for
		          // Amazon SES.
		            .withRegion(Regions.US_EAST_1).build();
		      SendEmailRequest request = new SendEmailRequest()
		          .withDestination(
		              new Destination().withToAddresses(to))
		          .withMessage(new Message()
		              .withBody(new Body()
		                  
		                  .withText(new Content()
		                      .withCharset("UTF-8").withData(message)))
		              .withSubject(new Content()
		                  .withCharset("UTF-8").withData("Status of payment")))
		          .withSource(from);
		          // Comment or remove the next line if you are not using a
		          // configuration set
		      //    .withConfigurationSetName(CONFIGSET);
		      client.sendEmail(request);
		return null;
	}

}
