# 建立数据库
DROP DATABASE IF EXISTS wabby;
CREATE DATABASE wabby;
USE wabby;

# 建立Tips表
DROP TABLE IF EXISTS Tips;
# 帖子表
CREATE TABLE Tips
(
    # 主键
    id      INT PRIMARY KEY AUTO_INCREMENT,
    # 发贴日期
    date    DATETIME    NOT NULL,
    # 帖子类型(最多10个字)
    type    VARCHAR(30) NOT NULL,
    # 点赞数
    starNum INT         NOT NULL DEFAULT 0,
    # 帖子内容
    content TEXT        NOT NULL
) engine = innodb,
  charset = utf8mb4;

# 建立comments表
DROP TABLE IF EXISTS Comments;
# 评论表
CREATE TABLE Comments
(
    # 主键
    id      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    # 评论内容
    content TEXT            NOT NULL,
    # 点赞数
    starNum INT             NOT NULL DEFAULT 0,
    # 发布日期
    date    DATETIME        NOT NULL,
    # 该评论所属的帖子id,外键
    tips_id INT             NOT NULL,
    FOREIGN KEY (tips_id) REFERENCES Tips (id)
) engine = innodb,
  charset = utf8mb4;

# 修改结束符设置为 //
DELIMITER //
# 建立查询的存储过程(分页查询 基于子查询优化)
DROP PROCEDURE IF EXISTS query_tips_by_type_with_page //
# 分页查询tips的存储过程
CREATE PROCEDURE query_tips_by_type_with_page(
    # 类型筛选
    IN tips_type varchar(30),
    # 页数
    IN page INT,
    # 每页容量
    IN page_size INT)
BEGIN
    DECLARE start_num INT UNSIGNED DEFAULT page_size * (page - 1);
    SELECT *
    FROM Tips ot
    WHERE ot.id <= (
        SELECT id
        FROM Tips it
        WHERE it.type = tips_type
        ORDER BY it.id DESC
        LIMIT start_num,1
    )
      AND ot.type = tips_type
    ORDER BY id DESC
    LIMIT page_size;
END //

# 分页查询comments的存储过程
DROP PROCEDURE IF EXISTS query_comments_by_type_with_page //
CREATE PROCEDURE query_comments_by_type_with_page(
    #所属帖子的id
    IN tid INT,
    #页数
    IN page INT,
    #每页容量
    IN page_size INT)
BEGIN
    DECLARE start_num INT UNSIGNED DEFAULT page_size * (page - 1);
    SELECT *
    FROM Comments oc
    WHERE oc.id <= (
        SELECT id
        FROM Comments ic
        WHERE ic.tips_id = tid
        ORDER BY ic.id DESC
        LIMIT start_num,1
    )
      AND oc.tips_id = tid
    ORDER BY id DESC
    LIMIT page_size;
END //

# tips 搜索功能（分页）
DROP PROCEDURE IF EXISTS search_tips_with_content //
CREATE PROCEDURE search_tips_with_content(
    IN pattern varchar(60),
    IN page INT,
    IN page_size INT
)
BEGIN
    DECLARE start_num INT UNSIGNED DEFAULT page_size * (page - 1);
    SELECT *
    FROM Tips ot
    WHERE ot.id <= (
        SELECT id
        FROM Tips it
        WHERE it.content LIKE pattern
        ORDER BY it.date DESC
        LIMIT start_num, 1
    )
      AND content LIKE pattern
    ORDER BY ot.date DESC
    LIMIT page_size;
END //
# 改为 ;
DELIMITER ;
