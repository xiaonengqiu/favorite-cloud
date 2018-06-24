package com.gfs.favorite.service;

import com.gfs.favorite.domain.Collect;
import com.gfs.favorite.domain.Favorites;

public interface FavoritesService {
	public Favorites saveFavorites(Long userId,String name);
	public Favorites saveFavorites(Collect collect);
	public void countFavorites(long id);

}
