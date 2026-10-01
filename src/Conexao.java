import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {
    private Connection con;

    public Conexao() {
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "011820.ghi";
        String url = "jdbc:postgresql://localhost:5432/Academia";

        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}