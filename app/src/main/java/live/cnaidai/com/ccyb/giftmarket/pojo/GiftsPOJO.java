package live.cnaidai.com.ccyb.giftmarket.pojo;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.base.BasePOJO;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;

/**
 * Created by ChristLu on 16/11/26.
 */

public class GiftsPOJO extends BasePOJO {


    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        /**
         * indexPic : image/gift/gift20170110010036.jpg
         * commissionFee : null
         * descNote : 中秋超值豪礼
         * giftName : 中秋豪礼
         * giftId : 18
         * orderTime : null
         * orderId : null
         * hasUse : null
         * totalPrice : 100
         */

        private List<HomeDataPOJO.DataEntity.IndexGiftListEntity> ticketList;
        /**
         * indexPic : image/gift/gift20170720162936.jpg
         * commissionFee : null
         * descNote :   xx
         * giftName : 套餐
         * giftId : 38
         * orderTime : null
         * orderId : null
         * hasUse : null
         * totalPrice : 10
         */

        private List<BarListEntity> barList;

        public List<HomeDataPOJO.DataEntity.IndexGiftListEntity> getTicketList() {
            return ticketList;
        }

        public void setTicketList(List<HomeDataPOJO.DataEntity.IndexGiftListEntity> ticketList) {
            this.ticketList = ticketList;
        }

        public List<BarListEntity> getBarList() {
            return barList;
        }

        public void setBarList(List<BarListEntity> barList) {
            this.barList = barList;
        }


        public static class BarListEntity implements Serializable{
            private String indexPic;
            private Object commissionFee;
            private String descNote;
            private String giftName;
            private int giftId;
            private Object orderTime;
            private Object orderId;
            private Object hasUse;
            private int totalPrice;

            public String getIndexPic() {
                return indexPic;
            }

            public void setIndexPic(String indexPic) {
                this.indexPic = indexPic;
            }

            public Object getCommissionFee() {
                return commissionFee;
            }

            public void setCommissionFee(Object commissionFee) {
                this.commissionFee = commissionFee;
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

            public Object getOrderTime() {
                return orderTime;
            }

            public void setOrderTime(Object orderTime) {
                this.orderTime = orderTime;
            }

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public Object getHasUse() {
                return hasUse;
            }

            public void setHasUse(Object hasUse) {
                this.hasUse = hasUse;
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
