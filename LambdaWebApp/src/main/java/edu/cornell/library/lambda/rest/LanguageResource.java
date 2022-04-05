package edu.cornell.library.lambda.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cornell.library.lambda.model.Language;

@RestController
public class LanguageResource {

    @RequestMapping(path = "/languages", method = RequestMethod.GET)
    public List<Language> listLambdaLanguages() {
        return Arrays.asList(
                new Language("node"), 
                new Language("java"), 
                new Language("python"),
                new Language("ruby")
                );
    }

}
