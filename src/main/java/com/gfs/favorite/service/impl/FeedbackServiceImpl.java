package com.gfs.favorite.service.impl;

import com.gfs.favorite.domain.Feedback;
import com.gfs.favorite.dao.FeedbackRepository;
import com.gfs.favorite.service.FeedbackService;
import com.gfs.favorite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenzhimin on 2017/2/23.
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void saveFeeddback(Feedback feedback,Long userId) {
        feedback.setUserId(userId == null || userId == 0L ? null : userId);
        feedback.setCreateTime(DateUtils.getCurrentTime());
        feedback.setLastModifyTime(DateUtils.getCurrentTime());
        feedbackRepository.save(feedback);
    }
}
