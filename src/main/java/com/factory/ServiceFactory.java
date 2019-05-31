package com.factory;

import com.bean.ReplyBean;
import com.service.HistoricoService;
import com.service.TrabajadorService;
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
            case "trabajador":
                TrabajadorService oClienteService = new TrabajadorService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oClienteService.get();
                        break;

                    default:
                        oReplyBean = new ReplyBean(500, "Operation doesn't exist");
                        break;
                }
                break;
            case "historico":
                HistoricoService oHistoricoService = new HistoricoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oHistoricoService.get();
                        break;
                    case "create":
                        oReplyBean = oHistoricoService.create();
                        break;
                    case "getpage":
                        oReplyBean = oHistoricoService.getpage();
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
