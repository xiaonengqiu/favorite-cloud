package com.gfs.favorite.service.impl;

import com.gfs.favorite.domain.Config;
import com.gfs.favorite.dao.ConfigRepository;
import com.gfs.favorite.service.ConfigService;
import com.gfs.favorite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService{
	
	@Autowired
	private ConfigRepository configRepository;
	
	/**
	 * 保存属性设置
	 * @param userId
	 * @param favoritesId
	 * @return
	 */
	public Config saveConfig(Long userId,String favoritesId){
		Config config = new Config();
		config.setUserId(userId);
		config.setDefaultModel("simple");
		config.setDefaultFavorties(favoritesId);
		config.setDefaultCollectType("public");
		config.setCreateTime(DateUtils.getCurrentTime());
		config.setLastModifyTime(DateUtils.getCurrentTime());
		configRepository.save(config);
		return config;
	}
	
	/**
	 * 属性修改
	 * @param id
	 * @param type
	 */
	public void updateConfig(long id ,String type,String defaultFavorites){
		Config config = configRepository.findById(id);
		String value="";
		if("defaultCollectType".equals(type)){
			if("public".equals(config.getDefaultCollectType())){
				value = "private";
			}else{
				value = "public";
			}
			configRepository.updateCollectTypeById(id, value, DateUtils.getCurrentTime());
		}else if("defaultModel".equals(type)){
			if("simple".equals(config.getDefaultModel())){
				value = "major";
			}else{
				value="simple";
			}
			configRepository.updateModelTypeById(id, value, DateUtils.getCurrentTime());
		}else if("defaultFavorites".equals(type)){
			configRepository.updateFavoritesById(id, defaultFavorites, DateUtils.getCurrentTime());
		}
		
	}

}
