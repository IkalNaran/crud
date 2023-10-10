package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FormAlumnos extends JFrame {
    private JLabel titleName;
    private JButton guardarButton;
    private JButton modifcarButton;
    private JButton eliminarButton;
    private JTextField textId;
    private JTextField textName;
    private JTextField textLastName;
    private JTable mostrarAlumnos;
    private JLabel id;
    private JPanel MainPanel;

    public FormAlumnos(){
       /* CConection cConection = new CConection();
        cConection.estableConexion();*/

        Students students = new Students();
        this.setLocationRelativeTo(null);
        textId.setEnabled(false);
        students.mostrarAlumnos(mostrarAlumnos);
        setContentPane(MainPanel);
        setTitle("Simple Gui App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080,720);
        setLocationRelativeTo(null);
        setVisible(true);


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Students students = new Students();
                students.InsertStudent(textName,textLastName);
                students.mostrarAlumnos(mostrarAlumnos);
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Students students = new Students();
                students.deleteStudent(textId);
                students.mostrarAlumnos(mostrarAlumnos);
            }
        });
        mostrarAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Students students = new Students();
                super.mouseClicked(e);
                students.SelecionarAlumno(mostrarAlumnos,textId,textName,textLastName);

            }
        });
        modifcarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Students students = new Students();
                students.ModificarAlumno(textId,textName,textLastName);
                students.mostrarAlumnos(mostrarAlumnos);
            }
        });
    }

    public static void main(String[] args) {
        new FormAlumnos();
    }

}
