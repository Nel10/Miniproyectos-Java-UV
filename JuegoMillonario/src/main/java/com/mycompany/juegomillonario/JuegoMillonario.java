/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juegomillonario;

/**
 *
 * @author holberton
 */
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.util.List;

public class JuegoMillonario {
    public static void main(String[] args) {
        int puntos = 0;
        boolean usar50_50 = true;//flag 5050
        boolean saltarPregunta = true;//flag saltar
        List<Pregunta> preguntas = Pregunta.crearPreguntas();
        int preguntasRespondidas = 0;

        for (Pregunta pregunta : preguntas) {
            StringBuilder mensaje = new StringBuilder(pregunta.getPregunta() + "\n");
            for (String respuesta : pregunta.getRespuestas()) {
                mensaje.append(respuesta).append("\n");
            }
            mensaje.append("\nElige una respuesta (a, b, c, d) o escribe '50-50' o 'saltar':");

            String respuestaUsuario = (String) JOptionPane.showInputDialog(null, mensaje.toString(), "Juego para ser Millonario",
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (respuestaUsuario != null) {
                respuestaUsuario = respuestaUsuario.toLowerCase();

                //respuesta correcta
                if (respuestaUsuario.equals(String.valueOf(pregunta.getRespuestaCorrecta()))) {
                    puntos += 100;
                    preguntasRespondidas++;
                    JOptionPane.showMessageDialog(null, "¡Correcto! Tienes " + puntos + " puntos.");

                //opción 50-50
                } else if (respuestaUsuario.equals("50-50")) {
                    if (usar50_50) {
                        usar50_50 = false; //flag para 50.-50
                        
                        List<String> respuestasIncorrectas = new ArrayList<>();
                        for (String respuesta : pregunta.getRespuestas()) {
                            if (!respuesta.equals(pregunta.getRespuestaCorrecta())) {
                                respuestasIncorrectas.add(respuesta);
                            }
                        }
                        Collections.shuffle(respuestasIncorrectas); 

                        mensaje.setLength(0); // Limpiar el mensaje
                        mensaje.append(pregunta.getPregunta()).append("\n");

                        char letraRespuestaCorrecta = pregunta.getRespuestaCorrecta(); 
                        
                        String respuestaCorrectaCompleta = pregunta.getRespuestas()[letraRespuestaCorrecta - 'a']; //accedemos al arreglo directamente

                        List<String> respuestasParaMostrar = new ArrayList<>();
                        respuestasParaMostrar.add(respuestaCorrectaCompleta);
                        respuestasParaMostrar.add(respuestasIncorrectas.get(0));

                        Collections.shuffle(respuestasParaMostrar);

                        mensaje.append("1. ").append(respuestasParaMostrar.get(0)).append("\n");
                        mensaje.append("2. ").append(respuestasParaMostrar.get(1)).append("\n");

                        mensaje.append("\nElige una respuesta, una letra:");

                        respuestaUsuario = (String) JOptionPane.showInputDialog(null, mensaje.toString(), "50-50",
                                JOptionPane.QUESTION_MESSAGE, null, null, null);

                        if (respuestaUsuario != null) {
                            respuestaUsuario = respuestaUsuario.toLowerCase();
                            if (respuestaUsuario.equals(String.valueOf(letraRespuestaCorrecta))) {
                                puntos += 100;
                                preguntasRespondidas++;
                                JOptionPane.showMessageDialog(null, "¡Correcto! Tienes " + puntos + " puntos.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrecto. Tienes " + puntos + " puntos.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya has usado la opción 50-50.");
                    }

                //opción de saltar pregunta
                } else if (respuestaUsuario.equals("saltar")) {
                    if (saltarPregunta) {
                        saltarPregunta = false;
                        JOptionPane.showMessageDialog(null, "Has saltado la pregunta. Tienes " + puntos + " puntos.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya has usado la opción de saltar pregunta.");
                    }

                //respuesta incorrecta
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrecto. Tienes " + puntos + " puntos.");
                }
            } else {
                break;//termina juego cuando le coloquen cancelar
            }
        }

        //final del juego
        if (preguntasRespondidas == preguntas.size()) {
            JOptionPane.showMessageDialog(null, "¡Ganaste el juego! Respondiste las " + preguntas.size() + " preguntas y tienes " + puntos + " puntos.");
        } else {
            JOptionPane.showMessageDialog(null, "Juego terminado. Tienes " + puntos + " puntos.");
        }
    }
}
