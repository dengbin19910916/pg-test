package io.xxx.demo.pgtest.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.xxx.demo.pgtest.data.OrderItemMapper;
import io.xxx.demo.pgtest.data.TradeOrderMapper;
import io.xxx.demo.pgtest.entity.OrderItem;
import io.xxx.demo.pgtest.entity.TradeOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MockService {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private final TradeOrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public MockService(TradeOrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Transactional
    public void saveOrder() {
        TradeOrder order = new TradeOrder();
        order.setId(IdWorker.getId());
        String orderChannel = channels[random.nextInt(channels.length)];
        order.setChannel(orderChannel);
        order.setOutId(IdWorker.getIdStr());

        Shop shop = shopMap.get(orderChannel);
        order.setShopCode(shop.getCode());
        order.setShopName(shop.getName());

        Buyer buyer = buyers.get(random.nextInt(buyers.size()));
        order.setBuyerName(buyer.getName());
        order.setReceiverName(buyer.getName());
        order.setReceiverMobile(buyer.getMobile());
        order.setBuyerMemo(memos[random.nextInt(memos.length)]);

        String province = provinceNames[random.nextInt(provinceNames.length)];
        String city = cityNames[random.nextInt(cityNames.length)];
        String county = countyNames[random.nextInt(countyNames.length)];
        order.setReceiverCountryCode("中国");
        order.setReceiverProvinceCode(province);
        order.setReceiverCityCode(city);
        order.setReceiverAreaCode(county);
        order.setReceiverAddress(province + city + county);
        order.setReceiverZip("000000");
        order.setStatus(orderStatus[random.nextInt(orderStatus.length)]);
//        LocalDateTime createdTime = getCreatedTime();
        LocalDateTime createdTime = LocalDateTime.now();
        order.setCreatedTime(createdTime);
        order.setUpdatedTime(createdTime);

        boolean includesGift = false;
        int itemQuantity = itemQuantities[random.nextInt(itemQuantities.length)];
        for (int i = 1; i <= itemQuantity; i++) {
            OrderItem item = new OrderItem();
            item.setId(IdWorker.getId());
            item.setOrderId(order.getId());
            item.setOrderChannel(orderChannel);
            item.setSerial(i);
            Product product = products.get(random.nextInt(products.size()));
            item.setSkuId(product.getGoodsNo());
            item.setName(product.getName());
            int quantity = random.nextInt(11);
            item.setQuantity(quantity);
            item.setTagPrice(product.getTagPrice());
            item.setPurchasePrice(product.getTagPrice().multiply(new BigDecimal(quantity)));
            boolean given = givens[random.nextInt(givens.length)];
            includesGift = includesGift | given;
            item.setGiven(given);
            item.setOrderCreatedTime(order.getCreatedTime());
            item.setOrderStatus(order.getStatus());
            item.setCreatedTime(order.getCreatedTime());
            item.setUpdatedTime(order.getCreatedTime());
            orderItemMapper.insert(item);
        }

        order.setIncludesGift(includesGift);
        orderMapper.insert(order);
    }

    private String[] orderStatus = new String[]{
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
            "2", "2", "2", "2", "2", "2", "2", "2", "2", "2",
            "2", "2", "2", "2", "2", "2", "2", "2", "2", "2",
            "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
            "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
            "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
            "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
    };

    private int[] itemQuantities = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            2, 2, 2, 3, 3,
            4, 5, 6,
    };

    private LocalDateTime getCreatedTime() {
        int month = months[random.nextInt(months.length)];
        return LocalDateTime.of(2019,
                month,
                random.nextInt(1, month == 2 ? 29
                        : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                        ? 32 : 31),
                random.nextInt(24),
                random.nextInt(60),
                random.nextInt(60));
    }

    private int[] months = new int[]{
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
            10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
            9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
            8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
            7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };

    private boolean[] givens = new boolean[]{
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false,
            true,
    };

    private String[] memos = new String[]{
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null,
            "我要赠品1", "我要赠品2", "我要赠品3",
            "帮我发顺丰", "帮我发邮政", "帮我发圆通",
            "开发票",
            "改一下收货地址：湖南省慈利县", "改地址：广东省深圳市", "改下尺寸：大码的"
    };

    private String[] channels = new String[]{
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "1", "1", "1", "1", "1",
            "2", "2", "2", "2", "2",
            "3", "3", "3",
            "4"
    };

    private Map<String, Shop> shopMap = new HashMap<String, Shop>() {{
        put("1", new Shop("TM", "天猫店旗舰店"));
        put("2", new Shop("JD", "京东自营店"));
        put("3", new Shop("VIP", "唯品会官方店"));
        put("4", new Shop("GW", "自营官网"));
    }};

    @Data
    @AllArgsConstructor
    private static class Shop {
        private String code;
        private String name;
    }

    @Data
    private static class Buyer {
        private String id;
        private String name;
        private String mobile;
        private String brandId;
        private int level;
        private String levelName;
    }

    @Data
    private static class Product {
        private String name;
        private BigDecimal tagPrice;
        private String productNo;
        private String productCode;
        private String goodsNo;
        private String goodsCode;
        private String barcode;
    }

    private List<Buyer> buyers = new ArrayList<Buyer>() {{
        for (int i = 1; i <= 500; i++) {
            Buyer buyer = new Buyer();
            buyer.setId(String.format("M00000%03d", i));
            buyer.setName(firstNames[random.nextInt(firstNames.length)] + lastNames[random.nextInt(lastNames.length)]);
            buyer.setBrandId("pureh2b");
            buyer.setMobile("139" + random.nextInt(10_000_000, 999_999_999));
            add(buyer);
        }
    }};

    private List<Product> products = new ArrayList<Product>() {{
        for (int i = 1; i <= 12; i++) {
            Product product = new Product();
            product.setName("商品" + i);
            product.setTagPrice(new BigDecimal(random.nextInt(10, 200)));
            product.setBarcode(String.format("69%07d", i));
            product.setProductNo(String.format("J%05d", i / 4 + 1));
            product.setProductCode(String.format("J%05d", i / 4 + 1));
            product.setGoodsNo(String.format("J%05d", i % 4));
            product.setGoodsCode(String.format("J%05d", i % 4));
            add(product);
        }
    }};

    private static String[] firstNames = new String[]{
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王",
            "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨",
            "朱", "秦", "尤", "许", "何", "吕", "施", "张",
            "孔", "曹", "严", "华", "金", "魏", "陶", "姜",
            "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
            "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方",
            "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐",
            "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤"};

    private static String[] lastNames = new String[]{
            "彦龙", "浩鹏", "天一", "铁刚", "君昊", "国艳", "恩德", "文雅",
            "文轩", "文博", "文璇", "文萱", "文渲", "美红", "雨洁", "诗蕊",
            "泊萱", "可昕", "章洪", "亚萍", "智博", "子宸", "鸿娜", "玉锁",
            "宏娜", "金煜", "艾玲", "绿峰", "子昊", "慧", "娜", "建中",
            "亚蒙", "亚梦", "中山", "汉煜", "越泽", "维哲", "逸", "乾",
            "赟", "淩", "祺", "昊", "卫", "昱烔", "鸿崧", "玮",
            "凌咺", "雨桐", "霖", "沁鈺", "啸林", "易", "晨", "梣",
            "麟", "煜", "酉晨", "子辰", "子晨", "亦晨", "弈晨", "胤帖",
            "缁坐", "法民", "法敏", "川", "国尧", "咏颐", "惠山", "思宇",
            "小宝", "恒世", "毛儿", "世恒", "毛小", "嘉浩", "恒", "恒",
            "岳宁", "恒龙", "恒贵", "贵恒", "洋", "佳霖", "佳欣", "杰",
            "曜岩", "臻", "文恒", "雨函", "昕", "星泽", "星睿", "恒文",
            "奕", "玉", "晨轩", "文", "文婍", "艾静", "昂然", "晨晨",
            "佳晨", "小龙", "定轩", "姗", "浩宇", "昕阳", "昊宇", "舒飞",
            "亚骏", "泊显", "漠煜", "辉", "凌薇", "佳雪", "景浩", "昱霖",
            "昱娴", "金禹", "明泽", "泽晓", "锦泽", "梦洁", "倩雪", "鸿毅",
            "雨泽", "钰熙", "吉洋", "晨欣", "雨涵", "晨濡", "茉", "佳阳",
            "婉儿", "菀儿", "宛儿", "诗琪", "瑾萱", "硕", "家熠", "颢",
            "嘉城", "优漩", "奕漩", "懿轩", "清明", "忆彤", "奕澄", "乐姗",
            "优璇", "昀蹊", "征洋", "扬", "春霞", "承樨", "承叡", "承檄",
            "博宇", "欣霖", "耀犇", "霆轩", "廷轩", "启睿", "梓睿", "承菥",
            "承淅", "易珂", "晨曦", "军", "俊之", "梓阳", "帅", "恒起",
            "承浠", "昊轩", "承烯", "俊博", "俊涵", "苛嘉", "苛佳", "豪轩",
            "佳龙", "嘉龙", "羽晗", "梓杰", "梓辰", "俊进", "逸辰", "昊然",
            "佳馨", "佳茜", "佳倩", "龙"
    };

    private String[] provinceNames = new String[]{
            "北京市", "天津市", "上海市", "重庆市", "河北省",
            "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省",
            "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "海南省",
            "四川省", "贵州省", "云南省", "陕西省", "甘肃省",
            "青海省", "台湾省", "内蒙古自治区", "广西壮族自治区", "西藏自治区"
    };

    private String[] cityNames = new String[]{
            "郑州市", "洛阳市", "焦作市", "商丘市", "信阳市", "周口市", "鹤壁市", "安阳市", "濮阳市", "驻马店市",
            "南阳市", "开封市", "漯河市", "许昌市", "新乡市", "济源市", "灵宝市", "偃师市", "邓州市", "登封市",
            "新郑市", "禹州市", "巩义市", "永城市", "长葛市", "义马市", "林州市", "项城市", "汝州市", "荥阳市",
            "平顶山市", "卫辉市", "辉县市", "舞钢市", "新密市", "孟州市", "沁阳市", "郏县", "三门峡市", "武汉市",
            "荆门市", "咸宁市", "襄阳市", "荆州市", "黄石市", "宜昌市", "随州市", "鄂州市", "孝感市", "黄冈市",
            "十堰市", "枣阳市", "老河口市", "恩施市", "仙桃市", "天门市", "钟祥市", "潜江市", "麻城市", "洪湖市",
            "汉川市", "赤壁市", "松滋市", "丹江口市", "武穴市", "广水市", "石首市大冶市", "枝江市", "应城市", "宜城市",
            "当阳市", "安陆市", "宜都市", "利川市", "长沙市", "郴州市", "益阳市", "娄底市", "株洲市", "衡阳市",
            "湘潭市", "岳阳市", "常德市", "邵阳市", "永州市", "张家界市", "怀化市", "浏阳市", "醴陵市", "湘乡市",
            "耒阳市", "沅江市", "涟源市", "常宁市", "吉首市", "津市市", "冷水江市", "临湘市", "汨罗市", "武冈市",
            "韶山市", "湘西州"
    };

    private String[] countyNames = new String[]{
            "玄武区", "秦淮区", "鼓楼区", "浦口区", "雨花台区",
            "六合区", "高淳县", "白下区", "建邺区", "下关区",
            "栖霞区", "江宁区", "溧水县", "连云区", "海州区",
            "东海县", "灌南县", "新浦区", "赣榆县", "灌云县",
            "宿城区", "沭阳县", "泗洪县", "宿豫区", "泗阳县",
            "迎江区", "郊区", "枞阳县", "太湖县", "望江县",
            "桐城市", "大观区", "怀宁县", "潜山县", "宿松县",
            "岳西县", "屯溪区", "徽州区", "休宁县", "祁门县",
            "黄山区", "歙县", "黟县", "琅琊区", "来安县",
            "定远县", "天长市", "明光市", "南谯区", "全椒县",
            "凤阳县", "墉桥区", "萧县", "泗县", "砀山县",
            "灵璧县", "金安区", "寿县", "舒城县", "裕安区",
            "霍邱县", "金寨县", "颍州区", "颍泉区", "太和县",
            "颍上县", "界首市", "颍东区", "临泉县", "阜南县",
    };
}
