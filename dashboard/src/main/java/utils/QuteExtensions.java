package utils;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class QuteExtensions {

    public static String round(Double number) {
        return String.format("%,.2f", number);
    }
}
