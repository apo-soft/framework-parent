/**
 * 
 */
package cn.aposoft.framework.ftp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;

/**
 * FTP 服务器访问地址
 * 
 * 如果是中文，文件目录，需要转换为ISO-8859-1编码，才可以安全访问
 * 
 * InputStream 无法直接访问？
 * 
 * @author LiuJian
 * @since 1.0
 */
public class FtpClientUtil {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);

    public static FTPClient getClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("localhost", 21);// 连接FTP服务器
        ftpClient.login("liujian", "liujian");// 登陆FTP服务器
        ftpClient.setControlEncoding("UTF-8");
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            logger.info("未连接到FTP，用户名或密码错误。");
            ftpClient.disconnect();
        } else {
            logger.info("FTP连接成功。");
        }
        // 进入被动模式
        ftpClient.enterLocalPassiveMode();
        return ftpClient;
    }

    public static void main(String[] args) {
        FTPClient client = null;
        try {

            client = getClient();
            logger.info("curr dir:{}", client.printWorkingDirectory());
            FTPFile[] dirs = client.listDirectories(client.printWorkingDirectory());
            logger.info("dirs empty result:" + (dirs == null || dirs.length == 0));
            for (FTPFile dir : dirs) {
                logger.info(dir.toString());
            }
            FTPFile[] files = client.listFiles("/");
            logger.info("files empty result:" + (files == null || files.length == 0));
            for (FTPFile file : files) {
                logger.info(file.toString());
            }
            long begin = System.currentTimeMillis();
            logger.info("begin:" + new Date(begin));
            // client.changeToParentDirectory();
            for (int i = 0; i < 1; i++) {
                try (ByteArrayOutputStream output = new ByteArrayOutputStream();) {
                    boolean retrieved = client.retrieveFile("aaa.docx", output);
                    logger.info("retrieved:" + retrieved);
                    if (retrieved) {
                        logger.info("length:" + output.toByteArray().length);
                    }
                }
            }
            long end = System.currentTimeMillis();
            logger.info("end:" + new Date(end) + "," + (end - begin));

            // 测试中文名称
            {
                logger.info("中文文件下载:");
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                boolean retrieved = client.retrieveFile(new String("Log4j2使用手册.docx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1),
                        output);
                logger.info("中文文件下载 retrieved:" + retrieved);
                if (retrieved) {
                    logger.info("中文文件下载 length:" + output.toByteArray().length);
                }
            }
            // InputStream 使用问题
            {
                try (InputStream input = client.retrieveFileStream(new String("Log4j2使用手册.docx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));) {
                    // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
                    // int reply = client.getReply();
                    // logger.info("reply:" + reply);
                    if (input != null) {
                        byte[] bytes = IOUtils.toByteArray(input);
                        logger.info("input stream file length:" + bytes.length);
                    } else {
                        logger.error("input stream file is null.");
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
