package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;

public class Students {
    int codigo;
    String nombreAlumno;
    String apellidosAlumnos;

    public int getCodigo(int codigo){
        return codigo;
    }
    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public void InsertStudent(JTextField paramNombres, JTextField paramApellidos){
        setNombreAlumno(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        CConection objConexion = new CConection();
        String consulta =("insert into Students(`FIRST NAME`,`LAST NAME`) values (?,?);");
        try{
            CallableStatement cs  = objConexion.estableConexion().prepareCall(consulta);
            cs.setString(1, nombreAlumno );
            cs.setString(2, apellidosAlumnos);
            cs.execute();
            JOptionPane.showMessageDialog(null, "se inserto correctamente el alumno");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }

    public void deleteStudent(JTextField paramCodigo){
        getCodigo(Integer.parseInt(paramCodigo.getText()));
        CConection objConexion = new CConection();
        String consulta =("DELETE FROM Students WHERE Students.id=?;");
        try{
            CallableStatement cs  = objConexion.estableConexion().prepareCall(consulta);
            cs.setInt(1,codigo);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se elimino alumno");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "error" + e);
        }
    }

    public void mostrarAlumnos(JTable paramTotalAlumnos){
        CConection objConexion = new CConection();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenTabla = new TableRowSorter<TableModel>(modelo);
        paramTotalAlumnos.setRowSorter(OrdenTabla);
        String sql="";
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");

        paramTotalAlumnos.setModel(modelo);
        sql = "select * from Students;";
        String[] datos = new String[3];
        Statement st;

        try {
            st = objConexion.estableConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                modelo.addRow(datos);
            }
            paramTotalAlumnos.setModel(modelo);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }

    public void SelecionarAlumno(JTable paramTablaAlumnos, JTextField paramId,JTextField paramNombres, JTextField paramApellidos){
        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            if(fila >= 0){
                paramId.setText(paramTablaAlumnos.getValueAt(fila,0).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila,1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila,2).toString());

            }else {
                JOptionPane.showMessageDialog(null,"fila no seleccionada");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
    }
}

