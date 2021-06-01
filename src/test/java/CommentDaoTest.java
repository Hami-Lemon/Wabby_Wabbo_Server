import com.github.lemon.dao.CommentDao;
import com.github.lemon.example.Comments;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class CommentDaoTest {
    DataSource source = new ComboPooledDataSource();
    CommentDao commentDao = new CommentDao(source);

    @Test
    public void insert() throws SQLException {
        Comments comments = new Comments(0,"你帅到不行",122, LocalDateTime.now().toString(), 4, 1);
        commentDao.releaseCom(comments);
    }

    @Test
    public void lookComById() throws SQLException, ParseException {
        List<Comments> list = commentDao.getCommentsById(1);
    }

    @Test
    public void lookComByOrder() throws SQLException, ParseException {
        List<Comments> list = commentDao.getTipsOrderByStar(1);
    }
}
