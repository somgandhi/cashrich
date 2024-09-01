package com.cashrich.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "coin_user_data")
public class ApiRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetail user;

    @Lob
    @Column(name = "response_data", columnDefinition = "LONGTEXT")
    private String responseData;

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public ApiRequest(UserDetail user, String responseData, String requestData, LocalDateTime timestamp) {
        this.user = user;
        this.responseData = responseData;
        this.requestData = requestData;
        this.timestamp = timestamp;
    }

    @Lob
    @Column(name = "request_data", columnDefinition = "LONGTEXT")
    private String requestData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private LocalDateTime timestamp;

}
