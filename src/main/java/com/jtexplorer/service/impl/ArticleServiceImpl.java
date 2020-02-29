package com.jtexplorer.service.impl;

import com.jtexplorer.entity.Article;
import com.jtexplorer.mapper.ArticleMapper;
import com.jtexplorer.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * article_文章表 服务实现类
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
