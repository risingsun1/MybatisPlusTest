package com.jtexplorer.service.impl;

import com.jtexplorer.entity.Exam;
import com.jtexplorer.mapper.ExamMapper;
import com.jtexplorer.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * exam_考试表 服务实现类
 * </p>
 *
 * @author xu.wang
 * @since 2020-03-01
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

}
