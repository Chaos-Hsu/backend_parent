package com.meetingfilm.film.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 影片主表
 * </p>
 *
 * @author xqc
 * @since 2020-12-09
 */
@Data
public class MoocFilmT extends Model<MoocFilmT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    /**
     * 影片名称
     */
    private String filmName;

    /**
     * 片源类型: 0-2D,1-3D,2-3DIMAX,4-无
     */
    private Integer filmType;

    /**
     * 影片主图地址
     */
    private String imgAddress;

    /**
     * 影片评分
     */
    private String filmScore;

    /**
     * 影片预售数量
     */
    @TableField("film_preSaleNum")
    private Integer filmPresalenum;

    /**
     * 影片票房：每日更新，以万为单位
     */
    private Integer filmBoxOffice;

    /**
     * 影片片源，参照片源字典表
     */
    private Integer filmSource;

    /**
     * 影片分类，参照分类表,多个分类以#分割
     */
    private String filmCats;

    /**
     * 影片区域，参照区域表
     */
    private Integer filmArea;

    /**
     * 影片上映年代，参照年代表
     */
    private Integer filmDate;

    /**
     * 影片上映时间
     */
    private LocalDateTime filmTime;

    /**
     * 影片状态,1-正在热映，2-即将上映，3-经典影片
     */
    private Integer filmStatus;


    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "MoocFilmT{" +
                ", uuid=" + uuid +
                ", filmName=" + filmName +
                ", filmType=" + filmType +
                ", imgAddress=" + imgAddress +
                ", filmScore=" + filmScore +
                ", filmPresalenum=" + filmPresalenum +
                ", filmBoxOffice=" + filmBoxOffice +
                ", filmSource=" + filmSource +
                ", filmCats=" + filmCats +
                ", filmArea=" + filmArea +
                ", filmDate=" + filmDate +
                ", filmTime=" + filmTime +
                ", filmStatus=" + filmStatus +
                "}";
    }
}
