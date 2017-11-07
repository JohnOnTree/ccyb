package live.cnaidai.com.ccyb.util;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * description: 上传工具类
 * autour: ChristLu
 * date: 17/6/26
 * package: live.cnaidai.com.ccyb.util
 */

public class UploadUtil {

    public static  String uploadFile(String actionUrl, String picPath,String fileName) {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";//边界标识
        try {
            URL url = new URL(actionUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
        /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);//允许输入流
            con.setDoOutput(true);//允许输出流
            con.setUseCaches(false);//不允许使用缓存
        /* 设置传送的method=POST */
            con.setRequestMethod("POST");
        /* setRequestProperty   设置编码  */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",// "multipart/form-data"这个参数来说明我们这传的是文件不是字符串了
                    "multipart/form-data;boundary=" + boundary);
        /* 设置DataOutputStream */
            DataOutputStream ds =
                    new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file1\";filename=\"" +
                    fileName + "\"" + end);
            ds.writeBytes(end);


        /* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(picPath);
        /* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
        /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
          /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
        /* close streams */
            fStream.close();
            ds.flush();
        /* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
        /* 将Response显示于Dialog */

            //  showDialog("上传成功"+b.toString().trim());
        /* 关闭DataOutputStream */
            ds.close();
            //返回客户端返回的信息
            return b.toString().trim();
        } catch (Exception e) {
            //showDialog("上传失败"+e);
            return null;
        }
    }

}