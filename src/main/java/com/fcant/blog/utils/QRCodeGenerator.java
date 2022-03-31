package com.fcant.blog.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * QRCodeGenerator
 * <p>
 * encoding:UTF-8
 *
 * @author Fcant 上午 8:24 2022/3/31/0031
 */
public class QRCodeGenerator {
    /**
     * 根据内容，大小生成二维码到指定路径
     *
     * @param contents 跳转的链接
     * @param width    宽度
     * @param height   高度
     * @param filePath 路径
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQrWithImage(String contents, int width, int height, String filePath) throws WriterException, IOException {
        //构造二维码写码器
        MultiFormatWriter mutiWriter = new com.google.zxing.MultiFormatWriter();
        HashMap<EncodeHintType, Object> hint = new HashMap<>(16);
        hint.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.MARGIN, 1);
        //生成二维码
        BitMatrix bitMatrix = mutiWriter.encode(contents, BarcodeFormat.QR_CODE, width, height, hint);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "jpeg", path);
    }
}
