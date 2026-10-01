package Alunos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class AlunosModel {
    public static void create(AlunosBean a, Connection con) throws SQLException {
        String sql = "INSERT INTO alunos (nome, telefone, data_nascimento, email, cpf, endereco) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, a.getNome());
        st.setString(2, a.getTelefone());
        st.setDate(3, a.getDataNascimento());
        st.setString(4, a.getEmail());
        st.setString(5, a.getCpf());
        st.setString(6, a.getEndereco());
        st.execute();
        st.close();
    }

    public static HashSet<AlunosBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String sql = "SELECT id_aluno, nome, telefone, data_nascimento, email, cpf, endereco FROM alunos ORDER BY id_aluno";
        ResultSet rs = st.executeQuery(sql);
        HashSet<AlunosBean> list = new HashSet<>();
        while (rs.next()) {
            list.add(new AlunosBean(
                rs.getInt("id_aluno"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getDate("data_nascimento"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("endereco")
            ));
        }
        rs.close();
        st.close();
        return list;
    }

    public static void update(AlunosBean a, Connection con) throws SQLException {
        String sql = "UPDATE alunos SET nome=?, telefone=?, data_nascimento=?, email=?, cpf=?, endereco=? WHERE id_aluno=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, a.getNome());
        st.setString(2, a.getTelefone());
        st.setDate(3, a.getDataNascimento());
        st.setString(4, a.getEmail());
        st.setString(5, a.getCpf());
        st.setString(6, a.getEndereco());
        st.setInt(7, a.getIdAluno());
        st.execute();
        st.close();
    }
    public static void remove(int idAluno, Connection con) throws SQLException {
        String sql = "DELETE FROM alunos WHERE id_aluno=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idAluno);
        st.execute();
        st.close();
    }
}