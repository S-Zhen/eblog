package com.critina.eblog.im.message;

import com.critina.eblog.im.vo.ImTo;
import com.critina.eblog.im.vo.ImUser;
import lombok.Data;

@Data
public class ChatImMess {

    private ImUser mine;
    private ImTo to;

}
