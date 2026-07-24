package com.example.dodast.DTO.Admin;

public class AdminDashboardResponse {

    private long userCount;
    private long advertisementCount;
    private long pendingAdvertisementCount;

    public AdminDashboardResponse() {}

    public AdminDashboardResponse(long userCount,
            long advertisementCount,
            long pendingAdvertisementCount) {

        this.userCount = userCount;
        this.advertisementCount = advertisementCount;
        this.pendingAdvertisementCount = pendingAdvertisementCount;
    }

    public long getUserCount() {
        return userCount;
    }

    public long getAdvertisementCount() {
        return advertisementCount;
    }

    public long getPendingAdvertisementCount() {
        return pendingAdvertisementCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public void setAdvertisementCount(long advertisementCount) {
        this.advertisementCount = advertisementCount;
    }

    public void setPendingAdvertisementCount(long pendingAdvertisementCount) {
        this.pendingAdvertisementCount = pendingAdvertisementCount;
    }
}