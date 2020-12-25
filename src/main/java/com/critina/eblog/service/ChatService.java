package com.critina.eblog.service;

import com.critina.eblog.im.vo.ImMess;
import com.critina.eblog.im.vo.ImUser;

import java.util.List;

public interface ChatService {
    ImUser getCurrentUser();

    void setGroupHistoryMsg(ImMess responseMess);

    List<Object> getGroupHistoryMsg(int count);
}
