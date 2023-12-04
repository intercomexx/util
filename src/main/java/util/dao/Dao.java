package util.dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.slf4j.LoggerFactory;

import java.sql.Blob;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Sisco
 */
public class Dao {

    public Connection conexao;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Dao.class);

    public Dao(Connection c) {
        this.conexao = c;
    }

    public int enviarMensagemErroResult(String nomeRobo, String tipoRobo, String caminhoArqErro, String caminhoImg, String caminhoHTML) {
        String procedureQuery = "{call ENVIA_MSG_ERRO_" + nomeRobo.toUpperCase() + "(" + (tipoRobo != null ? "?, " : "") + "?, ?, ?)}";
        LOGGER.info(procedureQuery);
        Blob arqErroBlob = null;
        Blob imgBlob = null;
        Blob htmlBlob = null;
        try {

            if (caminhoArqErro != null) {
                arqErroBlob = converterPdfBlob(caminhoArqErro);
                LOGGER.info("Tamanaho do Blob: " + (arqErroBlob.length()));
            }
            if (caminhoImg != null) {
                imgBlob = converterPdfBlob(caminhoImg);
                LOGGER.info("Tamanaho do imgBlob: " + (imgBlob.length()));
            }
            if (caminhoHTML != null) {
                htmlBlob = converterPdfBlob(caminhoHTML);
                LOGGER.info("Tamanaho do htmlBlob: " + (htmlBlob.length()));
            }

            CallableStatement callableSt = conexao.prepareCall(procedureQuery);

            int indexStatment = 1;

            if (tipoRobo != null) {
                callableSt.setString(indexStatment++, tipoRobo);
            }

            callableSt.setBlob(indexStatment++, arqErroBlob);
            callableSt.setBlob(indexStatment++, imgBlob);
            callableSt.setBlob(indexStatment++, htmlBlob);

            return callableSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Blob converterPdfBlob(String arq) throws SQLException, IOException {
        File file = new File(arq);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();

        Blob blob = conexao.createBlob();
        blob.setBytes(1, bytes);

        return blob;
    }
}
