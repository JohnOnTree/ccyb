package live.cnaidai.com.ccyb.home.pojo;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * Created by ChristLu on 16/11/26.
 */

public class HomeDataPOJO extends BasePOJO {

    /**
     * message : 首页加载成功
     * data : {"indexAdvertList":[{"adHtml5":"html5/advert/advert_detail_4.html","status":2,"updator":"admin","descNote":"反恐24小时反恐24小时反恐24小时反恐24小时1111","type":"2","deleted":false,"creator":"admin","id":4,"created":"2016-11-18T14:11:25","updated":"2016-11-18T15:46:21","name":"反恐24小时11","objId":32,"orders":5,"adPic":"image/advert/advert20161118143038.jpg"}],"indexGiftList":[{"indexPic":"image/gift/item1.png","descNote":"专享情侣套餐","giftName":"专享情侣套餐","giftId":1,"orderId":null,"totalPrice":20},{"indexPic":"image/gift/item2.png","descNote":"单品1","giftName":"单品1","giftId":2,"orderId":null,"totalPrice":30},{"indexPic":"image/gift/item3.png","descNote":"单品2","giftName":"单品2","giftId":3,"orderId":null,"totalPrice":40}]}
     */

    private DataEntity data;


    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable {
        /**
         * adHtml5 : html5/advert/advert_detail_4.html
         * status : 2
         * updator : admin
         * descNote : 反恐24小时反恐24小时反恐24小时反恐24小时1111
         * type : 2
         * deleted : false
         * creator : admin
         * id : 4
         * created : 2016-11-18T14:11:25
         * updated : 2016-11-18T15:46:21
         * name : 反恐24小时11
         * objId : 32
         * orders : 5
         * adPic : image/advert/advert20161118143038.jpg
         */

        private List<IndexAdvertListEntity> indexAdvertList;
        /**
         * indexPic : image/gift/item1.png
         * descNote : 专享情侣套餐
         * giftName : 专享情侣套餐
         * giftId : 1
         * orderId : null
         * totalPrice : 20
         */

        private List<IndexGiftListEntity> indexGiftList;

        public List<IndexAdvertListEntity> getIndexAdvertList() {
            return indexAdvertList;
        }

        public void setIndexAdvertList(List<IndexAdvertListEntity> indexAdvertList) {
            this.indexAdvertList = indexAdvertList;
        }

        public List<IndexGiftListEntity> getIndexGiftList() {
            return indexGiftList;
        }

        public void setIndexGiftList(List<IndexGiftListEntity> indexGiftList) {
            this.indexGiftList = indexGiftList;
        }

        public static class IndexAdvertListEntity implements Serializable {
            private String adHtml5;
            private int status;
            private String updator;
            private String descNote;
            private String type;
            private boolean deleted;
            private String creator;
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

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
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

        public static class IndexGiftListEntity implements Serializable {
            private String indexPic;
            private String descNote;
            private String giftName;
            private int giftId;
            private Object orderId;
            private int totalPrice;

            public String getIndexPic() {
                return indexPic;
            }

            public void setIndexPic(String indexPic) {
                this.indexPic = indexPic;
            }

            public String getDescNote() {
                return descNote;
            }

            public void setDescNote(String descNote) {
                this.descNote = descNote;
            }

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public int getGiftId() {
                return giftId;
            }

            public void setGiftId(int giftId) {
                this.giftId = giftId;
            }

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public int getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(int totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
