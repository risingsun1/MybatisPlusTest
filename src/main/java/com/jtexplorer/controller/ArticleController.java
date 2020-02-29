package com.jtexplorer.controller;

import com.jtexplorer.service.ArticleService;
import com.jtexplorer.entity.Article;
import com.jtexplorer.entity.enums.RequestEnum;
import com.jtexplorer.entity.dto.JsonResult;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@RestController
@RequestMapping("/front/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    /**
       * 分页查询条件
       * @param article 对象中填充查询条件
       * @param page 第几页
       * @param limit 每页显示的数量
       * @return jsonResult.root : 记录，jsonResult.success : 成功/失败，jsonResult.totalSize : 总数量
       */
    @GetMapping("/getPageList")
    public JsonResult getArticleList(@ModelAttribute Article article,
                                       @RequestParam(required = false,defaultValue = "1")Integer page,
                                       @RequestParam(required = false,defaultValue = "20")Integer limit) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
    //        //条件DEMO
    //        //like
    //        if(StringUtils.isNotEmpty(article.getArtiTitle())){
    //            articleWrapper.lambda().like(Article::getArtiTitle,article.getArtiTitle());
    //        }
    //        //等于
    //        articleWrapper.eq("column","value");
        Page articlePage = new Page<>(page,limit);
            //第三个参数为false的时候，不查询数据总数
    //        Page articlePage = new Page<>(page,limit,false);
        IPage<Article> articleIPage = articleService.page(articlePage,articleWrapper);

        jsonResult.setSuccess(true);
        jsonResult.setTotalSize(articleIPage.getTotal());
        jsonResult.setRoot(articleIPage.getRecords());

        return jsonResult;
    }

    /**
    * 不分页查询条件
    * @param article 对象中填充查询条件
    * @return jsonResult.root : 记录，jsonResult.success : 成功/失败，jsonResult.totalSize : 总数量
    */
    @GetMapping("/getList")
    public JsonResult getList(@ModelAttribute Article article) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();

    //        //条件DEMO
    //        //like
    //        if(StringUtils.isNotEmpty(article.getArtiTitle())){
    //            articleWrapper.lambda().like(Article::getArtiTitle,article.getArtiTitle());
    //        }
    //        //等于
    //        articleWrapper.eq("column","value");

        List<Article> articleList = articleService.list(articleWrapper);

        jsonResult.setSuccess(true);
        jsonResult.setTotalSize(articleService.count(articleWrapper));
        jsonResult.setRoot(articleList);

        return jsonResult;
    }

    /**
    * 新增一条数据
    * @param article
    * @return jsonResult.success : 成功/失败
    */
    @PostMapping("/add")
    public JsonResult add(Article article) {

        JsonResult jsonResult= new JsonResult();

        if(articleService.save(article)){
            jsonResult.setSuccess(true);
        }else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_UPDATE_ERROR);
        }

        return jsonResult;
    }

    /**
    * 根据主键更新
    * @param article
    * @return jsonResult.success : 成功/失败
    */
    @PostMapping("/updateById")
    public JsonResult articleSave(Article article) {
        JsonResult jsonResult= new JsonResult();

        if(articleService.updateById(article)){
            jsonResult.setSuccess(true);
        }else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_UPDATE_ERROR);
        }

        return jsonResult;
    }

    /**
    * 根据id删除对象
    * @param id  实体ID
    * @return jsonResult.success : 成功/失败
    */
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam(required = false,defaultValue = "0") Long id){
        JsonResult jsonResult = new JsonResult();
            //判断主键
        if(id == 0){
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_DELETE_NO_KEY);
        }

        if(articleService.removeById(id)){
            jsonResult.setSuccess(true);
        }else{
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_DELETE_ERROR);
        }

        return jsonResult;
    }

    /**
        * 根据Id批量删除数据
        * @param idList 需要删除对象ID列表
        * @return jsonResult.success : 成功/失败
        */
    @PostMapping("/batchDeleteById")
    public JsonResult deleteBatchIds(@RequestParam(required = false,defaultValue = "") String idList){
        JsonResult jsonResult=new JsonResult();

            //先验证id组成的JOSN
        List<Long> ids=null;
        try{
            ids=(List<Long>)JSON.parse(idList);
        }catch(Exception e){
            jsonResult.setSuccess(false);
            jsonResult.setFailReason("JSON格式错误!");
            return jsonResult;
        }

        if(articleService.removeByIds(ids)){
            jsonResult.setSuccess(true);
        }else{
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_DELETE_ERROR);
        }

        return jsonResult;
    }

}