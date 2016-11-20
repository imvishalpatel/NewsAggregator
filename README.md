# NewsAggregator
Sen final year project.

### How to send mail using template
    // content before button
    String firstMessage = "Verify yourself and be a member of vidico community.";
    // value you want to show i.e click here or reset password or confirm
    String buttonValue = "Confirm";
    // link for button
    String confirmationUrl = "http://localhost:8084/NewsAggregator/Controller?action=confirm&key=" + u.getId();
    // content after button
    String lastMessage = "If the link doesn't work please copy below link and paste it directly into your browser." + "<br> " + confirmationUrl;
    
    MailService mailContent = new MailService(firstMessage, lastMessage, buttonValue, confirmationUrl);
                    
    boolean mailStatus = mailContent.sendMail(u.getEmail(), "Confirmatiom Mail");
    
### How to use sequence
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            SequenceDAO seqDao = new SequenceDAO(mongo);
            
            // get sequence for COMMENT starting from 1
            long n = seqDao.getNextSequenceId("COMMENT");
            System.out.println(n);
            
### Libraries

***Download these libraries and add it to project.***

* [jstl-1.2.jar](http://www.java2s.com/Code/JarDownload/jstl/jstl-1.2.jar.zip) - tag library
* [java-mail-1.4.4.jar](http://www.java2s.com/Code/JarDownload/java-mail/java-mail-1.4.4.jar.zip) - for mailing purpose
* [mongo-java-driver-3.3.0.jar](http://central.maven.org/maven2/org/mongodb/mongo-java-driver/3.3.0/mongo-java-driver-3.3.0.jar) - for mongodb connectivity

### Installation
1. clone repository
2. import it
3. clean build
4. run

License
----
**Free Software, Hell Yeah!**
