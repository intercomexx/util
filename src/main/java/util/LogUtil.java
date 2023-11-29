package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    private  final Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);
    private static String classe;
    public LogUtil(Class<?> clazz){
        classe = clazz.getName();
    }
    public  void getLoggerInfo(String informacao){
        LocalDateTime horarioAgora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).minusHours(1);
//        LocalDateTime horarioAgora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String date = horarioAgora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        LOGGER.info("[" + classe + "] " + date + " " + informacao);
    }
}


