package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import javax.mail.Session;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	private static final String [] TEST_EMAILS = { "ab@Bc.com", "a.b@c.org", 
			"abcdefghijklmnopqrst@abcdefghijklmnopqrst.com.bs" };
	private static final String validName = "Luis";
	private static final String validNum = "1";
	private static final String emptyName = "";
	private static final String emptyNum = "";
	private static final String validEmail = "ab@Bc.com";
	private static final String validEmailMessage = "Today is the day we achieve 70% test coverage,"
			+ "minimum, for the buildMimeMessage. This should be a piece of cake, surely";
	
	// Define MAIL_HOST constant
    private static final String MAIL_HOST = "mail.smtp.host";
    private static final String subject = "Homework 3";
    
	/* Concrete Email Class for testing */
	private EmailConcrete email;
	
	/*
	 * @Before is executed before each test method. 
	 * It initializes the email variable with a new instance of EmailConcrete
	 * 
	 */
	@Before
	public void setUpEmailTest() throws Exception {
	
		email = new EmailConcrete();
		
	}
	
	/*
	 * @After is executed after each test method. 
	 * It can be used for cleanup operations, but in this case, it's empty.
	 * 
	 */
	@After
	public void tearDownEmailTest() throws Exception{
		
		
	}
	
	/*
	 *  TEST addBcc(String email) function
	 */
	
	@Test
	public void testAddBcc() throws Exception {
		
		email.addBcc(TEST_EMAILS);
		
		assertEquals(3, email.getBccAddresses().size());
	}
	
	@Test
	public void testAddcc() throws Exception {
		
		email.addCc(TEST_EMAILS);
		assertEquals(3, email.getCcAddresses().size());
		
	}
	
	@Test
	public void testAddHeader() throws Exception {
		
		email.addHeader(validName, validNum);
	}
	@Test(expected = EmailException.class)
	public void testAddHeaderNameEmpty() throws Exception {
		
		email.addHeader(emptyName, "emptyNum");
	}
	@Test(expected = EmailException.class)
	public void testAddHeaderNumEmpty() throws Exception {
		
		email.addHeader("emptyName", emptyNum);
	}
	
	@Test
	public void testBuildMimeMessage() throws Exception {
		
		email.setCharset("UTF-16");
		email.setSubject(subject);
		email.setHostName("Random.HostName.com");
		email.getHostName();
		email.setContent(subject, MAIL_HOST);
		email.setFrom(validEmail, validName, "UTF-16");
		email.addTo(validEmail, validName, "UTF-16");
		email.addCc(validEmail, validName, "UTF-16");
		email.addBcc(validEmail);
		email.addReplyTo(validEmail, validName, "UTF-16");
		
		// Create a map to hold the headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Mailer", "Sendmail");		
		headers.put("X-Priority", "1");	
		headers.put("Disposition-Notification-To", "user@domain.net");
		email.setHeaders(headers);
		
		email.buildMimeMessage();
			
	}
	@Test(expected = EmailException.class)
	public void testBuildMimeMessageAlreadyBuilt() throws Exception {
		
		email.setCharset("UTF-16");
		email.setSubject(subject);
		email.setHostName("Random.HostName.com");
		email.getHostName();
		email.setContent(subject, MAIL_HOST);
		email.setFrom(validEmail, validName, "UTF-16");
		email.addTo(validEmail, validName, "UTF-16");
		email.addCc(validEmail, validName, "UTF-16");
		email.addBcc(validEmail);
		email.addReplyTo(validEmail, validName, "UTF-16");
		
		// Create a map to hold the headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Mailer", "Sendmail");		
		headers.put("X-Priority", "1");	
		headers.put("Disposition-Notification-To", "user@domain.net");
		email.setHeaders(headers);
		
		email.buildMimeMessage();
		email.buildMimeMessage();
	}
	@Test(expected = EmailException.class)
	public void testBuildMimeMessageException() throws Exception {
		
		email.setCharset("UTF-16");
		email.setSubject(null);
		email.setHostName("Random.HostName.com");
		email.getHostName();
		email.setContent(null, null);
		
		
		// Create a map to hold the headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Mailer", "Sendmail");		
		headers.put("X-Priority", "1");	
		headers.put("Disposition-Notification-To", "user@domain.net");
		email.setHeaders(headers);
		
		email.buildMimeMessage();
		email.buildMimeMessage();
	}
	
	@Test
	public void testGetHostNameWithHostName() throws Exception {
		
		email.setHostName("Random.HostName.com");
		email.getHostName();
		
	}
	@Test
	public void testGetHostNameWithHostNameNull() throws Exception {
		 
		email.setHostName(null);
		email.getHostName();
		
	}
	
	@Test(expected = EmailException.class)
	public void testGetMailSession() throws Exception {
		 
		email.setCharset("UTF-16");
		email.setSubject(null);
		
		email.setContent(null, null);
		
		
		// Create a map to hold the headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Mailer", "Sendmail");		
		headers.put("X-Priority", "1");	
		headers.put("Disposition-Notification-To", "user@domain.net");
		email.setHeaders(headers);
		
		
		
		email.getMailSession();
		
	}
	
	@Test
	public void testGetSentDate() throws Exception {
		
		LocalDate date = LocalDate.of(2024, 2, 22);
		email.setSentDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		email.getSentDate();
		
	}
	
	@Test
	public void testSetSocketConnectionTimeout() throws Exception {
		
		email.getSocketConnectionTimeout();
		
	}
	
	@Test
	public void testSetFrom() throws Exception {
		
		email.setFrom(validEmail);
		
	}
	
}
