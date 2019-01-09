package com.factory;

import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;

public class ServiceFactory {

    public static ReplyBean executeService(HttpServletRequest oRequest) throws Exception {

        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;

        switch (ob) {
            case "usuario":
                UsuarioService oUsuarioService = new UsuarioService(oRequest);
                switch (op) {
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "check":
                        oReplyBean = oUsuarioService.check();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;

            default:
                oReplyBean = new ReplyBean(500, "Object doesn't exist");
                break;
        }
        return oReplyBean;

    }

}