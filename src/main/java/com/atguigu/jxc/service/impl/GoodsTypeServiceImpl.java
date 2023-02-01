package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.domain.GoodsTypeVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Override
    public String loadGoodsType() {
        List<GoodsType> goodsTypes = goodsTypeDao.getAllGoodsType();
        List<GoodsTypeVo> rootGoodsTypeVos = findRootType(goodsTypes);
        List<GoodsTypeVo> goodsTypeVos = rootGoodsTypeVos.stream()
                .peek(goodsTypeVo -> goodsTypeVo.setChildren(findChildTypes(goodsTypeVo, goodsTypes))
                ).collect(Collectors.toList());

        return JSON.toJSONString(goodsTypeVos);
    }

    private List<GoodsTypeVo> findChildTypes(GoodsTypeVo goodsTypeVo, List<GoodsType> goodsTypes) {
        return goodsTypes.stream()
                .filter(goodsType -> goodsType.getPId().equals(goodsTypeVo.getId()))
                .map(goodsType -> {
                    GoodsTypeVo childGoodsTypeVo = new GoodsTypeVo();
                    childGoodsTypeVo.setId(goodsType.getGoodsTypeId());
                    childGoodsTypeVo.setText(goodsType.getGoodsTypeName());
                    childGoodsTypeVo.setState(goodsType.getGoodsTypeState() == 1 ? "closed" : "open");
                    childGoodsTypeVo.setIconCls("goods-type");
                    HashMap<String, Integer> attrMap = new HashMap<>();
                    attrMap.put("state", goodsType.getGoodsTypeState());
                    childGoodsTypeVo.setAttributes(attrMap);
                    if (!childGoodsTypeVo.getState().equals("open")) {
                        childGoodsTypeVo.setChildren(findChildTypes(childGoodsTypeVo, goodsTypes));
                    }
                    return childGoodsTypeVo;
                }).collect(Collectors.toList());
    }

    private List<GoodsTypeVo> findRootType(List<GoodsType> goodsTypes) {
        return goodsTypes.stream()
                .filter(goodsType -> goodsType.getPId() == -1)
                .map(goodsType -> {
                    GoodsTypeVo goodsTypeVo = new GoodsTypeVo();
                    goodsTypeVo.setId(goodsType.getGoodsTypeId());
                    goodsTypeVo.setText(goodsType.getGoodsTypeName());
                    goodsTypeVo.setState(goodsType.getGoodsTypeState() == 1 ? "closed" : "open");
                    goodsTypeVo.setIconCls("goods-type");
                    HashMap<String, Integer> attrMap = new HashMap<>();
                    attrMap.put("state", goodsType.getGoodsTypeState());
                    goodsTypeVo.setAttributes(attrMap);
                    return goodsTypeVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save(String goodsTypeName, Integer pId) {
        goodsTypeDao.save(goodsTypeName, pId, 0);
        GoodsType goodsType = new GoodsType();
        goodsType.setGoodsTypeId(pId);
        goodsType.setGoodsTypeState(1);
        goodsTypeDao.update(goodsType);
    }

    @Override
    public ServiceVO deleteById(Integer goodsTypeId) {
        GoodsType goodsType = goodsTypeDao.getById(goodsTypeId);
        GoodsType parentGoodsType = goodsTypeDao.getById(goodsType.getPId());
        Integer row = goodsTypeDao.deleteById(goodsTypeId);
        if (row != 0) {
            List<GoodsType> goodsTypes = goodsTypeDao.getAllGoodsTypeByPID(goodsType.getPId());
            if (goodsTypes.isEmpty()) {
                parentGoodsType.setGoodsTypeState(0);
                goodsTypeDao.update(parentGoodsType);
            }
            return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        }
        throw new IllegalArgumentException();
    }

//    public JsonArray getAllGoodsType(Integer parentId) {
//        JsonArray array = this.getGoodSTypeByParentId(parentId);
//        for (int i = 0; i < array.size(); i++) {
//            JsonObject obj = (JsonObject) array.get(i);
//            if (!obj.get("state").getAsString().equals("open")) {
//                obj.add("children", this.getAllGoodsType(obj.get("id").getAsInt()));
//            }
//        }
//        return array;
//    }

//    public JsonArray getGoodSTypeByParentId(Integer parentId) {
//        JsonArray array = new JsonArray();
//        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByPID(parentId);
//        for (GoodsType goodsType : goodsTypeList) {
//            JsonObject obj = new JsonObject();
//            obj.addProperty("id", goodsType.getGoodsTypeId());
//            obj.addProperty("text", goodsType.getGoodsTypeName());
//            if (goodsType.getGoodsTypeState() == 1) {
//                obj.addProperty("state", "closed");
//            } else {
//                obj.addProperty("state", "open");
//            }
//            obj.addProperty("iconCls", "goods-type");
//            JsonObject attributes = new JsonObject(); //扩展属性
//            attributes.addProperty("state", goodsType.getGoodsTypeState());
//            obj.add("attributes", attributes);
//            array.add(obj);
//        }
//        return array;
//    }
}
