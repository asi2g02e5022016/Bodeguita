/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.restaurantebcd.negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author samaelopez
 */
public class Validator {
    
    /*<editor-fold defaultstate="collapsed"
                   desc="Constantes para manejo de errores">*/
    /**Constante de nivel INFO = 0.*/
    public static final int INFO = 0;
    /**Constante de nivel WARNING = 1.*/
    public static final int WARNING = 1;
    /**Constante de nivel ERROR = 2.*/
    public static final int ERROR = 2;
    /**Constante de nivel FATAL = 3.*/
    public static final int FATAL = 3;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constantes para validaciones">
    /**Constante de validación que permite analizar un patrón donde solo
     * existen espacios en blanco.*/
    public static final String ESPENBLANCPTR = "\\s+";
    /**Constante de validación que permite analizar un patrón de texto
     * que coincida con el formato de un email por ejemplo nombre@dominio.*/
    public static final String EMAILPTR =
            "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    /**Constante de validación que permite analizar un patrón de texto
     * que coincida solo con números enteros sin signo.*/
    public static final String NUMENTPTR = "\\d+";
    /**Constante de validación que permite analizar un patrón de texto
     * que coincida solo con números enteros o con números decimales sin
     * signo.*/
    public static final String NUMDECPTR =
           "(\\d+)|(\\d+([.]\\d+)?)|(\\d+[.](\\d+)?)|([.]\\d+)";
    /** Expresión regular que identifica a los tipos de datos BigInteger
     * con signo o sin signo. */
    public static final String NUMENTCSIGPTR =
           "(\\+|\\-)?((\\d+)|(\\d+([.]\\d+)?)|(\\d+[.](\\d+)?)|([.]\\d+))?";
    /** Expresión regular que identifica a los tipos de datos BigDecimal
     * con signo o sin signo. */
    public static final String NUMDECCSIGPTR =
                  "(\\d+[.]\\d*)|([.]\\d+)|([-]\\d+[.]\\d*)|([-][.]\\d+)";
    /**
     * Constante de validación que permite analizar un patrón de texto
     * que coincida con el de un número telefónico por ejemplo 22708585,
     * 2526-9898, (501) 2636-6536, (501)2636-6536, (301)26366536. */
    public static final String TELEFONPTR =
            "(\\d+)|(\\d+[-]?\\d+)|(([(]\\d+[)]\\s*)?\\d+[-]?\\d+)";
    /**
     * Constante que permite validar el contenido de una fecha vajo el
     * formato <b>99/99/9999</b>. La constante valida ese patrón de texto,
     * pero no si es una fecha valida. Para validar una fecha, se debera hacer
     * uso del objeto SimpleDateFormat.*/
    public static final String FECHFORMAT00 = "\\d{2}/\\d{2}/\\d{4}";
    /**Constante que contiene el patrón de texto para un SKU.*/
    public static final String SKU_PATTERN =
            "([^\\s]+)"
          + "|((((\\w)|([\\p{Punct}])|([�?É�?ÓÚÑáéíóúñ]))+"
          + "\\s*"
          + "((\\w)|([\\p{Punct}])|([�?É�?ÓÚÑáéíóúñ]))+)+)";
    /** Constante que permite identificar un patron de texto sin espacios
     * laterales.*/
    public static final String TEXTO_SIN_ESPACIOS_LATERALES =
            "([^\\s]+)|([^\\s]+(\\s*[^\\s]+)*)";
    /** Constante que verifica simplemente el texto. Puede contener cualquier
     * cosa.*/
    public static final String TEXTO = "(\\s*)|(\\s*[^\\s]+(\\s*[^\\s]*)*)";
    /**Patrón de texto que valida a las fechas bajo el formato:
     * dd/MM/yyyy HH:mm:ss.*/
    public static final String FORMATO_DE_FECHA_DDMMYYY_HHMMSS =
            "dd/MM/yyyy HH:mm:ss";
    /**Patrón de texto que valida a las fechas bajo el formato: dd/MM/yyyy.*/
    public static final String FORMATO_DE_FECHA_DDMMYYY = "dd/MM/yyyy";
    //</editor-fold>

    private FacesContext context;
    private boolean getError;
    FacesMessage msg = null;

    public Validator(FacesContext context) {
        this.context = context;
    }

    private boolean getError() {
        return getError;
    }

    private void setError(boolean getError) {
        this.getError = getError;
    }


    
    /**
     * Este metodo es opcional y su funcionalidad es devolver El FacesMessage
     * que se ha construido.
     * @return retorna el FacesMessage que se genero
     */
    public FacesMessage getMsg() {
        return msg;
    }

