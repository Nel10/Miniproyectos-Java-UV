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
import java.util.List;

public class Pregunta {
    private String pregunta;
    private String[] respuestas;
    private char respuestaCorrecta;

    public Pregunta(String pregunta, String[] respuestas, char respuestaCorrecta) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public char getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public static List<Pregunta> crearPreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta("¿Cuál es la capital de Amazonas?", 
                new String[]{"a. Cali", "b. Amazonas", "c. Medellín", "d. Leticia"}, 'd'));
        
        preguntas.add(new Pregunta("¿Cuál es la capital de Francia?", 
                new String[]{"a. Berlín", "b. Madrid", "c. París", "d. Lisboa"}, 'c'));
        
        preguntas.add(new Pregunta("¿Qué es el ADN?", 
                new String[]{"a. Ácido Desoxirribonucleico", "b. Ácido Ribonucléico", "c. Proteína", "d. Carbohidrato"}, 'a'));
        
        preguntas.add(new Pregunta("¿Cuál es el continente más grande?", 
                new String[]{"a. África", "b. Asia", "c. América", "d. Europa"}, 'b'));
        
        preguntas.add(new Pregunta("¿Quién escribió 'Cien años de soledad'?", 
                new String[]{"a. Gabriel García Márquez", "b. Julio Cortázar", "c. Mario Vargas Llosa", "d. Jorge Luis Borges"}, 'a'));
        
        preguntas.add(new Pregunta("¿Cuál es el océano más grande?", 
                new String[]{"a. Atlántico", "b. Índico", "c. Ártico", "d. Pacífico"}, 'd'));
        
        preguntas.add(new Pregunta("¿Cuál es el país más poblado?", 
                new String[]{"a. India", "b. China", "c. Estados Unidos", "d. Indonesia"}, 'b'));
        
        preguntas.add(new Pregunta("¿Cuál es la capital de Japón?", 
                new String[]{"a. Seúl", "b. Tokio", "c. Pekín", "d. Bangkok"}, 'b'));
        
        preguntas.add(new Pregunta("¿Qué gas respiramos?", 
                new String[]{"a. Oxígeno", "b. Dióxido de carbono", "c. Helio", "d. Hidrógeno"}, 'a'));
        
        preguntas.add(new Pregunta("¿Cuál es el planeta más cercano al sol?", 
                new String[]{"a. Venus", "b. Mercurio", "c. Tierra", "d. Marte"}, 'b'));
        
        preguntas.add(new Pregunta("¿Quién pintó la 'Mona Lisa'?", 
                new String[]{"a. Vincent van Gogh", "b. Pablo Picasso", "c. Leonardo da Vinci", "d. Claude Monet"}, 'c'));
        
        preguntas.add(new Pregunta("¿Qué elemento químico tiene el símbolo 'O'?", 
                new String[]{"a. Oro", "b. Oxígeno", "c. Osmio", "d. Oxido"}, 'b'));
        
        preguntas.add(new Pregunta("¿Cuál es la capital de Egipto?", 
                new String[]{"a. El Cairo", "b. Lagos", "c. Túnez", "d. Rabat"}, 'a'));
        
        preguntas.add(new Pregunta("¿Qué animal es conocido como el rey de la selva?", 
                new String[]{"a. Tigre", "b. Elefante", "c. León", "d. Oso"}, 'c'));
        
        preguntas.add(new Pregunta("¿Qué instrumento mide la temperatura?", 
                new String[]{"a. Barómetro", "b. Termómetro", "c. Higrómetro", "d. Manómetro"}, 'b'));

        if (preguntas.size() != 15) {
            throw new IllegalStateException("Se deben tener exactamente 15 preguntas.");
        }

        return preguntas;
 
    }
}