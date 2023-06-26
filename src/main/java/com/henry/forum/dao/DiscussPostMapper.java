package com.henry.forum.dao;

import com.henry.forum.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    /**
     *
     * @param userId
     * @param offset    起始
     * @param limit     个数
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /**
     * 查询帖子的行数
     * @param userId
     * @return
     */
    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>动态标签里使用,则必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);
}
