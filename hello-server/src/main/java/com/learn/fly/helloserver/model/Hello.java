package com.learn.fly.helloserver.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author:Fly
 * @Date:Create in 2019/6/7 下午10:26
 * @Description:
 * @Modified:
 */
@Data
@TableName(value = "hello")
public class Hello {

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "name")
    private String name;
}
