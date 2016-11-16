/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.asi.restaurantebcd.negocio.base;

import java.io.File;
import java.net.InetAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author samaelopez
 */
@Stateless
public class GestorEmail implements GestorEmailLocal {


//    @EJB
//    private UtilsPOSLocal ejbUtilsPOS;
//     @EJB
//    private BusquedasGenLocal ejbBusqGen;

    /**
     * Encabezado del mensaje.
     */
    private String header = "";
    /**
     * Pie de pagina del mensaje.
     */
    private String footer = "";
    /**
     * Nombre del que envia el mensaje.
     */
    private String nombre = "";
    /**
     * Variable para indocar la ruta de la plantilla.
     */
    private String rutaPlantilla = "";
    /**
     * Variable para la ruta de la imagen que va ser incluida.
     */
    private String rutaImgAttach = "";
    /**
     * variable del contexto.
     */
    private FacesContext context;
    /**
     * Objeto de tipo Session.
     */
    @Resource(name = "mail/dbc")
    private Session mailSession;
    
    
    final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Crea y envia email con recursos y plantillas del web.
     *
     * @param contenido Cuerpo de mail.
     * @param asunto asunto del correo.
     * @param nombre nombre del usurio de correo.
     * @param lstMails lista de email de destinatarios.
     * @param pheader encabezado.
     * @throws Exception error generico.
     */

