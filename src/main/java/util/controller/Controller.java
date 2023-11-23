/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.controller;

import util.dao.Dao;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sisco
 */
public class Controller {

    private String nrProcesso;
    private String anoProcesso;
    private ResultSet result;
    private Dao dao;

    public Controller(Connection connect) {
        dao = new Dao(connect);
    }

    public boolean enviarMensagemErroResult(String nomeRobo, String tipoRobo, String caminhoArqErro, String caminhoImg, String caminhoHTML) {
        System.out.println("Chamou a função do Controlador enviarMensagemErroResult()");
        return dao.enviarMensagemErroResult(nomeRobo, tipoRobo, caminhoArqErro, caminhoImg, caminhoHTML) == 1;
    }
}
