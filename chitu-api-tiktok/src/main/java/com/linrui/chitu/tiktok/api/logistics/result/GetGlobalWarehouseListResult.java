package com.linrui.chitu.tiktok.api.logistics.result;

import java.util.List;

/**
 * GlobalWarehouseListDTO
 *
 * @author nick.zw
 * @since 2024/10/31
 */
public class GetGlobalWarehouseListResult {

    private List<GlobalWarehouse> globalWarehouses;

    public List<GlobalWarehouse> getGlobalWarehouses() {
        return globalWarehouses;
    }

    public void setGlobalWarehouses(List<GlobalWarehouse> globalWarehouses) {
        this.globalWarehouses = globalWarehouses;
    }

    public static class GlobalWarehouse {

        private String id;

        private String name;

        private String ownership;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOwnership() {
            return ownership;
        }

        public void setOwnership(String ownership) {
            this.ownership = ownership;
        }
    }
}
