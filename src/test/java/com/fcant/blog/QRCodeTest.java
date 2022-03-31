package com.fcant.blog;

import com.fcant.blog.utils.QRCodeGenerator;
import com.fcant.blog.utils.ThumbnailsImageUtils;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * QRCodeTest
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 上午 8:26 2022/3/31/0031
 */
public class QRCodeTest {
    @Test
    public void qrCodeTest() {
        try {
            QRCodeGenerator.generateQrWithImage("https://www.yuque.com/fcant/java", 500, 500, "./QRCode.jpeg");
        } catch (Exception e) {
        }
    }

    @Test
    public void reSizePhotoTest() throws IOException {
        ThumbnailsImageUtils.size("./photo.jpg", 100, 100, 1, "./photo100.png");
    }

    @Test
    public void writerMakerTest() {
        try {
            // 将大图片缩小到指定大小
            // 通过水印的形式，将头像加到生成的二维码上面
            ThumbnailsImageUtils.watermark("./QRCode.jpeg",
                    500, 500, Positions.CENTER, "./photo100.png",
                    1f, 1f, "./result.png");
        } catch (Exception e) {
        }
    }
}
