import com.github.lemon.dao.TipsDao;
import com.github.lemon.example.Tips;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class TipsDaoTest {
    DataSource source = new ComboPooledDataSource();
    @Test
    public void insert() throws SQLException {
        Tips tip = new Tips(0, LocalDateTime.now().toString(), "meimei",2220,"睡觉都ii哦啊是解耦i大家啊iOS京东i窘境扫i觉得i哦加哦i是的骄傲i是i偶氮基祭扫");
        TipsDao tipsDao = new TipsDao(source);
        tipsDao.releaseTips(tip);
    }

    @Test
    public void lookByType() throws SQLException, ParseException {
        TipsDao tipsDao = new TipsDao(source);
        List<Tips> list = tipsDao.getTipsByType("知识");
    }

    @Test
    public void lookById() throws SQLException, ParseException {
        TipsDao tipsDao = new TipsDao(source);
        Tips tip = tipsDao.getTipById(1);
    }

    @Test
    public void lookByOrder() throws SQLException, ParseException {
        TipsDao tipsDao = new TipsDao(source);
        List<Tips> list = tipsDao.getTipsOrderByStar();
    }
}
