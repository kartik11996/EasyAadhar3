package com.stackroute.emailservice.model;

import org.springframework.data.mongodb.core.mapping.Document;


public class EmailModel {
    private String UserEmail;
    private String MailSubject;
    private String MailBody;

    public EmailModel(String userEmail, String mailSubject, String mailBody) {
        UserEmail = userEmail;
        MailSubject = mailSubject;
        MailBody = mailBody;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getMailSubject() {
        return MailSubject;
    }

    public void setMailSubject(String mailSubject) {
        MailSubject = mailSubject;
    }

    public String getMailBody() {
        return MailBody;
    }

    public void setMailBody(String mailBody) {
        MailBody = mailBody;
    }

    @Override
    public String toString() {
        return "EmailModel{" +
                "UserEmail='" + this.UserEmail + '\'' +
                ", MailSubject='" + this.MailSubject + '\'' +
                ", MailBody='" + this.MailBody + '\'' +
                '}';
    }
}
