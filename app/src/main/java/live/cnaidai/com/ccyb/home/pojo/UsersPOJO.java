package live.cnaidai.com.ccyb.home.pojo;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * description: 首页用户实体$
 * autour: ChristLu
 * date: $
 * version: $
 */
public class UsersPOJO extends BasePOJO{


    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        /**
         * creditScore : null
         * birthday : null
         * sex : 2
         * phone : null
         * lon : null
         * score : null
         * headPic : image/member/head20170110021823.jpg
         * type : null
         * nowCity : null
         * shareCode : null
         * lvName : 短工
         * distance : 距离未知
         * level : lv1
         * parentShareCode : null
         * faceScoreStr : null
         * memberId : 64
         * memberName : 潇洒哥
         * lat : null
         * faceScore : null
         * memberLabelList : [{"id":55,"objName":"潇洒哥","updated":null,"created":"2017-01-10T02:18:23","updator":null,"objId":64,"labelName":"美剧爱好","type":1,"deleted":false,"labelId":6,"creator":null}]
         */

        private List<MemberListEntity> memberList;
        /**
         * adHtml5 : html5/advert/advert_detail_11.html
         * status : 2
         * updator : "null"
         * descNote : 1
         * type : 1
         * deleted : false
         * creator : null
         * id : 11
         * created : 2016-12-01T10:05:14
         * updated : 2017-01-10T01:46:54
         * name : 大话西游
         * objId : 1
         * orders : 1
         * adPic : image/advert/advert20170110011254.jpg
         */

        private List<IndexAdvertListEntity> indexAdvertList;

        public List<MemberListEntity> getMemberList() {
            return memberList;
        }

        public void setMemberList(List<MemberListEntity> memberList) {
            this.memberList = memberList;
        }

        public List<IndexAdvertListEntity> getIndexAdvertList() {
            return indexAdvertList;
        }

        public void setIndexAdvertList(List<IndexAdvertListEntity> indexAdvertList) {
            this.indexAdvertList = indexAdvertList;
        }

        public static class MemberListEntity implements Serializable{
            private Object creditScore;
            private Object birthday;
            private int sex;
            private Object phone;
            private Object lon;
            private Object score;
            private String headPic;
            private Object type;
            private Object nowCity;
            private Object shareCode;
            private String lvName;
            private String distance;
            private String level;
            private Object parentShareCode;
            private Object faceScoreStr;
            private int memberId;
            private String memberName;
            private Object lat;
            private Object faceScore;
            /**
             * id : 55
             * objName : 潇洒哥
             * updated : null
             * created : 2017-01-10T02:18:23
             * updator : null
             * objId : 64
             * labelName : 美剧爱好
             * type : 1
             * deleted : false
             * labelId : 6
             * creator : null
             */

            private List<MemberLabelListEntity> memberLabelList;

            public Object getCreditScore() {
                return creditScore;
            }

            public void setCreditScore(Object creditScore) {
                this.creditScore = creditScore;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getLon() {
                return lon;
            }

            public void setLon(Object lon) {
                this.lon = lon;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
                this.score = score;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getNowCity() {
                return nowCity;
            }

            public void setNowCity(Object nowCity) {
                this.nowCity = nowCity;
            }

            public Object getShareCode() {
                return shareCode;
            }

            public void setShareCode(Object shareCode) {
                this.shareCode = shareCode;
            }

            public String getLvName() {
                return lvName;
            }

            public void setLvName(String lvName) {
                this.lvName = lvName;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public Object getParentShareCode() {
                return parentShareCode;
            }

            public void setParentShareCode(Object parentShareCode) {
                this.parentShareCode = parentShareCode;
            }

            public Object getFaceScoreStr() {
                return faceScoreStr;
            }

            public void setFaceScoreStr(Object faceScoreStr) {
                this.faceScoreStr = faceScoreStr;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public Object getFaceScore() {
                return faceScore;
            }

            public void setFaceScore(Object faceScore) {
                this.faceScore = faceScore;
            }

            public List<MemberLabelListEntity> getMemberLabelList() {
                return memberLabelList;
            }

            public void setMemberLabelList(List<MemberLabelListEntity> memberLabelList) {
                this.memberLabelList = memberLabelList;
            }

            public static class MemberLabelListEntity implements Serializable{
                private int id;
                private String objName;
                private Object updated;
                private String created;
                private Object updator;
                private int objId;
                private String labelName;
                private int type;
                private boolean deleted;
                private int labelId;
                private Object creator;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getObjName() {
                    return objName;
                }

                public void setObjName(String objName) {
                    this.objName = objName;
                }

                public Object getUpdated() {
                    return updated;
                }

                public void setUpdated(Object updated) {
                    this.updated = updated;
                }

                public String getCreated() {
                    return created;
                }

                public void setCreated(String created) {
                    this.created = created;
                }

                public Object getUpdator() {
                    return updator;
                }

                public void setUpdator(Object updator) {
                    this.updator = updator;
                }

                public int getObjId() {
                    return objId;
                }

                public void setObjId(int objId) {
                    this.objId = objId;
                }

                public String getLabelName() {
                    return labelName;
                }

                public void setLabelName(String labelName) {
                    this.labelName = labelName;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public int getLabelId() {
                    return labelId;
                }

                public void setLabelId(int labelId) {
                    this.labelId = labelId;
                }

                public Object getCreator() {
                    return creator;
                }

                public void setCreator(Object creator) {
                    this.creator = creator;
                }
            }
        }

        public static class IndexAdvertListEntity implements Serializable{
            private String adHtml5;
            private int status;
            private String updator;
            private String descNote;
            private String type;
            private boolean deleted;
            private Object creator;
            private int id;
            private String created;
            private String updated;
            private String name;
            private int objId;
            private int orders;
            private String adPic;

            public String getAdHtml5() {
                return adHtml5;
            }

            public void setAdHtml5(String adHtml5) {
                this.adHtml5 = adHtml5;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUpdator() {
                return updator;
            }

            public void setUpdator(String updator) {
                this.updator = updator;
            }

            public String getDescNote() {
                return descNote;
            }

            public void setDescNote(String descNote) {
                this.descNote = descNote;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public Object getCreator() {
                return creator;
            }

            public void setCreator(Object creator) {
                this.creator = creator;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getUpdated() {
                return updated;
            }

            public void setUpdated(String updated) {
                this.updated = updated;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getObjId() {
                return objId;
            }

            public void setObjId(int objId) {
                this.objId = objId;
            }

            public int getOrders() {
                return orders;
            }

            public void setOrders(int orders) {
                this.orders = orders;
            }

            public String getAdPic() {
                return adPic;
            }

            public void setAdPic(String adPic) {
                this.adPic = adPic;
            }
        }
    }
}
