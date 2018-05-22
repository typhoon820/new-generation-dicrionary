package com.assessment.project.controller;

import com.assessment.project.TestTrans;
import com.assessment.project.component.Dictionary;
import com.assessment.project.component.WordStat;
import com.assessment.project.model.UserEntity;
import com.assessment.project.service.ProcessingService;
import com.assessment.project.service.TranslationService;
import com.assessment.project.service.UserService;
import com.assessment.project.service.WordService;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import smile.nlp.Corpus;
import smile.nlp.SimpleCorpus;
import smile.nlp.collocation.BigramCollocation;
import smile.nlp.collocation.BigramCollocationFinder;

import java.util.*;

@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    private String inputText="";
    private String res ="";
    TestTrans test;

    private String question;

    @Autowired
    TranslationService translationService;
    @Autowired
    ProcessingService processingService;
    @Autowired
    WordService wordService;
    @Autowired
    UserService userService;

    List<Dictionary> dictionary;

    private final String KEY = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170521T153439Z.bf33a7b4b5a4d7ed.d0784f6fa37e5ba9e604001edc2e52b39346666b";
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView show(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userService.findByLogin("privetik");
//        wordService.addWord("house", "дом", user);
//        wordService.addWord("mouse", "мышь", user);
//        wordService.addWord("house", "домик", user);
//        wordService.addWord("package", "пакет", user);




        ModelAndView model = new ModelAndView();
        model.addObject("toTranslate", new TestTrans());
        model.addObject("result", res);
        model.addObject("inputText", inputText);
        model.addObject("dictionary", dictionary);
        model.setViewName("test");
        return model;
    }
    @RequestMapping(value = "/translate",method = RequestMethod.POST)
    public ModelAndView doIt(@ModelAttribute("toTranslate") TestTrans toTranslate, BindingResult bindingResult) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final int PERCENT = 80;
        dictionary = new ArrayList<>();
        SimpleCorpus corpus = new SimpleCorpus();
        corpus.add("1", "text", toTranslate.text);
        BigramCollocationFinder finder = new BigramCollocationFinder(0);
        BigramCollocation[] array = finder.find(corpus, 0.02);

        ModelAndView model = new ModelAndView();
        if(bindingResult.hasErrors()) model.setViewName("redirect:/");
        else{
            inputText = toTranslate.text;
            WordStat statistics = processingService.getWordStat(toTranslate.text, PERCENT);
//            List<String> useful = new ArrayList<>(statistics.getFrequency().keySet());
            Set<String> useful = processingService.getMainClauses(toTranslate.text);
            useful.addAll(processingService.getMainClasesAdvanced(toTranslate.text));
            for (String s: useful){
                String translated = translationService.translate("ru",s);
                wordService.addWord(s,
                        translated,
                        userService.findByLogin(auth.getName()));
                //String trans = translationService.translate("ru",s);
                dictionary.add(new Dictionary(s,translated ));

            }
            logger.info(wordService.findAllWords().get(1).getWord());


            //res = translationService.translate("ru",toTranslate.text);
            model.setViewName("redirect:/");
        }

        return model;
    }


}
