package liten.genealogy.webUtilities;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

public class HttpUtils {

    static HttpSession session;
    static String sessionID;

    public HttpUtils() {

    }

    public static Collection<String> getRenderedIDs() {
        return FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds();
    }

    public static void setRenderAllTrue() {
        FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
    }

    public static void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getFacesExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public static void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static String getUserMachineName() {
        String ipAddress = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
        }
        return ipAddress;
    }

    public static String getRequestURI() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
    }

    public static HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static HttpSession initiateHttpSession() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessionID = session.getId();
        return session;
    }

    public static HttpSession getHttpSession() {
        if ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false) == null) {
            return initiateHttpSession();
        } else {
            return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        }
    }

    public static void invalidateSession() {
        session.invalidate();
    }

    public static ServletContext getServletContext() {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getServletContext();
    }

    public static String getContextPath() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }

    public static Map<String, String> getRequestParamsMap() {
        Map<String, String> requestParamsMap = getFacesExternalContext().getRequestParameterMap();
        return requestParamsMap;
    }

    public static Map<String, Object> getSessionMap() {
        Map<String, Object> sessionMap = getFacesExternalContext().getSessionMap();
        return sessionMap;
    }

    public static Map<String, Object> getApplicationMap() {
        Map<String, Object> applicationMap = getFacesExternalContext().getApplicationMap();
        return applicationMap;
    }

    public static String getCurrentView() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    public static UIComponent getUIComponent(String id) {
        return FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
    }

    public static String getRequestFormID() {
        String foundFormID = "";
        Map requestParamsMap = getFacesExternalContext().getRequestParameterMap();
        Set<String> keyList = requestParamsMap.keySet();
        for (String key : keyList) {
            if (key.endsWith("FormID")) {
                foundFormID = key;
                break;
            }
        }
        return foundFormID;
    }

    public static HttpSession getSession() {
        return session;
    }

    public static String getSessionID() {
        return sessionID;
    }

    public static void buildSessionInfos(String transactionID) {
        session = getHttpSession();
        sessionID = session.getId();
        getSessionMap().put(transactionID, sessionID);
        getServletContext().setAttribute(sessionID, session);
        getServletContext().setAttribute("acbayo", getSessionMap());
        System.out.println("sessionID: " + sessionID);
        System.out.println("session: " + session);
        System.out.println("sessonMap: " + getSessionMap());
    }

    public static Map retrieveSession(String transactionID) {
        Map sessionMap = (Map) getServletContext().getAttribute("acbayo");
        String sessionID = (String) sessionMap.get(transactionID);
        //    session = (HttpSession) getServletContext().getAttribute(sessionID);
        System.out.println("sessionID: " + sessionID);
        //    System.out.println("session: " + session);
        System.out.println("sessonMap: " + sessionMap);
        return sessionMap;
    }

}
