package com.gfs.favorite.service.impl;

import com.gfs.favorite.domain.view.IndexCollectorView;
import com.gfs.favorite.dao.CollectorRepository;
import com.gfs.favorite.dao.UserRepository;
import com.gfs.favorite.service.CollectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 获取收藏家
 * @Auth: yuyang
 * @Date: 2017/1/19 14:14
 * @Version: 1.0
 **/
@Service
public class CollectorServiceImpl implements CollectorService {
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取收藏家
     * @return
     */
    @Override
    public IndexCollectorView getCollectors() {
        IndexCollectorView indexCollectorView = new IndexCollectorView();
        try {
            long mostCollectUser = collectorRepository.getMostCollectUser();
            indexCollectorView.setMostCollectUser(userRepository.findById(mostCollectUser));
            long mostFollowedUser = collectorRepository.getMostFollowedUser(mostCollectUser);
            indexCollectorView.setMostFollowedUser(userRepository.findById(mostFollowedUser));
            String notUserIds = mostCollectUser+","+mostFollowedUser;
            long mostPraisedUser = collectorRepository.getMostPraisedUser(notUserIds);
            indexCollectorView.setMostPraisedUser(userRepository.findById(mostPraisedUser));
            notUserIds += ","+mostPraisedUser;
            long mostCommentedUser = collectorRepository.getMostCommentedUser(notUserIds);
            indexCollectorView.setMostCommentedUser(userRepository.findById(mostCommentedUser));
            notUserIds += ","+ mostCommentedUser;
            long mostPopularUser = collectorRepository.getMostPopularUser(notUserIds);
            indexCollectorView.setMostPopularUser(userRepository.findById(mostPopularUser));
            notUserIds += ","+ mostPopularUser;
            long mostActiveUser = collectorRepository.getMostActiveUser(notUserIds);
            indexCollectorView.setMostActiveUser(userRepository.findById(mostActiveUser));
        }catch (Exception e){
            logger.info("错误",e);
        }
        return indexCollectorView;
    }
}
