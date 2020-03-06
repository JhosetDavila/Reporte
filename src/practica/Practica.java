package practica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Practica {
    
    public static Connection conectar(){
        Connection con=null;    
        try {
            String url="jdbc:mysql://localhost:3306/tienda?user=root&&password=mysqladmin";
            con=DriverManager.getConnection(url);
            if (con !=null) {
                System.out.println("Conexion Satisfactoria");                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        try {
            JasperReport jr=(JasperReport)JRLoader.loadObject(Practica.class.getResource("./Reporte.jasper"));
            Map parametros=new HashMap<String,Object>();
            parametros.put("PRECIO",400);
            parametros.put("PRECIO2",1400);
            JasperPrint jp=JasperFillManager.fillReport(jr,parametros,conectar());
            JasperViewer jv=new JasperViewer(jp);
            jv.show(); 
        } catch (JRException e) {
            System.err.print(e);
        }
    }
}

