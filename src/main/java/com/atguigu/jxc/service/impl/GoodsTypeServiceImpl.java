package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.service.GoodsTypeService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Override
    public String loadGoodsType() {
        return getAllGoodsType(-1).toString();
    }

    @Override
    public void save(String goodsTypeName, Integer pId) {
        goodsTypeDao.save(goodsTypeName, pId);
    }

    @Override
    public void deleteById(Integer goodsTypeId) {
        goodsTypeDao.deleteById(goodsTypeId);
    }

    public JsonArray getAllGoodsType(Integer parentId) {
        JsonArray array = this.getGoodSTypeByParentId(parentId);
        for (int i = 0; i < array.size(); i++) {
            JsonObject obj = (JsonObject) array.get(i);
            if (!obj.get("state").getAsString().equals("open")) {
                obj.add("children", this.getAllGoodsType(obj.get("id").getAsInt()));
            }
        }
        return array;
    }


    public JsonArray getGoodSTypeByParentId(Integer parentId) {
        JsonArray array = new JsonArray();
        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByPID(parentId);
        for (GoodsType goodsType : goodsTypeList) {
            JsonObject obj = new JsonObject();
            obj.addProperty("id", goodsType.getGoodsTypeId());
            obj.addProperty("text", goodsType.getGoodsTypeName());
            if (goodsType.getGoodsTypeState() == 1) {
                obj.addProperty("state", "closed");
            } else {
                obj.addProperty("state", "open");
            }
            obj.addProperty("iconCls", "goods-type");
            JsonObject attributes = new JsonObject(); //扩展属性
            attributes.addProperty("state", goodsType.getGoodsTypeState());
            obj.add("attributes", attributes);
            array.add(obj);
        }
        return array;
    }
}
