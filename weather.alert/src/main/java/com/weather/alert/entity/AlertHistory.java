package com.weather.alert.entity;//it belong to entity package

import jakarta.persistence.*;//JPA annotation
import lombok.AllArgsConstructor;//consructor will all args
import lombok.Data;//getters and setters ,toString
import lombok.NoArgsConstructor;//Empty constructor
import java.time.LocalDateTime;//Date/time handling

@Entity //make this class as database table (JPA create a table for this )
@Table(name = "alert_history")//set exact table name
@Data //generate getters ,setters ,equals ,hashcode
@NoArgsConstructor //Empty Constuctor (JPA requires this)
@AllArgsConstructor //Constuctor will all field
public class AlertHistory {

    @Id    //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto-increment by databases
    private Long id;

    @Column(nullable = false)//nullable beacause every alert must have a temperature value
    private Double temperatureAtAlert; //double for decimal

    @Column(nullable = false) //sets current time when object is crated
    private LocalDateTime alertSentAt = LocalDateTime.now();

    @Column(nullable = false)//true because default is true
    private Boolean emailSent = true;

    @ManyToOne(fetch = FetchType.LAZY)//many AlertHistory record belong to one subcription
    @JoinColumn(name = "subscription_id", nullable = false)//foreign key column name
    private Subscription subscription;
}