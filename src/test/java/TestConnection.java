import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

public class TestConnection {
    @Test
    public void testConnection() {
        var em = Persistence.createEntityManagerFactory("LuongSanPham MSSQL").createEntityManager();
        var tx = em.getTransaction();
        tx.begin();
        tx.commit();
    }
}
