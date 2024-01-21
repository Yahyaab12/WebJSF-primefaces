package domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {

    private String currentLanguage = "en"; // Default language is English

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public Locale getLocale() {
        return new Locale(currentLanguage);
    }

    public String getMessage(String key) {
        ResourceBundle bundle = getBundle();
        return bundle.getString(key);
    }

    private ResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = new Locale(currentLanguage);
        String bundleName = "Bundle_" + currentLanguage;
        return ResourceBundle.getBundle(bundleName, locale, context.getClass().getClassLoader());
    }

    public String changeLanguage() {
        System.out.println("Changing language to: " + currentLanguage);

        // Get the selected language from the request
        String selectedLanguage = getCurrentLanguageFromRequest();

        // Update the current language if it's a valid language
        if ("en".equals(selectedLanguage) || "fr".equals(selectedLanguage)) {
            this.currentLanguage = selectedLanguage;
        }

        addRefreshScript(); // Corrected method name

        // Redirect to the current page to apply the language change
        return "/template?faces-redirect=true&includeViewParams=true";
    }

    private String getCurrentLanguageFromRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> requestParams = facesContext.getExternalContext().getRequestParameterMap();

        // Assuming you have a parameter named "lang" in the request
        String selectedLanguage = requestParams.get("lang");

        // Validate that the selected language is either "en" or "fr"
        if ("en".equals(selectedLanguage) || "fr".equals(selectedLanguage)) {
            return selectedLanguage;
        } else {
            // Default to French if the language is not found or invalid
            return selectedLanguage;
        }
    }

    private void addRefreshScript() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        try {
            // Use JavaScript to trigger an immediate refresh
            externalContext.getResponseOutputWriter().write("<script>location.reload(true);</script>");
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }
}
