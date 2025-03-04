/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webListeners;

import liten.genealogy.webUtilities.ErrorMessage;
import liten.genealogy.webUtilities.HttpUtils;
import liten.genealogy.webUtilities.SendHttpRequest;
import liten.genealogy.webUtilities.RetrieveHttpResponse;
import liten.genealogy.views.EnrollmentModelView;
import liten.genealogy.views.AccountModelView;
import liten.genealogy.views.PaymentModelView;
import liten.genealogy.views.ClientModelView;
import liten.genealogy.views.MobileMoneyModelView;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.managers.PaymentManager;
import liten.genealogy.mainEntities.PaymentMethod;
import liten.genealogy.mainEntities.Payment;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.MobilePhone;
import liten.genealogy.cinetPay.MobilePayment;
import java.text.SimpleDateFormat;
import java.io.OutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Transaction;
import java.io.Serializable;
import java.net.HttpURLConnection;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author FACULTY
 */
@Named
@SessionScoped
public class PaymentListener implements Serializable {

    @Inject
    PaymentManager paymentManager;
    PaymentModelView paymentModelView;
    AccountModelView accountModelView;
    ClientModelView clientModelView;
    EnrollmentModelView enrollmentModelView;
    @Inject
    ErrorMessage errorMessage;
    String origURI = "";
    String transactionID = "";

    public PaymentListener() {

    }

