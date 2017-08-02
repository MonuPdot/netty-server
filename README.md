# netty-server
Build a Java Netty server that accepts a message and saves it into a Kafka queue. 

Short Description:
The project accepts the message from the client and decodes the message  and save's to the kafka ..

Note:The project is built using apache maven.

Server.java:
  The server is created at he local host .

Child handler(Http kafka initializer.java):
  It adds the encoder,decoder  and sevlet handler to pipeline.

HttpKafkaServerHandler.java:
  It initializes the property of the kafka producer and creates a topic and sends to the port mentioned.

Person.java: 
  since message can be of any type. It is taken for example. A function to convert object to
byte is included in the class.

JsonDecoder.java:
  Decodes the object to to byteArray.
  
JsonEncoder.java :
  Converts the message to byteArray.
  
Client.java:
  To send  the message a client is created.
  
Client Handler.java:
   In this class the message is send when the server is connected at channel active.

