package com.example.hand_bag.Coupin;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Created by IT Empire 6/May/2020
 */
public class CouponDetails implements Serializable {

    public String arabicName;
    public String code;
    public long codeCount;
    public String details;
    public String discount;
    public long disLikeCount;
    public String expireDate;
    public String id;
    public String imgUrl;
    public String isVerified;
    public long likeCount;
    public String name;
    public String souce;
    public long timeStamp;
    public String website;
    public String uniqueId;
    public String status;
    public int order;
    public String coupon_id;
    public String type;
    public String fk_cat_id;
    public String date_time;
    public String eng_details;
    public String eng_discount;
    public String lu_type;
    public String lu_total;
    public String brief;
    public String eng_brief;
    public String favourite;
    public String adjust_event_id;
    public int slide_like_check;
    public int slide_dislike_check;
    public transient JSONArray orderList;



    public CouponDetails(String arabicName, String code, Long codeCount, String details,
                         String discount, String coupens_id, String imgUrl, String name, String website,
                         String fk_cat_id, String eng_details,
                         String eng_discount, String lu_type, String lu_total,
                         String brief, String eng_brief, String favourite, String adjust_event_id, int order, JSONArray orderList) {
        this.arabicName = arabicName;
        this.code = code;
        this.codeCount = codeCount;
        this.details = details;
        this.discount = discount;
        this.coupon_id = coupens_id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.website = website;
        this.fk_cat_id = fk_cat_id;
        this.eng_details = eng_details;
        this.eng_discount = eng_discount;
        this.lu_type = lu_type;
        this.lu_total = lu_total;
        this.brief = brief;
        this.eng_brief = eng_brief;
        this.favourite = favourite;
        this.adjust_event_id = adjust_event_id;
        this.orderList = orderList;
        this.order = order;
    }
    public JSONArray getOrderList() {
        return orderList;
    }

    public void setOrderList(JSONArray orderList) {
        this.orderList = orderList;
    }
    public String getAdjust_event_id() {
        return adjust_event_id;
    }

    public void setAdjust_event_id(String adjust_event_id) {
        this.adjust_event_id = adjust_event_id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getEng_brief() {
        return eng_brief;
    }

    public void setEng_brief(String eng_brief) {
        this.eng_brief = eng_brief;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public int getSlide_like_check() {
        return slide_like_check;
    }

    public void setSlide_like_check(int slide_like_check) {
        this.slide_like_check = slide_like_check;
    }

    public int getSlide_dislike_check() {
        return slide_dislike_check;
    }

    public void setSlide_dislike_check(int slide_dislike_check) {
        this.slide_dislike_check = slide_dislike_check;
    }

    public String getLu_type() {
        return lu_type;
    }

    public void setLu_type(String lu_type) {
        this.lu_type = lu_type;
    }

    public String getLu_total() {
        return lu_total;
    }

    public void setLu_total(String lu_total) {
        this.lu_total = lu_total;
    }

    public String getEng_details() {
        return eng_details;
    }

    public void setEng_details(String eng_details) {
        this.eng_details = eng_details;
    }

    public String getEng_discount() {
        return eng_discount;
    }

    public void setEng_discount(String eng_discount) {
        this.eng_discount = eng_discount;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getFk_cat_id() {
        return fk_cat_id;
    }

    public void setFk_cat_id(String fk_cat_id) {
        this.fk_cat_id = fk_cat_id;
    }

    public String getVerified() {
        return isVerified;
    }

    public void setDisLikeCount(long disLikeCount) {
        this.disLikeCount = disLikeCount;
    }

    public void setVerified(String verified) {
        isVerified = verified;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public CouponDetails(String name) {
        this.name = name;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(long codeCount) {
        this.codeCount = codeCount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public long getDisLikeCount() {
        return disLikeCount;
    }

//    public void setDisLikeCount(long disLikeCount) {
//        this.disLikeCount = disLikeCount;
//    }

    public void setDisLikeCount(Object disLikeCount) {
        if (disLikeCount instanceof Long)
            this.disLikeCount = (long) disLikeCount;
    }

    public void setLikeCount(Object likeCount) {
        if (likeCount instanceof Long)
            this.likeCount = (long) likeCount;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String verified) {
        isVerified = verified;
    }

    public long getLikeCount() {
        return likeCount;
    }

//    public void setLikeCount(long likeCount) {
//        this.likeCount = likeCount;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSouce() {
        return souce;
    }

    public void setSouce(String souce) {
        this.souce = souce;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public CouponDetails(String arabicName, String code, long codeCount, String details, String discount, long disLikeCount, String expireDate,
                         String id, String imgUrl, String isVerified, long likeCount, String name, int order, String source,
                         long timeStamp, String website, String status, String type, String fk_cat_id, String date_time) {
        this.arabicName = arabicName;
        this.code = code;
        this.codeCount = codeCount;
        this.details = details;
        this.discount = discount;
        this.disLikeCount = disLikeCount;
        this.expireDate = expireDate;
        this.coupon_id = id;
        this.imgUrl = imgUrl;
        this.isVerified = isVerified;
        this.likeCount = likeCount;
        this.name = name;
        this.order = order;
        this.souce = source;
        this.timeStamp = timeStamp;
        this.website = website;
        this.status = status;
        this.type = type;
        this.fk_cat_id = fk_cat_id;
        this.date_time = date_time;
    }

    /*public CouponDetails(String arabicName, String code, long codeCount, String details, String discount, long disLikeCount, String id,
                          Boolean isVerified, long likeCount, String name, int order, String source, long timeStamp, String website) {
        this.arabicName = arabicName;
        this.code = code;
        this.codeCount = codeCount;
        this.details = details;
        this.discount = discount;
        this.disLikeCount = disLikeCount;
        this.id = id;
        this.isVerified = isVerified;
        this.likeCount = likeCount;
        this.name = name;
        this.order = order;
        this.souce = source;
        this.timeStamp = timeStamp;
        this.website = website;
  }
*/

    public CouponDetails() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
