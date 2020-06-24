package com.yekai.community.mapper;

import com.yekai.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modify,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{size} offset #{offset}")
    List<Question> list(@Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(1) from question")
    Integer count();
}
