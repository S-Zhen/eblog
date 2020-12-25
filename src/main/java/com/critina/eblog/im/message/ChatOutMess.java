package com.critina.eblog.im.message;

import com.critina.eblog.im.vo.ImMess;
import lombok.Data;

@Data
public class ChatOutMess {

    private String emit;
    private ImMess data;

}
