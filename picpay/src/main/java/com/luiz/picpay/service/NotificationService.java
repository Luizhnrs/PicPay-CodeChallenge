package com.luiz.picpay.service;

import com.luiz.picpay.client.NotificationClient;
import com.luiz.picpay.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transaction transaction){
        try {
            logger.info("Sending notification...");
            var resp = notificationClient.sendNotification(transaction);
            notificationClient.sendNotification(transaction);
            if(resp.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not OK");
            }
        } catch (Exception ex){
            logger.error("Error while sending notification", ex);
        }
    }
}