    /**
     *Este metodo se encarga de poner el mensaje de aviso de validacion
     * lanzando una excepcion con severidad para obligar a que se bloquee la
     * aplicacion y es alterno al metodo setMsgValidation y debe ser invocado
     * unicamente desde el contexto de un metodo validador.
     * @param errormsg recibe el mensaje
     * @param value recibe el id del componente que lanza el mensaje
     * @param severity recibe la categoria de mensaje a desplegar
     * 0 lanza un mensaje de INFO
     * 1 lanza un mensaje de WARNING
     * 2 lanza un mensaje de ERROR
     * 3 lanza un mensaje de tipo FATAL
     */
    public String setThrowValidator(
            String errormsg, String value, int severity) {
        FacesMessage m = null;
        if (severity == 0) {
            m = new FacesMessage(FacesMessage.SEVERITY_INFO, errormsg, value);
        } else if (severity == 1) {
            m = new FacesMessage(FacesMessage.SEVERITY_WARN, errormsg, value);
        } else if (severity == 2) {
            m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errormsg, value);
        } else if (severity == 3) {
            m = new FacesMessage(FacesMessage.SEVERITY_FATAL, errormsg, value);
        }

        throw new ValidatorException(m);
    }

    /**
     * Este metodo valida que solo existan numeros en la cadena.
     * @param value recibe el valor del componente
     * @return retorna Verdadero si se encontraron numeros
     */
    public boolean soloNumeros(Object value) {
        setError(false);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            setError(true);
        }
        return getError();
    } //Fin de validacion del metodo de solo numeros

    /**
     * Este metodo valida Formato de Email.
     * @param value recibe el valor del componente
     * @return retorna Verdadero si el formato no es correcto
     */
    public boolean validarEmail(Object value) {
        setError(false);
        String email = (String) value;
        if (email.indexOf('@') == -1) {
            setError(true);
        }
        return getError();
    }

    /**
     * Este metodo valida que solo se hayan ingresado letras y espacios.
     * @param value recibe el valor del componente
     * @return retorna Verdadero si encuentra algo diferente de letras y
     * espacios.
     */
    public boolean soloLetras(Object value) {
        //It can't contain non-Letters
        Pattern p = Pattern.compile("^[[a-zA-Z]\\s]+$");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            this.setError(true);
        } else {
            this.setError(false);
        }//end else
        return this.getError();
    }//End soloLetras

    /**
     * Este metodo valida que solo se hayan ingresado letras, espacios y
     * numeros.
     * @param value recibe el valor del componente
     * @return retorna Verdadero si encuentra algo diferente de letras, numeros
     * y espacios.
     */
    public boolean soloLetrasYNumeros(Object value) {
        //It can't contain non-Letters
        Pattern p = Pattern.compile("^[[a-zA-Z0-9]\\s]+$");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            this.setError(true);
        } else {
            this.setError(false);
        }//end else
        return this.getError();
    }//End soloLetras

    /**
     * Este metodo valida que solo se hayan ingresado números enteros.
     * @param value recibe el valor del componente.
     * @return retorna false si no se cumple la condición.
     */
    public boolean soloEnteros(Object value) {
        //It can't contain non-Letters
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(value.toString());
//        if (m.matches()) {
//            return true;
//        } else {
//            return false;
//        }
        return m.matches();
    }

    /**
     * Este metodo valida que se ingresen cualquier Letra o Caracter menos
     * Numeros.
     * @param value recibe el valor del componente.
     * @return retorna Verdadero si encuentra algun numero.
     */
    public boolean noSeAdmiteNumeros(Object value) {
        setError(false);
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            setError(true);
        } else {
            setError(false);
        }
        return getError();
    }

    /**
     * Este metodo valida que el valor no este vacio.
     * @param value recibe el valor del componente.
     * @return retorna Verdadero si Esta Vacio.
     */
    public boolean noVacio(Object value) {
        setError(false);
        if (value.toString().isEmpty()) {
            setError(true);
        } else {
            setError(false);
        }
        return getError();
    }

    /**
     * Este metodo valida que el Formato de Nit sea correcto.
     * @param value recibe el valor del componente donde el nit es ingresado.
     * @param errormsg recibe el mensaje de error a desplegar.
     * @return retorna Verdadero si el formato no es correcto.
     */
    public boolean ValidarNit(Object value) {
        //Patron de nit 14 digitos
        setError(false);
        Pattern p = Pattern.compile("\\d{14}");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            setError(true);
        } else {
            setError(false);
        }
        return this.getError();

    }

    /**
     * Este metodo valida que la fecha sea menor mayor o igual a  la fecha
     * actual.
     * @param f recibe la fecha como Date si el parametro format es nulo por
     * defecto el formato sera "MM-dd-yyyy".
     * @param validacion recibe como parametro un entero que especifica si la
     * fecha tiene que ser menor, mayor o igual a la fecha actual:
     * 1 :indica que la fecha debe ser menor a la fecha actual.
     * 2 :indica que la fecha tiene que ser igual a la fecha actual.
     * 3 :indica que la fecha tiene que ser mayor a la fecha actual.
     * Si no hay parametro especificado para validation entonces la
     * validacion por defecto es 3 (mayor a la fecha actual).
     * @return retorna Verdadero si NO se cumplio la validacion.
     */
    public boolean ValidarFecha(int validacion, Date f) {
        setError(false);
        try {

            if (validacion == 1) {
                if (f.compareTo(new Date()) >= 0) {
                    setError(true);
                }
            } //end if de validacion 1
            else if (validacion == 2) {
                if (f.compareTo(new Date()) < 0 || f.compareTo(new Date()) > 0) {
                    setError(true);
                }
            }//end if validacion 2
            else if (validacion == 3) {
                if (f.compareTo(new Date()) <= 0) {
                    setError(true);
                }
            }//end if validacion 3

        } catch (Exception e) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE,
                    null, e);
        }//end catch
        return getError();
    }//End Validar Fecha

    /**
     * Este metodo valida que el numero ingresado tenga exactamente la cantidad
     * de numeros decimales deseados.
     * @param value recibe como objeto el valor.
     * @return retorna Verdadero cuando no se cumple la condicion.
     */
    public boolean NumerosFlotantes(Object value) {
        this.setError(false);
        Pattern p = Pattern.compile("\\d+(\\.{0,1}\\d{0,2})");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            this.setError(true);
        }
        return this.getError();
    }

    /**
     * Este metodo valida que el numero ingresado tenga exactamente la cantidad
     * de numeros decimales especificados en la variable <code>precision</code>.
     * @param value recibe como objeto el valor.
     * @param precision recibe la precision que se desea validar.
     * @return retorna Verdadero cuando no se cumple la condicion.
     */
    public boolean NumerosFlotantes(Object value, String precision) {
        this.setError(false);
        Pattern p = Pattern.compile("\\d+(\\.{0,1}\\d{" + precision + "})");
        Matcher m = p.matcher(value.toString().replace(",",""));
        if (!m.matches()) {
            this.setError(true);
        }
        return this.getError();
    }

    /**
     * Este metodo se encarga de verificar si hay mensajes de error en cola.
     * @return  retorna verdadero si hay mensajes en cola.
     * @throws ValidatorException error al obtener cola de mensajes.
     */
    public boolean msgsListener() throws ValidatorException {
        boolean status = false;
        if (context.getMessages().hasNext()) {
            status = true;
        }

        return status;
    }



      /**
     * Este metodo valida que el numero ingresado tenga exactamente la cantidad
     * de numeros decimales deseados.
     * @param value recibe como objeto el valor.
     * @return retorna Verdadero cuando no se cumple la condicion.
     */
    public boolean NumerosDecimales(Object value) {
        this.setError(false);
        Pattern p = Pattern.compile("\\d+(\\.{0,1}\\d{0,4})");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            this.setError(true);
        }
        return this.getError();
    }

    /**
     * Este metodo valida que el numero ingresado tenga la cantidad
     * de numeros decimales menor o igual a los especificados en
     * la variable <code>precision</code>.
     * @param value recibe como objeto el valor.
     * @param precision recibe la precision que se desea validar.
     * @return retorna Verdadero cuando no se cumple la condicion.
     */
    public boolean PrecisionFlotantes(Object value, String precision) {
        this.setError(false);
        Pattern p = Pattern.compile("\\d+([.]\\d{1," + precision + "})?");
        Matcher m = p.matcher(value.toString());
        if (!m.matches()) {
            this.setError(true);
        }
        return this.getError();
    }

    /**
     * Método que permite validar datos de en base a una expreción regular.
     * Éste método se auxilia de las constantes de validación de la clase
     * Validador. Ver sección "Constantes para validaciones."
     * @param data Dato que será evaluado. El dato a evaluar es de tipo
     * Object que posteriormente será convertido a String para entontrar un
     * patrón que indique que es valedero, en base a una de las constantes
     * de validación. En caso de que el dato a evaluar o la expreción regular
     * sean null el método retorna false.
     * @param regex Expreción regular.
     * @return boolean, true en caso de exito.
     */
    public final boolean validarData(final Object data, final String regex) {
       String dataStr = null;
       if (data == null || regex == null) {
         return false;
       } else {
         dataStr = data.toString();
         return dataStr.matches(regex);
       }
    }

    /**
     * Método que permite validar si una fecha cumple un formato específico.
     * @param fecha Objeto que contiene la fecha a evaluar.
     * @param dateFormat Formato de la fecha por ejemplo dd/MM/yyyy
     * @return boolean, true en caso de exito.
     */
    public final boolean validarFecha(final Object fecha,
           final String dateFormat) {
        if (fecha == null || dateFormat == null) {
            return false;
        }
        //--Variables de proceso.
        String fecha2Val = fecha.toString();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        boolean exito = false;
        //--
        try {
          sdf.parse(fecha2Val);
          exito = true;
        } catch (ParseException e) {
          exito = false;
        } catch (Exception e) {
          exito = false;
        }
        return exito;
    }

    public final String quitarFormato(String num) throws Exception {
        String nuevonum = num.replace(",", "");
        if (nuevonum.matches(NUMDECPTR)) {
            return nuevonum;
        } else {
            throw new Exception("'" + num + "' no es un número válido.");
        }
    }
    
    public void setMsgValidation(final String errormsg, final String idpopup,
    final int severity, HtmlGraphicImage imgpopup, final String msglog,
    final Class pclass, final Throwable excep) {
        try {
            StringBuffer mensaje = new StringBuffer();
            StringBuffer logID = new StringBuffer();
            if (errormsg == null) {
                mensaje.append("Error null.");
            } else {
               mensaje.append(errormsg);
            }
            if (msglog != null) {
               mensaje.append("<br/> IdLogError: ");
               logID.append(msglog);
               logID.append(" ");
               logID.append(new Date().toString());
               mensaje.append(logID.toString().hashCode());
            }
            String tipimg = "";
            Level lev = Level.OFF;
            if (severity == INFO) {
                this.msg = new FacesMessage(
                   FacesMessage.SEVERITY_INFO, "Info: " , mensaje.toString());
                tipimg = "imgMsgInfo";
                lev = Level.INFO;
            }
            if (severity == WARNING) {
                this.msg = new FacesMessage(
                   FacesMessage.SEVERITY_WARN, "Warning: ", mensaje.toString());
                tipimg = "imgMsgWarn";
                lev = Level.WARNING;
            }
            if (severity == ERROR) {
                msg = new FacesMessage(
                   FacesMessage.SEVERITY_ERROR, "Error: ", mensaje.toString());
                tipimg = "imgMsgError";
                lev = Level.SEVERE;
            }
            if (severity == FATAL) {
                msg = new FacesMessage(
                   FacesMessage.SEVERITY_FATAL, "Fatal.", mensaje.toString());
                tipimg = "imgMsgFatal";
                lev = Level.SEVERE;
            }
            if (imgpopup != null) {
                imgpopup.setStyleClass(tipimg);
            }
            FacesContext.getCurrentInstance().addMessage(idpopup, msg);
            if (pclass != null) {
                Logger.getLogger(pclass.getName()).log(
                        lev, "IdLogError " + logID.toString().hashCode()
                        + ": " + msglog, excep);
            }
        } catch (Exception e) {
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE,
                    "Error al Lanzar Mensaje al Contexto...", e);
        }
    }
    /**
     * M�todo Lite para poder enviar mensajes al usuario.<br/>
     * <b>Nota</b>: El contenedor puede ser cualquier componente siempre
     * y cuando se utilice la propiedad rendered. Asi en el m�todo prerender
     * en el java bean asociada al jsp quedaria:<br/>
     * <code>
     *  public void prerender() {<br/>
     *      if (validacion.msgsListener()) {<br/>
     *        algunComponenteContenedorDeMensajes.setRendered(true);<br/>
     *      } else {<br/>
     *        algunComponenteContenedorDeMensajes.setRendered(false);<br/>
     *      }<br/>
     *  }<br/>
     * </code>
     * @param ui Componente tipo contenedor que muestra los mensajes.
     * @param l Nivel de severidad
     * @param msg El mensaje a mostrar.
     */
    public final void alert(UIComponent ui, final Level l, final String msgalert) {
       
       if (ui == null) {
           System.out.println("El contenedor de los mensajes ui esta tiene asignado null");
         return;
       }
       //--
       Level lev = l;
       int level = 0;
       //--
       if (lev == null) { lev = Level.SEVERE; }
       String msgl = null;
       if (msgalert != null) {
         msgl = msgalert;
       } else {
         msgl = "Error nivel (" + Level.SEVERE.getName() + ")";
       }
       if (l.equals(Level.INFO)) {
         level = 0;
       } else if (l.equals(Level.WARNING)) {
         level = 1;
       } else if (l.equals(Level.SEVERE)) {
         level = 2;
       } else {
         level = 1;
       }
       setMsgValidation(msgl, null, level, null, null, null, null);
       ui.setRendered(true);
    }
    
}
