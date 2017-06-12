package com.campiador.respdroid.model;

import com.google.gson.Gson;

/**
 * Created by behnam on 6/11/17.
 */

public class RespNode {

    private int node_id;
    private int experiment_id;
    private long delay;
    private String timestamp;
    private String device;
    private String operation;
    private String img_base;
    private long img_perc;
    private int img_sizeKB;
    private int imgWidth;
    private int imgHeight;
    private float img_MPs;

    public RespNode(int node_id, int experiment_id, long delay, String timestamp, String device,
                    String operation, String img_base, long img_perc, int img_sizeKB,
                    int imgWidth, int imgHeight) {
        this.node_id = node_id;
        this.experiment_id = experiment_id;
        this.delay = delay;
        this.timestamp = timestamp;
        this.device = device;
        this.operation = operation;
        this.img_base = img_base;
        this.img_perc = img_perc;
        this.img_sizeKB = img_sizeKB;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.img_MPs = getImgHeight()* getImgHeight() / (float)1000000;
    }


    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getExperiment_id() {
        return experiment_id;
    }

    public void setExperiment_id(int experiment_id) {
        this.experiment_id = experiment_id;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getImg_base() {
        return img_base;
    }

    public void setImg_base(String img_base) {
        this.img_base = img_base;
    }

    public long getImg_perc() {
        return img_perc;
    }

    public void setImg_perc(int img_perc) {
        this.img_perc = img_perc;
    }

    public int getImg_sizeKB() {
        return img_sizeKB;
    }

    public void setImg_sizeKB(int img_sizeKB) {
        this.img_sizeKB = img_sizeKB;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }


    public String toString() {

        return "nid: " + node_id + ", xid: " + experiment_id + ", datetime: " + timestamp
        + ", device: " + device + ", delay: " + delay + ", operation: " + operation +
        ", imgbase: " + img_base + ", imgperc: " + img_perc + ", sizeKB: "
        + img_sizeKB + ", Resolution: " + getImageResolution() + ", MPs: " + img_MPs;
    }

    private String getImageResolution() {

        return getImgWidth() + "x" + getImgHeight();
    }


    public String serialize_to_Json(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
