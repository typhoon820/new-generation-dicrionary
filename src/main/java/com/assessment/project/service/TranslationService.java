package com.assessment.project.service;

import java.io.IOException;

/**
 * Created by Никита on 21.05.2017.
 */
public interface TranslationService {
    String translate (String lang, String enteredText) throws IOException;
}
