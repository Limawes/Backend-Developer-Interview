package br.com.mobiauto.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
@Component
public class StringUtils {

    public static String normalizeTextAccent(String target) {
        return Normalizer.normalize(target.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
