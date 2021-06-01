import com.github.lemon.dao.impl.CommentDaoImpl;
import com.github.lemon.pojo.CommentsEntity;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class CommentDaoImplTest {
    DataSource source = new ComboPooledDataSource();
    CommentDaoImpl commentDaoImpl = new CommentDaoImpl(source);

    @Test
    public void insert() throws SQLException {
        CommentsEntity commentsEntity = new CommentsEntity(0,"你帅到不行",122, LocalDateTime.now().toString(), 4, 1);
        commentDaoImpl.releaseCom(commentsEntity);
    }

    @Test
    public void lookComById() throws SQLException, ParseException {
        List<CommentsEntity> list = commentDaoImpl.getCommentsById(1);
    }

    @Test
    public void lookComByOrder() throws SQLException, ParseException {
        List<CommentsEntity> list = commentDaoImpl.getTipsOrderByStar(1);
    }
}
