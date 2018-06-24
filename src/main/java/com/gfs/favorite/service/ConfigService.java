package com.gfs.favorite.service;

import com.gfs.favorite.domain.Config;

public interface ConfigService {
	
	public Config saveConfig(Long userId,String favoritesId);

	public void updateConfig(long id ,String type,String defaultFavorites);
}
