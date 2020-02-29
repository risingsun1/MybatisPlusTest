package com.jtexplorer.controller;

import com.jtexplorer.service.ExamService;
import com.jtexplorer.entity.Exam;
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
@RequestMapping("/front/exam")
public class ExamController {

    @Autowired
    public ExamService examService;

    /**
       * 分页查询条件
       * @param exam 对象中填充查询条件
       * @param page 第几页
       * @param limit 每页显示的数量
       * @return jsonResult.root : 记录，jsonResult.success : 成功/失败，jsonResult.totalSize : 总数量
       */
    @GetMapping("/getPageList")
    public JsonResult getExamList(@ModelAttribute Exam exam,
                                       @RequestParam(required = false,defaultValue = "1")Integer page,
                                       @RequestParam(required = false,defaultValue = "20")Integer limit) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<Exam> examWrapper = new QueryWrapper<>();
    //        //条件DEMO
    //        //like
    //        if(StringUtils.isNotEmpty(article.getArtiTitle())){
    //            articleWrapper.lambda().like(Article::getArtiTitle,article.getArtiTitle());
    //        }
    //        //等于
    //        articleWrapper.eq("column","value");
        Page examPage = new Page<>(page,limit);
            //第三个参数为false的时候，不查询数据总数
    //        Page articlePage = new Page<>(page,limit,false);
        IPage<Exam> examIPage = examService.page(examPage,examWrapper);

        jsonResult.setSuccess(true);
        jsonResult.setTotalSize(examIPage.getTotal());
        jsonResult.setRoot(examIPage.getRecords());

        return jsonResult;
    }

    /**
    * 不分页查询条件
    * @param exam 对象中填充查询条件
    * @return jsonResult.root : 记录，jsonResult.success : 成功/失败，jsonResult.totalSize : 总数量
    */
    @GetMapping("/getList")
    public JsonResult getList(@ModelAttribute Exam exam) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<Exam> examWrapper = new QueryWrapper<>();

    //        //条件DEMO
    //        //like
    //        if(StringUtils.isNotEmpty(article.getArtiTitle())){
    //            articleWrapper.lambda().like(Article::getArtiTitle,article.getArtiTitle());
    //        }
    //        //等于
    //        articleWrapper.eq("column","value");

        List<Exam> examList = examService.list(examWrapper);

        jsonResult.setSuccess(true);
        jsonResult.setTotalSize(examService.count(examWrapper));
        jsonResult.setRoot(examList);

        return jsonResult;
    }

    /**
    * 新增一条数据
    * @param exam
    * @return jsonResult.success : 成功/失败
    */
    @PostMapping("/add")
    public JsonResult add(Exam exam) {

        JsonResult jsonResult= new JsonResult();

        if(examService.save(exam)){
            jsonResult.setSuccess(true);
        }else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_UPDATE_ERROR);
        }

        return jsonResult;
    }

    /**
    * 根据主键更新
    * @param exam
    * @return jsonResult.success : 成功/失败
    */
    @PostMapping("/updateById")
    public JsonResult articleSave(Exam exam) {
        JsonResult jsonResult= new JsonResult();

        if(examService.updateById(exam)){
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

        if(examService.removeById(id)){
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

        if(examService.removeByIds(ids)){
            jsonResult.setSuccess(true);
        }else{
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_DELETE_ERROR);
        }

        return jsonResult;
    }

}