    public void enviarEmail(String contenido, String asunto,
            String nombre, List lstMails, String pheader) throws Exception {
        StringBuffer err = null;
        boolean exito = true;
        String template = null;
        List< String> lstImgsAttach = new ArrayList();
        String mensajeHTML = null;
//        Genconfig conf = null;
//        GenconfigPK confPK = null;
        footer = "Bodeguita del Cerdito"
                + " <br>Visítenos en www.bodeguitadelcerdito.com";
        this.nombre = "Bodeguita del Cerdito ";
        try {
            /**
             * Configuración para obtener el archivo de la plantilla para envio
             * de correo.
             */
//            confPK = new GenconfigPK();
//            confPK.setCodcia(Numeros.CERO.getBigInteger());
//            confPK.setCodmod(Modulo.GENERAL.getCodigo());
//            confPK.setCodpant(Numeros.CERO.getBigInteger());
//            confPK.setVersion("PLANTILLA_CORREO");
//            conf = ejbBusqGen.buscarConfigMod(confPK);
            template = getPlantillaAlternativa();
            //--Obteniendo la imagen para la plantilla.
//            confPK = new GenconfigPK();
//            confPK.setCodcia(Numeros.CERO.getBigInteger());
//            confPK.setCodmod(Modulo.GENERAL.getCodigo());
//            confPK.setCodpant(Numeros.CERO.getBigInteger());
//            confPK.setVersion("LOGO_CORREO");
//            conf = ejbBusqGen.buscarConfigMod(confPK);
//            if (conf != null) {
//                lstImgsAttach.add("");
////            }
            exito = true;
        } catch (NullPointerException e) {
            err = new StringBuffer();
            err.append("Error en método enviarEmail. ");
            err.append("Detalle[");
            err.append(e.getMessage());
            err.append("]");
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.toString());
            exito = false;
            e.printStackTrace();
        } catch (Exception e) {
              e.printStackTrace();
            err = new StringBuffer();
            err.append("Error en método enviarEmail. ");
            err.append("Detalle[");
            err.append(e.getMessage());
            err.append("]");
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.toString());
            exito = false;
        }
        /**
         * Si no se puede obtener los archivos de plantilla, obtener una
         * plantilla alternativa.
         */
        if (!exito) {
            template = getPlantillaAlternativa();
        }
        //asunto = toHtmlString(asunto);
        pheader = toHtmlString(pheader);
        contenido = toHtmlString(contenido);
        footer = toHtmlString(footer);
        mensajeHTML = tunearMensaje(pheader, contenido, footer, template);

        boolean enviado = enviarEmailHTML(lstMails, asunto, mensajeHTML,
                nombre, lstImgsAttach, null);

        if (!enviado) {
            err = new StringBuffer();
            err.append("No se pudo enviar el correo. ");
            err.append("<br/>Revise el log para ver detalles.");
            Logger.getLogger(this.getClass().getName())
                    .log(Level.INFO, err.toString());
            
            //throw new Exception(err.toString());
        }
    }

    /**
     * Método que construye una plantilla alternativa en caso de que no se
     * puedan leer los archivos de imagenes y plantillas que serán adjuntados.
     *
     * @return String --> plantilla alternativa.
     */
    public String getPlantillaAlternativa() {
        StringBuffer html = new StringBuffer();
        // <editor-fold defaultstate="collapsed" desc="Plantilla">
        html.append("<html>");
        html.append("<head>");
        html.append("<title> Bodeguita del Cerdito </title>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div style=\"margin:0%;margin-top:5%;\">");
        html.append("<p style=\"font-family:verdana;color:#4682B4;font-size:");
        html.append("15px;margin:10px;font-weight:bold;text-align:left;\">");
        html.append("[HEADER]");
        html.append("</p>");
        html.append("<br>");
        html.append("<p style=\"font-family:verdana;color:#4682B4;");
        html.append("font-size:14px;margin:20px\">");
        html.append("[BODY]");
        html.append("</p>");
        html.append("<br>");
        html.append("<p style=\"font-family:verdana;color:#4682B4;");
        html.append("font-size:12px;font-weight:bold;margin:10px;");
        html.append("text-align:left;\">");
        html.append("[FOOTER]");
        html.append("</p>");
        html.append("</div></body></html>");
        // </editor-fold>
        return html.toString();
    }

    /**
     * Método que permite buscar todos los caracteres tildados y reemplazarlos
     * por códigos html, por ejemplo la palabra
     * <code><b>Murciélago</b>
     * </code>, será reemplazada por
     * <code><b>Murci&eacute;lago.</b></code>.
     *
     * @param str2Convert String a convertir.
     * @return Cadena caracterés con los códigos html.
     */
    private String toHtmlString(String str2Convert) {
        String[] tildados = {"á", "é", "í", "ó", "ú",
            "ä", "ë", "ï", "ö", "ü",
            "Ä", "Ë", "Ï", "Ö", "Ü",
            "Á", "É", "Í", "Ó", "Ú",
            "ñ", "Ñ"};
        String[] codigos = {"&aacute;", "&eacute;", "&iacute;", "&oacute;", "&uacute;",
            "&auml;", "&euml;", "&iuml;", "&ouml;", "&uuml;",
            "&Auml;", "&Euml;", "&Iuml;", "&Ouml;", "&Uuml;",
            "&Aacute;", "&Eacute;", "&Iacute;", "&Oacute;", "&Uacute;",
            "&ntilde;", "&Ntilde;"};
        //--
        if (str2Convert == null) {
            return "";
        }
        //--
        String strFinal = str2Convert;
        char[] strProc = str2Convert.toCharArray();
        int pos = 0;
        //--
        for (char c : strProc) {
            pos = 0;
            for (String t : tildados) {
                if (("" + c).equals(t)) {
                    strFinal = strFinal.replace("" + c, codigos[pos]);
                }
                pos++;
            }
        }
        return strFinal;
    }

    /**
     * Permite utilizar una plantilla HTML para que el mensaje se envíe con un
     * formato predefinido. La plantilla debe tener estos valores dentro de
     * ella: -[HEADER]: Encabezado del email. -[BODY]: Cuerpo del email.
     * -[FOOTER]: Pie de página del email. Para que despues estos sean
     * reemplazados por los datos propios del email.
     *
     * @param header Encabezado del mensaje
     * @param body Cuerpo del mensaje.
     * @param footer Pie de página del mensaje.
     * @param template el contenido de la plantilla a tunear.
     * @return String Mensaje con formato HTML
     */
    public String tunearMensaje(String header, String body,
            String footer, String template) {
        String htmlMsg = new String();
        htmlMsg = template.replace("[HEADER]", header);
        htmlMsg = htmlMsg.replace("[BODY]", body);
        htmlMsg = htmlMsg.replace("[FOOTER]", footer);
        return htmlMsg;
    }

    /**
     * *
     * Permite enviar Correos con una plantilla HTML predefinida la cual incluye
     * las imagenes que se le adjuntarán. Las imagenes en la plantilla deben ser
     * nombradas, de la siguiente manera: - "figuran", donde n es un numero
     * secuencial de 1 en adelate Ejemplo: src="cid:figura1"
     *
     * @param lstMails Objeto de tipo Lista de Strings el cual contiene los
     * emails destinatarios. En caso de ser null o si la lista está vacía.
     * obviamente no podrá ser enviado.
     * @param asunto Objeto de tipo String Contiene el asunto o título del email
     * @param contenido Objeto de tipo String Contiene el contenido del email.
     * @param nombre Objeto de tipo String que contiene el nombre de quien emite
     * el correo electrónico.
     * @param lstImgsAttach Listado con las rutas absolutas de las imagenes que
     * serán adjuntadas a la plantilla del email.
     * @param archAttach Listado de las rutas con los archivos a adjuntar , null
     * si no se adunta nada
     * @return true o false en caso de que el email haya sido enviado.
     */
    public boolean enviarEmailHTML(List< String> lstMails, String asunto,
            String contenido, String nombre, List< String> lstImgsAttach, List<String> archAttach) {
        StringBuffer err = null;
        try {

            if (lstMails == null) {
                return false;
            } else if (lstMails.isEmpty()) {
                return false;
            } else if (asunto.compareTo("") == 0) {
                return false;
            } else {
                Message mensaje = new MimeMessage(mailSession);
                Address de = new InternetAddress(mailSession.getProperties()
                        .getProperty("mail.from"), nombre);
                Address[] para = new Address[lstMails.size()];
                int i = 0;
                for (String strMail : lstMails) {
                    para[i] = new InternetAddress(strMail);
                    i++;
                }
                mensaje.setFrom(de);
                //BCC: Blind Carbon Copy, permite mandar los correos
                //con destinatarios ocultos.
                mensaje.addRecipients(Message.RecipientType.CC, para);
                //  mensaje.addRecipients(Message.RecipientType.CC, para);

                mensaje.setSubject(asunto);


                Multipart multipart = new MimeMultipart("related");
                /**
                 * Procesando plantilla HTML
                 */
                BodyPart texto = new MimeBodyPart();
                texto.setContent(contenido, "text/html");
                multipart.addBodyPart(texto);

                /**
                 * Procesar Imagenes.
                 */
                MimeBodyPart imagen;
                int j = 1;
                for (String rstr : lstImgsAttach) {
                    imagen = new MimeBodyPart();
                    imagen.attachFile(getRecurso(rstr));
                    //imagen.attachFile(rstr);
                    imagen.setHeader("Content-ID", "<figura" + j + ">");
                    multipart.addBodyPart(imagen);
                    j++;
                }
                /**
                 * Adjuntar archivos
                 */
                MimeBodyPart archivo;
                if (archAttach != null) {
                    for (String dirFile : archAttach) {
                        archivo = new MimeBodyPart();
                        File arch = new File(dirFile);
                        FileDataSource source = new FileDataSource(dirFile);
//              archivo.attachFile(arch);
                        archivo.setDataHandler(new DataHandler(source));
                        archivo.setFileName(arch.getName());
                        multipart.addBodyPart(archivo);
                    }
                }

                mensaje.setContent(multipart);
                //Valida si server esta accesible.
                InetAddress ina;
                String serverHostname = mailSession.getProperties()
                        .getProperty("mail.smtp.host");
                //ina = InetAddress.getByName(serverHostname);
//          if (ina.isReachable(
//                  Numeros.SEIS.getBigInteger().multiply(
//                  Numeros.MIL.getBigInteger()).intValue())) {
                Transport t = mailSession
                        .getTransport(mailSession.getProperties()
                        .getProperty("mail.transport.protocol"));
                t.connect(mailSession.getProperties()
                        .getProperty("mail.smtp.user"),
                        mailSession.getProperties()
                        .getProperty("mail.smtp.password"));
                t.sendMessage(mensaje, mensaje.getAllRecipients());
                t.close();
                return true;
//          } else {
//              return false;
//          }
            }
        } catch (MessagingException e) {
            err = new StringBuffer();
            err.append("Error en el método enviarEmailHTML. ");
            err.append("Detalle [");
            err.append(e.getMessage());
            err.append("]");
            Logger.getLogger(this.getClass().getName())
                    .log(Level.INFO, err.toString());
            return false;
        } catch (Exception e) {
            err = new StringBuffer();
            err.append("Error en el método enviarEmailHTML. ");
            err.append("Detalle [");
            err.append(e.getMessage());
            err.append("]");
            Logger.getLogger(this.getClass().getName())
                    .log(Level.INFO, err.toString());
            return false;
        }
    }

    /**
     * Convierte un String en un arrego. El String es separado por el caracter
     * especificado.
     *
     * @param linea String q se desea convertir a array
     * @param caracter por el cual se va a separar
     * @return List de String del separar por el caracter
     */
    public List< String> CrearArray(String linea, String caracter) {
        List< String> list = new ArrayList();
        StringTokenizer token = new StringTokenizer(linea, caracter);
        while (token.hasMoreTokens()) {
            list.add(token.nextToken().trim());
        }
        return list;
    }
    
        public final File getRecurso(final String path) throws Exception {
        StringBuffer err;
        URI uriObj;
        try {
            uriObj = getClass().getResource(path).toURI();
            return new File(uriObj);
        } catch (Exception e) {
            err = new StringBuffer();
            err.append("Error al ejecutar el método getRecurso. ");
            err.append("Detalle [");
            err.append(e.getMessage());
            err.append("]");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    err.toString());
            throw e;
        }
    }
}