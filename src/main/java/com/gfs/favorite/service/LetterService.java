package com.gfs.favorite.service;

import com.gfs.favorite.domain.Letter;
import com.gfs.favorite.domain.view.LetterSummary;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by DingYS on 2017/3/8.
 */
public interface LetterService {

    public void sendLetter(Letter letter);

    public List<LetterSummary> findLetter(Long userId, Pageable pageable);
}
