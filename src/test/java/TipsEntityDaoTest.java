import com.github.lemon.dao.impl.TipsDaoImpl;
import com.github.lemon.pojo.TipsEntity;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class TipsEntityDaoTest {
    DataSource source = new ComboPooledDataSource();
    @Test
    public void insert() throws SQLException {
        TipsEntity tip = new TipsEntity(0, LocalDateTime.now().toString(), "meimei",2220,"睡觉都ii哦啊是解耦i大家啊iOS京东i窘境扫i觉得i哦加哦i是的骄傲i是i偶氮基祭扫");
        TipsDaoImpl tipsDaoImpl = new TipsDaoImpl(source);
        tipsDaoImpl.releaseTips(tip);
    }

    @Test
    public void lookByType() throws SQLException, ParseException {
        TipsDaoImpl tipsDaoImpl = new TipsDaoImpl(source);
        List<TipsEntity> list = tipsDaoImpl.getTipsByType("知识");
    }

    @Test
    public void lookById() throws SQLException, ParseException {
        TipsDaoImpl tipsDaoImpl = new TipsDaoImpl(source);
        TipsEntity tip = tipsDaoImpl.getTipById(1);
    }

    @Test
    public void lookByOrder() throws SQLException, ParseException {
        TipsDaoImpl tipsDaoImpl = new TipsDaoImpl(source);
        List<TipsEntity> list = tipsDaoImpl.getTipsOrderByStar();
    }
}
