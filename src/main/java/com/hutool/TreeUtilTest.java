package com.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.expression.ExpressionUtil;
import cn.hutool.extra.pinyin.PinyinUtil;

import java.lang.reflect.Array;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author rensong.pu
 * @date 2024/8/13
 */
public class TreeUtilTest {
    public static void main(String[] args) {
        final Dict dict = Dict.create()
                .set("a", 100.3)
                .set("b", 45)
                .set("c", -199.100);

// -143.8
        final Object eval = ExpressionUtil.eval("a-(b-c)", dict);

        System.out.println(eval);
    }
}
