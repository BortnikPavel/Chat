package com.bortnik;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 14.03.2017.
 */
public class Consumer implements Runnable {
    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Topic");
            MessageConsumer consumer = session.createConsumer(topic);
            while (true) {
                Message message = consumer.receive();
                System.out.println(((TextMessage) message).getText());
            }
            //session.close();
            //myConnection.close();
        }catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
