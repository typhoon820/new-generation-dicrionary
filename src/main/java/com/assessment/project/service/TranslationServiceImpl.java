package com.assessment.project.service;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

@Service("TranslationService")
public class TranslationServiceImpl implements TranslationService {

    String key = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170521T153439Z.bf33a7b4b5a4d7ed.d0784f6fa37e5ba9e604001edc2e52b39346666b";

    @Override
    public String translate(String lang, String enteredText) throws IOException {
        String textEscaped = enteredText.replace(" ", "%20");
        String url =key+ "&lang="
                + lang + "&text=" + textEscaped;
        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf("No text for translate");
        }
        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        return translated;
    }
}

