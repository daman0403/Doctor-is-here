package com.example.android.doctorishere;

public class request {
    String latitude, requestTime, speciality;
    Boolean status;

    public request() {
    }

    public request(String address, String requestTime, String speciality, Boolean status) {
        this.latitude = address;
        this.requestTime = requestTime;
        this.speciality = speciality;
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
