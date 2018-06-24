package com.gfs.favorite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfs.favorite.common.aop.LoggerManage;
import com.gfs.favorite.domain.Follow;
import com.gfs.favorite.domain.enums.FollowStatus;
import com.gfs.favorite.domain.result.ExceptionMsg;
import com.gfs.favorite.domain.result.Response;
import com.gfs.favorite.dao.FollowRepository;
import com.gfs.favorite.utils.DateUtils;

@RestController
@RequestMapping("/follow")
public class FollowController extends BaseController{
	
	@Autowired
	private FollowRepository followRepository;
	
	/**
	 * 关注&取消关注
	 * @return
	 */
	@RequestMapping("/changeFollowStatus")
	@LoggerManage(description="关注&取消关注")
	public Response changeFollowStatus(String status,Long userId){
		try {
			FollowStatus followStatus = FollowStatus.FOLLOW;
			if(!"follow".equals(status)){
				followStatus = FollowStatus.UNFOLLOW;
			}
			Follow follow = followRepository.findByUserIdAndFollowId(getUserId(), userId);
			if(null != follow){
				followRepository.updateStatusById(followStatus, DateUtils.getCurrentTime(), follow.getId());
			}else{
				follow = new Follow();
				follow.setFollowId(userId);
				follow.setUserId(getUserId());
				follow.setStatus(followStatus);
				follow.setCreateTime(DateUtils.getCurrentTime());
				follow.setLastModifyTime(DateUtils.getCurrentTime());
				followRepository.save(follow);
			}
		} catch (Exception e) {
			logger.error("关注&取消关注异常：",e);
			return result(ExceptionMsg.FAILED);
		}
		return result();
	}

}
