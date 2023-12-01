package com.ms.email.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "TB_SUBSCRIBERS")
@Table(name = "TB_SUBSCRIBERS")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscriber_id")
    private long subscriberId;
    @Column(name = "subscriber_name")
    private String subscriberName;
    @Column(name = "subscriber_email")
    private String subscriberEmail;
}
