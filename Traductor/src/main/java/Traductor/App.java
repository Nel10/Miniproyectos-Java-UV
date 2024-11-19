/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Traductor;

/**
 *
 * @author holberton
 */
public class App {
    public static void main(String[] args) {
        //Inicializar todo en la main
        Interfaz interfaz = new Interfaz();
        Texto modelo = new Texto("", "", "");
        Controlador controlador = new Controlador(modelo, interfaz);
        interfaz.setVisible(true);
        interfaz.setLocationRelativeTo(null);
    }
}
