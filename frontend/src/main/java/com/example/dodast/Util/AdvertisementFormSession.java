package com.example.dodast.Util;

import com.example.dodast.Model.AdvertisementFormMode;

public final class AdvertisementFormSession {

    private static AdvertisementFormMode mode = AdvertisementFormMode.CREATE;

    private static Long advertisementId;

    private AdvertisementFormSession() {}

    public static void openCreate() {
        mode = AdvertisementFormMode.CREATE;
        advertisementId = null;
    }

    public static void openEdit(Long id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("شناسه آگهی معتبر نیست");

        mode = AdvertisementFormMode.EDIT;
        advertisementId = id;
    }

    public static AdvertisementFormMode getMode() {
        return mode;
    }

    public static Long getAdvertisementId() {
        return advertisementId;
    }

    public static boolean isCreateMode() {
        return mode == AdvertisementFormMode.CREATE;
    }

    public static boolean isEditMode() {
        return mode == AdvertisementFormMode.EDIT;
    }

    public static void clear() {
        mode = AdvertisementFormMode.CREATE;
        advertisementId = null;
    }
}