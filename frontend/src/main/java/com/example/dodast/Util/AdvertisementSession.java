package com.example.dodast.Util;

public class AdvertisementSession {

    private static Long selectedAdvertisementId;

    private AdvertisementSession() {
    }

    public static void setSelectedAdvertisementId(
            Long advertisementId
    ) {
        selectedAdvertisementId = advertisementId;
    }

    public static Long getSelectedAdvertisementId() {
        return selectedAdvertisementId;
    }

    public static void clear() {
        selectedAdvertisementId = null;
    }
}