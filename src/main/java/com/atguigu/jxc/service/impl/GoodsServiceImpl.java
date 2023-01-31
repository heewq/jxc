package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SaleListGoodsService saleListGoodsService;
    @Autowired
    private CustomerReturnListGoodsService customerReturnListGoodsService;

    @Override
    public Map<String, Object> stockSearch(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        page = page == 0 ? 1 : page;
        Integer offset = (page - 1) * rows;
        List<Goods> goods = goodsDao.listInventory(offset, rows, codeOrName, goodsTypeId);

        for (Goods good : goods) {
            // 销售总量等于销售单据的销售数据减去退货单据的退货数据
            good.setSaleTotal(saleListGoodsService.getSaleTotalByGoodsId(good.getGoodsId())
                    - customerReturnListGoodsService.getCustomerReturnTotalByGoodsId(good.getGoodsId()));

        }

        Map<String, Object> map = new HashMap<>();
        map.put("rows", goods);
        map.put("total", goodsDao.getGoodsInventoryCount(codeOrName, goodsTypeId));

        return map;
    }

    @Override
    public Map<String, Object> listGoods(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        return this.stockSearch(page, rows, goodsName, goodsTypeId);
//        page = page == 0 ? 1 : page;
//        Integer offset = (page - 1) * rows;
//        List<Goods> goodsList = goodsDao.listGoods(offset, rows, goodsName, goodsTypeId);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("rows", goodsList);
//
//        map.put("total", goodsDao.getTotalGoods(goodsName, goodsTypeId));
//        return map;
    }

    @Override
    public void insert(Goods goods) {
        // first purchasing
        //set lastPurchasingPrice = goods.purchasingPrice
        goods.setLastPurchasingPrice(goods.getPurchasingPrice());
        goods.setInventoryQuantity(0);
        goods.setState(0);
        goodsDao.insert(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public String getCode() {
        String code = goodsDao.getMaxCode();
        int intCode = Integer.parseInt(code) + 1;
        StringBuilder builder = new StringBuilder(Integer.toString(intCode));
        for (int index = 0; index < 4 - Integer.toString(intCode).length(); index++) {
            builder.insert(0, 0);
        }
        return new String(builder);
    }

    @Override
    public ServiceVO delete(Integer goodsId) {
        Goods goods = goodsDao.getById(goodsId);
        if (goods.getState() == 1) {
            return new ServiceVO<>(ErrorCode.STORED_ERROR_CODE, ErrorCode.STORED_ERROR_MESS);
        } else if (goods.getState() == 2) {
            return new ServiceVO<>(ErrorCode.HAS_FORM_ERROR_CODE, ErrorCode.HAS_FORM_ERROR_MESS);
        } else {
            goodsDao.deleteById(goodsId);
            return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        }
    }

    @Override
    public Map<String, Object> listNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        page = page == 0 ? 1 : page;
        Integer offset = (page - 1) * rows;
        List<Goods> noInventoryQuantityGoods = goodsDao.listNoInventoryQuantity(offset, rows, nameOrCode);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", noInventoryQuantityGoods);
        map.put("total", goodsDao.totalNoInventoryQuantity(nameOrCode));
        return map;
    }

    @Override
    public Map<String, Object> listHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        Integer offset = (page - 1 == -1 ? 0 : page - 1) * rows;
        List<Goods> hasInventoryQuantityGoods = goodsDao.listHasInventoryQuantity(offset, rows, nameOrCode);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", hasInventoryQuantityGoods);
        map.put("total", goodsDao.totalHasInventoryQuantity(nameOrCode));
        return map;
    }

    @Override
    public void updateById(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        goodsDao.updateById(goodsId, inventoryQuantity, purchasingPrice);
    }

    @Override
    public Map<String, Object> listAlarm() {
        List<Goods> alarmGoods = goodsDao.listAlarm();
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", alarmGoods);
        return map;
    }

    @Override
    public void updateInventoryQuantityById(Integer goodsId, Integer quantity) {
        goodsDao.updateInventoryQuantityById(goodsId, quantity);
    }

    @Override
    public ServiceVO deleteStock(Integer goodsId) {
        Goods goods = goodsDao.getById(goodsId);
        if (goods.getState() == 2) {
            return new ServiceVO<>(ErrorCode.HAS_FORM_ERROR_CODE, ErrorCode.HAS_FORM_ERROR_MESS);
        } else {
            this.updateInventoryQuantityById(goodsId, goods.getInventoryQuantity() * -1);
            return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        }
    }

    @Override
    public Goods getById(Integer goodsId) {
        return goodsDao.getById(goodsId);
    }
}
