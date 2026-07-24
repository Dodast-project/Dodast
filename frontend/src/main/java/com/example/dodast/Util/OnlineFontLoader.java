package com.example.dodast.Util;

import javafx.scene.text.Font;

public final class OnlineFontLoader {

    private static boolean loaded;

    private static final String REGULAR_FONT_URL = "https://cdn.jsdelivr.net/gh/rastikerdar/vazirmatn@v33.003/fonts/ttf/Vazirmatn-Regular.ttf";

    private static final String BOLD_FONT_URL = "https://cdn.jsdelivr.net/gh/rastikerdar/vazirmatn@v33.003/fonts/ttf/Vazirmatn-Bold.ttf";

    private OnlineFontLoader() {
    }

    public static void load() {

        if (loaded) {
            return;
        }

        try {
            Font regularFont = Font.loadFont(REGULAR_FONT_URL, 14);
            Font boldFont = Font.loadFont(BOLD_FONT_URL, 14);

            if (regularFont == null) 
                System.err.println("فونت معمولی لود نشد");

            if (boldFont == null) {
                System.err.println("فونت بولد لود نشد");
            }

            loaded = regularFont != null;

        } catch (Exception exception) {
            System.err.println("خطا در بارگذاری فونت آنلاین");
        }
    }
}