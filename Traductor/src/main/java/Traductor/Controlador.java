/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Traductor;

/**
 *
 * @author holberton
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONObject;

public class Controlador implements ActionListener, FocusListener {
    
    private final String API_KEY = "AIzaSyCbA12CKJ14B4TPHplrqNb7y6ePcIc0dFo";
    private final String API_URL = "https://translation.googleapis.com/language/translate/v2?key=" + API_KEY;
    private final Interfaz interfaz;
    private final Texto modelo;
    private Font fuenteActual;
    private float tamañoActual;
    private Font nuevaFuente;
    private JComponent ultimoFoco = (JComponent) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    
    public Controlador(Texto modelo, Interfaz interfaz) {
        this.interfaz = interfaz;
        this.modelo = modelo;
        interfaz.BtnTraducir.addActionListener(this);
        interfaz.BtnCambiarColor.addActionListener(this);
        interfaz.BtnMasTamaño.addActionListener(this);
        interfaz.BtnMenosTamaño.addActionListener(this);
        interfaz.JCOrigen.addActionListener(this);
        interfaz.JCDestino.addActionListener(this);
        interfaz.TexOrigen.addFocusListener(this);
        interfaz.TexDestino.addFocusListener(this);
    }
    
    public void Traducir(Texto modelo) {
        //JSON Para la petición
        JSONObject ingresoJSON = new JSONObject()
        .put("q", modelo.getTexto())
        .put("source", modelo.getOrigen())
        .put("target", modelo.getDestino())
        .put("format", "text");

        //conexión a la API
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setDoOutput(true);
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            //Enviar la petición
            try (OutputStream salida = conexion.getOutputStream()) {
                byte[] ingreso = ingresoJSON.toString().getBytes("utf-8");
                salida.write(ingreso, 0, ingreso.length);
            }

            //Leer la respuesta
            StringBuilder respuesta = new StringBuilder();
            try (Scanner scanner = new Scanner(conexion.getInputStream())) {
                while (scanner.hasNext()) {
                    respuesta.append(scanner.nextLine());
                }

                //Procesar el JSON para obtener solo la traduccion
                JSONObject respuestaJSON = new JSONObject(respuesta.toString());
                JSONArray traducciones = respuestaJSON.getJSONObject("data").getJSONArray("translations");
                String traduccion  =  traducciones.getJSONObject(0).getString("translatedText");

                interfaz.TexDestino.setText(traduccion);

            } catch (IOException e) {
                try (Scanner errorScanner = new Scanner(conexion.getErrorStream())) {
                    StringBuilder errorResponse = new StringBuilder();
                    while (errorScanner.hasNext()) {
                        errorResponse.append(errorScanner.nextLine());
                    }
                    System.out.println("Respuesta de error: " + errorResponse.toString());
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL malformada: " + e.getMessage());
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "No hay conexion a internet", "Error de conexion", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println("Error en la solicitud: " + e.getMessage());
        }
    }
    
    public String convertirIdioma(String idiomaSeleccionado) {
        switch (idiomaSeleccionado) {
            case "Español":
                return "es";
            case "Ingles":
                return "en";
            case "Frances":
                return "fr";
            default:
                return "";
        }
    }
    
    public void ajustarTamaño(ActionEvent e) {
        try {
            if (ultimoFoco instanceof JTextArea) {
            JTextArea textoArea = (JTextArea) ultimoFoco;
            fuenteActual = textoArea.getFont();
            tamañoActual = fuenteActual.getSize();
            float cambio = (e.getSource() == interfaz.BtnMasTamaño) ? 5f : -5f;
            nuevaFuente = fuenteActual.deriveFont(tamañoActual + cambio);
            textoArea.setFont(nuevaFuente);
            }
        } catch (Exception ex) {
            System.out.println("Excepcion: " + ex);
        }
    }
    
    private void cambiarColorTexto() {
        if (ultimoFoco instanceof JTextArea) {
            JTextArea textoArea = (JTextArea) ultimoFoco;
            Color colorSeleccionado = JColorChooser.showDialog(null, "Seleccione un color", Color.BLACK);
            
            if (colorSeleccionado != null) {
                textoArea.setForeground(colorSeleccionado);
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) {        
        try {
            if (e.getSource() == interfaz.BtnTraducir) {
                modelo.setTexto(interfaz.TexOrigen.getText());
                modelo.setOrigen(convertirIdioma((String) interfaz.JCOrigen.getSelectedItem()));
                modelo.setDestino(convertirIdioma((String) interfaz.JCDestino.getSelectedItem()));
                
                Traducir(modelo);
            }
        } catch (NullPointerException ex) {
            System.out.println("EError: Uno de los componentes de la interfaz no está inicializado: " + ex);
        }
        
        if (e.getSource() == interfaz.BtnMasTamaño || e.getSource() == interfaz.BtnMenosTamaño) {
            ajustarTamaño(e);
        }
        
        if (e.getSource() == interfaz.BtnCambiarColor) {
            cambiarColorTexto();
        }
    }
        public void focusGained(FocusEvent e) {
        ultimoFoco = (JComponent) e.getSource();
    }
    
    public void focusLost(FocusEvent e) {
        
    }
}
