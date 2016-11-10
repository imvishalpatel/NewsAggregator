# NewsAggregator
Sen final year project.

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
