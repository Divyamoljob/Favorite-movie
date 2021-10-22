import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class sample_sqlite3 {

    public void selectAll(){
            String sql = "SELECT name,directer, date FROM movies";
            
            try (Connection conn = this.connect();
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){
                
                while (rs.next()) {
                    System.out.println(rs.getString("name") + "\t" +
                                       rs.getString("directer") + "\t" +
                                       rs.getDouble("release_date"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    public void insert(String name, double capacity) {
        String sql = "INSERT INTO movies(name,directer,release_date) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, directer);
            pstmt.setDouble(3, release_date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite://home/kali/Desktop/sample_java.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                
                String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
                Statement stmt = conn.createStatement()) {

                stmt.execute(sql);
                
                InsertApp app = new InsertApp();
                app.insert("Avengers","joe Russo", 2012);
                app.insert("Infinity War","Antonie Fuqua", 2019);
                app.insert("End Game","Kevin Feige", 2020);
                
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        

    }
}
