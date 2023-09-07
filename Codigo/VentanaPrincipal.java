package Codigo;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class VentanaPrincipal extends javax.swing.JFrame {

    int m1, m2, contPal1 = 0, contPal2 = 0, contPal3 = 0, contPal4 = 0, contPal5 = 0;
    String posiPal1, posFPal1, posiPal2, posFPal2, posiPal3, posFPal3, posiPal4, posFPal4, posiPal5, posFPal5;
    boolean matriz[][];
    ArrayList<JCheckBox> listaCheck = new ArrayList<JCheckBox>();
    static String arregloPalabras[] = new String[5];
    static String palabra;
    static JButton boton[][];
    static int cantidadPalabras;
    static boolean saltarPalabra[] = new boolean[5];
    static int poscol = 0, posfil = 0;

    public VentanaPrincipal() {
        initComponents();
        this.setVisible(false);
        gestionDelPrograma();
    }

    public void gestionDelPrograma() {
        pedirPalabras();
        CrearSopaDeLetras(arregloPalabras);
    }

    private String[] pedirPalabras() {
        cantidadPalabras = 5;

        Scanner entrada = new Scanner(System.in);
        JOptionPane.showMessageDialog(null, "Deberá digitar 5 palabras, las cuales estaran en la sopa de letras"
                + "\ny usted deberá encontrarlas.");

        for (int i = 0; i < 5; i++) {
            palabra = JOptionPane.showInputDialog("\ndigite la palabra " + (i + 1) + ": ");
            arregloPalabras[i] = palabra;
            System.out.println("Palabra " + (i + 1) + " " + palabra);
        }
        palabra1.setText(arregloPalabras[0].toUpperCase());
        palabra2.setText(arregloPalabras[1].toUpperCase());
        palabra3.setText(arregloPalabras[2].toUpperCase());
        palabra4.setText(arregloPalabras[3].toUpperCase());
        palabra5.setText(arregloPalabras[4].toUpperCase());

        return arregloPalabras;
    }

    private char letrasRandom() {
        Random r = new Random();
        char caracter = (char) (r.nextInt(26) + 'A');
        return caracter;
    }

    private void matrizQueActivaLasLetras() {
        matriz = new boolean[m1][m2]; // Matriz para comprobar que boton tiene la letra de una palabra
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {
                matriz[i][j] = false; // todos los botones desactivados
            }
        }
    }

    public static int pabalabraMayor(String[] arregloPalabras) {
        int tamaño = 0;
        int i = 0;

        while (i < arregloPalabras.length - 1) {

            if (arregloPalabras[i].length() >= arregloPalabras[i + 1].length() && arregloPalabras[i].length() > tamaño) {
                tamaño = arregloPalabras[i].length();
            } else if (arregloPalabras[i].length() < arregloPalabras[i + 1].length() && arregloPalabras[i + 1].length() > tamaño) {
                tamaño = arregloPalabras[i + 1].length();
            }
            i++;
        }
        return tamaño;
    }

    private char[] obtenerCaracteresDePalabras(String arregloPalabras[], int pos) {
        String palabraInsertar;
        char caracterDePalabra;
        char caracteresPalabra[];

        palabraInsertar = arregloPalabras[pos];
        caracteresPalabra = new char[palabraInsertar.length()];
        for (int i = 0; i < palabraInsertar.length(); i++) {
            caracterDePalabra = palabraInsertar.charAt(i);
            caracteresPalabra[i] = caracterDePalabra;
        }
        return caracteresPalabra;
    }

    private void CrearSopaDeLetras(String[] arregloPalabras) {

        int mayorDimension = pabalabraMayor(arregloPalabras);
        m1 = mayorDimension + 4;
        m2 = mayorDimension + 4;
        boton = new JButton[m1][m2];

        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {

                java.awt.GridBagConstraints gridBagConstraints1;
                gridBagConstraints1 = new java.awt.GridBagConstraints();
                gridBagConstraints1.gridx = 1;
                gridBagConstraints1.gridy = 0;
                //panel.setPreferredSize(new java.awt.Dimension(poscol,posfil));
                gridBagConstraints1.gridwidth = 1;
                gridBagConstraints1.gridheight = 1;
                //gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                gridBagConstraints1.ipadx = poscol + 40;
                gridBagConstraints1.ipady = posfil + 40;
                //this.setSize(poscol, posfil);
                //gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
                panelTotal.add(panel, gridBagConstraints1);

                if (poscol > panelTotal.getWidth() || posfil > panelTotal.getHeight()) {
                    java.awt.GridBagConstraints gridBagConstraints;
                    gridBagConstraints = new java.awt.GridBagConstraints();
                    gridBagConstraints.gridx = 1;
                    gridBagConstraints.gridy = 0;
                    gridBagConstraints.gridwidth = 1;
                    gridBagConstraints.gridheight = 1;
                    gridBagConstraints.ipadx = poscol + 40;
                    gridBagConstraints.ipady = posfil + 40;
                    panelTotal.add(panel, gridBagConstraints);
                }

                boton[i][j] = new JButton();
                panel.add(boton[i][j]);// agrego los botones al panel de botones
                boton[i][j].setSize(40, 40);
                boton[i][j].setLocation(posfil, poscol);
                boton[i][j].setText(String.valueOf(letrasRandom()));
                boton[i][j].setBackground(java.awt.Color.LIGHT_GRAY);
                poscol += 40;
            }
            posfil += 40;
            poscol = 0;
        }

        panelTotal.setBackground(java.awt.Color.cyan);
        panel.setBackground(java.awt.Color.gray);
        agregarZonaDeTexto();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        ingresarPalabrasSopaLetras(arregloPalabras, boton);

    }

    private void ingresarPalabrasSopaLetras(String arregloPalabras[], JButton botones[][]) {
        int posicionPalabra = 0; // cuento la posicion en la que voy en cada palabra
        char caracteresDePalabra[]; //Almaceno los caracteres de una palabra y otra
        Random r = new Random(); // numeros aleatorios para un boton cualquiera en el rango actual de la SPDL
        saltarPalabra = new boolean[arregloPalabras.length];

        int primNumMatriz;
        int segunNumMatriz;

        matrizQueActivaLasLetras();
        boolean respuestaProceso;

        for (int i = 0; i < arregloPalabras.length; i++) {
            saltarPalabra[i] = false; // Letras Desactivadas
        }

        for (int i = 0; i < arregloPalabras.length; i++) {
            do {
                primNumMatriz = r.nextInt(m1);
                segunNumMatriz = r.nextInt(m2);
                caracteresDePalabra = obtenerCaracteresDePalabras(arregloPalabras, posicionPalabra);
                //==================================================================
                respuestaProceso = validaIngresaPalabrasEnSopa(primNumMatriz,segunNumMatriz, posicionPalabra, caracteresDePalabra, botones);
                //==================================================================
            } while (respuestaProceso == false);

            posicionPalabra++;
        }

    }

    private boolean validaIngresaPalabrasEnSopa(int primNumMatriz,int segunNumMatriz, int posicionPalabra, char caracteresDePalabra[], JButton botones[][]) {
        int rotarPosicionBoton = 0;
        boolean saberSiRotarPosicionBoton[] = new boolean[8];
        for (int i = 0; i < 8; i++) {
            saberSiRotarPosicionBoton[i] = false; // Letras Desactivadas
        }

        do {
            Random r = new Random();
            int opcion = r.nextInt(8);

            switch (opcion) {
                case 0:
                    // Hacia el lado Derecho °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° >>>>>>>>>> 1
                    int contadorPosicionesLadoDerecho = 0;
                    while (segunNumMatriz < m2) {
                        contadorPosicionesLadoDerecho++;
                        segunNumMatriz++;
                    }
                    segunNumMatriz -= contadorPosicionesLadoDerecho;

                    if (saberSiRotarPosicionBoton[0] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoDerecho >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[0] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoDerecho >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz + p] == false || p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz + p] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz + p] == false) {
                                botones[primNumMatriz][segunNumMatriz + p].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz][segunNumMatriz + p] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz + p] == true && botones[primNumMatriz][segunNumMatriz + p].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz + p] == true && (!botones[primNumMatriz][segunNumMatriz + p].getText().equals(String.valueOf(caracteresDePalabra[p])))) {
                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz][segunNumMatriz + j] = false;
                                }
                                saberSiRotarPosicionBoton[0] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz, segunNumMatriz + (p - 1));
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz, segunNumMatriz + j, arregloPalabras, botones[primNumMatriz][segunNumMatriz + j], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesLadoDerecho < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 1:
                    // Hacia el lado izquierdo °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° >>>>>>>> 2
                    int contadorPosicionesLadoIzquierdo = 0;
                    while (segunNumMatriz >= 0) {
                        contadorPosicionesLadoIzquierdo++;
                        segunNumMatriz--;
                    }
                    segunNumMatriz += contadorPosicionesLadoIzquierdo;

                    if (saberSiRotarPosicionBoton[1] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoIzquierdo >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[1] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoIzquierdo >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz - p] == false || p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz - p] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz - p] == false) {
                                botones[primNumMatriz][segunNumMatriz - p].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz][segunNumMatriz - p] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz - p] == true && botones[primNumMatriz][segunNumMatriz - p].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz - p] == true && (!botones[primNumMatriz][segunNumMatriz - p].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz][segunNumMatriz - j] = false;
                                }
                                saberSiRotarPosicionBoton[1] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz, segunNumMatriz - (p - 1));
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz, segunNumMatriz - j, arregloPalabras, botones[primNumMatriz][segunNumMatriz - j], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesLadoIzquierdo < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 2:
                    // Hacia el arriba °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° >>>>>>>>>>>>>>>> 3
                    int contadorPosicionesLadoArriba = 0;
                    while (primNumMatriz >= 0) {
                        contadorPosicionesLadoArriba++;
                        primNumMatriz--;
                    }
                    primNumMatriz += contadorPosicionesLadoArriba;

                    if (saberSiRotarPosicionBoton[2] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoArriba >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[2] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoArriba >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz] == false || p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz] == false) {
                                botones[primNumMatriz - p][segunNumMatriz].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz - p][segunNumMatriz] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz] == true && botones[primNumMatriz - p][segunNumMatriz].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz] == true && (!botones[primNumMatriz - p][segunNumMatriz].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz - j][segunNumMatriz] = false;
                                }
                                saberSiRotarPosicionBoton[2] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz - (p - 1), segunNumMatriz);
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz - j, segunNumMatriz, arregloPalabras, botones[primNumMatriz - j][segunNumMatriz], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesLadoArriba < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 3:
                    // Hacia Abajo °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°° >>>>>>>>>>>>>>>>>>>> 4
                    int contadorPosicionesLadoAbajo = 0;
                    while (primNumMatriz < m1) {
                        contadorPosicionesLadoAbajo++;
                        primNumMatriz++;
                    }
                    primNumMatriz -= contadorPosicionesLadoAbajo;

                    if (saberSiRotarPosicionBoton[3] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoAbajo >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[3] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesLadoAbajo >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz] == false || p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz] == false) {
                                botones[primNumMatriz + p][segunNumMatriz].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz + p][segunNumMatriz] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz] == true && botones[primNumMatriz + p][segunNumMatriz].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz] == true && (!botones[primNumMatriz + p][segunNumMatriz].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz + j][segunNumMatriz] = false;
                                }
                                saberSiRotarPosicionBoton[3] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra, primNumMatriz, segunNumMatriz, primNumMatriz + (p - 1), segunNumMatriz);
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz + j, segunNumMatriz, arregloPalabras, botones[primNumMatriz + j][segunNumMatriz], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesLadoAbajo < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 4:
                    // Hacia Diagonal Arriba Derecha °°°°°°°°°°°°°°°°°°°°°>>>>>>>>>>>>>>>>>> 5
                    int contadorPosicionesDiagonalArribaDerecha = 0;
                    while (primNumMatriz >= 0 && segunNumMatriz < m2) {
                        contadorPosicionesDiagonalArribaDerecha++;
                        primNumMatriz--;
                        segunNumMatriz++;
                    }
                    primNumMatriz += contadorPosicionesDiagonalArribaDerecha;
                    segunNumMatriz -= contadorPosicionesDiagonalArribaDerecha;

                    if (saberSiRotarPosicionBoton[4] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalArribaDerecha >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[4] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalArribaDerecha >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz + p] == false || p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz + p] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz + p] == false) {
                                botones[primNumMatriz - p][segunNumMatriz + p].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz - p][segunNumMatriz + p] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz + p] == true && botones[primNumMatriz - p][segunNumMatriz + p].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz + p] == true && (!botones[primNumMatriz - p][segunNumMatriz + p].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz - j][segunNumMatriz + j] = false;
                                }
                                saberSiRotarPosicionBoton[4] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz - (p - 1), segunNumMatriz + (p - 1));
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz - j, segunNumMatriz + j, arregloPalabras, botones[primNumMatriz - j][segunNumMatriz + j], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesDiagonalArribaDerecha < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 5:
                    // Hacia Diagonal Arriba Izquierda °°°°°°°°°°°°°°°°°°°°°>>>>>>>>>>>>>>>>>> 6
                    int contadorPosicionesDiagonalArribaIzquierda = 0;
                    while (primNumMatriz >= 0 && segunNumMatriz >= 0) {
                        contadorPosicionesDiagonalArribaIzquierda++;
                        primNumMatriz--;
                        segunNumMatriz--;
                    }
                    primNumMatriz += contadorPosicionesDiagonalArribaIzquierda;
                    segunNumMatriz += contadorPosicionesDiagonalArribaIzquierda;

                    if (saberSiRotarPosicionBoton[5] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalArribaIzquierda >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[5] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalArribaIzquierda >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz - p] == false || p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz - p] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz - p] == false) {
                                botones[primNumMatriz - p][segunNumMatriz - p].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz - p][segunNumMatriz - p] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz - p] == true && botones[primNumMatriz - p][segunNumMatriz - p].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz - p][segunNumMatriz - p] == true && (!botones[primNumMatriz - p][segunNumMatriz - p].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz - j][segunNumMatriz - j] = false;
                                }
                                saberSiRotarPosicionBoton[5] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz - (p - 1), segunNumMatriz - (p - 1));
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz - j, segunNumMatriz - j, arregloPalabras, botones[primNumMatriz - j][segunNumMatriz - j], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesDiagonalArribaIzquierda < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 6:
                    // Hacia Diagonal Abajo Derechar °°°°°°°°°°°°°°°°°°°°°>>>>>>>>>>>>>>>>>> 7
                    int contadorPosicionesDiagonalAbajoDerecha = 0;
                    while (primNumMatriz < m1 && segunNumMatriz < m2) {
                        contadorPosicionesDiagonalAbajoDerecha++;
                        primNumMatriz++;
                        segunNumMatriz++;
                    }
                    primNumMatriz -= contadorPosicionesDiagonalAbajoDerecha;
                    segunNumMatriz -= contadorPosicionesDiagonalAbajoDerecha;

                    if (saberSiRotarPosicionBoton[6] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalAbajoDerecha >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[6] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalAbajoDerecha >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz + p] == false || p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz + p] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz + p] == false) {
                                botones[primNumMatriz + p][segunNumMatriz + p].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz + p][segunNumMatriz + p] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz + p] == true && botones[primNumMatriz + p][segunNumMatriz + p].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz + p] == true && (!botones[primNumMatriz + p][segunNumMatriz + p].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz + j][segunNumMatriz + j] = false;
                                }
                                saberSiRotarPosicionBoton[6] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz + (p - 1), segunNumMatriz + (p - 1));
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz + j, segunNumMatriz + j, arregloPalabras, botones[primNumMatriz + j][segunNumMatriz + j], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesDiagonalAbajoDerecha < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
                case 7:
                    // Hacia Diagonal Abajo Izquierda °°°°°°°°°°°°°°°°°°°°°°>>>>>>>>>>>>>>>>>> 8
                    int contadorPosicionesDiagonalAbajoIzquierda = 0;
                    while (primNumMatriz < m1 && segunNumMatriz >= 0) {
                        contadorPosicionesDiagonalAbajoIzquierda++;
                        primNumMatriz++;
                        segunNumMatriz--;
                    }
                    primNumMatriz -= contadorPosicionesDiagonalAbajoIzquierda;
                    segunNumMatriz += contadorPosicionesDiagonalAbajoIzquierda;

                    if (saberSiRotarPosicionBoton[7] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalAbajoIzquierda >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == false || saberSiRotarPosicionBoton[7] == false && saltarPalabra[posicionPalabra] == false && contadorPosicionesDiagonalAbajoIzquierda >= caracteresDePalabra.length && matriz[primNumMatriz][segunNumMatriz] == true) {
                        int p = 0;
                        while (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz - p] == false || p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz - p] == true) {
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz - p] == false) {
                                botones[primNumMatriz + p][segunNumMatriz - p].setText(String.valueOf(caracteresDePalabra[p]).toUpperCase());
                                matriz[primNumMatriz + p][segunNumMatriz - p] = true;
                                p++;
                            }
                            if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz - p] == true && botones[primNumMatriz + p][segunNumMatriz - p].getText().equals(String.valueOf(caracteresDePalabra[p]))) {
                                p++;
                            } else if (p < caracteresDePalabra.length && matriz[primNumMatriz + p][segunNumMatriz - p] == true && (!botones[primNumMatriz + p][segunNumMatriz - p].getText().equals(String.valueOf(caracteresDePalabra[p])))) {

                                for (int j = 0; j < p; j++) {
                                    matriz[primNumMatriz + j][segunNumMatriz - j] = false;
                                }
                                saberSiRotarPosicionBoton[7] = true;
                                rotarPosicionBoton++;
                                p = 100;
                            }
                            if (p == caracteresDePalabra.length) {
                                saltarPalabra[posicionPalabra] = true;
                                rotarPosicionBoton = 8;
                                guardarPosicionIyF(posicionPalabra,primNumMatriz, segunNumMatriz, primNumMatriz + (p - 1), segunNumMatriz - (p - 1));
                                for (int j = 0; j < p; j++) {
                                    accionUndirBotones(primNumMatriz + j, segunNumMatriz - j, arregloPalabras, botones[primNumMatriz + j][segunNumMatriz - j], posicionPalabra);
                                }
                            }
                        }
                    } else if (contadorPosicionesDiagonalAbajoIzquierda < caracteresDePalabra.length) {
                        rotarPosicionBoton++;
                    }
                    break;
            }
            System.out.println(opcion);
        } while (rotarPosicionBoton < 8);

        return saltarPalabra[posicionPalabra];

    }

    private void descubrirPalabras() {
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {
                if (matriz[i][j] == true) {
                    boton[i][j].setBackground(java.awt.Color.red);
                }
            }
        }
        contPal1 = arregloPalabras[0].length();
        contPal2 = arregloPalabras[1].length();
        contPal3 = arregloPalabras[2].length();
        contPal4 = arregloPalabras[3].length();
        contPal5 = arregloPalabras[4].length();
        validacionEnLosCheck();
    }

    private void validacionEnLosCheck() {
        if (contPal1 == arregloPalabras[0].length()) {
            listaCheck.get(0).setSelected(true);
        }
        if (contPal2 == arregloPalabras[1].length()) {
            listaCheck.get(1).setSelected(true);
        }
        if (contPal3 == arregloPalabras[2].length()) {
            listaCheck.get(2).setSelected(true);
        }
        if (contPal4 == arregloPalabras[3].length()) {
            listaCheck.get(3).setSelected(true);
        }
        if (contPal5 == arregloPalabras[4].length()) {
            listaCheck.get(4).setSelected(true);
        }
    }

    private void agregarZonaDeTexto() {
        check1.setEnabled(false);
        check2.setEnabled(false);
        check3.setEnabled(false);
        check4.setEnabled(false);
        check5.setEnabled(false);
        listaCheck.add(check1);//Para Crear array de checks y aumentar palabras 
        listaCheck.add(check2);
        listaCheck.add(check3);
        listaCheck.add(check4);
        listaCheck.add(check5);
    }

    private void accionUndirBotones(int x, int y, String[] arregloPalabras, JButton boton, int posicion) {
        ActionListener oyenteDeAccion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (posicion == 0) {
                    boton.setBackground(java.awt.Color.green);
                    contPal1++;
                    validacionEnLosCheck();
                    validacionDeJuegoTerminado();
                } else if (posicion == 1) {
                    boton.setBackground(java.awt.Color.cyan);
                    contPal2++;
                    validacionEnLosCheck();
                    validacionDeJuegoTerminado();
                } else if (posicion == 2) {
                    boton.setBackground(java.awt.Color.yellow);
                    contPal3++;
                    validacionEnLosCheck();
                    validacionDeJuegoTerminado();
                } else if (posicion == 3) {
                    boton.setBackground(java.awt.Color.magenta);
                    contPal4++;
                    validacionEnLosCheck();
                    validacionDeJuegoTerminado();
                } else if (posicion == 4) {
                    boton.setBackground(java.awt.Color.blue);
                    contPal5++;
                    validacionEnLosCheck();
                    validacionDeJuegoTerminado();
                }
            }
        };
        boton.addActionListener(oyenteDeAccion);
    }

    private void guardarPosicionIyF(int posicionPalabra, int primNumMatrizInicio, int segunNumMatrizInicio, int primNumMatrizFin, int segunNumMatrizFin) {
        // posiciones Iniciales y Finales de la palabra 1
        if (posicionPalabra == 0) {
            posiPal1 = ("["+ segunNumMatrizInicio + ","+primNumMatrizInicio +"]");
            posFPal1 = ("["+segunNumMatrizFin + ","+primNumMatrizFin+"]");
        }
        // posiciones Iniciales y Finales de la palabra 2
        if (posicionPalabra == 1) {
            posiPal2 = ("["+segunNumMatrizInicio+ ","+primNumMatrizInicio+"]");
            posFPal2 = ("["+segunNumMatrizFin + ","+primNumMatrizFin+"]");
        }
        // posiciones Iniciales y Finales de la palabra 3
        if (posicionPalabra == 2) {
            posiPal3 = ("["+ segunNumMatrizInicio + ","+primNumMatrizInicio+"]");
            posFPal3 = ("["+segunNumMatrizFin + ","+primNumMatrizFin+"]");
        }
        // posiciones Iniciales y Finales de la palabra 4
        if (posicionPalabra == 3) {
            posiPal4 = ("["+segunNumMatrizInicio+ ","+primNumMatrizInicio +"]");
            posFPal4 = ("["+segunNumMatrizFin + ","+primNumMatrizFin+"]");
        }
        // posiciones Iniciales y Finales de la palabra 5
        if (posicionPalabra == 4) {
            posiPal5 = ("["+segunNumMatrizInicio  + ","+primNumMatrizInicio+"]");
            posFPal5 = ("["+segunNumMatrizFin + ","+primNumMatrizFin+"]");
        }

    }
 
    private void mostrarPosicionesDeLaspalabras() {
        JOptionPane.showMessageDialog(null, "Palabra 1 \nPosicion Inicial : " + posiPal1 + "\nPosicion Final : " + posFPal1 + "\n\n"
                + "Palabra 2 \nPosicion Inicial : " + posiPal2 + "\nPosicion Final : " + posFPal2 + "\n\n"
                        + "Palabra 3 \nPosicion Inicial : " + posiPal3 + "\nPosicion Final : " + posFPal3 + "\n\n"
                                + "Palabra 4 \nPosicion Inicial : " + posiPal4 + "\nPosicion Final : " + posFPal4 + "\n\n"
                                        + "Palabra 5 \nPosicion Inicial : " + posiPal5 + "\nPosicion Final : " + posFPal5);
    }

    private void validacionDeJuegoTerminado() {
        if (contPal1 == arregloPalabras[0].length() && contPal2 == arregloPalabras[1].length() && contPal3 == arregloPalabras[2].length() && contPal4 == arregloPalabras[3].length() && contPal5 == arregloPalabras[4].length()) {
            mostrarPosicionesDeLaspalabras();
            JuegoTerminado jN = new JuegoTerminado(this, true);
            jN.setLocationRelativeTo(null);
            jN.setSize(317, 241);
            jN.setVisible(true);
            limpiarJuego();
        }
    }

    public void limpiarJuego() {

        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < m2; j++) {
                panel.remove(boton[i][j]); // quito todos los botones
            }
        }
        for (int i = 0; i < 5; i++) {
            saltarPalabra[i] = false;
        }
        for (int i = 0; i < 5; i++) {
            arregloPalabras[i] = null;
        }

        m1 = 0;
        contPal1 = 0;
        contPal2 = 0;
        contPal3 = 0;
        contPal4 = 0;
        contPal5 = 0;
        m2 = 0;
        poscol = 0;
        posfil = 0;
        listaCheck.get(0).setSelected(false);
        listaCheck.get(1).setSelected(false);
        listaCheck.get(2).setSelected(false);
        listaCheck.get(3).setSelected(false);
        listaCheck.get(4).setSelected(false);
        dispose();
        gestionDelPrograma();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        panelTotal = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        botonDescubrirPalabras = new javax.swing.JButton();
        check1 = new javax.swing.JCheckBox();
        check2 = new javax.swing.JCheckBox();
        check3 = new javax.swing.JCheckBox();
        check4 = new javax.swing.JCheckBox();
        check5 = new javax.swing.JCheckBox();
        palabrasAEncontrar = new javax.swing.JLabel();
        palabra1 = new javax.swing.JLabel();
        palabra2 = new javax.swing.JLabel();
        palabra3 = new javax.swing.JLabel();
        palabra4 = new javax.swing.JLabel();
        palabra5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelTotal.setLayout(new java.awt.GridBagLayout());

        panel.setPreferredSize(panel.getPreferredSize());
        panel.setPreferredSize(new java.awt.Dimension(poscol,posfil));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5030, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4740, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelTotal.add(panel, gridBagConstraints);

        botonDescubrirPalabras.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        botonDescubrirPalabras.setText("Descubrir palabras");
        botonDescubrirPalabras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDescubrirPalabrasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        panelTotal.add(botonDescubrirPalabras, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelTotal.add(check1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelTotal.add(check2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelTotal.add(check3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelTotal.add(check4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panelTotal.add(check5, gridBagConstraints);

        palabrasAEncontrar.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        palabrasAEncontrar.setText("Palabras a Encontrar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        panelTotal.add(palabrasAEncontrar, gridBagConstraints);

        palabra1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelTotal.add(palabra1, gridBagConstraints);

        palabra2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelTotal.add(palabra2, gridBagConstraints);

        palabra3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelTotal.add(palabra3, gridBagConstraints);

        palabra4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelTotal.add(palabra4, gridBagConstraints);

        palabra5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelTotal.add(palabra5, gridBagConstraints);

        jScrollPane2.setViewportView(panelTotal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonDescubrirPalabrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDescubrirPalabrasActionPerformed
        descubrirPalabras();
        validacionDeJuegoTerminado();
    }//GEN-LAST:event_botonDescubrirPalabrasActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDescubrirPalabras;
    private javax.swing.JCheckBox check1;
    private javax.swing.JCheckBox check2;
    private javax.swing.JCheckBox check3;
    private javax.swing.JCheckBox check4;
    private javax.swing.JCheckBox check5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel palabra1;
    private javax.swing.JLabel palabra2;
    private javax.swing.JLabel palabra3;
    private javax.swing.JLabel palabra4;
    private javax.swing.JLabel palabra5;
    private javax.swing.JLabel palabrasAEncontrar;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelTotal;
    // End of variables declaration//GEN-END:variables
}
