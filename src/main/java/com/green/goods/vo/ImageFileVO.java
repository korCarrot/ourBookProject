package com.green.goods.vo;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("imageFileVO")
public class ImageFileVO {
    private int image_id;
    private int goods_id;
    private String fileName;
    private String reg_id;
    private String fileType;

    private Date rgdate;


    public ImageFileVO() {

    }


    public int getGoods_id() {
        return goods_id;
    }




    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }




    public int getImage_id() {
        return image_id;
    }




    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }




    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }


    public Date getRgdate() {
        return rgdate;
    }

    public void setRgdate(Date rgdate) {
        this.rgdate = rgdate;
    }
}
