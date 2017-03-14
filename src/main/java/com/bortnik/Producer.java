package com.bortnik;

import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

/**
 * Created by admin on 14.03.2017.
 */
public class Producer implements Runnable {
    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Scanner scanner = new Scanner(System.in);
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Topic");
            //Destination destination = session.createQueue("Dest");
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            StringBuilder stringBuffer = new StringBuilder("Hello");
            while (!stringBuffer.equals("0")&&scanner.hasNext()) {
                stringBuffer = new StringBuilder(scanner.next());
                java.lang.String string = new java.lang.String(stringBuffer);
                TextMessage textMessage = session.createTextMessage(string);
                producer.send(textMessage);
            }
            myConnection.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