    @PostConstruct
    public void init() {
        try {
            if (HttpUtils.getSessionMap().get("paymentManager") == null) {
                HttpUtils.getSessionMap().put("paymentManager", paymentManager);
            } else {
                paymentManager = (PaymentManager) HttpUtils.getSessionMap().get("paymentManager");
            }
            if (HttpUtils.getSessionMap().get("paymentModelView") == null) {
                paymentModelView = new PaymentModelView();
                HttpUtils.getSessionMap().put("paymentModelView", paymentModelView);
            } else {
                paymentModelView = (PaymentModelView) HttpUtils.getSessionMap().get("paymentModelView");
            }
            if (HttpUtils.getSessionMap().get("clientModelView") == null) {
                HttpUtils.getSessionMap().put("clientModelView", clientModelView);
            } else {
                clientModelView = (ClientModelView) HttpUtils.getSessionMap().get("clientModelView");
            }
            if (HttpUtils.getSessionMap().get("accountModelView") == null) {
                HttpUtils.getSessionMap().put("accountModelView", accountModelView);
            } else {
                accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
            }
            if (HttpUtils.getSessionMap().get("enrollmentModelView") == null) {
                HttpUtils.getSessionMap().put("enrollmentModelView", enrollmentModelView);
            } else {
                enrollmentModelView = (EnrollmentModelView) HttpUtils.getSessionMap().get("enrollmentModelView");
            }
            if (HttpUtils.getSessionMap().get("errorMessage") == null) {
                HttpUtils.getSessionMap().put("errorMessage", errorMessage);
            } else {
                errorMessage = (ErrorMessage) HttpUtils.getSessionMap().get("errorMessage");
            }
            Map<String, String> sentParamsMap = HttpUtils.getRequestParamsMap();
            if (sentParamsMap != null && sentParamsMap.get("cpm_trans_id") != null && !((String) sentParamsMap.get("cpm_trans_id")).equals("")) {
                HttpSession session = (HttpSession) HttpUtils.getApplicationMap().get((String) sentParamsMap.get("cpm_trans_id"));
                System.out.println("Previous session: " + session);
                paymentManager = (PaymentManager) session.getAttribute("paymentManager");
                paymentModelView = (PaymentModelView) session.getAttribute("paymentModelView");
                accountModelView = (AccountModelView) session.getAttribute("accountModelView");
                errorMessage = (ErrorMessage) session.getAttribute("errorMessage");
                transactionID = (String) sentParamsMap.get("cpm_trans_id");
            } else {
                HttpSession session = HttpUtils.getHttpSession();
                System.out.println("New session: " + session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AccountModelView getAccountModelView() {
        return accountModelView;
    }

    public EnrollmentModelView getEnrollmentModelView() {
        return enrollmentModelView;
    }

    public PaymentModelView getPaymentModelView() {
        return paymentModelView;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    public void paymentProcess(ActionEvent evt) {
        try {
            String paymentMethodID = paymentModelView.getPaymentMethodID();
            String email = clientModelView.getEmail();
            Client client = MainDAO.findClientPerEmail(email);
            EnrollmentModelView enrollmentModelView = (EnrollmentModelView) HttpUtils.getSessionMap().get("serviceType");
            PaymentMethod paymentMethod = MainDAO.findPaymentMethod(paymentMethodID);
            Payment payment = paymentModelView.getPayment();
            payment.setClient(client);
            payment.setPaymentDate(new Date());
            payment.setPaymentMethod(paymentMethod);
            MobileMoneyModelView mobilePhoneModelView = (MobileMoneyModelView) HttpUtils.getSessionMap().get("MobileMoneyModelView ");
            MobilePhone mobMon = null;
            if (mobilePhoneModelView.getMobilePhone() != null && mobilePhoneModelView.getMobilePhone().getMobilePhoneID() != null) {
                String mobileMoneyID = mobilePhoneModelView.getMobilePhone().getMobilePhoneID();
                mobMon = MainDAO.findMobilePhone(mobileMoneyID);
            }
            mobilePaymentProcess(payment, mobMon);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void mobilePaymentProcess(Payment payment, MobilePhone mobileMoney) {
        try {
            Transaction transaction = new Transaction();
            transaction.setClient(payment.getClient());
            transaction.setTransactionDate(new Date());
            transaction.setTotal(payment.getTotal());
            transaction = paymentManager.addNewTransaction(transaction);
            MobilePayment mobilePayment = paymentModelView.getMobilePayment();
            mobilePayment.setCellPhoneNbr(mobileMoney.getMobileNbr());
            mobilePayment.setAmount(transaction.getTotal());
            mobilePayment.setCurrency("XOF");
            mobilePayment.setCustom(enrollmentModelView.getEnrollment().getEnrollmentID());
            mobilePayment.setTransactionID(transaction.getTransactionID());
            mobilePayment.setTransactionDate(transaction.getTransactionDate());

            HashMap<String, String> requestParamsMap = new HashMap<String, String>();

            if (mobilePayment.getCurrency().equals("USD")) {
                requestParamsMap.put("cpm_amount", Long.toString(Math.round((double) mobilePayment.getAmount())));
            } else {
                requestParamsMap.put("cpm_amount", Long.toString(Math.round((double) mobilePayment.getAmount() * 500)));
            }
            requestParamsMap.put("cpm_currency", mobilePayment.getCurrency());
            requestParamsMap.put("cpm_site_id", mobilePayment.getSiteID());
            requestParamsMap.put("cpm_trans_id", mobilePayment.getTransactionID());
            String transDateString = MainDAO.convertDate(mobilePayment.getTransactionDate());
            System.out.println("transDateString: " + transDateString);
            requestParamsMap.put("cpm_trans_date", transDateString);
            requestParamsMap.put("cpm_payment_config", mobilePayment.getPaymentConfig());
            requestParamsMap.put("cpm_page_action", mobilePayment.getPageAction());
            requestParamsMap.put("cpm_version", mobilePayment.getVersion());
            requestParamsMap.put("cpm_language", mobilePayment.getLanguage());
            requestParamsMap.put("cpm_designation", mobilePayment.getDesignation());
            requestParamsMap.put("cpm_custom", mobilePayment.getCustom());
            requestParamsMap.put("apikey", mobilePayment.getApiKey());
            String postData = SendHttpRequest.getURLPostDataString(requestParamsMap);
            String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
            HttpURLConnection http = SendHttpRequest.sendPostData(MobilePayment.SIGN_URL, postData, CONTENT_TYPE);
            StringBuffer response = RetrieveHttpResponse.retrieveResponse(http);
            String uri = "";
            String contextPath = HttpUtils.getContextPath();
            System.out.println(response);
            System.out.println(response.toString().substring(1, response.toString().length() - 2));
            System.out.println(http.getResponseMessage());
            JSONObject resultJSON;
            if (response.toString().contains("status")) {
                //Handle error
                resultJSON = new JSONObject(response.toString());
                JSONObject status = resultJSON.getJSONObject("status");
                errorMessage.setCode(Integer.valueOf(status.getString("code")).intValue());
                String resultExplanation = status.getString("message");
                errorMessage.setMessage(resultExplanation);
                System.out.println("Error Message= " + errorMessage.getMessage());
                HttpUtils.getSessionMap().put("errorMassage", errorMessage);
                uri = contextPath + "/" + "payment/processingError.xhtml";
                HttpUtils.getFacesExternalContext().redirect(uri);
            } else {
                String signature = response.toString().substring(1, response.toString().length() - 2);
                mobilePayment.setSignature(signature);
                transaction.setSignature(signature);
                System.out.println(transaction.getSignature());
                paymentManager.updateTransaction(transaction);
                requestParamsMap = new HashMap<String, String>();
                if (mobilePayment.getCurrency().equals("USD")) {
                    requestParamsMap.put("cpm_amount", String.valueOf(Math.round(Math.floor(mobilePayment.getAmount()))));
                } else {
                    requestParamsMap.put("cpm_amount", String.valueOf(Math.round(Math.floor(mobilePayment.getAmount() * 500))));
                }
                requestParamsMap.put("cpm_currency", mobilePayment.getCurrency());
                requestParamsMap.put("cpm_site_id", mobilePayment.getSiteID());
                requestParamsMap.put("cpm_trans_id", mobilePayment.getTransactionID());
                requestParamsMap.put("cpm_trans_date", transDateString);
                requestParamsMap.put("cpm_payment_config", mobilePayment.getPaymentConfig());
                requestParamsMap.put("cpm_page_action", mobilePayment.getPageAction());
                requestParamsMap.put("cpm_version", mobilePayment.getVersion());
                requestParamsMap.put("cpm_language", mobilePayment.getLanguage());
                requestParamsMap.put("cpm_designation", mobilePayment.getDesignation());
                requestParamsMap.put("cpm_custom", mobilePayment.getCustom());
                requestParamsMap.put("apikey", mobilePayment.getApiKey());
                requestParamsMap.put("signature", mobilePayment.getSignature());
                requestParamsMap.put("notify_url", mobilePayment.getNotifyURL());
                requestParamsMap.put("return_url", mobilePayment.getReturnURL());
                requestParamsMap.put("cancel_url", mobilePayment.getCancelURL());

                postData = SendHttpRequest.getURLPostDataString(requestParamsMap);
                /*CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
                http = SendHttpRequest.sendGetData(MobilePayment.PAYMENT_URL, postData, CONTENT_TYPE);
                response = RetrieveHttpResponse.retrieveResponse(http);
                System.out.println(http.getResponseCode());
                System.out.println(response);
                System.out.println(http.getResponseMessage());*/
 /* HttpServletResponse httpServletResponse=HttpUtils.getHttpServletResponse();
                uri = contextPath +"/cinetPay/signedPayment.jsp" + "?" + postData;
                httpServletResponse.encodeRedirectURL(uri);
                httpServletResponse.sendRedirect(uri);*/
                //HttpUtils.getSessionMap().put("sessionID", HttpUtils.getHttpSession());
                System.out.println("sent transactionID: " + mobilePayment.getTransactionID());
                HttpUtils.getServletContext().setAttribute(mobilePayment.getTransactionID(), HttpUtils.getSessionMap());
                HttpUtils.getApplicationMap().put(mobilePayment.getTransactionID(), accountModelView.getLogins().get(clientModelView.getClient().getClientID()));
                uri = MobilePayment.PAYMENT_URL + "?" + postData;
                HttpUtils.getFacesExternalContext().redirect(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String retrievePaymentTransaction() {

        String uri = "";
        HttpSession session = null;
        try {
            if (!transactionID.equals("")) {
                System.out.println("Dans retrievePaymentTransaction");

                Map<String, String> sentParamsMap = HttpUtils.getRequestParamsMap();
                Set<String> keySet = sentParamsMap.keySet();
                Iterator<String> it = keySet.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    System.out.println(next + ": " + sentParamsMap.get(next));
                }

                System.out.println("new session: " + HttpUtils.getHttpSession());
                System.out.println("new sessionID: " + HttpUtils.getHttpSession().getId());
                System.out.println("transactionID: " + (String) sentParamsMap.get("cpm_trans_id"));
                System.out.println("previous session app: " + HttpUtils.getApplicationMap().get((String) sentParamsMap.get("cpm_trans_id")));
                System.out.println("previous sessionID app: " + ((HttpSession) HttpUtils.getApplicationMap().get((String) sentParamsMap.get("cpm_trans_id"))).getId());

                if (HttpUtils.getHttpSession().getId() != ((HttpSession) HttpUtils.getApplicationMap().get((String) sentParamsMap.get("cpm_trans_id"))).getId()) {
                    session = (HttpSession) HttpUtils.getApplicationMap().get((String) sentParamsMap.get("cpm_trans_id"));
                    System.out.println("Previous session: " + session);
                } else {
                    session = HttpUtils.getHttpSession();
                    System.out.println("New session: " + session);
                }
                String contextPath = HttpUtils.getContextPath();
                paymentModelView = (PaymentModelView) session.getAttribute("paymentModelView");
                MobilePayment mobilePayment = paymentModelView.getMobilePayment();
                String serviceTypeID = (String) sentParamsMap.get("cpm_custom");
                String transactionID = (String) sentParamsMap.get("cpm_trans_id");
                System.out.println("received transactionID: " + transactionID);
                if (sentParamsMap.get("cpm_amount") == null || sentParamsMap.get("cpm_amount") == " ") {
                    mobilePayment.setAmount(0.0);
                } else {
                    mobilePayment.setAmount(Double.valueOf(sentParamsMap.get("cpm_amount")));
                }
                mobilePayment.setSiteID(sentParamsMap.get("cpm_site_id"));
                mobilePayment.setLanguage(sentParamsMap.get("cpm_language"));
                mobilePayment.setVersion(sentParamsMap.get("cpm_version"));
                mobilePayment.setPaymentConfig(sentParamsMap.get("cpm_payment_config"));
                mobilePayment.setPageAction(sentParamsMap.get("cpm_page_action"));
                mobilePayment.setPaymentMethod(sentParamsMap.get("payment_method"));
                mobilePayment.setSignature(sentParamsMap.get("signature"));
                mobilePayment.setPhonePrefixe(sentParamsMap.get("cpm_phone_prefixe"));
                mobilePayment.setCellPhoneNbr(sentParamsMap.get("cel_phone_num"));

                //Map sessionMap = (Map) HttpUtils.getServletContext().getAttribute(transactionID);
                //System.out.println(sessionMap);
                //  HttpSession session = (HttpSession) sessionMap.get("sessionID");
                System.out.println("serviceTypeID: " + serviceTypeID);
                PaymentManager paymentManager = new PaymentManager();
                Transaction transaction = MainDAO.findTransaction(transactionID);
                Payment payment = new Payment();
                System.out.println("Payment method: " + sentParamsMap.get("payment_method"));
                if (sentParamsMap.get("payment_method").equals("MoMo") || sentParamsMap.get("payment_method").equals("OM")) {
                    PaymentMethod paymentMethod = MainDAO.findPaymentMethod("PM_2");
                    payment.setPaymentMethod(paymentMethod);
                    transaction.setMobilePhoneNbr(mobilePayment.getCellPhoneNbr());
                    transaction.setPhoneCountryCode(mobilePayment.getPhonePrefixe());
                }
                HashMap<String, String> requestParamsMap = new HashMap<String, String>();
                requestParamsMap.put("cpm_site_id", sentParamsMap.get("cpm_site_id"));
                requestParamsMap.put("cpm_trans_id", sentParamsMap.get("cpm_trans_id"));
                requestParamsMap.put("apikey", mobilePayment.getApiKey());
                String postData = SendHttpRequest.getURLPostDataString(requestParamsMap);
                String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
                HttpURLConnection http = SendHttpRequest.sendPostData(MobilePayment.VERIFICATION_URL, postData, CONTENT_TYPE);
                StringBuffer response = RetrieveHttpResponse.retrieveResponse(http);

                JSONObject resultJSON = new JSONObject(response.toString());
                JSONObject transactionJSON = resultJSON.getJSONObject("transaction");
                transaction.setBrokerTransactionDate(transactionJSON.getString("cpm_trans_date"));
                transaction.setBrokerPaymentID(transactionJSON.getString("cpm_payid"));
                transaction.setMessage(transactionJSON.getString("cpm_error_message"));
                transaction.setResult(transactionJSON.getString("cpm_result"));
                transaction.setStatus(transactionJSON.getString("cpm_trans_status"));
                System.out.println("Result: " + transactionJSON.getString("cpm_result"));
                System.out.println("Signature stored: " + transaction.getSignature());
                System.out.println("Signature received: " + transactionJSON.getString("signature"));
                long transactionAmount = 0;
                if (mobilePayment.getCurrency().equals("USD")) {
                    transactionAmount = Math.round(Math.floor(transaction.getTotal()));
                } else {
                    transactionAmount = Math.round(Math.floor(transaction.getTotal() * 500));
                }

                if (transactionJSON.getString("cpm_result").equals("00") && transactionJSON.getLong("cpm_amount") == transactionAmount && transactionJSON.getString("signature").equals(transaction.getSignature())) {
                    paymentModelView.setPaidTransaction(true);
                    payment.setPaymentDate(convertDate(transactionJSON.getString("cpm_payment_date") + " " + transactionJSON.getString("cpm_payment_time")));
                    payment = paymentManager.addNewPayment(payment);
                    // uri = contextPath + "/" + "payment/transactionCompletion.xhtml";
                } else {
                    paymentModelView.setPaidTransaction(false);
                    errorMessage.setCode(Integer.valueOf(transaction.getResult()).intValue());
                    String resultExplanation = transaction.getMessage();
                    errorMessage.setMessage(resultExplanation);
                    System.out.println("Error Message= " + errorMessage.getMessage());
                    HttpUtils.getSessionMap().put("errorMassage", errorMessage);
                    //uri = contextPath + "/" + "payment/processingError.xhtml";
                }
                paymentManager.updateTransaction(transaction);
                /* enrollmentManager.removeEnrollment(enrollment);
                EnrollmentListener enrollmentListener = (EnrollmentListener) session.getAttribute("enrollmentListener");
                enrollmentListener.reinitializeEnrollment();
                // HttpUtils.getFacesExternalContext().redirect(uri);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendXMLPostData(HttpsURLConnection con, String xmlRequest) {
        try {
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Language", "application/soap+xml; charset=utf-8"); // request properties, set your needs
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(xmlRequest.getBytes("utf-8"));
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
    }

    public String removeDash(String orig) {
        String result = "";
        int cur = 0, next = 0;
        while (next != -1) {
            next = orig.indexOf("-", cur);
            if (next != -1) {
                result += orig.substring(cur, next);
            } else {
                result += orig.substring(cur);
            }
            cur = next + 1;
        }
        return result;
    }

    public void setOrigURI(String origURI) {
        this.origURI = origURI;
    }

    public String getOrigURI() {
        return this.origURI;
    }

    public PaymentManager getPaymentManager() {
        return paymentManager;
    }

    public void setPaymentModelView(PaymentModelView paymentModelView) {
        this.paymentModelView = paymentModelView;
    }

    public void setAccountModelView(AccountModelView accountModelView) {
        this.accountModelView = accountModelView;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }

    public void ongoingPayments(ActionEvent event) {
        String uri = "";
        try {
            if (accountModelView == null || !accountModelView.getAuthenticated()) {
                origURI = "/payment/ongoingPayments.xhtml";
                uri = "account/authentication.xhtml";
                HttpUtils.getFacesExternalContext().redirect(uri);
            } else {
                String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
                uri = contextPath + "/payment/ongoingPayments.xhtml";
                HttpUtils.getFacesExternalContext().redirect(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void historicalPayments(ActionEvent event) {
        String uri = "";
        try {
            if (accountModelView == null || !accountModelView.getAuthenticated()) {
                origURI = "/payment/historicalPayments.xhtml";
                uri = "account/authentication.xhtml";
                HttpUtils.getFacesExternalContext().redirect(uri);
            } else {
                String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
                uri = contextPath + "/payment/historicalPayments.xhtml";
                HttpUtils.getFacesExternalContext().redirect(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectPayment(AjaxBehaviorEvent event) {
        try {
            String form = (String) HttpUtils.getRequestFormID();
            String paymentID = (String) HttpUtils.getRequestParamsMap().get(form + ":selectedPaymentID");
            Payment payment = paymentManager.findPayment(paymentID);
            paymentModelView.setPaymentID(paymentID);
            paymentModelView.setPayment(payment);
            String uri = HttpUtils.getRequestURI();
            System.out.println(uri);
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectPaymentMethod(AjaxBehaviorEvent event) {
        try {
            String form = HttpUtils.getRequestFormID();
            String paymentMethodID = (String) HttpUtils.getRequestParamsMap().get(form + ":selectPaymentMethodID");
            PaymentMethod paymentMethod = MainDAO.findPaymentMethod(paymentMethodID);
            System.out.println("PaymentMentodID: " + paymentMethodID);
            paymentModelView.setPaymentMethod(paymentMethod);
            String uri = HttpUtils.getRequestURI();
            System.out.println(uri);
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date convertDate(String curDate) {
        Date myDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            myDate = format.parse(curDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myDate;
    }

    public void redirectOutcomePage(ActionEvent evt) {
        String contextPath = HttpUtils.getContextPath();
        String uri = "";
        try {
            if (paymentModelView.getPaidTransaction() && !paymentModelView.getNonPaidTransaction()) {
                uri = contextPath + "/welcomeToSBO.xhtml";
                paymentModelView.setPaidTransaction(false);
                paymentModelView.setNonPaidTransaction(false);
                HttpUtils.getFacesExternalContext().redirect(uri);
            } else if (!paymentModelView.getPaidTransaction() && paymentModelView.getNonPaidTransaction()) {
                uri = contextPath + "/payment/paymentProcessing.xhtml";
                paymentModelView.setPaidTransaction(false);
                paymentModelView.setNonPaidTransaction(false);
                HttpUtils.getFacesExternalContext().redirect(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchPaymentlForGivenDate(ActionEvent evt) {
        try {
            String uri = (String) HttpUtils.getRequestURI();
            String searchPaymentDate = paymentModelView.getSearchPaymentDate();
            paymentModelView.setAllDailyPaymentList(MainDAO.findAllDailyPayment(MainDAO.convertStringToDate(searchPaymentDate)));
            if (paymentModelView.getAllDailyPaymentList() != null && paymentModelView.getAllDailyPaymentList().size() != 0) {
                paymentModelView.setPayment(paymentModelView.getAllDailyPaymentList().get(0));
            }
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
