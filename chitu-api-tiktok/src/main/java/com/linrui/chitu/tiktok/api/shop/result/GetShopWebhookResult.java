package com.linrui.chitu.tiktok.api.shop.result;

import java.util.List;

/**
 * GetShopWebhookResult
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class GetShopWebhookResult {

    private Integer total;

    private List<ShopWebhook> webhooks;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ShopWebhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<ShopWebhook> webhooks) {
        this.webhooks = webhooks;
    }

    public static class ShopWebhook {

        private String address;

        private String eventType;

        private String createTime;

        private Long updateTime;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
