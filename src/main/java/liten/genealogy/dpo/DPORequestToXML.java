/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.dpo;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author FACULTY
 */
public class DPORequestToXML {

    public String marshal(DPORequest curDPORequest) {
        StringWriter stringWriter = new StringWriter();
        try {
            stringWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            JAXBContext context = JAXBContext.newInstance(DPORequest.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(curDPORequest, stringWriter);
            /* QName qName = new QName("businessModel.dpo", "DPORequest");
            JAXBElement<DPORequest> root = new JAXBElement<>(qName, DPORequest.class, curDPORequest);
            m.marshal(root, stringWriter);*/

            String xml = stringWriter.toString();
            stringWriter.close();
            return xml;
        } catch (JAXBException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String marshal(CreateToken curCreateToken) {
        StringWriter stringWriter = new StringWriter();
        try {
            stringWriter.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            JAXBContext context = JAXBContext.newInstance(CreateToken.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(curCreateToken, stringWriter);
            /* QName qName = new QName("businessModel.dpo", "DPORequest");
            JAXBElement<DPORequest> root = new JAXBElement<>(qName, DPORequest.class, curDPORequest);
            m.marshal(root, stringWriter);*/

            String xml = stringWriter.toString();
            stringWriter.close();
            return xml;
        } catch (JAXBException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public DPOResponse unmarshal(String xml) {
        try {
            System.out.println(xml);
            JAXBContext context = JAXBContext.newInstance(DPOResponse.class);
            Unmarshaller u = context.createUnmarshaller();
            return (DPOResponse) u.unmarshal(new StringReader(xml));
            //return (DPOResponse) (u.unmarshal(new StreamSource(xml), DPOResponse.class).getValue());
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
}
